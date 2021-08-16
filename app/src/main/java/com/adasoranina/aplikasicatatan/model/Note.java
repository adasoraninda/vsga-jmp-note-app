package com.adasoranina.aplikasicatatan.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Note {
    private String fileName;
    private Date date;
    private String description;

    public Note(String fileName, String description) {
        this.fileName = formatFileName(fileName);
        this.description = description;
    }

    public Note(String fileName, Date date, String description) {
        this.fileName = formatFileName(fileName);
        this.date = date;
        this.description = description;
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

    private String formatFileName(String fileName) {
        if (fileName.contains(" ")) {
            return fileName.replace(' ', '_');
        }

        return fileName;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return formatDate.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(fileName, note.fileName) && Objects.equals(date, note.date) && Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, date, description);
    }
}
