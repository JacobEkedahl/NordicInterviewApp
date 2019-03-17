/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads the data from previous run, if not possible will throw an exception
 * Loads the formular into the formular object
 *
 * @author Jacob
 */
public class DataLoader {

    public static boolean hasStartedBefore() {
        File tmpDir = new File("." + File.separator + DataSaver.dirName);
        if (!tmpDir.exists()) {
            return false;
        }

        return true;
    }

    public static Formular get() throws FileNotFoundException, IOException, ClassNotFoundException {
        Formular form = null;
        FileInputStream fileIn = new FileInputStream("." + File.separator + DataSaver.dirName + File.separator + DataSaver.formOneName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        form = (Formular) in.readObject();
        in.close();
        fileIn.close();
        return form;
    }

    public static ArrayList<String> getQuestions(String filename) throws FileNotFoundException, IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(filename);

        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        ArrayList<String> questions = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            questions.add(line);
        }

        return questions;
    }
}
