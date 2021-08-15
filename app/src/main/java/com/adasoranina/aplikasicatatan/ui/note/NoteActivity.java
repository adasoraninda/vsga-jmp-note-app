package com.adasoranina.aplikasicatatan.ui.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;
import com.google.android.material.textfield.TextInputLayout;

public class NoteActivity extends AppCompatActivity {

    private static final String KEY_MODE = "MODE";
    private static final String KEY_NOTE = "NOTE";

    private TextInputLayout inputFileNameLayout;
    private TextInputLayout inputDescLayout;
    private Button buttonAddUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        inputFileNameLayout = findViewById(R.id.input_layout_file_name);
        inputDescLayout = findViewById(R.id.input_layout_desc);
        buttonAddUpdate = findViewById(R.id.button_add_update_note);

        setUpActionBar();
        setUpButton();

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
            showAlertDialog(R.string.save,
                    getNoteMode() ?
                            R.string.alert_save_Message :
                            R.string.alert_update_Message);
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

    public static void navigate(Context context, Mode mode, @Nullable Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(KEY_MODE, mode);
        intent.putExtra(KEY_NOTE, note);

        context.startActivity(intent);
    }

    private void showAlertDialog(
            @StringRes int titleRes,
            @StringRes int messageRes) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    finish();
                    dialogInterface.dismiss();
                })
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(R.string.alert_exit_title, R.string.alert_exit_Message);
    }

    public enum Mode {
        ADD, UPDATE;
    }

}