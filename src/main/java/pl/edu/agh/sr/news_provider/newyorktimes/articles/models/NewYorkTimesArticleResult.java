package pl.edu.agh.sr.news_provider.newyorktimes.articles.models;

import java.io.Serializable;

public class NewYorkTimesArticleResult implements Serializable {
    private String section;
    private String subsection;
    private String title;
    private String uri;
    private String url;
    private String item_type;
    private String source;
    private String updated_date;
    private String created_date;
    private String published_date;
    private String first_published_date;
    private String material_type_facet;

    public NewYorkTimesArticleResult() {
    }

    public NewYorkTimesArticleResult(String section, String subsection, String title, String uri, String url, String item_type, String source, String updated_date, String created_date, String published_date, String first_published_date, String material_type_facet) {
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this.uri = uri;
        this.url = url;
        this.item_type = item_type;
        this.source = source;
        this.updated_date = updated_date;
        this.created_date = created_date;
        this.published_date = published_date;
        this.first_published_date = first_published_date;
        this.material_type_facet = material_type_facet;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getFirst_published_date() {
        return first_published_date;
    }

    public void setFirst_published_date(String first_published_date) {
        this.first_published_date = first_published_date;
    }

    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public void setMaterial_type_facet(String material_type_facet) {
        this.material_type_facet = material_type_facet;
    }
}
