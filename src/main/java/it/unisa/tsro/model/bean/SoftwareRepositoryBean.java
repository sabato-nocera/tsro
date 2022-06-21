package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SoftwareRepositoryBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource softwareRepositoryUrl;
    private String softwareRepositoryUrlString;
    private String softwareRepositoryTitleString;
    private Literal softwareRepositoryTitle;
    private SoftwareBean softwareBean;
    private Literal repositoryHtmlUrl;
    private Literal repositoryCloneUrl;
    private List<SoftwareRepositoryBean> repositoriesForkList;
    private UserAccountBean userAccountBean;
    private List<BranchBean> branchBeanList;
    private boolean hasForks;

    public SoftwareRepositoryBean() {
    }

    public Resource getSoftwareRepositoryUrl() {
        return softwareRepositoryUrl;
    }

    public void setSoftwareRepositoryUrl(Resource softwareRepositoryUrl) {
        this.softwareRepositoryUrl = softwareRepositoryUrl;
    }

    public String getSoftwareRepositoryUrlString() {
        return softwareRepositoryUrlString;
    }

    public void setSoftwareRepositoryUrlString(String softwareRepositoryUrlString) {
        this.softwareRepositoryUrlString = softwareRepositoryUrlString;
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

    public SoftwareBean getSoftwareBean() {
        return softwareBean;
    }

    public void setSoftwareBean(SoftwareBean softwareBean) {
        this.softwareBean = softwareBean;
    }

    public Literal getRepositoryHtmlUrl() {
        return repositoryHtmlUrl;
    }

    public void setRepositoryHtmlUrl(Literal repositoryHtmlUrl) {
        this.repositoryHtmlUrl = repositoryHtmlUrl;
    }

    public Literal getRepositoryCloneUrl() {
        return repositoryCloneUrl;
    }

    public void setRepositoryCloneUrl(Literal repositoryCloneUrl) {
        this.repositoryCloneUrl = repositoryCloneUrl;
    }

    public List<SoftwareRepositoryBean> getRepositoriesForkList() {
        return repositoriesForkList;
    }

    public void setRepositoriesForkList(List<SoftwareRepositoryBean> repositoriesForkList) {
        this.repositoriesForkList = repositoriesForkList;
    }

    public UserAccountBean getUserAccountBean() {
        return userAccountBean;
    }

    public void setUserAccountBean(UserAccountBean userAccountBean) {
        this.userAccountBean = userAccountBean;
    }

    public List<BranchBean> getBranchBeanList() {
        return branchBeanList;
    }

    public void setBranchBeanList(List<BranchBean> branchBeanList) {
        this.branchBeanList = branchBeanList;
    }

    public boolean isHasForks() {
        return hasForks;
    }

    public void setHasForks(boolean hasForks) {
        this.hasForks = hasForks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoftwareRepositoryBean)) return false;
        SoftwareRepositoryBean that = (SoftwareRepositoryBean) o;
        return isHasForks() == that.isHasForks() && Objects.equals(getSoftwareRepositoryUrl(), that.getSoftwareRepositoryUrl()) && Objects.equals(getSoftwareRepositoryUrlString(), that.getSoftwareRepositoryUrlString()) && Objects.equals(getSoftwareRepositoryTitleString(), that.getSoftwareRepositoryTitleString()) && Objects.equals(getSoftwareRepositoryTitle(), that.getSoftwareRepositoryTitle()) && Objects.equals(getSoftwareBean(), that.getSoftwareBean()) && Objects.equals(getRepositoryHtmlUrl(), that.getRepositoryHtmlUrl()) && Objects.equals(getRepositoryCloneUrl(), that.getRepositoryCloneUrl()) && Objects.equals(getRepositoriesForkList(), that.getRepositoriesForkList()) && Objects.equals(getUserAccountBean(), that.getUserAccountBean()) && Objects.equals(getBranchBeanList(), that.getBranchBeanList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoftwareRepositoryUrl(), getSoftwareRepositoryUrlString(), getSoftwareRepositoryTitleString(), getSoftwareRepositoryTitle(), getSoftwareBean(), getRepositoryHtmlUrl(), getRepositoryCloneUrl(), getRepositoriesForkList(), getUserAccountBean(), getBranchBeanList(), isHasForks());
    }

    @Override
    public String toString() {
        return "SoftwareRepositoryBean{" +
                "softwareRepositoryUrl=" + softwareRepositoryUrl +
                ", softwareRepositoryUrlString='" + softwareRepositoryUrlString + '\'' +
                ", softwareRepositoryTitleString='" + softwareRepositoryTitleString + '\'' +
                ", softwareRepositoryTitle=" + softwareRepositoryTitle +
                ", softwareBean=" + softwareBean +
                ", repositoryHtmlUrl=" + repositoryHtmlUrl +
                ", repositoryCloneUrl=" + repositoryCloneUrl +
                ", repositoriesForkList=" + repositoriesForkList +
                ", userAccountBean=" + userAccountBean +
                ", branchBeanList=" + branchBeanList +
                ", hasForks=" + hasForks +
                '}';
    }
}
