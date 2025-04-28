/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author thomas.vaz
 */
public class FontLoader {
    public Font loadFont(String fontPath, int fontSize)throws Exception {
        File fontFile = new File(fontPath);
        if (!fontFile.exists()) {
            throw new IllegalArgumentException("Font file not found at: " + fontPath);
        }
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (FontFormatException e) {
            throw new FontFormatException("The font format is invalid for the file: " + fontPath);
        } catch (IOException e) {
            throw new IOException("An I/O error occurred while loading the font from: " + fontPath);
        }

    }
}
