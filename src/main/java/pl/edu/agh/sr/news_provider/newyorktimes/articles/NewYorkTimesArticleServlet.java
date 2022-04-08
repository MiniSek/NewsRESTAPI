package pl.edu.agh.sr.news_provider.newyorktimes.articles;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.models.NewYorkTimesArticleResponse;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.models.NewYorkTimesArticleResult;
import pl.edu.agh.sr.news_provider.theguardian.articles.models.GuardianArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class NewYorkTimesArticleServlet {
    private final RestTemplate restTemplate;
    private UriComponentsBuilder url;
    private String query = null;

    public NewYorkTimesArticleServlet(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        buildUrl();
    }

    private void buildUrl() {
        String baseUrl = "https://api.nytimes.com/svc/news/v3/content/all/all.json";
        url = UriComponentsBuilder.fromHttpUrl(baseUrl).
                queryParam("api-key", "GvrAnjSjuTbuowbZIn3E8vVM9fSTeyjr");
    }

    public void addQuery(String query) {
        this.query = query;
    }

    public void addSection(String section) {
        String baseUrl = "https://api.nytimes.com/svc/news/v3/content/all/" + section + ".json";
        url = UriComponentsBuilder.fromHttpUrl(baseUrl).
                queryParam("api-key", "GvrAnjSjuTbuowbZIn3E8vVM9fSTeyjr");
    }

    public void addPage(String offset) {
        url = url.queryParam("offset", offset);
    }

    public void addPageSize(String limit) {
        url = url.queryParam("limit", limit);
    }

    public List<NewYorkTimesArticleResult> getArticles() {
        ResponseEntity<NewYorkTimesArticleResponse> response = restTemplate.getForEntity(url.encode().toUriString(), NewYorkTimesArticleResponse.class);

        if(response.getStatusCode().is5xxServerError()) {
            System.out.println("[Guardian get article] service error");
            throw new RestClientException("[Guardian get article] service error");
        } else if(response.getStatusCode().is4xxClientError()) {
            System.out.println("[Guardian get article] request error");
            throw new RestClientException("[Guardian get article] request error");
        } else if(response.getStatusCode().is2xxSuccessful()) {
            NewYorkTimesArticleResponse newYorkTimesArticleResponse = response.getBody();
            if(newYorkTimesArticleResponse == null)
                throw new RestClientException("[Guardian get article] get HTTP body error");

            System.out.println(url.encode().toUriString());

            if(query == null)
                return newYorkTimesArticleResponse.getResults();

            List<NewYorkTimesArticleResult> articles = new ArrayList<>();
            for(NewYorkTimesArticleResult result : newYorkTimesArticleResponse.getResults())
                if(result.getTitle().contains(query))
                    articles.add(result);

            query = null;
            buildUrl();

            return articles;
        }
        throw new RestClientException("Unidentified error");
    }
}
