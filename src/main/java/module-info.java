module com.example.farmaciaflores3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.burningwave.core;
    requires java.sql;

    opens com.example.farmaciaflores3 to javafx.fxml;
    exports com.example.farmaciaflores3;
    exports com.example.farmaciaflores3.Controllers;
    opens com.example.farmaciaflores3.Controllers to javafx.fxml;
}