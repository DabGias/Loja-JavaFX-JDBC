module br.com.fiap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.com.fiap to javafx.fxml;
    opens br.com.fiap.model to javafx.base;
    exports br.com.fiap;
}
