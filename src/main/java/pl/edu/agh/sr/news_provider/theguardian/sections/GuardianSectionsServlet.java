package pl.edu.agh.sr.news_provider.theguardian.sections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.sr.news_provider.theguardian.sections.models.GuardianSectionResponse;
import pl.edu.agh.sr.news_provider.theguardian.sections.models.GuardianSectionResult;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GuardianSectionsServlet {
    private final RestTemplate restTemplate;

    public GuardianSectionsServlet(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Map<String, String> getSections() {
        String baseUrl = "https://content.guardianapis.com/sections";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).
                queryParam("api-key", "d1db9c4f-8ae0-4db7-b1ca-452a029c90ff").encode().toUriString();

        ResponseEntity<GuardianSectionResponse> response = restTemplate.getForEntity(url, GuardianSectionResponse.class);

        if(response.getStatusCode().is5xxServerError()) {
            System.out.println("[Guardian get section] service error");
            throw new RestClientException("[Guardian get section] service error");
        } else if(response.getStatusCode().is4xxClientError()) {
            System.out.println("[Guardian get section] request error");
            throw new RestClientException("[Guardian get section] request error");
        } else if(response.getStatusCode().is2xxSuccessful()) {
            GuardianSectionResponse guardianSectionResponse = response.getBody();
            if(guardianSectionResponse == null)
                throw new RestClientException("[Guardian get section] get HTTP body error");

            Map<String, String> sections = new HashMap<>();
            for (GuardianSectionResult guardianSectionResult : guardianSectionResponse.getResponse().getResults())
                sections.put(guardianSectionResult.getWebTitle(), guardianSectionResult.getId());
            return sections;
        }
        throw new RestClientException("Unidentified error");
    }
}
