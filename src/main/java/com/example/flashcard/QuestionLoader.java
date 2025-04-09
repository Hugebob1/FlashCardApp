package com.example.flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionLoader {
    private static List<Question> questions = new ArrayList<Question>();
    public static List<Question> loadQuestions() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/questions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":", 2); // tylko pierwsze wystąpienie ":"
                if (parts.length == 2) {
                    String prompt = parts[0].trim();
                    String answer = parts[1].trim();
                    questions.add(new Question(prompt, answer));
                } else {
                    System.out.println("Pominięto niepoprawną linię: " + line);
                }
            }
        }
        Collections.shuffle(questions);
        return questions;
    }
    public static Question getQuestion(int index){
        if (index < questions.size()) {
            return questions.get(index);
        } else {
            return null;
        }
    }
    public static void main(String[] args) {
        try {
            questions = QuestionLoader.loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
            questions = new ArrayList<>(); // lub null
        }
        for (Question question : questions) {
            System.out.println(question.getQuestion());
        }
    }
}
