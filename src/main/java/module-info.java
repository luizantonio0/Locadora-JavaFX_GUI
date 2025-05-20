module com.locadorafx {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        //requires jasypt;

        //TODO: Remover
        opens com.locadorafx.Entities.Clientes to javafx.base;
        opens com.locadorafx.Controllers to javafx.fxml;
        opens com.locadorafx to javafx.fxml;
        exports com.locadorafx;
        }