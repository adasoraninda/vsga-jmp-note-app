package com.adasoranina.aplikasicatatan.ui.note;

import android.content.Context;

import androidx.annotation.Nullable;

import com.adasoranina.aplikasicatatan.model.Note;

public interface NoteContract {

    interface View {
        void getNote(@Nullable Note note);

        void showMessage(String message);

        Context getContext();
    }

    interface Presenter {
        boolean save(@Nullable String fileName,
                     @Nullable String description);

        void readFile(String fileName);
    }

}
