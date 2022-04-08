package pl.edu.agh.sr.news_provider.theguardian.sections.models;

import java.io.Serializable;

public class GuardianSectionResult implements Serializable {
    private String id;
    private String webTitle;
    private String webUrl;
    private String apiUrl;

    public GuardianSectionResult() {}

    public GuardianSectionResult(String id, String webTitle, String webUrl, String apiUrl) {
        this.id = id;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
