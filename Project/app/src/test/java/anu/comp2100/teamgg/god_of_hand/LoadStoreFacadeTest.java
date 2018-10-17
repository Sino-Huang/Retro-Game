package anu.comp2100.teamgg.god_of_hand;

import org.junit.Test;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class LoadStoreFacadeTest {

    @Test
    public void Save() {
        File testfile = new File("./test.txt");
        try {
            LoadStoreFacade save = LoadStoreFacade.createSave(new FileOutputStream(testfile));
            save.addElement("Test", 12);
            save.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Load() {
        File testfile = new File("./test.txt");
        if (testfile.exists()) {
            try {
                LoadStoreFacade load = LoadStoreFacade.createLoad(new FileInputStream(testfile));
                Node node = load.doc.getFirstChild();
                System.out.println(node.getTextContent());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}