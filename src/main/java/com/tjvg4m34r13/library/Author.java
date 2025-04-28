package com.tjvg4m34r13.library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Author {

    private String name;
    private String birthdayDate;
    private String birthCountry;
    private String databaseFormattedBirthday;
    private AuthorForm authorForm;
    private MainForm mainForm;

    public Author(String name, String birthdayDate, String birthCountry, AuthorForm authorForm, MainForm mainForm) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.birthCountry = birthCountry;
        this.authorForm = authorForm;
        this.mainForm = mainForm;
        this.databaseFormattedBirthday = ConvertBirthdayDate(this.birthdayDate);
    }
    public String ConvertBirthdayDate(String birthdayDate) {
        LocalDate date = null;
        DateTimeFormatter outputFormatter = null;
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(birthdayDate, inputFormatter);
            outputFormatter = DateTimeFormatter.ISO_DATE;
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            if(authorForm != null) {
                this.mainForm.createPopUp("Date out of pattern", authorForm);
            }
            return null;
        }
    }

    public void Register() {
        System.out.println(databaseFormattedBirthday);
        if (databaseFormattedBirthday != null){
            this.mainForm.createPopUp("Author Registered", authorForm);
        }


    }

}