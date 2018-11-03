package com.example.gonza.flashcardapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() { //when the flashcard question is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.app_background).setBackgroundColor(getResources().getColor(R.color.lightgreenComplement, null));
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() { //when the flashcard answer is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.app_background).setBackgroundColor(getResources().getColor(R.color.skyblueComplement, null));

            }
        });
        findViewById(R.id.choice1).setOnClickListener(new View.OnClickListener() { //this is the first choice is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice1).setBackground(getDrawable(R.drawable.wrong_background));
                findViewById(R.id.choice2).setBackground(getDrawable(R.drawable.answer_background));
            }
        });
        findViewById(R.id.choice2).setOnClickListener(new View.OnClickListener() { //when choice 2 is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice2).setBackground(getDrawable(R.drawable.answer_background));
            }
        });
        findViewById(R.id.choice3).setOnClickListener(new View.OnClickListener() { //when choice 3 is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.choice2).setBackground(getDrawable(R.drawable.answer_background));
                findViewById(R.id.choice3).setBackground(getDrawable(R.drawable.wrong_background));
            }
        });

        findViewById(R.id.hide_choices).setOnClickListener(new View.OnClickListener() { //when hide_choices is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.hide_choices).setVisibility(View.INVISIBLE);
                findViewById(R.id.show_choices).setVisibility(View.VISIBLE);

                findViewById(R.id.choice1).setVisibility(View.INVISIBLE);
                findViewById(R.id.choice2).setVisibility(View.INVISIBLE);
                findViewById(R.id.choice3).setVisibility(View.INVISIBLE);


            }
        });
        findViewById(R.id.show_choices).setOnClickListener(new View.OnClickListener() { //when show_choices is clicked
            @Override
            public void onClick(View v) {
                findViewById(R.id.hide_choices).setVisibility(View.VISIBLE);
                findViewById(R.id.show_choices).setVisibility(View.INVISIBLE);
                findViewById(R.id.choice1).setVisibility(View.VISIBLE);
                findViewById(R.id.choice2).setVisibility(View.VISIBLE);
                findViewById(R.id.choice3).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.Reset_Button).setOnClickListener(new View.OnClickListener() //resets to beginning state
        {
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.app_background).setBackgroundColor(getResources().getColor(R.color.skyblueComplement, null));

                findViewById(R.id.hide_choices).setVisibility(View.VISIBLE);
                findViewById(R.id.show_choices).setVisibility(View.INVISIBLE);
                findViewById(R.id.choice1).setVisibility(View.VISIBLE);
                findViewById(R.id.choice2).setVisibility(View.VISIBLE);
                findViewById(R.id.choice3).setVisibility(View.VISIBLE);

                findViewById(R.id.choice1).setBackground(getDrawable(R.drawable.question_background));
                findViewById(R.id.choice2).setBackground(getDrawable(R.drawable.question_background));
                findViewById(R.id.choice3).setBackground(getDrawable(R.drawable.question_background));

            }
        });


        //----------

        findViewById(R.id.add_card).setOnClickListener(new View.OnClickListener() { //add button takes user to addCardActivity
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.edit_card).setOnClickListener(new View.OnClickListener() { //we want this to do the same as add_card but with the values from addCardActivity
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("edit_question", user_question);
                intent.putExtra("edit_answer", user_answer);
                intent.putExtra("edit_firstChoice", firstChoice);
                intent.putExtra("edit_secondChoice", secondChoice);
                intent.putExtra("edit_thirdChoice", thirdChoice);
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });


    }
    String user_question, user_answer;
    String firstChoice, secondChoice, thirdChoice;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) //meaning that the 100 has to match the other 100 we used we called startActivityForResult
        {
            user_question = data.getExtras().getString("user's question");
            user_answer = data.getExtras().getString("user's answer");

            firstChoice = data.getExtras().getString("user's answer choice1");
            secondChoice = data.getExtras().getString("user's answer choice2");
            thirdChoice = data.getExtras().getString("user's answer choice3");


            ((TextView) findViewById(R.id.flashcard_question)).setText(user_question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(user_answer);

            ((TextView) findViewById(R.id.choice1)).setText(firstChoice);
            ((TextView) findViewById(R.id.choice2)).setText(secondChoice);
            ((TextView) findViewById(R.id.choice3)).setText(thirdChoice);

            Snackbar.make(findViewById(R.id.flashcard_answer), "Card successfully created", Snackbar.LENGTH_SHORT).show();

        }
    }
}
