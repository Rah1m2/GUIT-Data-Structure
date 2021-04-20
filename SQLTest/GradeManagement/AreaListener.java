package GradeManagement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaListener implements DocumentListener {
    JTable jtable;
    JTextArea consoletexta;

    @Override
    public void insertUpdate(DocumentEvent e) {
//        String str = consoletexta.getText();
//        System.out.println(str);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
//        System.out.println("test3");

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String str[] = consoletexta.getText().split("\n");
        System.out.println(str);
    }

    public void getConsoleText(JTextArea ConsoleTextA){
        consoletexta = ConsoleTextA;
    }
}
