package com.adasoranina.aplikasicatatan.ui.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;
import com.google.android.material.textfield.TextInputLayout;

public class NoteActivity extends AppCompatActivity implements NoteContract.View {

    private static final String KEY_MODE = "MODE";
    private static final String KEY_FILE_NAME = "FILE_NAME";

    private TextInputLayout inputFileNameLayout;
    private TextInputLayout inputDescLayout;
    private Button buttonAddUpdate;

    private NoteContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        inputFileNameLayout = findViewById(R.id.input_layout_file_name);
        inputDescLayout = findViewById(R.id.input_layout_desc);
        buttonAddUpdate = findViewById(R.id.button_add_update_note);

        setUpActionBar();
        setUpButton();
        setUpInput();

        presenter = new NotePresenter(this);

        initData();
    }

    private void initData() {
        String fileName = getIntent().getStringExtra(KEY_FILE_NAME);
        if (fileName != null) {
            presenter.readFile(fileName);
        }
    }

    private void setUpInput() {
        if (inputFileNameLayout.getEditText() != null) {
            inputFileNameLayout.getEditText().setEnabled(getNoteMode());
        }
    }

    private void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getNoteMode() ? R.string.add_note_title : R.string.update_note_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpButton() {
        buttonAddUpdate.setText(R.string.save);
        buttonAddUpdate.setOnClickListener(v -> {
            if (inputFileNameLayout.getEditText() != null && inputDescLayout.getEditText() != null) {
                String fileName = inputFileNameLayout.getEditText().getText().toString().trim();
                String description = inputDescLayout.getEditText().getText().toString().trim();

                showAlertDialog(
                        R.string.save,
                        getNoteMode() ?
                                R.string.alert_save_Message :
                                R.string.alert_update_Message,
                        fileName, description);
            }
        });
    }

    private boolean getNoteMode() {
        Mode mode = (Mode) getIntent().getSerializableExtra(KEY_MODE);
        return mode == Mode.ADD;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Context context, Mode mode, @Nullable String fileName) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(KEY_MODE, mode);
        intent.putExtra(KEY_FILE_NAME, fileName);

        context.startActivity(intent);
    }

    private void showAlertDialog(
            @StringRes int titleRes,
            @StringRes int messageRes,
            @Nullable String fileName,
            @Nullable String description) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    if (fileName != null) {
                        if (presenter.save(fileName, description)) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    } else {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(R.string.alert_exit_title, R.string.alert_exit_Message, null, null);
    }

    public enum Mode {
        ADD, UPDATE;
    }

    @Override
    public void getNote(@Nullable Note note) {
        if (note != null) {
            if (inputFileNameLayout.getEditText() != null && inputDescLayout.getEditText() != null) {
                inputFileNameLayout.getEditText().setText(note.getFileName());
                inputDescLayout.getEditText().setText(note.getDescription());
            }
        }
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