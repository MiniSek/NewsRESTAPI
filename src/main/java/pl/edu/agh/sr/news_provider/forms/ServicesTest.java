package pl.edu.agh.sr.news_provider.forms;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.NewYorkTimesArticleServlet;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.models.NewYorkTimesArticleResult;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.NewYorkTimesSectionsServlet;
import pl.edu.agh.sr.news_provider.theguardian.articles.GuardianArticlesServlet;
import pl.edu.agh.sr.news_provider.theguardian.articles.models.GuardianArticleResult;
import pl.edu.agh.sr.news_provider.theguardian.sections.GuardianSectionsServlet;

import java.util.List;
import java.util.Map;

@RestController
public class ServicesTest {
    @GetMapping("/guardian/sections")
    public Map<String, String> getSectionsGuardian() {
        return new GuardianSectionsServlet(new RestTemplateBuilder()).getSections();
    }

    @GetMapping("/guardian/articles")
    public List<GuardianArticleResult> getArticlesGuardian() {
        GuardianArticlesServlet guardianArticlesServlet =
                new GuardianArticlesServlet(new RestTemplateBuilder());
        guardianArticlesServlet.addSection("games");
        return guardianArticlesServlet.getArticles();
    }

    @GetMapping("/newyorktimes/sections")
    public Map<String, String> getSectionsNewYorkTimes() {
        return new NewYorkTimesSectionsServlet(new RestTemplateBuilder()).getSections();
    }

    @GetMapping("/newyorktimes/articles")
    public List<NewYorkTimesArticleResult> getArticlesNewYorkTimes() {
        NewYorkTimesArticleServlet servlet = new NewYorkTimesArticleServlet(new RestTemplateBuilder());
        servlet.addQuery("war");
        return servlet.getArticles();
    }
}
