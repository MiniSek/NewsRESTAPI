package pl.edu.agh.sr.news_provider.newyorktimes.sections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.models.NewYorkTimesSectionsResult;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.models.NewYorkTimesSectionResponse;
import pl.edu.agh.sr.news_provider.theguardian.sections.models.GuardianSectionResponse;
import pl.edu.agh.sr.news_provider.theguardian.sections.models.GuardianSectionResult;

import java.util.HashMap;
import java.util.Map;

public class NewYorkTimesSectionsServlet {
    private final RestTemplate restTemplate;

    public NewYorkTimesSectionsServlet(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Map<String, String> getSections() {
        String baseUrl = "https://api.nytimes.com/svc/news/v3/content/section-list.json";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).
                queryParam("api-key", "GvrAnjSjuTbuowbZIn3E8vVM9fSTeyjr").encode().toUriString();

        ResponseEntity<NewYorkTimesSectionResponse> response = restTemplate.getForEntity(url, NewYorkTimesSectionResponse.class);

        if(response.getStatusCode().is5xxServerError()) {
            System.out.println("[NYT get section] service error");
            throw new RestClientException("[NYT get section] service error");
        } else if(response.getStatusCode().is4xxClientError()) {
            System.out.println("[NYT get section] request error");
            throw new RestClientException("[NYT get section] request error");
        } else if(response.getStatusCode().is2xxSuccessful()) {
            NewYorkTimesSectionResponse newYorkTimesSectionResponse = response.getBody();
            if(newYorkTimesSectionResponse == null)
                throw new RestClientException("[NYT get section] get HTTP body error");

            Map<String, String> sections = new HashMap<>();
            for(NewYorkTimesSectionsResult result : newYorkTimesSectionResponse.getResults())
                sections.put(result.getDisplay_name(), result.getSection());
            return sections;
        }
        throw new RestClientException("Unidentified error");
    }
}
