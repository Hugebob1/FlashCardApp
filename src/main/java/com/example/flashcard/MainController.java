package com.example.flashcard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> sets = Filelister.getFileNames("src/main/resources");

        ObservableList<String> zestawy = FXCollections.observableArrayList(sets);

        listView.setItems(zestawy);
    }

    @FXML
    private void handlePlayClick(ActionEvent event) throws IOException {
        String wybranyPlik = (String) listView.getSelectionModel().getSelectedItem();
        if (wybranyPlik != null) {
            System.out.println("Wybrano plik: " + wybranyPlik);
            FileNameXD.pickedset = wybranyPlik;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("helloview.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/com/example/flashcard/style.css").toExternalForm());

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } else {
            System.out.println("Nie wybrano żadnego pliku.");
        }
    }

    @FXML
    private void handleAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addset.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/com/example/flashcard/style.css").toExternalForm());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
