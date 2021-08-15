package com.adasoranina.aplikasicatatan.util;

import com.adasoranina.aplikasicatatan.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteDummyRes {

    public static List<Note> getListNotes() {
        List<Note> notes = new ArrayList<>();

        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));
        notes.add(new Note("file_name", new Date(), "description"));

        return notes;
    }

}
