package com.tjvg4m34r13.library;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Author {

    private String name;
    private String birthdayDate;
    private String birthCountry;
    private String rawdesc;
    private String convertedDesc;
    private String rawdocument;
    private String convertedDocument;
    private String databaseFormattedBirthday;
    private AuthorForm authorForm;
    private MainForm mainForm;

    public Author(String name, String birthdayDate, String birthCountry, String rawdesc, String rawdocument,AuthorForm authorForm, MainForm mainForm) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.birthCountry = birthCountry;
        this.rawdesc = rawdesc;
        this.rawdocument = rawdocument;
        this.authorForm = authorForm;
        this.mainForm = mainForm;
        this.databaseFormattedBirthday = ConvertBirthdayDate(this.birthdayDate);
        this.convertedDesc = escapeNewlinesForJson(rawdesc);
        this.convertedDocument = removeDotsAndDash(rawdocument);
    }

    public static String removeDotsAndDash(String text){
        return text.replace(".", "").replace("-","");
    }

    public static String escapeNewlinesForJson(String text) {
        // Replace both \r\n (Windows) and \n (Unix) with \\n
        return text.replace("\r\n", "\\n").replace("\n", "\\n").replace("\u00a0", " ");
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

    String json(){
        String data = "{"
                        +"\"name\":\"" + this.name +"\","
                        +"\"birthday\":\"" + this.databaseFormattedBirthday + "\","
                        +"\"country\":\"" + this.birthCountry + "\","
                        +"\"document\":\"" + this.convertedDocument + "\","
                        +"\"desc\":\"" + this.convertedDesc + "\""
                     +"}";
        System.out.println(data);
        return data;
    }

    public void Register() throws IOException {
        if (databaseFormattedBirthday != null){
            Connection connection = new Connection("cadastroAutor", this.json());
            if (connection.success == true){
                this.mainForm.createPopUp("Author Registered", authorForm);
            } else {
                this.mainForm.createPopUp("Error. Please enter in contact with support.", authorForm);
            }


        }


    }

}