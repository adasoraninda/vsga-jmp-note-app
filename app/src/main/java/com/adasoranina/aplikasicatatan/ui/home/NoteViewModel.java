package com.adasoranina.aplikasicatatan.ui.home;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;

public class NoteViewModel {

    @Nullable
    private Note note;

    public Note getNote() {
        return this.note;
    }

    public void setNote(@Nullable Note note) {
        this.note = note;
    }

    @DrawableRes
    public int getCheckImageResource() {
        if (this.note != null) {
            return note.getChecked() ?
                    R.drawable.ic_check_box :
                    R.drawable.ic_check_box_outline_blank;
        }

        return R.drawable.ic_check_box_outline_blank;
    }

}
