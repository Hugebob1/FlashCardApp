package com.example.flashcard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class refactorc implements Initializable {
    @FXML private TextField textField;
    @FXML private TextArea textArea;
    @FXML private Label label;
    public String folder_path = "src/main/resources";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            String x = Filelister.readFromFileToString(folder_path, FileNameXD.pickedset);
            textField.setText(FileNameXD.pickedset);
            textArea.setText(x);
            label.setText("");
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @FXML
    private void handleAddClick(ActionEvent event) throws IOException {
        String name = textField.getText();
        String all = textArea.getText();
        if(all!=null && name!=null) {
            if(check(all)){
                Filelister.writeToFile(folder_path, name, all);
                label.setText("Saved");
            }
            else{
                label.setText("Invalid Format");
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
