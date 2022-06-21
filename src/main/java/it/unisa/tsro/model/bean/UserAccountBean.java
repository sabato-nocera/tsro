package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserAccountBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource userAccountUrl;
    private Literal userAccountName;
    private String userAccountUrlString;
    List<AgentBean> agentBeanList;
    List<SoftwareRepositoryBean> softwareCreatedList;
    List<SoftwareRepositoryBean> softwareLikedList;
    List<UserAccountBean> userAccountFollowedList;
    List<UserAccountBean> userAccountFollowingList;

    public Resource getUserAccountUrl() {
        return userAccountUrl;
    }

    public void setUserAccountUrl(Resource userAccountUrl) {
        this.userAccountUrl = userAccountUrl;
    }

    public Literal getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(Literal userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getUserAccountUrlString() {
        return userAccountUrlString;
    }

    public void setUserAccountUrlString(String userAccountUrlString) {
        this.userAccountUrlString = userAccountUrlString;
    }

    public List<AgentBean> getAgentBeanList() {
        return agentBeanList;
    }

    public void setAgentBeanList(List<AgentBean> agentBeanList) {
        this.agentBeanList = agentBeanList;
    }

    public List<SoftwareRepositoryBean> getSoftwareCreatedList() {
        return softwareCreatedList;
    }

    public void setSoftwareCreatedList(List<SoftwareRepositoryBean> softwareCreatedList) {
        this.softwareCreatedList = softwareCreatedList;
    }

    public List<SoftwareRepositoryBean> getSoftwareLikedList() {
        return softwareLikedList;
    }

    public void setSoftwareLikedList(List<SoftwareRepositoryBean> softwareLikedList) {
        this.softwareLikedList = softwareLikedList;
    }

    public List<UserAccountBean> getUserAccountFollowedList() {
        return userAccountFollowedList;
    }

    public void setUserAccountFollowedList(List<UserAccountBean> userAccountFollowedList) {
        this.userAccountFollowedList = userAccountFollowedList;
    }

    public List<UserAccountBean> getUserAccountFollowingList() {
        return userAccountFollowingList;
    }

    public void setUserAccountFollowingList(List<UserAccountBean> userAccountFollowingList) {
        this.userAccountFollowingList = userAccountFollowingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountBean)) return false;
        UserAccountBean that = (UserAccountBean) o;
        return Objects.equals(getUserAccountUrl(), that.getUserAccountUrl()) && Objects.equals(getUserAccountName(), that.getUserAccountName()) && Objects.equals(getUserAccountUrlString(), that.getUserAccountUrlString()) && Objects.equals(getAgentBeanList(), that.getAgentBeanList()) && Objects.equals(getSoftwareCreatedList(), that.getSoftwareCreatedList()) && Objects.equals(getSoftwareLikedList(), that.getSoftwareLikedList()) && Objects.equals(getUserAccountFollowedList(), that.getUserAccountFollowedList()) && Objects.equals(getUserAccountFollowingList(), that.getUserAccountFollowingList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserAccountUrl(), getUserAccountName(), getUserAccountUrlString(), getAgentBeanList(), getSoftwareCreatedList(), getSoftwareLikedList(), getUserAccountFollowedList(), getUserAccountFollowingList());
    }

    @Override
    public String toString() {
        return "UserAccountBean{" +
                "userAccountUrl=" + userAccountUrl +
                ", userAccountName=" + userAccountName +
                ", userAccountUrlString='" + userAccountUrlString + '\'' +
                ", agentBeanList=" + agentBeanList +
                ", softwareCreatedList=" + softwareCreatedList +
                ", softwareLikedList=" + softwareLikedList +
                ", userAccountFollowedList=" + userAccountFollowedList +
                ", userAccountFollowingList=" + userAccountFollowingList +
                '}';
    }
}
