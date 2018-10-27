package com.example.gonza.flashcardapp;

import android.content.Intent;
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
            public void onClick(View v)
            {
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

        findViewById(R.id.add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("stringKey1", "harry potter");
                intent.putExtra("stringKey2", "voldemort");
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

    }


   protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 100 && resultCode == RESULT_OK) //meaning that the 100 has to match the other 100 we used we called startActivityForResult
        {
            String user_question = data.getExtras().getString("user's question");
            String user_answer = data.getExtras().getString("user's answer");

            ((TextView)findViewById(R.id.flashcard_question)).setText(user_question);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(user_answer);
        }
    }
}
