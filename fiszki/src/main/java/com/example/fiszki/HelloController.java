package com.example.fiszki;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    int pos = 0;
    int N = 10;
    //int current = pos+1;

    String []Flash = new String[N];
    String []ans = new String[N];

    @FXML
    private Label welcomeText;

    @FXML
    private Label posText;

    @FXML
    protected void onHelloButtonClick() {
        String text = welcomeText.getText();
        if(text.equals(ans[pos])) {
            welcomeText.setText(Flash[pos]);
        }
        else{
            welcomeText.setText(ans[pos]);
        }
        //welcomeText.setText(ans[pos]);
    }
    @FXML
    protected void onprevButtonClick() {
        if(pos >= 1) {
            pos-=1;
            int c = pos+1;
            welcomeText.setText(Flash[pos]);
            posText.setText("Nr. " + c);
        }
    }

    @FXML
    protected void onnxtButtonClick() {
        if(pos < N-1) {
            pos+=1;
            int c = pos+1;
            welcomeText.setText(Flash[pos]);
            posText.setText("Nr. " + c);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadFlash();
        //welcomeText.setAlignment(welcomeText.CENTER);
        int c = pos+1;
        posText.setText("Nr. " + c);
        welcomeText.setText(Flash[pos]);
    }

    public void loadFlash(){
        try{
            int i = 0;
            BufferedReader reader = new BufferedReader(new FileReader("fiszki.txt"));
            String s;
            while((s = reader.readLine()) != null){
                //System.out.println(s);
                String[] words = s.split("  ");
                Flash[i] = words[0];
                ans[i] = words[1];
                i+=1;
            }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}