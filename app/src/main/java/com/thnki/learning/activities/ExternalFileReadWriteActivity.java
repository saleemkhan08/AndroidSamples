package com.thnki.learning.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.thnki.learning.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ExternalFileReadWriteActivity extends AppCompatActivity {

    private static final int READ_WRITE_PERMISSION_CODE = 123;
    private static final String FILE_IS_EMPTY = "File is Empty!!!";
    private EditText fileContentEdit;
    private TextView fileContentText;
    private ScrollView fileContentTextContainer;
    private Button edit;
    private Button save;
    private static final String FILE_NAME = "sampleTest.txt";
    private String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_file_read_write);
        fileContentEdit = findViewById(R.id.fileContentEdit);
        fileContentText = findViewById(R.id.fileContent);
        fileContentTextContainer = findViewById(R.id.fileContentTextContainer);
        fileContentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFile(v);
            }
        });
        edit = findViewById(R.id.editFile);
        save = findViewById(R.id.saveFile);
        Log.d(FILE_NAME, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showFileContent();
        Log.d(FILE_NAME, "onStart");
    }

    private void showFileContent() {
        try {
            fileContent = readFile();
        } catch (IOException e) {
            Log.d(FILE_NAME, e.getMessage());
        }
        fileContent = fileContent.isEmpty() ? FILE_IS_EMPTY : fileContent;
        fileContentText.setText(fileContent);
        fileContentEdit.setVisibility(View.GONE);
        Log.d(FILE_NAME, "showFileContent");
    }

    public void writeToFile() {
        fileContent = fileContentEdit.getText().toString();
        try {
            if (isExternalFileReadWritePermissionAvailable()) {
                writeToFile(fileContent);
                showFileContent();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            } else {
                requestFileReadPermission();
            }
        } catch (IOException ioe) {
            Toast.makeText(this, ioe.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Log.d(FILE_NAME, "writeToFile()");
    }

    private void writeToFile(String text) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(getFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(text);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        fileOutputStream.close();
        Log.d(FILE_NAME, "writeToFile(text)");
    }

    private String readFile() throws IOException {
        String line;
        FileInputStream fileInputStream = new FileInputStream(getFile());
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        inputStreamReader.close();
        fileInputStream.close();
        Log.d(FILE_NAME, "readFile()");
        return stringBuilder.toString();
    }

    private File getFile() throws IOException {
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        } else {
            file = new File(getFilesDir(), FILE_NAME);
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException();
            }
        }
        Log.d(FILE_NAME, "getFile");
        return file;
    }

    private boolean isExternalFileReadWritePermissionAvailable() {
        //Checking either one of WRITE_EXTERNAL_STORAGE and READ_EXTERNAL_STORAGE is enough as they are in same group
        Log.d(FILE_NAME, "isExternalFileReadWritePermissionAvailable");
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestFileReadPermission() {
        //Asking either one of WRITE_EXTERNAL_STORAGE and READ_EXTERNAL_STORAGE is enough as they are in same group
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showRationale();
        } else {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_WRITE_PERMISSION_CODE);
        }
        Log.d(FILE_NAME, "requestFileReadPermission");
    }

    private void showRationale() {
        Snackbar.make(findViewById(android.R.id.content), R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction("ENABLE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_WRITE_PERMISSION_CODE);
                    }
                }).show();
        Log.d(FILE_NAME, "showRationale");
    }

    private void requestPermission(String permission, int code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{permission}, code);
        }
        Log.d(FILE_NAME, "requestPermission");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_WRITE_PERMISSION_CODE) {
            Log.d(FILE_NAME, "onRequestPermissionsResult");
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeToFile();
                Log.d(FILE_NAME, "PERMISSION_GRANTED");
            } else {
                Toast.makeText(this, "File not saved!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void editFile(View view) {
        fileContentEdit.setVisibility(View.VISIBLE);
        fileContent = fileContentText.getText().toString();
        fileContentEdit.setText(fileContent.equals(FILE_IS_EMPTY) ? "" : fileContent);
        fileContentTextContainer.setVisibility(View.GONE);
        save.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        fileContentEdit.requestFocus();
        Log.d(FILE_NAME, "editFile");
    }

    public void saveFile(View view) {
        fileContentEdit.setVisibility(View.GONE);
        fileContent = fileContentEdit.getText().toString();
        fileContentText.setText(fileContent.equals(FILE_IS_EMPTY) ? "" : fileContent);
        fileContentTextContainer.setVisibility(View.VISIBLE);
        save.setVisibility(View.GONE);
        edit.setVisibility(View.VISIBLE);
        writeToFile();
        Log.d(FILE_NAME, "saveFile");
    }
}
