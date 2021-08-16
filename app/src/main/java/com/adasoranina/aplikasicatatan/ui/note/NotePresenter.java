package com.adasoranina.aplikasicatatan.ui.note;

import androidx.annotation.Nullable;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.ExternalFile;
import com.adasoranina.aplikasicatatan.model.Note;
import com.adasoranina.aplikasicatatan.model.NoteValidation;

public class NotePresenter implements NoteContract.Presenter {

    private final NoteContract.View view;
    private final ExternalFile externalFile;

    public NotePresenter(NoteContract.View view) {
        this.view = view;
        this.externalFile = new ExternalFile(view.getContext());
    }

    @Override
    public boolean save(@Nullable String fileName,
                        @Nullable String description) {

        if (!NoteValidation.validateFileName(fileName)) {
            view.showMessage(view.getContext().getString(R.string.file_name_invalid));
            return false;
        }

        if (!NoteValidation.validateFileDesc(description)) {
            view.showMessage(view.getContext().getString(R.string.file_description_invalid));
            return false;
        }

        try {
            externalFile.updateFile(fileName, description);

            view.showMessage(view.getContext().getString(R.string.sucess_add_note));

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            view.showMessage(e.getMessage());
            return false;
        }
    }

    @Override
    public void readFile(String fileName) {
        try {
            String description = externalFile.readDescFile(fileName);

            view.getNote(new Note(fileName, description));
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage(view.getContext().getString(R.string.fail_read_note));
        }
    }
}
