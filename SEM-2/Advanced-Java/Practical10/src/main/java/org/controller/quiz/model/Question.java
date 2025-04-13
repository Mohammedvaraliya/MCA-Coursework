package org.controller.quiz.model;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<String> options;
    private int correctAnswerIndex;

    public Question() {
    }

    public Question(int id, String text, List<String> options, int correctAnswerIndex) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int selectedOption) {
        return selectedOption == correctAnswerIndex;
    }
}