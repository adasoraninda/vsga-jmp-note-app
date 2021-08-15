package com.adasoranina.aplikasicatatan.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;
import com.adasoranina.aplikasicatatan.ui.note.NoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private ProgressBar progressBar;
    private RecyclerView listNotes;

    private NoteAdapter noteAdapter;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);
        progressBar = findViewById(R.id.progress_bar_home);
        listNotes = findViewById(R.id.list_notes);

        setUpListNotes();

        presenter = new HomePresenter(this);

        buttonAdd.setOnClickListener(v -> NoteActivity.navigate(this, NoteActivity.Mode.ADD, null));
    }

    private void setUpListNotes() {
        noteAdapter = new NoteAdapter(new NoteAdapter.NoteClickListener() {
            @Override
            public void onItemClick(Note note) {
                if (note != null) {
                    NoteActivity.navigate(HomeActivity.this, NoteActivity.Mode.UPDATE, note);
                }
            }

            @Override
            public void onCheckClick(Note note) {
                if (note != null) {
                    presenter.checkNote(note);
                }
            }
        });

        listNotes.setAdapter(noteAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void getNotes(List<Note> allNotes) {
        noteAdapter.setListNotes(allNotes);
    }

    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(@StringRes int resMessage) {
        Toast.makeText(this, getString(resMessage), Toast.LENGTH_SHORT).show();
    }
}