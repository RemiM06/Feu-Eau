module com.feueau.sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires netty.socketio;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires socket.io.client;
    requires engine.io.client;


    opens com.feueau.sae to javafx.fxml;
    exports com.feueau.sae;
}
