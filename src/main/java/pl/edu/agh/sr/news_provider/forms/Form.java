package pl.edu.agh.sr.news_provider.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import pl.edu.agh.sr.news_provider.forms.models.Article;
import pl.edu.agh.sr.news_provider.forms.models.FormRequest;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.NewYorkTimesArticleServlet;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.models.NewYorkTimesArticleResult;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.NewYorkTimesSectionsServlet;
import pl.edu.agh.sr.news_provider.theguardian.articles.GuardianArticlesServlet;
import pl.edu.agh.sr.news_provider.theguardian.articles.models.GuardianArticleResult;
import pl.edu.agh.sr.news_provider.theguardian.sections.GuardianSectionsServlet;

import javax.servlet.annotation.WebServlet;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@WebServlet("/")
public class Form {
    @Autowired
    private GuardianSectionsServlet guardianSectionsServlet;

    @Autowired
    private NewYorkTimesSectionsServlet newYorkTimesSectionsServlet;

    @Autowired
    private GuardianArticlesServlet guardianArticlesServlet;

    @Autowired
    private NewYorkTimesArticleServlet newYorkTimesArticleServlet;

    @RequestMapping("/form")
    public String form(Model model) {
        Map<String, String> guardianSections = new HashMap<>();;
        Map<String, String> newYorkTimesSections = new HashMap<>();;
        int er = 0;
        String er1 = "", er2 = "";

        try {
            guardianSections = guardianSectionsServlet.getSections();
        } catch(RestClientException e) {
            er++;
            er1 = e.getMessage();
        }

        try {
            newYorkTimesSections = newYorkTimesSectionsServlet.getSections();
        } catch(RestClientException e) {
            er++;
            er2 = e.getMessage();
        }

        if(er == 2) {
            model.addAttribute("error1", er1);
            model.addAttribute("error2", er2);
            return "error";
        }

        List<String> sections = new ArrayList<>();
        sections.addAll(guardianSections.keySet());
        for(String section : newYorkTimesSections.keySet())
            if(!sections.contains(section))
                sections.add(section);

        java.util.Collections.sort(sections);
        sections.add(0, "All");

        List<String> sortBy = new ArrayList<>(Arrays.asList("Title asc", "Title desc", "Date asc", "Date desc"));

        model.addAttribute("sections", sections);
        model.addAttribute("sortByList", sortBy);
        model.addAttribute("formRequest", new FormRequest());
        return "form";
    }

    @RequestMapping("/save_form")
    public String saveForm(Model model, FormRequest formRequest) {
        Map<String, String> guardianSections = new HashMap<>();;
        Map<String, String> newYorkTimesSections = new HashMap<>();;
        int er = 0;
        String er1 = "", er2 = "";

        try {
            guardianSections = guardianSectionsServlet.getSections();
        } catch(RestClientException e) {
            er++;
            er1 = e.getMessage();
        }

        try {
            newYorkTimesSections = newYorkTimesSectionsServlet.getSections();
        } catch(RestClientException e) {
            er++;
            er2 = e.getMessage();
        }

        if(er == 2) {
            model.addAttribute("error1", er1);
            model.addAttribute("error2", er2);
            return "error";
        }

        if(formRequest.getPageSize() == null)
            formRequest.setPageSize(10);

        List<Article> allArticles = new ArrayList<>();
        if(formRequest.getSection().equals("All") || newYorkTimesSections.containsKey(formRequest.getSection())) {
            List<Article> newYorkTimesArticles = getNewYorkTimesArticles(formRequest, newYorkTimesArticleServlet, newYorkTimesSections);
            if(newYorkTimesArticles != null)
                allArticles.addAll(newYorkTimesArticles);
        }

        int pageSize = Math.min(Math.max(formRequest.getPageSize(), 0), 500);
        int page = 1;
        if(formRequest.getSection().equals("All") || guardianSections.containsKey(formRequest.getSection())) {
            List<Article> guardianArticles;
            while (pageSize > 0) {
                guardianArticles = getGuardianArticles(formRequest, guardianArticlesServlet, guardianSections,
                        page, Math.min(pageSize, 50));
                if(guardianArticles != null)
                    allArticles.addAll(guardianArticles);
                pageSize -= Math.min(pageSize, 50);
                page++;
            }
        }

        String title;
        for(int i=allArticles.size()-1; i>=0; i--) {
            title = allArticles.get(i).getTitle();
            if(title.replace(" ", "").replace("\t", "").
                    replace("\n", "").replace(".", "").equals(""))
                allArticles.remove(i);
        }

        allArticles.sort(Comparator.comparing(o -> LocalDateTime.parse(o.getPublicationDate())));
        Collections.reverse(allArticles);
        if (allArticles.size() > formRequest.getPageSize())
            allArticles.subList(formRequest.getPageSize(), allArticles.size()).clear();

        switch (formRequest.getSortBy()) {
            case "Title asc":
                allArticles.sort(Comparator.comparing(Article::getTitle));
                break;
            case "Title desc":
                allArticles.sort(Comparator.comparing(Article::getTitle));
                Collections.reverse(allArticles);
                break;
            case "Date asc":
                allArticles.sort(Comparator.comparing(o -> LocalDateTime.parse(o.getPublicationDate())));
                break;
            case "Date desc":
                allArticles.sort(Comparator.comparing(o -> LocalDateTime.parse(o.getPublicationDate())));
                Collections.reverse(allArticles);
                break;
        }

        allArticles.add(0, new Article("Source", "Title", "Section", "URL",
                "Publication date"));
        model.addAttribute("articleList", allArticles);

        return "articles";
    }

