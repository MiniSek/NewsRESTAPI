package pl.edu.agh.sr.news_provider.theguardian.articles.models;

import java.util.List;

public class GuardianArticleSimpleResponse {
    private String status;
    private String userTier;
    private String total;
    private String startIndex;
    private String pageSize;
    private String currentPage;
    private String pages;
    private String orderBy;
    private List<GuardianArticleResult> results;

    public GuardianArticleSimpleResponse() {}

    public GuardianArticleSimpleResponse(String status, String userTier, String total, String startIndex, String pageSize, String currentPage, String pages, String orderBy, List<GuardianArticleResult> results) {
        this.status = status;
        this.userTier = userTier;
        this.total = total;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pages = pages;
        this.orderBy = orderBy;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<GuardianArticleResult> getResults() {
        return results;
    }

    public void setResults(List<GuardianArticleResult> results) {
        this.results = results;
    }
}
