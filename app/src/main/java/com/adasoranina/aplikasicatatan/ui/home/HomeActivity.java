package com.adasoranina.aplikasicatatan.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;
import com.adasoranina.aplikasicatatan.ui.note.NoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static final int REQUEST_CODE_FILE = 101;

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

        requestPermission();
    }

    @Override
    protected void onResume() {
        presenter.getNotes();
        super.onResume();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            EasyPermissions
                    .requestPermissions(new PermissionRequest.Builder(this, REQUEST_CODE_FILE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void setUpListNotes() {
        noteAdapter = new NoteAdapter(new NoteAdapter.NoteClickListener() {
            @Override
            public void onItemClick(@Nullable String fileName) {
                if (fileName != null) {
                    NoteActivity.navigate(HomeActivity.this, NoteActivity.Mode.UPDATE, fileName);
                }
            }

            @Override
            public void onLongItemClick(@Nullable Note note) {
                if (note != null) {
                    showAlertDialog(note);
                }
            }
        });

        listNotes.setAdapter(noteAdapter);
    }

    private void deleteNote(Note note) {
        int position = presenter.deleteNote(note);
        if (position >= 0) {
            noteAdapter.notifyItemRemoved(position);
        } else {
            showMessage(getString(R.string.fail_delete_note));
        }
    }

    private void showAlertDialog(Note note) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.alert_delete_notes)
                .setMessage(String.format(getString(R.string.alert_delete_notes_message), note.getFileName()))
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    deleteNote(note);
                    dialogInterface.dismiss();
                })
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getNotes(List<Note> allNotes) {
        if (allNotes.isEmpty()) {
            showMessage(getString(R.string.error_empty_note));
            return;
        }

        noteAdapter.submitList(allNotes);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}