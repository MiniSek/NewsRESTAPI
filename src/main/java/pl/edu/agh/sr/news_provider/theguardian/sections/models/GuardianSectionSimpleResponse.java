package pl.edu.agh.sr.news_provider.theguardian.sections.models;

import java.io.Serializable;
import java.util.List;

public class GuardianSectionSimpleResponse implements Serializable {
    private String status;
    private String total;
    private List<GuardianSectionResult> results;

    public GuardianSectionSimpleResponse() {}

    public GuardianSectionSimpleResponse(String status, String total, List<GuardianSectionResult> results) {
        this.status = status;
        this.total = total;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<GuardianSectionResult> getResults() {
        return results;
    }

    public void setResults(List<GuardianSectionResult> guardianSectionResults) {
        this.results = guardianSectionResults;
    }
}
