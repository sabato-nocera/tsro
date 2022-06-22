package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CommitBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource commitUrl; //
    private String commitUrlString; //
    private Literal commitNumber; //
    private Literal commitDate; //
    private UserAccountBean committer;
    private List<FileBean> fileBeanList;
    private BranchBean branchBean; //

    public CommitBean() {
    }

    public Resource getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(Resource commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getCommitUrlString() {
        return commitUrlString;
    }

    public void setCommitUrlString(String commitUrlString) {
        this.commitUrlString = commitUrlString;
    }

    public Literal getCommitNumber() {
        return commitNumber;
    }

    public void setCommitNumber(Literal commitNumber) {
        this.commitNumber = commitNumber;
    }

    public Literal getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Literal commitDate) {
        this.commitDate = commitDate;
    }

    public UserAccountBean getCommitter() {
        return committer;
    }

    public void setCommitter(UserAccountBean committer) {
        this.committer = committer;
    }

    public List<FileBean> getFileBeanList() {
        return fileBeanList;
    }

    public void setFileBeanList(List<FileBean> fileBeanList) {
        this.fileBeanList = fileBeanList;
    }

    public BranchBean getBranchBean() {
        return branchBean;
    }

    public void setBranchBean(BranchBean branchBean) {
        this.branchBean = branchBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommitBean)) return false;
        CommitBean that = (CommitBean) o;
        return Objects.equals(getCommitUrl(), that.getCommitUrl()) && Objects.equals(getCommitUrlString(), that.getCommitUrlString()) && Objects.equals(getCommitNumber(), that.getCommitNumber()) && Objects.equals(getCommitDate(), that.getCommitDate()) && Objects.equals(getCommitter(), that.getCommitter()) && Objects.equals(getFileBeanList(), that.getFileBeanList()) && Objects.equals(getBranchBean(), that.getBranchBean());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommitUrl(), getCommitUrlString(), getCommitNumber(), getCommitDate(), getCommitter(), getFileBeanList(), getBranchBean());
    }

    @Override
    public String toString() {
        return "CommitBean{" +
                "commitUrl=" + commitUrl +
                ", commitUrlString='" + commitUrlString + '\'' +
                ", commitNumber=" + commitNumber +
                ", commitDate=" + commitDate +
                ", committer=" + committer +
                ", fileBeanList=" + fileBeanList +
                ", branchBean=" + branchBean +
                '}';
    }
}
