package com.tjvg4m34r13.library;

import javax.swing.SwingUtilities;
import java.io.IOException;

public class Library {
    public static void main(String[] args) throws IOException {
        MainForm mainForm = new MainForm();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm.setVisible(true);
            }
        });
    }
}
