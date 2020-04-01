package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.models.Notes;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText titleInput;
    @NotEmpty
    private EditText contentInput;
    private Notes item;
    private int index;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        validator = new Validator(this);
        validator.setValidationListener(this);

        titleInput = findViewById(R.id.input_title);
        contentInput = findViewById(R.id.input_content);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable(MainActivity.NOTES_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            titleInput.setText(item.getTitle());
            contentInput.setText(item.getContent());
        }
    }

    public void handleSubmit(View view) {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
        String title = titleInput.getText().toString();
        String content = contentInput.getText().toString();

        item.setTitle(title);
        item.setContent(content);
        item.setWaktu(time.format(dt));
        item.setDate(date.format(dt));

        Intent intent = new Intent();
        intent.putExtra(MainActivity.NOTES_KEY, item);
        intent.putExtra(MainActivity.INDEX_KEY, index);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
