package pl.edu.agh.sr.news_provider.theguardian.articles.models;

public class GuardianArticleResponse {
    private GuardianArticleSimpleResponse response;

    public GuardianArticleResponse() {}

    public GuardianArticleResponse(GuardianArticleSimpleResponse guardianSectionSimpleResponse) {
        this.response = guardianSectionSimpleResponse;
    }

    public GuardianArticleSimpleResponse getResponse() {
        return response;
    }

    public void setResponse(GuardianArticleSimpleResponse response) {
        this.response = response;
    }

}
