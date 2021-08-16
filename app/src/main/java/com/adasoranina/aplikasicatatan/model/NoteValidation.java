package com.adasoranina.aplikasicatatan.model;

public class NoteValidation {

    public static boolean validateFileName(String fileName) {
        return fileName != null && !fileName.isEmpty();
    }

    public static boolean validateFileDesc(String description) {
        return description != null && !description.isEmpty();
    }

}
