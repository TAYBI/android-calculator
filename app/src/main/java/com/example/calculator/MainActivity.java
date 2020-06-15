package com.example.calculator;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText result;
    TextView preview;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preview = (TextView)findViewById(R.id.preview);
        result = (EditText)findViewById(R.id.result);
        result.setShowSoftInputOnFocus(false);
    }

    public void preview_to_reslt(View view) {
        String string = preview.getText().toString();
        if (string.equals("NaN"))
            return;
        else {
            double f = Double.parseDouble(string);
            String formattedString = String.format("%.02f", f);
            result.setText(formattedString);
            preview.setText("");
        }
    }

    public void pos_n(View view) {
        int pos = result.getSelectionEnd();
        String num = result.getText().toString();
        if (num.contains("+") || num.contains("-") || num.contains("/") || num.contains("*") || num.contains("%")){
            result.setText(num + "(-");
            result.setSelection(pos + 2);
        }
        else if(num.equals("")){
            result.setText("(-");
            result.setSelection(pos + 2);
        }
        else {
            Double d = Double.parseDouble(num);
            result.setText(-d + "");
            result.setSelection(result.getText().toString().length());
        }
    }

    public void btnEqual(View view) {
        String string = result.getText().toString();
        Expression expression = new Expression(string);
        String result_ = String.valueOf(expression.calculate());
        preview.setText(result_);
    }

    public void  print_slash(View view){
        print("/");
    }

    public void  print_x(View view){
        print("*");
    }

    public void print_text(View view) {
        Button button = (Button)view;
        print(button.getText().toString());
    }

    public void clear(View view) {
        result.setText("");
    }

    public void delete_last_num(View view) {
        int pos = result.getSelectionEnd();
        String text = result.getText().toString();
        if(text.equals("") || pos == 0 )
            return;
        String str1 =  text.substring(0, pos - 1);
        String str2 =  text.substring(pos, (int) text.length());
        result.setText(str1 + str2);
        result.setSelection(pos -1);
    }

    void print(String str) {
        int pos = result.getSelectionEnd();
        String text = result.getText().toString();

        if(btn_is_num(str)){
            if(text.length() > 15){
                Toast.makeText(this, "le nombre maximum de chiffres est de 15",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String str1 =  text.substring(0, pos);
        String str2 =  text.substring(pos, (int) text.length());

        result.setText(str1 + str + str2);
        result.setSelection(pos + 1);
    }

    boolean btn_is_num(String str){
        if(str.equals("0") || str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4") ||
                str.equals("5") || str.equals("6") || str.equals("7") || str.equals("8") || str.equals("9"))
            return true;
        else
            return false;
    }
}
