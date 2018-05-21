package com.thnki.learning.activities;

import android.os.Bundle;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InternalFileReadWriteActivity extends AppCompatActivity {

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
        setContentView(R.layout.internal_file_read_write);
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
            writeToFile(fileContent);
            showFileContent();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException ioe) {
            Toast.makeText(this, ioe.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Log.d(FILE_NAME, "writeToFile()");
    }

    private void writeToFile(String text) throws IOException {

        FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(text);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        fileOutputStream.close();
        Log.d(FILE_NAME, "writeToFile(text)");
    }

    private String readFile() throws IOException {
        String line;
        FileInputStream fileInputStream = openFileInput(FILE_NAME);
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
