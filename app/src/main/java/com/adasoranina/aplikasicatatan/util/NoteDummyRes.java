package com.adasoranina.aplikasicatatan.util;

import com.adasoranina.aplikasicatatan.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteDummyRes {

    public static List<Note> getListNotes() {
        List<Note> notes = new ArrayList<>();

        notes.add(new Note("file_name_1", new Date(), "description"));
        notes.add(new Note("file_name_2", new Date(), "description"));
        notes.add(new Note("file_name_3", new Date(), "description"));
        notes.add(new Note("file_name_4", new Date(), "description"));
        notes.add(new Note("file_name_5", new Date(), "description"));
        notes.add(new Note("file_name_6", new Date(), "description"));
        notes.add(new Note("file_name_7", new Date(), "description"));
        notes.add(new Note("file_name_8", new Date(), "description"));
        notes.add(new Note("file_name_9", new Date(), "description"));
        notes.add(new Note("file_name_10", new Date(), "description"));
        notes.add(new Note("file_name_11", new Date(), "description"));
        notes.add(new Note("file_name_12", new Date(), "description"));
        notes.add(new Note("file_name_13", new Date(), "description"));
        notes.add(new Note("file_name_14", new Date(), "description"));
        notes.add(new Note("file_name_15", new Date(), "description"));

        return notes;
    }

}
