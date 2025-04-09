package com.example.flashcard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    int pos = 0;

    //int current = pos+1;

    private List<Question> questions = new ArrayList<Question>();
    int N = 0;

    @FXML
    private Label welcomeText;

    @FXML
    private Label posText;

    @FXML
    protected void onHelloButtonClick() {
        String text = welcomeText.getText();
        if(text.equals(QuestionLoader.getQuestion(pos).getAnswer())) {
            welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
        }
        else{
            welcomeText.setText(QuestionLoader.getQuestion(pos).getAnswer());
        }
        //welcomeText.setText(ans[pos]);
    }
    @FXML
    protected void onprevButtonClick() {
        if(pos >= 1) {
            pos-=1;
            int c = pos+1;
            welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
            posText.setText("Nr. " + c);
        }
    }

    @FXML
    protected void onnxtButtonClick() {
        if(pos < N-1) {
            pos+=1;
            int c = pos+1;
            welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
            posText.setText("Nr. " + c);
        }
        else if(pos == N-1) {
            welcomeText.setText("Dzieki ze zagrales trzymaj tak dalej:");
        }
    }
    @FXML
    protected void restartButtonClick() {
        try {
            questions = QuestionLoader.loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
            questions = new ArrayList<>(); // lub null
        }
        pos = 0;
        welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
        posText.setText("Nr. " + (pos+1));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            questions = QuestionLoader.loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
            questions = new ArrayList<>(); // lub null
        }
        //welcomeText.setAlignment(welcomeText.CENTER);
        int c = pos+1;
        N = questions.size();
        posText.setText("Nr. " + c);
        welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
    }
}