/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Saves the input received by the user in serialized form
 *
 * @author Jacob
 */
public class DataSaver {

    public static String dirName = "savedForms";
    public static String formOneName = "formular.ser";

    public static void saveForm(Formular form) throws FileNotFoundException, IOException {
        createDirIfNotExists();
        FileOutputStream fos = new FileOutputStream("." + File.separator + dirName + File.separator + formOneName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(form);
        oos.close();
        fos.close();
    }

    public static void createDirIfNotExists() {
        File tmpDir = new File("." + File.separator + dirName);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
    }
}
