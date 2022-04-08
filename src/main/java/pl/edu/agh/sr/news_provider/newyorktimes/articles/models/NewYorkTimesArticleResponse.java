package pl.edu.agh.sr.news_provider.newyorktimes.articles.models;

import java.io.Serializable;
import java.util.List;

public class NewYorkTimesArticleResponse implements Serializable {
    private String status;
    private String copyright;
    private String num_results;
    private List<NewYorkTimesArticleResult> results;

    public NewYorkTimesArticleResponse() {
    }

    public NewYorkTimesArticleResponse(String status, String copyright, String num_results, List<NewYorkTimesArticleResult> results) {
        this.status = status;
        this.copyright = copyright;
        this.num_results = num_results;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getNum_results() {
        return num_results;
    }

    public void setNum_results(String num_results) {
        this.num_results = num_results;
    }

    public List<NewYorkTimesArticleResult> getResults() {
        return results;
    }

    public void setResults(List<NewYorkTimesArticleResult> results) {
        this.results = results;
    }
}
