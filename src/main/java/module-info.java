module com.webitofy.idm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.webitofy.idm to javafx.fxml;
    exports com.webitofy.idm;
}