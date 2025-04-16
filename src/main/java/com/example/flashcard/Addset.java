package com.example.flashcard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Addset {
    @FXML private TextField textField;
    @FXML private TextArea textArea;
    @FXML private Label label;
    public String folder_path = "src/main/resources";

    @FXML
    private void handleAddClick(ActionEvent event) throws IOException {
        String name = textField.getText();
        String all = textArea.getText();
        if(all!=null && name!=null) {
            System.out.println(all);
            List<String> files = Filelister.getFileNames(folder_path);
            if(files.contains(name)) {
                label.setText("Set with this name already exists");
            }
            else if(!check(all)){
                label.setText("Invalid format");
            }
            else{
                label.setText("");
                Filelister.addFile(folder_path, name, all);
                textField.setText("");
                textArea.setText("");
            }
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/com/example/flashcard/style.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private boolean check(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            int x = line.indexOf(":");
            if (x == -1 || x == 0 || x == line.length() - 1) {
                return false;
            }
        }
        return true;
    }
}
