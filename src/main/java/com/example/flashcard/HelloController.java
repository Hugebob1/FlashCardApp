package com.example.flashcard;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Pane cardPane;

    @FXML
    private ProgressBar barOfProgress;

    @FXML
    private Label posText;

    @FXML
    protected void onHelloButtonClick() {
        String text = welcomeText.getText();
        Boolean isFliped = false;
        long start_time = System.nanoTime();
        if (text.equals(QuestionLoader.getQuestion(pos).getAnswer())) {

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.25), welcomeText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            cardPane.setRotationAxis(Rotate.Y_AXIS);
            RotateTransition flip = new RotateTransition(Duration.seconds(0.5), cardPane);
            flip.setByAngle(180);
            flip.setCycleCount(1);
            flip.setAutoReverse(false);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.25), welcomeText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            PauseTransition halfway = new PauseTransition(Duration.seconds(0.25));
            halfway.setOnFinished(ev -> {
                welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
                fadeIn.play();
            });

            fadeOut.setOnFinished(e -> {
                flip.play();
                halfway.play();
            });

            fadeOut.play();
        }
        else{
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.25), welcomeText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            cardPane.setRotationAxis(Rotate.Y_AXIS);
            RotateTransition flip = new RotateTransition(Duration.seconds(0.5), cardPane);
            flip.setByAngle(180);
            flip.setCycleCount(1);
            flip.setAutoReverse(false);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.25), welcomeText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            PauseTransition halfway = new PauseTransition(Duration.seconds(0.25));
            halfway.setOnFinished(ev -> {
                welcomeText.setText(QuestionLoader.getQuestion(pos).getAnswer());
                fadeIn.play();
            });

            fadeOut.setOnFinished(e -> {
                flip.play();
                halfway.play();
            });

            fadeOut.play();
        }
        //welcomeText.setText(ans[pos]);
    }
    @FXML
    protected void onprevButtonClick() {
        if(pos >= 1) {
            pos-=1;
            int c = pos+1;
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), welcomeText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
            fadeIn.play();
            posText.setText("Nr. " + c);
            animateProgressBar(barOfProgress, (double) pos /(N-1), Duration.millis(650));
            barOfProgress.setProgress((double) pos /(N-1));
        }
    }

    @FXML
    protected void onnxtButtonClick() {
        if(pos < N-1) {
            pos+=1;
            int c = pos+1;
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), welcomeText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            welcomeText.setText(QuestionLoader.getQuestion(pos).getQuestion());
            fadeIn.play();
            posText.setText("Nr. " + c);
            animateProgressBar(barOfProgress, (double) pos /(N-1), Duration.millis(650));
            barOfProgress.setProgress((double) pos /(N-1));
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
        animateProgressBar(barOfProgress, 0.0, Duration.millis(650));
        barOfProgress.setProgress(0.0);
        System.out.println("restart");

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
        barOfProgress.setProgress(0.0);
    }
    @FXML
    private void flipCard() {
        // Ustawienie osi obrotu – tylko raz, przy pierwszym kliknięciu
        cardPane.setRotationAxis(Rotate.Y_AXIS);

        RotateTransition flip = new RotateTransition(Duration.seconds(0.5), cardPane);
        flip.setByAngle(180);
        flip.setCycleCount(1);
        flip.setAutoReverse(false);
        flip.play();
    }
    public void animateProgressBar(ProgressBar progressBar, double targetProgress, Duration duration) {
        double currentProgress = progressBar.getProgress();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), currentProgress)),
                new KeyFrame(duration, new KeyValue(progressBar.progressProperty(), targetProgress))
        );

        timeline.play();
    }
    @FXML
    public void switchscene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/com/example/flashcard/style.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}