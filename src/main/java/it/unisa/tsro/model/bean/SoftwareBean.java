package it.unisa.tsro.model.bean;


import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;


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
                '}';
    }
}
