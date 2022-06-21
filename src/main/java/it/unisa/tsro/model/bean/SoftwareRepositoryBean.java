package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SoftwareRepositoryBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource softwareRepositoryUrl;
    private String softwareRepositoryTitleString;
    private Literal softwareRepositoryTitle;

    public Resource getSoftwareRepositoryUrl() {
        return softwareRepositoryUrl;
    }

    public void setSoftwareRepositoryUrl(Resource softwareRepositoryUrl) {
        this.softwareRepositoryUrl = softwareRepositoryUrl;
    }

    public String getSoftwareRepositoryTitleString() {
        return softwareRepositoryTitleString;
    }

    public void setSoftwareRepositoryTitleString(String softwareRepositoryTitleString) {
        this.softwareRepositoryTitleString = softwareRepositoryTitleString;
    }

    public Literal getSoftwareRepositoryTitle() {
        return softwareRepositoryTitle;
    }

    public void setSoftwareRepositoryTitle(Literal softwareRepositoryTitle) {
        this.softwareRepositoryTitle = softwareRepositoryTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoftwareRepositoryBean)) return false;
        SoftwareRepositoryBean that = (SoftwareRepositoryBean) o;
        return Objects.equals(getSoftwareRepositoryUrl(), that.getSoftwareRepositoryUrl()) && Objects.equals(getSoftwareRepositoryTitleString(), that.getSoftwareRepositoryTitleString()) && Objects.equals(getSoftwareRepositoryTitle(), that.getSoftwareRepositoryTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoftwareRepositoryUrl(), getSoftwareRepositoryTitleString(), getSoftwareRepositoryTitle());
    }

    @Override
    public String toString() {
        return "SoftwareRepositoryBean{" +
                "softwareRepositoryUrl=" + softwareRepositoryUrl +
                ", softwareRepositoryTitleString='" + softwareRepositoryTitleString + '\'' +
                ", softwareRepositoryTitle=" + softwareRepositoryTitle +
                '}';
    }
}
