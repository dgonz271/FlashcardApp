package com.example.gonza.flashcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String s1 = getIntent().getStringExtra("stringKey1"); // this string will be 'harry potter`
        String s2 = getIntent().getStringExtra("stringKey2");

        findViewById(R.id.save_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuestion = ((EditText) findViewById(R.id.user_question)).getText().toString(); //this line and the next isnt needed, I can just  put the content where the Variable is
                String userAnswer =  ((EditText) findViewById(R.id.user_answer)).getText().toString();
                Intent data = new Intent(); //this intent will store our data
                data.putExtra("user's question", userQuestion); //puts the value into data, and the name of the value is user's...
                data.putExtra("user's answer", userAnswer); // same as above
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // goes back to original activity by closing this one
            }
        });

        findViewById(R.id.cancel_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //dismisses this card activity and returns to mainActivity
            }
        });

    }
}
