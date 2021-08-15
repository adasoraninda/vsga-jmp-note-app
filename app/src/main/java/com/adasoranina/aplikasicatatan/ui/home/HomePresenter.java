package com.adasoranina.aplikasicatatan.ui.home;

import static com.adasoranina.aplikasicatatan.ui.home.HomeContract.Presenter;
import static com.adasoranina.aplikasicatatan.ui.home.HomeContract.View;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;
import com.adasoranina.aplikasicatatan.util.NoteDummyRes;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements Presenter {

    private final View view;
    private final List<Note> allNotes = new ArrayList<>();

    public HomePresenter(View view) {
        this.view = view;
        allNotes.addAll(NoteDummyRes.getListNotes());

        getNotes();
    }

    @Override
    public void getNotes() {
        view.showLoading();

        view.getNotes(allNotes);

        if (allNotes.isEmpty()) {
            view.showMessage(R.string.error_empty_note);
        }

        view.dismissLoading();
    }

    @Override
    public void checkNote(Note note) {
        int index = allNotes.indexOf(note);
        note.setChecked(!note.getChecked());

        allNotes.set(index, note);

        view.getNotes(allNotes);
    }
}
