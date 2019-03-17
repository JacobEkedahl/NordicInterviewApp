/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.nordicinterviewapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Fascade;
import model.QuestionAnswer;
import interfaces.ModelListener;
import interfaces.UserActionListener;
import java.io.FileNotFoundException;

/**
 *
 * @author Jacob
 */
public class FormController implements Initializable, ModelListener {

    @FXML
    private VBox scrollContainer;

    @FXML
    private Button saveBtn;

    Fascade fascade;
    List<HBox> questionAnswers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionAnswers = new ArrayList<>();
        initForm();
        initButtons();
    }
    
    private void initForm() {
        try {
            fascade = Fascade.getInstance();
            fascade.attachForm(this);
        } catch (IOException ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initButtons() {
        for (QuestionAnswer qa : fascade.getFormular().getForm()) {
            HBox box = new HBox();
            Label question = new Label(qa.getQuestion());
            TextField answer = new TextField(qa.getAnswer());
            box.setAlignment(Pos.TOP_CENTER);
            questionAnswers.add(box);

            box.getChildren().addAll(question, answer);
            scrollContainer.getChildren().add(box);

            answer.setOnAction(actionEvent -> {
                //log these events
                System.out.println("clicked textbox");
            });
        }
    }

    @FXML
    private void save(Event event) {
        ArrayList<QuestionAnswer> result = new ArrayList<>();

        for (HBox box : questionAnswers) {
            String question = "", answer = "";
            for (Node child : box.getChildren()) {

                if (child instanceof Label) {
                    question = ((Label) child).getText();
                } else if (child instanceof TextField) {
                    answer = ((TextField) child).getText();
                }

            }
            result.add(new QuestionAnswer(question, answer));
        }

        try {
            fascade.save(result);
        } catch (IOException ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            errorNotSaved(ex.getMessage());
        }
    }

    public void errorNotSaved(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Could not save");
        alert.setHeaderText(null);
        alert.setContentText("Could not save the form! \n" + message);

        alert.showAndWait();
    }

    @Override
    public void saveCompleted() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Form saved!");

        alert.showAndWait();
    }
}
