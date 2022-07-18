package com.example.braintrainer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // ini perubahan baru
    Button startButton;
    TextView resultTextView;
    TextView pointTextView;
    Button button0, button1, button2, button3, playAgainButton;
    TextView sumTextView;
    TextView timerTextView;
    ConstraintLayout constraintLayout;


    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswers;
    int score = 0;
    int numberOfQuestion = 0;

    public void playAgain(View view){

        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            }
        }.start();

    }

    public void generateQuestion(){
        Random rand = new Random(); // membuat nilai perjumlahan secara random

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        // menampilan angka random pada sumtextview
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswers = rand.nextInt(4);// inisialiasisi tempat muncul jawban angka secara randomint incore
        answers.clear();

        int incorrectAnswert;






        // membuat looping untuk menempatkan jawaban benar dan salah secara random
        /* jika urutan pertambahan int i tidak sama dengan locationcorrect answer maka akan masuk ke else
        lebih dahulu dan membentuk/mengisi index awal/index 0 dengan nilai random dari variabel answer..
        sama sperti index selanjutnya sampai i sama atau bertemu dengan locationofcorectanswer(random)*/
        for (int i=0; i<4; i++ ){
            if (i == locationOfCorrectAnswers){
                answers.add(a + b) ; // mengisi angka random benar pada index variabel answer
            } else{
                /* variabel i tidak sama dengan locationofcorrectanswer maka masuk ke else
                 dan membentuk index awal*/
                incorrectAnswert = rand.nextInt(41);
                while (incorrectAnswert == a+b ){
                    /* jika pada jawaban random salah menajdi jawaban benar maka dirubah
                      lagi menjadi jawaban random yang salah */
                    incorrectAnswert = rand.nextInt(41);
                }
                answers.add(incorrectAnswert); // mengisi angka random salah pada index variabel answer
            }
        }
        // menampilkan angka pada button0-4 dengan mengambil index int answer
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){
         /* gettag() Menghbungkan fungsi onclick
         chooseAnswer ke masing - masing tag */
        //
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))){
            score++;
            resultTextView.setText("Correct");
        }else{
            resultTextView.setText("Wrong!");
        }

        numberOfQuestion++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(view.INVISIBLE);
        constraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView) ;
        int incorrectAnswert;
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView4);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);





    }
}