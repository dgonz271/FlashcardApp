package com.example.gonza.flashcardapp;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    int randomIndex = 0;
    Flashcard cardToEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());

            ((TextView) findViewById(R.id.choice1)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.choice2)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.choice3)).setText(allFlashcards.get(0).getWrongAnswer2());

        }


        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() { //when the flashcard question is clicked
            @Override
            public void onClick(View v) {

                View answerSideView = findViewById(R.id.flashcard_answer);
                View questionSideView = findViewById(R.id.flashcard_question);

                int centerx = answerSideView.getWidth() / 2;
                int centery = answerSideView.getHeight() / 2;

                float finalRadius = (float) Math.hypot(centerx, centery);

                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, centerx, centery, 0f, finalRadius);

                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(500);
                anim.start();

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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
                MainActivity.this.startActivityForResult(intent, 200); //using a new requestcode to distinguish from adding a card

                overridePendingTransition(R.anim.right_in, R.anim.left_out);

                for (int i = 0; i < allFlashcards.size(); i++) {
                    if (((TextView) findViewById(R.id.flashcard_question)).getText().equals(allFlashcards.get(i).getQuestion())) {
                        cardToEdit = allFlashcards.get(i);
                        break;
                    }
                }
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlashcards = flashcardDatabase.getAllCards();
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                if (allFlashcards.size() > 1) {//to show our next card
                    randomIndex = getRandomNumber(0, allFlashcards.size() - 1);

                    findViewById(R.id.flashcard_question).startAnimation(leftOutAnim); //then runs the animation listener

                    leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            leftOutAnim.setDuration(500);
                            leftOutAnim.start();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    rightInAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            rightInAnim.setDuration(500);
                            rightInAnim.start();

                            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(randomIndex).getQuestion());
                            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(randomIndex).getAnswer());
                            ((TextView) findViewById(R.id.choice1)).setText(allFlashcards.get(randomIndex).getWrongAnswer1());
                            ((TextView) findViewById(R.id.choice2)).setText(allFlashcards.get(randomIndex).getAnswer());
                            ((TextView) findViewById(R.id.choice3)).setText(allFlashcards.get(randomIndex).getWrongAnswer2());
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });


                } else
                    Snackbar.make(findViewById(R.id.flashcard_answer), "No cards ahead", Snackbar.LENGTH_SHORT).show();


            }
        });

        findViewById(R.id.delete_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());

                allFlashcards = flashcardDatabase.getAllCards();


                if (allFlashcards.size() < 1) {
                    //currentCardDisplayedIndex = 0;
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Oops! There are no more questions... Feel free to add more");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("Oops! There are no more answers... Feel free to add more");

                    ((TextView) findViewById(R.id.choice1)).setText("--No Answer--");
                    ((TextView) findViewById(R.id.choice2)).setText("--No Answer--");
                    ((TextView) findViewById(R.id.choice3)).setText("--No Answer--");

                    Snackbar.make(findViewById(R.id.flashcard_answer), "No cards to delete", Snackbar.LENGTH_SHORT).show();

                } else {
                    //currentCardDisplayedIndex--;
                    randomIndex = getRandomNumber(0, allFlashcards.size() - 1);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(randomIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(randomIndex).getAnswer());

                    ((TextView) findViewById(R.id.choice1)).setText(allFlashcards.get(randomIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.choice2)).setText(allFlashcards.get(randomIndex).getAnswer());
                    ((TextView) findViewById(R.id.choice3)).setText(allFlashcards.get(randomIndex).getWrongAnswer2());


                }


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

            flashcardDatabase.insertCard(new Flashcard(user_question, user_answer, firstChoice, thirdChoice));


            Snackbar.make(findViewById(R.id.flashcard_answer), "Card successfully created", Snackbar.LENGTH_SHORT).show();

        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            cardToEdit.setQuestion(user_question);
            cardToEdit.setAnswer(user_answer);
            cardToEdit.setWrongAnswer1(firstChoice);
            cardToEdit.setWrongAnswer2(thirdChoice);

            flashcardDatabase.updateCard(cardToEdit);
        }
    }


    // returns a random number between minNumber and maxNumber, inclusive.
// for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
}
