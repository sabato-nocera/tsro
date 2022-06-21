package it.unisa.tsro.model.bean;


import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class SoftwareBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Literal softwareTitle;
    private Resource softwareUrl;
    private Literal authorName;
    private Resource authorUrl;
    private Literal repositoryName;
    private Resource repositoryUrl;
    private int miPiace;
    private int numeroDiCommit;
    private List<TopicBean> topicBeanList;
    private Resource licensa;

    public SoftwareBean() {

    }

    public Literal getSoftwareTitle() {
        return softwareTitle;
    }

    public void setSoftwareTitle(Literal softwareTitle) {
        this.softwareTitle = softwareTitle;
    }

    public Resource getSoftwareUrl() {
        return softwareUrl;
    }

    public void setSoftwareUrl(Resource softwareUrl) {
        this.softwareUrl = softwareUrl;
    }

    public Literal getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Literal authorName) {
        this.authorName = authorName;
    }

    public Resource getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(Resource authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Literal getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(Literal repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Resource getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(Resource repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public int getMiPiace() {
        return miPiace;
    }

    public void setMiPiace(int miPiace) {
        this.miPiace = miPiace;
    }

    public int getNumeroDiCommit() {
        return numeroDiCommit;
    }

    public void setNumeroDiCommit(int numeroDiCommit) {
        this.numeroDiCommit = numeroDiCommit;
    }

    public List<TopicBean> getTopicBeanList() {
        return topicBeanList;
    }

    public void setTopicBeanList(List<TopicBean> topicBeanList) {
        this.topicBeanList = topicBeanList;
    }

    public Resource getLicensa() {
        return licensa;
    }

    public void setLicensa(Resource licensa) {
        this.licensa = licensa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoftwareBean)) return false;
        SoftwareBean that = (SoftwareBean) o;
        return getMiPiace() == that.getMiPiace() && getNumeroDiCommit() == that.getNumeroDiCommit() && Objects.equals(getSoftwareTitle(), that.getSoftwareTitle()) && Objects.equals(getSoftwareUrl(), that.getSoftwareUrl()) && Objects.equals(getAuthorName(), that.getAuthorName()) && Objects.equals(getAuthorUrl(), that.getAuthorUrl()) && Objects.equals(getRepositoryName(), that.getRepositoryName()) && Objects.equals(getRepositoryUrl(), that.getRepositoryUrl()) && Objects.equals(getTopicBeanList(), that.getTopicBeanList()) && Objects.equals(getLicensa(), that.getLicensa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoftwareTitle(), getSoftwareUrl(), getAuthorName(), getAuthorUrl(), getRepositoryName(), getRepositoryUrl(), getMiPiace(), getNumeroDiCommit(), getTopicBeanList(), getLicensa());
    }

    @Override
    public String toString() {
        return "SoftwareBean{" +
                "softwareTitle=" + softwareTitle +
                ", softwareUrl=" + softwareUrl +
                ", authorName=" + authorName +
                ", authorUrl=" + authorUrl +
                ", repositoryName=" + repositoryName +
                ", repositoryUrl=" + repositoryUrl +
                ", miPiace=" + miPiace +
                ", numeroDiCommit=" + numeroDiCommit +
                ", topicBeanList=" + topicBeanList +
                ", licensa=" + licensa +
                '}';
    }
}
