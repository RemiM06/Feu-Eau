module com.aokijiakainu.sae {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.feueau.sae to javafx.fxml;
    exports com.feueau.sae;
}