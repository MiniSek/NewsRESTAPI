package pl.edu.agh.sr.news_provider.newyorktimes.sections.models;

import java.io.Serializable;

public class NewYorkTimesSectionsResult implements Serializable {
    private String section;
    private String display_name;

    public NewYorkTimesSectionsResult() {
    }

    public NewYorkTimesSectionsResult(String section, String display_name) {
        this.section = section;
        this.display_name = display_name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
