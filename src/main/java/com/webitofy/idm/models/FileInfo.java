package com.webitofy.idm.models;

import javafx.beans.property.*;

public class FileInfo {
    private SimpleStringProperty index = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty url = new SimpleStringProperty();
    private SimpleStringProperty status = new SimpleStringProperty();
    private SimpleStringProperty path = new SimpleStringProperty();
    private SimpleStringProperty percentage= new SimpleStringProperty();


    public FileInfo(String index, String name, String url, String status, String path, String percentage) {
        this.index.set(index);
        this.name.set(name);
        this.url.set(url);
        this.status.set(status);
        this.path.set(path);
        this.percentage.set(percentage);
    }

    public String getPercentage() {
        return percentage.get();
    }

    public SimpleStringProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }

    public String getIndex() {
        return index.get();
    }


    public SimpleStringProperty indexProperty() {
        return index;
    }

    public void setIndex(String index) {
        this.index.set(index);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "index=" + index +
                ", name=" + name +
                ", url=" + url +
                ", status=" + status +
                ", path=" + path +
                '}';
    }
}
