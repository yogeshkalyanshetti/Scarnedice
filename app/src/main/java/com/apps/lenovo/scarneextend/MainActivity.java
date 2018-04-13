package com.apps.lenovo.scarneextend;



        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.util.Random;

//import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {
    int userScore=0,userScoreFinal=0,computerScore=0,computerScoreFinal=0, roll1 =0,roll2=0,FinalScore=100;
    Button rollbtn,holdbtn,resetbtn;
    TextView textViewUser,textViewComp,diceScore,diceScore2;
    ImageView image1,image2;

    boolean userturn=true,compturn=false;
    Handler timerHandler = new Handler();
    Toast toast;
    CharSequence winner;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollbtn=(Button) findViewById(R.id.rollBtn);
        holdbtn=(Button) findViewById(R.id.holdBtn);
        resetbtn=(Button) findViewById(R.id.resetBtn);
        textViewUser = (TextView) findViewById(R.id.textUserScore);
        textViewComp = (TextView) findViewById(R.id.textCompScore);

        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        diceScore=(TextView) findViewById(R.id.DiceS);
        diceScore2=(TextView) findViewById(R.id.DiceS2);

        rollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RollOnClick","Roll Click Entered..");

                RollDice();
                if(roll1 !=1 && roll2!=1){
                    userScore+= (roll1 + roll2);
                }
                else if(roll1==1 && roll2==1){
                    userScore=0;
                    userScoreFinal=0;
                    holdDice();
                    ComputerPlays();
                }
                else {
                    userScore=0;
                    holdDice();
                    ComputerPlays();
                }
            }
        });

        holdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("HoldOnClick","Roll Click Entered..");

                holdDice();
//                ComputerPlays();

                //rollbtn.setClickable();
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDice();
            }
        });

    }


    private void resetDice() {
        computerScore=0;
        computerScoreFinal=0;
        userScoreFinal=0;
        userScore=0;
        roll1 =0;
        roll2=0;
        textViewUser.setText("0");
        textViewComp.setText("0");

        image1.setImageResource(R.drawable.dice1);
        image2.setImageResource(R.drawable.dice2);
        DiceScoreUpdate();
        rollbtn.setEnabled(true);
        holdbtn.setEnabled(true);

    }


    private void holdDice() {
        Log.i("HoldDice","Roll Entered..");

        {
            userScoreFinal+=userScore;
            // String userScoreFinalString= String.valueOf(userScoreFinal);
            textViewUser.setText(String.valueOf(userScoreFinal));
            userScore=0;
        }
        Log.i("HoldDice","Roll Entered..");
        if(userScoreFinal>=FinalScore){
            //Winner();
            Toast.makeText(getApplicationContext(),"Congratulations You Won!",Toast.LENGTH_LONG).show();
            rollbtn.setEnabled(false);
            holdbtn.setEnabled(false);
        }
        else
            ComputerPlays();
    }

    private void RollDice() {
        Log.i("RollDice","Roll Entered..");

        //image1.setImageResource(R.drawable.xxx);

        int no1 = r.nextInt(6) + 1;
        int no2 = r.nextInt(6) + 1;

        if(no1==1) {
            //image1 = (ImageView) findViewById(R.id.imageView);
            image1.setImageResource(R.drawable.dice1);
        }
        if(no1==2) {
            image1.setImageResource(R.drawable.dice2);
        }
        if(no1==3) {
            image1.setImageResource(R.drawable.dice3);
        }
        if(no1==4) {
            image1.setImageResource(R.drawable.dice4);
        }
        if(no1==5) {
            image1.setImageResource(R.drawable.dice5);
        }
        if(no1==6) {
            image1.setImageResource(R.drawable.dice6);
        }
        if(no2==1) {
            //image1 = (ImageView) findViewById(R.id.imageView);
            image2.setImageResource(R.drawable.dice1);
        }
        if(no2==2) {
            image2.setImageResource(R.drawable.dice2);
        }
        if(no2==3) {
            image2.setImageResource(R.drawable.dice3);
        }
        if(no2==4) {
            image2.setImageResource(R.drawable.dice4);
        }
        if(no2==5) {
            image2.setImageResource(R.drawable.dice5);
        }
        if(no2==6) {
            image2.setImageResource(R.drawable.dice6);
        }
        roll1 =no1;
        roll2= no2;
        DiceScoreUpdate();
        Log.i("RollDice","Roll Finished..");

    }


    void DiceScoreUpdate(){
        diceScore.setText(String.valueOf(roll1));
        diceScore2.setText(String.valueOf(roll2));

    }

    int randomNo(){
        Random ran= new Random();
        int rand=ran.nextInt(100);
        return rand;
    }


    Runnable runnablefun=new Runnable() {
        @Override
        public void run() {
            RollDice();

            if (roll1 != 1 && roll2!=1) {
                computerScore += (roll1 + roll2);
                if(r.nextBoolean())
                    timerHandler.postDelayed(this,500);
                else {

                    computerScoreFinal += computerScore;
                    textViewComp.setText(String.valueOf(computerScoreFinal));
                    computerScore = 0;

                    if(computerScoreFinal>=FinalScore){
                        // Winner();
                        Toast.makeText(getApplicationContext(),"Computer Wins!",Toast.LENGTH_LONG).show();
                        rollbtn.setEnabled(false);
                        holdbtn.setEnabled(false);
                    }
                    else
                    {
                        rollbtn.setEnabled(true);
                        holdbtn.setEnabled(true);
                    }

                }
            }else if (roll1==1 && roll2==1){
                computerScore=0;
                computerScoreFinal=0;
                textViewComp.setText(String.valueOf(computerScoreFinal));
                rollbtn.setEnabled(true);
                holdbtn.setEnabled(true);
            }
            else {
                computerScore = 0;
                compturn = false;

                computerScoreFinal += computerScore;
                textViewComp.setText(String.valueOf(computerScoreFinal));
                rollbtn.setEnabled(true);
                holdbtn.setEnabled(true);


            }


        }
    };


    void ComputerPlays() {
        //rollbtn.setClickable(false);
        //holdbtn.setClickable(false);
        // boolean tempScoreReset = false;
        rollbtn.setEnabled(false);
        holdbtn.setEnabled(false);
        compturn = true;


        timerHandler.postDelayed(runnablefun, 500);


    }
}