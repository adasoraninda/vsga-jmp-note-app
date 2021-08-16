package com.adasoranina.aplikasicatatan.ui.home;

import android.content.Context;

import androidx.annotation.StringRes;

import com.adasoranina.aplikasicatatan.model.Note;

import java.util.List;

public interface HomeContract {

    interface View {
        void showLoading();

        void getNotes(List<Note> allNotes);

        void dismissLoading();

        void showMessage(String message);

        Context getContext();
    }

    interface Presenter {
        void getNotes();

        int deleteNote(Note note);

        boolean deleteDirectory();
    }

}
