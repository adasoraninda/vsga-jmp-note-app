package com.adasoranina.aplikasicatatan.ui.home;

import androidx.annotation.StringRes;

import com.adasoranina.aplikasicatatan.model.Note;

import java.util.List;

public interface HomeContract {

    interface View {
        void showLoading();

        void getNotes(List<Note> allNotes);

        void dismissLoading();

        void showMessage(@StringRes int resMessage);
    }

    interface Presenter {
        void checkNote(Note note);

        void getNotes();
    }

}
