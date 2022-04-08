package pl.edu.agh.sr.news_provider.forms.models;

public class FormRequest {
    private String section;
    private String query;
    private Integer pageSize;
    private String sortBy;

    public FormRequest() {}

    public FormRequest(String section, String query, Integer pageSize, String sortBy) {
        this.section = section;
        this.query = query;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
