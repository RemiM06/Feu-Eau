module com.feueau.sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
                            
    opens com.feueau.sae to javafx.fxml;
    exports com.feueau.sae;
}
