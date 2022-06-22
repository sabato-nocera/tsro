package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BranchBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource branchUrl;
    private String branchUrlString;
    private Literal branchTitle;
    private Literal isMainBranch;
    private SoftwareRepositoryBean softwareRepositoryBean;
    private List<CommitBean> commitBeanList;

    public BranchBean() {
    }

    public Resource getBranchUrl() {
        return branchUrl;
    }

    public void setBranchUrl(Resource branchUrl) {
        this.branchUrl = branchUrl;
    }

    public String getBranchUrlString() {
        return branchUrlString;
    }

    public void setBranchUrlString(String branchUrlString) {
        this.branchUrlString = branchUrlString;
    }

    public Literal getBranchTitle() {
        return branchTitle;
    }

    public void setBranchTitle(Literal branchTitle) {
        this.branchTitle = branchTitle;
    }

    public Literal getIsMainBranch() {
        return isMainBranch;
    }

    public void setIsMainBranch(Literal isMainBranch) {
        this.isMainBranch = isMainBranch;
    }

    public SoftwareRepositoryBean getSoftwareRepositoryBean() {
        return softwareRepositoryBean;
    }

    public void setSoftwareRepositoryBean(SoftwareRepositoryBean softwareRepositoryBean) {
        this.softwareRepositoryBean = softwareRepositoryBean;
    }

    public List<CommitBean> getCommitBeanList() {
        return commitBeanList;
    }

    public void setCommitBeanList(List<CommitBean> commitBeanList) {
        this.commitBeanList = commitBeanList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchBean)) return false;
        BranchBean that = (BranchBean) o;
        return Objects.equals(getBranchUrl(), that.getBranchUrl()) && Objects.equals(getBranchUrlString(), that.getBranchUrlString()) && Objects.equals(getBranchTitle(), that.getBranchTitle()) && Objects.equals(getIsMainBranch(), that.getIsMainBranch()) && Objects.equals(getSoftwareRepositoryBean(), that.getSoftwareRepositoryBean()) && Objects.equals(getCommitBeanList(), that.getCommitBeanList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranchUrl(), getBranchUrlString(), getBranchTitle(), getIsMainBranch(), getSoftwareRepositoryBean(), getCommitBeanList());
    }

    @Override
    public String toString() {
        return "BranchBean{" +
                "branchUrl=" + branchUrl +
                ", branchUrlString='" + branchUrlString + '\'' +
                ", branchTitle=" + branchTitle +
                ", isMainBranch=" + isMainBranch +
                ", softwareRepositoryBean=" + softwareRepositoryBean +
                ", commitBeanList=" + commitBeanList +
                '}';
    }
}
