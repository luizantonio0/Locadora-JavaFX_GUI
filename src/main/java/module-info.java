module com.locadorafx {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires org.slf4j;
        //requires jasypt;

        opens com.locadorafx.Controllers to javafx.fxml;
        opens com.locadorafx to javafx.fxml;
        exports com.locadorafx;
        }