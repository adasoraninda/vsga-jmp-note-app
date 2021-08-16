package com.adasoranina.aplikasicatatan.model;

import android.content.Context;
import android.os.Environment;

import com.adasoranina.aplikasicatatan.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// location storage/self/primary/Android/data/packageName/files/catatan
public class ExternalFile {

    private static final String DIR = "/catatan";
    private final Context context;

    public ExternalFile(Context context) {
        this.context = context;
    }

    private String getPath() {
        return context.getExternalFilesDir(null) + DIR;
    }

    public List<Note> getListFiles() throws Exception {
        File directory = new File(getPath());

        if (!directory.exists()) {
            throw new Exception(context.getString(R.string.folder_not_found));
        }

        File[] files = directory.listFiles();

        if (files == null) {
            throw new Exception(context.getString(R.string.file_not_found));
        }

        List<Note> notes = new ArrayList<>();

        for (File file : files) {
            String fileName = file.getName();
            Date lastModDate = new Date(file.lastModified());
            String description = readDescFile(fileName);

            notes.add(new Note(fileName, lastModDate, description));
        }

        return notes;
    }

    public String readDescFile(String fileName) throws Exception {
        File file = new File(getPath(), fileName);

        if (!file.exists()) {
            throw new Exception(context.getString(R.string.file_not_found));
        }

        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }

        return text.toString();
    }

    public void updateFile(String fileName, String description) throws Exception {
        if (!checkStateMounted()) {
            throw new Exception(context.getString(R.string.file_fail_mounted));
        }

        File directory = new File(getPath());

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new Exception(context.getString(R.string.folder_not_found));
            }
        }

        File file = new File(getPath(), fileName);

        if (file.createNewFile()) {
            createFile(file, description);
            return;
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
        streamWriter.append(description);
        streamWriter.flush();
        streamWriter.close();

    }

    private void createFile(File file, String description) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(file, false);
        outputStream.write(description.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public boolean deleteFile(String fileName) {
        File file = new File(getPath(), fileName);
        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean deleteDirectory() {
        File directory = new File(getPath());
        if (directory.exists() && directory.isDirectory()) {
            String[] content = directory.list();

            if (content != null) {
                for (String c : content) {
                    new File(directory, c).delete();
                }
            }

            return directory.delete();
        }
        return false;
    }

    private boolean checkStateMounted() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
