module com.locadorafx {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires org.slf4j;
        requires org.apache.poi.ooxml;
        requires org.apache.poi.poi;
    requires jasypt;
    //requires jasypt;

        opens com.locadorafx.controllers to javafx.fxml;
        opens com.locadorafx to javafx.fxml;
        exports com.locadorafx;
        }