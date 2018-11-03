package com.example.gonza.flashcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String editQuestion = getIntent().getStringExtra("edit_question");
        String editAnswer = getIntent().getStringExtra("edit_answer");
        String editFirstChoice = getIntent().getStringExtra("edit_firstChoice");
        String editSecondChoice = getIntent().getStringExtra("edit_secondChoice");
        String editThirdChoice = getIntent().getStringExtra("edit_thirdChoice");

        ((EditText)findViewById(R.id.user_question)).setText(editQuestion);
        ((EditText)findViewById(R.id.user_answer)).setText(editAnswer);
        ((EditText)findViewById(R.id.user_answer_choice1)).setText(editFirstChoice);
        ((EditText)findViewById(R.id.user_answer_choice2)).setText(editSecondChoice);
        ((EditText)findViewById(R.id.user_answer_choice3)).setText(editThirdChoice);



        findViewById(R.id.save_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuestion = ((EditText) findViewById(R.id.user_question)).getText().toString(); //this line and the next isnt needed, I can just  put the content where the Variable is
                String userAnswer =  ((EditText) findViewById(R.id.user_answer)).getText().toString();

                String userAnswerChoice1 = ((EditText) findViewById(R.id.user_answer_choice1)).getText().toString();
                String userAnswerChoice2 = ((EditText) findViewById(R.id.user_answer_choice2)).getText().toString();
                String userAnswerChoice3 = ((EditText) findViewById(R.id.user_answer_choice3)).getText().toString();

                Intent data = new Intent(); //this intent will store our data
                data.putExtra("user's question", userQuestion); //puts the value into data, and the name of the value is user's...
                data.putExtra("user's answer", userAnswer); // same as above
                data.putExtra("user's answer choice1", userAnswerChoice1);
                data.putExtra("user's answer choice2", userAnswerChoice2);
                data.putExtra("user's answer choice3", userAnswerChoice3);


                if(userQuestion.equals("") || userAnswer.equals("") || userAnswerChoice1.equals("") || userAnswerChoice2.equals("") || userAnswerChoice3.equals("") ) //if any of the fields are left empty
                {
                    Toast.makeText(getApplicationContext(), "You must enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // goes back to original activity by closing this one
                }
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
