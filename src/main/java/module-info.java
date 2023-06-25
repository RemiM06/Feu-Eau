module com.feueau.sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires netty.socketio;
    requires engine.io.client;
    requires socket.io.client;
    requires com.fasterxml.jackson.databind;


    opens com.feueau.sae to javafx.fxml;
    exports com.feueau.sae;
}
