package pl.edu.agh.sr.news_provider.theguardian.articles;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.models.NewYorkTimesSectionResponse;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.models.NewYorkTimesSectionsResult;
import pl.edu.agh.sr.news_provider.theguardian.articles.models.GuardianArticleResponse;
import pl.edu.agh.sr.news_provider.theguardian.articles.models.GuardianArticleResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuardianArticlesServlet {
    private final RestTemplate restTemplate;
    private UriComponentsBuilder url;

    public GuardianArticlesServlet(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        buildUrl();
    }

    private void buildUrl() {
        String baseUrl = "https://content.guardianapis.com/search";
        url = UriComponentsBuilder.fromHttpUrl(baseUrl).
                queryParam("api-key", "d1db9c4f-8ae0-4db7-b1ca-452a029c90ff");
    }

    public void addQuery(String query) {
        url = url.queryParam("q", query);
    }

    public void addSection(String section) {
        url = url.queryParam("section", section);
    }

    public void addPage(String page) {
        url = url.queryParam("page", page);
    }

    public void addPageSize(String pageSize) {
        url = url.queryParam("page-size", pageSize);
    }

    public List<GuardianArticleResult> getArticles() {
        ResponseEntity<GuardianArticleResponse> response = restTemplate.getForEntity(url.encode().toUriString(), GuardianArticleResponse.class);

        if(response.getStatusCode().is5xxServerError()) {
            System.out.println("[Guardian get article] service error");
            throw new RestClientException("[Guardian get article] service error");
        } else if(response.getStatusCode().is4xxClientError()) {
            System.out.println("[Guardian get article] request error");
            throw new RestClientException("[Guardian get article] request error");
        } else if(response.getStatusCode().is2xxSuccessful()) {
            GuardianArticleResponse guardianArticleResponse = response.getBody();
            if(guardianArticleResponse == null)
                throw new RestClientException("[Guardian get article] get HTTP body error");

            System.out.println(url.encode().toUriString());

            buildUrl();

            return guardianArticleResponse.getResponse().getResults();
        }
        throw new RestClientException("Unidentified error");
    }
}
