module com.example.flashcard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.flashcard to javafx.fxml;
    exports com.example.flashcard;
}