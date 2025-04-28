package com.tjvg4m34r13.library;

import javax.swing.SwingUtilities;

public class Library {
    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm.setVisible(true);
            }
        });
    }
}
