package com.heroes.lesh.kidneys.Models.ModelsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionsApi {
    @SerializedName("questions")
    @Expose
    private ArrayList<QuestionApi> questionApis;

    public QuestionsApi() {

    }

    public QuestionsApi(ArrayList<QuestionApi> questionApis) {
        this.questionApis = questionApis;
    }

    public ArrayList<QuestionApi> getQuestionApis() {
        return questionApis;
    }

    public void setQuestionApis(ArrayList<QuestionApi> questionApis) {
        this.questionApis = questionApis;
    }
}
