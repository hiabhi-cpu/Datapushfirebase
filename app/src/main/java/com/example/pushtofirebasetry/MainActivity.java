package com.example.pushtofirebasetry;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView randomNumberTextView;
    private Handler handler;
    private Random random;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(Looper.getMainLooper());
        random = new Random();
        randomNumberTextView=findViewById(R.id.textView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        Toast.makeText(this, "dfgf", Toast.LENGTH_SHORT).show();

//        writeNewUser("hello","fdf","dfdf");
        startRandomNumberGenerator();
    }

    private void startRandomNumberGenerator() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int randomNumber = random.nextInt(100); // Change this range as needed

                LocalDateTime currentDateTime = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = currentDateTime.format(formatter);
                    formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedtime=currentDateTime.format(formatter);
                    Log.i("hello",randomNumber+"::"+formattedDate+":::->"+formattedtime);
                    writeNewData(formattedDate,formattedtime,randomNumber+"",System.currentTimeMillis()+"");

                }



                randomNumberTextView.setText("Random Number: " + randomNumber);

                // Call the method again after 1 second
                handler.postDelayed(this, 5000);
            }
        }, 1000);
    }


    public void writeNewData(String userId,String time, String temp, String mill) {
        TimeData user = new TimeData(temp, mill); //Create a class of TimeData

        mDatabase.child(userId).child(time).setValue(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback to prevent memory leaks
        handler.removeCallbacksAndMessages(null);
    }




}