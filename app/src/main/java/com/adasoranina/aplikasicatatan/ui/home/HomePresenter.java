package com.adasoranina.aplikasicatatan.ui.home;

import static com.adasoranina.aplikasicatatan.ui.home.HomeContract.Presenter;
import static com.adasoranina.aplikasicatatan.ui.home.HomeContract.View;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.ExternalFile;
import com.adasoranina.aplikasicatatan.model.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePresenter implements Presenter {

    private final View view;
    private final ExternalFile externalFile;
    private final List<Note> allNotes;

    public HomePresenter(View view) {
        this.view = view;
        externalFile = new ExternalFile(view.getContext());
        allNotes = new ArrayList<>();
    }

    @Override
    public void getNotes() {
        view.showLoading();

        try {
            List<Note> newNotes = externalFile.getListFiles();

            if (newNotes.isEmpty()) {
                view.showMessage(view.getContext().getString(R.string.error_empty_note));
            } else {
                this.allNotes.clear();
                allNotes.addAll(newNotes);
                view.getNotes(allNotes);
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage(e.getMessage());
        }

        view.dismissLoading();
    }

    @Override
    public int deleteNote(Note note) {
        int position = allNotes.indexOf(note);

        if (externalFile.deleteFile(note.getFileName()) && allNotes.remove(note)) {
            view.getNotes(allNotes);
            return position;
        }

        return -1;
    }

    @Override
    public boolean deleteDirectory() {
        this.allNotes.clear();
        view.getNotes(allNotes);
        return externalFile.deleteDirectory();
    }
}
