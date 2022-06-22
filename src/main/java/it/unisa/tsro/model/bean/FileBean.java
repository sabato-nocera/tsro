package it.unisa.tsro.model.bean;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.io.Serializable;
import java.util.Objects;

public class FileBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Resource fileUrl;
    private String fileUrlString;
    private Literal fileTitle;
    private Literal programmingLanguage;
    private Literal extension;

    public FileBean() {
    }

    public Resource getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(Resource fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrlString() {
        return fileUrlString;
    }

    public void setFileUrlString(String fileUrlString) {
        this.fileUrlString = fileUrlString;
    }

    public Literal getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(Literal fileTitle) {
        this.fileTitle = fileTitle;
    }

    public Literal getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(Literal programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public Literal getExtension() {
        return extension;
    }

    public void setExtension(Literal extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileBean)) return false;
        FileBean fileBean = (FileBean) o;
        return Objects.equals(getFileUrl(), fileBean.getFileUrl()) && Objects.equals(getFileUrlString(), fileBean.getFileUrlString()) && Objects.equals(getFileTitle(), fileBean.getFileTitle()) && Objects.equals(getProgrammingLanguage(), fileBean.getProgrammingLanguage()) && Objects.equals(getExtension(), fileBean.getExtension());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileUrl(), getFileUrlString(), getFileTitle(), getProgrammingLanguage(), getExtension());
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "fileUrl=" + fileUrl +
                ", fileUrlString='" + fileUrlString + '\'' +
                ", fileTitle=" + fileTitle +
                ", programmingLanguage=" + programmingLanguage +
                ", extension=" + extension +
                '}';
    }
}
