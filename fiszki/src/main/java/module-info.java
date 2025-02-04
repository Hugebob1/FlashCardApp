module com.example.fiszki {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fiszki to javafx.fxml;
    exports com.example.fiszki;
}