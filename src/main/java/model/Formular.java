/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class Formular implements Serializable {

    private int numberOfQuestionsLoaded;
    ArrayList<QuestionAnswer> form = new ArrayList<>();

    public Formular() {
    }

    public Formular(ArrayList<String> questions) {
        for (String question : questions) {
            form.add(new QuestionAnswer(question, ""));
        }
    }

    public void addQuestions(ArrayList<String> questions) {
        for (String q : questions) {
            form.add(new QuestionAnswer(q, ""));
        }
    }
    
    public ArrayList<String> getQuestions() {
        ArrayList<String> answer = new ArrayList<>();
        
        for (QuestionAnswer qa : form) {
            answer.add(qa.getQuestion());
        }
        
        return answer;
    }

    public int getNumberOfQuestionsLoaded() {
        return numberOfQuestionsLoaded;
    }

    public void setNumberOfQuestionsLoaded(int numberOfQuestionsLoaded) {
        this.numberOfQuestionsLoaded = numberOfQuestionsLoaded;
    }
    
    

    public ArrayList<QuestionAnswer> getForm() {
        return (ArrayList<QuestionAnswer>) form.clone();
    }

    public void setForm(ArrayList<QuestionAnswer> form) {
        this.form = form;
    }
}
