package pl.edu.agh.sr.news_provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.sr.news_provider.newyorktimes.articles.NewYorkTimesArticleServlet;
import pl.edu.agh.sr.news_provider.newyorktimes.sections.NewYorkTimesSectionsServlet;
import pl.edu.agh.sr.news_provider.theguardian.articles.GuardianArticlesServlet;
import pl.edu.agh.sr.news_provider.theguardian.sections.GuardianSectionsServlet;

@Configuration
public class ServiceConfiguration {
    @Bean
    public RestTemplateBuilder getRestTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public GuardianSectionsServlet getGuardianSectionsServlet() {
        return new GuardianSectionsServlet(getRestTemplateBuilder());
    }

    @Bean
    public NewYorkTimesSectionsServlet getNewYorkTimesSectionsServlet() {
        return new NewYorkTimesSectionsServlet(getRestTemplateBuilder());
    }

    @Bean
    public GuardianArticlesServlet getGuardianArticlesServlet() {
        return new GuardianArticlesServlet(getRestTemplateBuilder());
    }

    @Bean
    public NewYorkTimesArticleServlet getNewYorkTimesArticlesServlet() {
        return new NewYorkTimesArticleServlet(getRestTemplateBuilder());
    }
}
