package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AgentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource authorUrlResource;
    private String authorUrl;
    private Literal authorName;
    private List<SoftwareBean> softwareBeanList;
    private List<UserAccountBean> userAccountBeanList;
    private String fromTheCloud;

    public Resource getAuthorUrlResource() {
        return authorUrlResource;
    }

    public void setAuthorUrlResource(Resource authorUrlResource) {
        this.authorUrlResource = authorUrlResource;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Literal getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Literal authorName) {
        this.authorName = authorName;
    }

    public List<SoftwareBean> getSoftwareBeanList() {
        return softwareBeanList;
    }

    public void setSoftwareBeanList(List<SoftwareBean> softwareBeanList) {
        this.softwareBeanList = softwareBeanList;
    }

    public List<UserAccountBean> getUserAccountBeanList() {
        return userAccountBeanList;
    }

    public void setUserAccountBeanList(List<UserAccountBean> userAccountBeanList) {
        this.userAccountBeanList = userAccountBeanList;
    }

    public String getFromTheCloud() {
        return fromTheCloud;
    }

    public void setFromTheCloud(String fromTheCloud) {
        this.fromTheCloud = fromTheCloud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentBean)) return false;
        AgentBean agentBean = (AgentBean) o;
        return Objects.equals(getAuthorUrlResource(), agentBean.getAuthorUrlResource()) && Objects.equals(getAuthorUrl(), agentBean.getAuthorUrl()) && Objects.equals(getAuthorName(), agentBean.getAuthorName()) && Objects.equals(getSoftwareBeanList(), agentBean.getSoftwareBeanList()) && Objects.equals(getUserAccountBeanList(), agentBean.getUserAccountBeanList()) && Objects.equals(getFromTheCloud(), agentBean.getFromTheCloud());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorUrlResource(), getAuthorUrl(), getAuthorName(), getSoftwareBeanList(), getUserAccountBeanList(), getFromTheCloud());
    }

    @Override
    public String toString() {
        return "AgentBean{" +
                "authorUrlResource=" + authorUrlResource +
                ", authorUrl='" + authorUrl + '\'' +
                ", authorName=" + authorName +
                ", softwareBeanList=" + softwareBeanList +
                ", userAccountBeanList=" + userAccountBeanList +
                ", fromTheCloud='" + fromTheCloud + '\'' +
                '}';
    }
}
