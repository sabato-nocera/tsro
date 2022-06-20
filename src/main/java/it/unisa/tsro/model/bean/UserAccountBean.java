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

    public UserAccountBean() {

    }

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

    @Override
    public String toString() {
        return "UserAccountBean{" +
                "userAccountUrl=" + userAccountUrl +
                ", userAccountName=" + userAccountName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountBean)) return false;
        UserAccountBean that = (UserAccountBean) o;
        return Objects.equals(getUserAccountUrl(), that.getUserAccountUrl()) && Objects.equals(getUserAccountName(), that.getUserAccountName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserAccountUrl(), getUserAccountName());
    }
}
