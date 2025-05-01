module com.locadorafx {
        requires javafx.controls;
        requires javafx.fxml;

        opens com.locadorafx.Controllers to javafx.fxml;
        opens com.locadorafx to javafx.fxml;
        exports com.locadorafx;
        }