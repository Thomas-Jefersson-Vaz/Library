package com.tjvg4m34r13.library;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Publisher {

    private PublisherForm publisherForm;
    private MainForm mainForm;
    private String name;
    private String country;
    private String foundationDate;
    public Publisher(String name, String country, String rawFoundation,PublisherForm publisherForm, MainForm mainForm){
        this.publisherForm = publisherForm;
        this.mainForm = mainForm;
        this.name = name;
        this.country = country;
        this.foundationDate = ConvertFoundationDate(rawFoundation);
    }

    private String testFields(){

        return null;
    }

    public String ConvertFoundationDate(String rawFoundation) {
        LocalDate date;
        DateTimeFormatter outputFormatter;
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(rawFoundation, inputFormatter);
            outputFormatter = DateTimeFormatter.ISO_DATE;
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            if(publisherForm != null) {
                this.mainForm.createPopUp("Date out of pattern", publisherForm);
            }
            return null;
        }
    }

    private String json() {
        String data = "{"
                +"\"name\":\"" + this.name +"\","
                +"\"birthday\":\"" + this.country + "\","
                +"\"country\":\"" + this.foundationDate + "\""
                +"}";
        System.out.println(data);
        return data;
    }


    public void Register() throws IOException {
        if (foundationDate != null){
            Connection connection = new Connection("registerPublisher", this.json());
            if (connection.success == true){
                this.mainForm.createPopUp("Author Registered", publisherForm);
            } else {
                this.mainForm.createPopUp("Error. Please enter in contact with support.", publisherForm);
            }


        }


    }


}
