module com.locadorafx {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;
    //requires jasypt;

//        opens com.locadorafx.Entities.Locacao to javafx.base;
//        opens com.locadorafx.Entities.Veiculos to javafx.base;
        opens com.locadorafx.Entities.Clientes to javafx.base;
        opens com.locadorafx.Controllers to javafx.fxml;
        opens com.locadorafx to javafx.fxml;
        exports com.locadorafx;
        }