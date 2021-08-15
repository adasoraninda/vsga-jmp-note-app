package com.adasoranina.aplikasicatatan.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Note implements Serializable {
    private String fileName;
    private Date date;
    private String description;
    private boolean isChecked;

    public Note(String fileName, Date date, String description) {
        this.fileName = fileName;
        this.date = date;
        this.description = description;
        this.isChecked = false;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDate() {
        return formatDate(date);
    }

    public String getDescription() {
        return this.description;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    private String formatFileName(String fileName) {
        if (fileName.contains(" ")) {
            return fileName.replace(' ', '_');
        }

        return fileName;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        return formatDate.format(date);
    }


}
