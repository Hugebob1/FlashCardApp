package com.example.flashcard;

public class Question {
    private String question;
    private String answer;
    Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}
