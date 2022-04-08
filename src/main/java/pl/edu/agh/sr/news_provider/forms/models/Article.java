package pl.edu.agh.sr.news_provider.forms.models;

public class Article {
    private String source;
    private String title;
    private String section;
    private String webUrl;
    private String publicationDate;

    public Article(String source, String title, String section, String webUrl, String publicationDate) {
        this.source = source;
        this.title = title;
        this.section = section;
        this.webUrl = webUrl;
        this.publicationDate = publicationDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
