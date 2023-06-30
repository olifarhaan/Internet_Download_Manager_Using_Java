package com.webitofy.idm;

import com.webitofy.idm.config.AppConfig;
import com.webitofy.idm.models.FileInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class DownloadManager {

    @FXML
    private TableView<FileInfo> tableView;

    @FXML
    private TextField urlText;
    public int index = 0;

    @FXML
    public void downloadButtonClicked() {
        String url = urlText.getText().trim();
        int i = url.length() - 1;
        while (i >= 0) {
            if (url.charAt(i) != ('/')) break;
            i--;
        }
        url = url.substring(0, i + 1);
        if (!url.equals("") && this.isValidURL(url)) {
            String name = url.substring(url.lastIndexOf('/') + 1);
            String status = "STARTING...";
            String action = "OPEN";
            String path = AppConfig.DOWNLOAD_PATH + File.separator + name;
            FileInfo file = new FileInfo(String.valueOf(index + 1), name, url, status, path, "0");
            this.index = this.index + 1;
            DownloadThread thread = new DownloadThread(file, this);
            this.tableView.getItems().add(Integer.parseInt(file.getIndex()) - 1, file);
            thread.setDaemon(true);
            thread.start();
            this.urlText.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (url.equals(""))
                alert.setHeaderText("Enter the URL first");
            else alert.setHeaderText("Enter a valid URL");
            alert.setContentText("Enter a valid URL in the text box");
            alert.show();
        }
    }

    public void updateUI(FileInfo file) {
        FileInfo fileInfo = tableView.getItems().get(Integer.parseInt(file.getIndex()) - 1);
        fileInfo.setPercentage(file.getPercentage());
        fileInfo.setStatus(file.getStatus());
        tableView.refresh();
    }

    @FXML
    public void initialize() {
        System.out.println("View Initialized");
        TableColumn<FileInfo, String> sn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(0);
        sn.setCellValueFactory(p -> p.getValue().indexProperty());
        TableColumn<FileInfo, String> filename = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(1);
        filename.setCellValueFactory(p -> p.getValue().nameProperty());

        TableColumn<FileInfo, String> url = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(2);
        url.setCellValueFactory(p -> p.getValue().urlProperty());

        TableColumn<FileInfo, String> status = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(3);
        status.setCellValueFactory(p -> p.getValue().statusProperty());

        TableColumn<FileInfo, String> percentage = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(4);
        percentage.setCellValueFactory(p -> {
            SimpleStringProperty percentageProperty = new SimpleStringProperty();
            percentageProperty.set(p.getValue().getPercentage() + "%");
            return percentageProperty;
        });

    }

    private boolean isValidURL(String urlString) {
        try {
            URL url = new URI(urlString).toURL();
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
