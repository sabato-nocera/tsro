package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;

public class TopicBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Literal topicLabel;
    private Resource topicUrl;

    public TopicBean() {

    }

    public Literal getTopicLabel() {
        return topicLabel;
    }

    public void setTopicLabel(Literal topicLabel) {
        this.topicLabel = topicLabel;
    }

    public Resource getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(Resource topicUrl) {
        this.topicUrl = topicUrl;
    }

    @Override
    public String toString() {
        return "TopicBean{" +
                "topicLabel=" + topicLabel +
                ", topicUrl=" + topicUrl +
                '}';
    }
}