    private List<Article> getGuardianArticles(FormRequest formRequest,
                                              GuardianArticlesServlet guardianArticlesServlet,
                                              Map<String, String> guardianSections, Integer page, Integer pageSize) {

        List<GuardianArticleResult> guardianArticles = null;

        if(pageSize>10)
            guardianArticlesServlet.addPageSize(String.valueOf(pageSize));
        if(page>1)
            guardianArticlesServlet.addPage(String.valueOf(page));

        if(!formRequest.getSection().equals("All"))
            if (guardianSections.containsKey(formRequest.getSection()))
                guardianArticlesServlet.addSection(guardianSections.get(formRequest.getSection()));

        if (!formRequest.getQuery().replace(" ", "").equals(""))
            guardianArticlesServlet.addQuery(formRequest.getQuery());
        guardianArticles = guardianArticlesServlet.getArticles();
        if(guardianArticles == null)
            return null;

        List<Article> allArticles = new ArrayList<>();
        for(GuardianArticleResult guardianArticle : guardianArticles)
            allArticles.add(new Article("The Guardian", guardianArticle.getWebTitle(),
                    guardianArticle.getSectionName(), guardianArticle.getWebUrl(),
                    guardianArticle.getWebPublicationDate().replace("Z", "")));

        return allArticles;
    }


    private List<Article> getNewYorkTimesArticles(FormRequest formRequest,
                                                  NewYorkTimesArticleServlet newYorkTimesArticleServlet,
                                                  Map<String, String> newYorkTimesSections) {
        List<NewYorkTimesArticleResult> newYorkTimesArticles = null;

        int pageSize = Math.min(Math.max(formRequest.getPageSize(), 0), 500);
        int nytPageSize = (pageSize-1)/20 + 1;
        if(pageSize > 20)
            newYorkTimesArticleServlet.addPageSize(String.valueOf(20*nytPageSize));

        if(!formRequest.getSection().equals("All"))
            if (newYorkTimesSections.containsKey(formRequest.getSection()))
                newYorkTimesArticleServlet.addSection(newYorkTimesSections.get(formRequest.getSection()));

        if (!formRequest.getQuery().replace(" ", "").equals(""))
            newYorkTimesArticleServlet.addQuery(formRequest.getQuery());
        newYorkTimesArticles = newYorkTimesArticleServlet.getArticles();
        if(newYorkTimesArticles == null)
            return null;

        List<Article> allArticles = new ArrayList<>();
        for(NewYorkTimesArticleResult newYorkTimesArticle : newYorkTimesArticles) {
            String date = "";
            String [] unparsed = newYorkTimesArticle.getPublished_date().split("-");
            for(int i=0; i<unparsed.length-1; i++) {
                date = date + unparsed[i];
                if(i != unparsed.length-2)
                    date = date + "-";
            }

            date = LocalDateTime.parse(date).plusHours(
                    Integer.parseInt(unparsed[unparsed.length-1].split(":")[0])).toString();
            date = LocalDateTime.parse(date).plusMinutes(
                    Integer.parseInt(unparsed[unparsed.length-1].split(":")[1])).toString();

            allArticles.add(new Article("The New York Times", newYorkTimesArticle.getTitle(),
                    newYorkTimesArticle.getSection(), newYorkTimesArticle.getUrl(), date));
        }

        return allArticles;
    }
}
