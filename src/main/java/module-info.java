module com.aokijiakainu.sae {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.aokijiakainu.sae to javafx.fxml;
    exports com.aokijiakainu.sae;
}