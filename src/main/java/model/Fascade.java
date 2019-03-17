/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import datalayer.FirebaseDb;
import java.io.IOException;
import java.util.ArrayList;
import interfaces.ModelListener;
import interfaces.UserActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacob
 */
public class Fascade {

    public static String[] questionForms = {"questions.txt", "secondQuestions.txt"};
    private ModelListener formListener;
    private UserActionListener actionListener;
    private static Fascade instance = null;
    private Formular form;
    private FirebaseDb db;

    public static Fascade getInstance() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (instance == null) {
            instance = new Fascade();
        }

        return instance;
    }

    public void attachForm(ModelListener listener) {
        this.formListener = listener;
    }
    
    private Fascade() throws IOException {
        initDb();
        initForm();
    }

    private void initDb() throws IOException {
        db = new FirebaseDb();
        actionListener = db;
    }

    private void initForm() throws IOException {
        if (!DataLoader.hasStartedBefore()) {
            DataSaver.createDirIfNotExists();
            form = new Formular(DataLoader.getQuestions(questionForms[0]));
            form.setNumberOfQuestionsLoaded(0);
            System.out.println("has not started before");

        } else {
            try {
                form = DataLoader.get();
                int newToLoad = form.getNumberOfQuestionsLoaded() + 1;
                System.out.println("next to load " + newToLoad);

                if (questionForms.length > newToLoad) {
                    form.addQuestions(DataLoader.getQuestions(questionForms[newToLoad]));
                    form.setNumberOfQuestionsLoaded(newToLoad);
                }
            } catch (FileNotFoundException | ClassNotFoundException ex) {
                form = new Formular(DataLoader.getQuestions(questionForms[0]));
            }
        }
        
        actionListener.addStart();
    }

    public Formular getFormular() {
        return this.form;
    }

    public void save(ArrayList<QuestionAnswer> questionAnswer) throws IOException {
        try {
            this.form.setForm(questionAnswer);
            DataSaver.saveForm(form);
            formListener.saveCompleted();
            actionListener.addSave();
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
