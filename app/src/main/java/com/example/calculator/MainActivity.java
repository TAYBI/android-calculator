package com.example.calculator;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText result;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText)findViewById(R.id.result);
        result.setShowSoftInputOnFocus(false);
    }

    void print(String str) {
        result.setText(result.getText() + str);
    }
}
