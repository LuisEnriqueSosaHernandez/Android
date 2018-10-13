package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionApi {
    @SerializedName("idquestion")
    @Expose
    private int idQuestion;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;

    public QuestionApi() {

    }

    public QuestionApi(int idQuestions, String question, String answer) {
        this.idQuestion = idQuestions;
        this.question = question;
        this.answer = answer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestions) {
        this.idQuestion = idQuestions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
