package pl.edu.agh.sr.news_provider.theguardian.sections.models;

import java.io.Serializable;

public class GuardianSectionResponse implements Serializable {
    private GuardianSectionSimpleResponse response;

    public GuardianSectionResponse() {}

    public GuardianSectionResponse(GuardianSectionSimpleResponse guardianSectionSimpleResponse) {
        this.response = guardianSectionSimpleResponse;
    }

    public GuardianSectionSimpleResponse getResponse() {
        return response;
    }

    public void setResponse(GuardianSectionSimpleResponse response) {
        this.response = response;
    }
}
