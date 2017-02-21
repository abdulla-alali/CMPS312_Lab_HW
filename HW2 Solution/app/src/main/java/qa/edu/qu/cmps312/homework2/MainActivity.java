package qa.edu.qu.cmps312.homework2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final static String TAG = MainActivity.class.getSimpleName();

    TextView onCreateTv, onStartTv, onResumeTv, onPauseTv, onStopTv, onRestartTv, onDestroyTv;

    int onCreateCounter = 0;
    int onStartCounter = 0;
    int onPauseCounter = 0;
    int onResumeCounter = 0;
    int onStopCounter = 0;
    int onRestartCounter = 0;
    int onDestroyCounter = 0;

    // TODO :
    // Create all the 7 Key tags you will be using for restoring the bundle values

    final static String CREATE_TAG = "Create";
    final static String START_TAG = "Start";
    final static String PAUSE_TAG = "Pause";
    final static String RESUME_TAG = "Resume";
    final static String STOP_TAG = "Stop";
    final static String RESTART_TAG = "Restart";
    final static String DESTROY_TAG = "Destroy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Logcat printout
        Log.i(TAG, "onCreate called");

        //Initialize all of the textviews
        //this gets the reference of the Text views

        onCreateTv = (TextView) findViewById(R.id.onCreateTv);
        onStartTv = (TextView) findViewById(R.id.onStartTv);
        onResumeTv = (TextView) findViewById(R.id.onResumeTv);
        onPauseTv = (TextView) findViewById(R.id.onPauseTv);
        onStopTv = (TextView) findViewById(R.id.onStopTv);
        onRestartTv = (TextView) findViewById(R.id.onRestartTv);
        onDestroyTv = (TextView) findViewById(R.id.onDestroyTv);


        //Check if the passed bundle is empty or not and restore the values accordingly
        //Hint : check if the savedInstanceState is NULL
        if(savedInstanceState == null){

        } else {
           onCreateCounter = savedInstanceState.getInt(CREATE_TAG);
            onStartCounter = savedInstanceState.getInt(START_TAG);
            onResumeCounter = savedInstanceState.getInt(RESUME_TAG);
            onPauseCounter = savedInstanceState.getInt(PAUSE_TAG);
            onStopCounter = savedInstanceState.getInt(STOP_TAG);
            onRestartCounter = savedInstanceState.getInt(RESTART_TAG);
            onDestroyCounter = savedInstanceState.getInt(DESTROY_TAG);
        }

        onCreateCounter++;
        displayCounts();

    }

    // lifecycle callback overrides

    @Override
    public void onStart() {
        super.onStart();

        //Log cat print out
        Log.i(TAG, "onStart called");

        onStartCounter++;
        displayCounts();

    }

    @Override
    public void onResume() {
        super.onResume();

        //Log cat print out
        Log.i(TAG, "onResume called");

       onResumeCounter++;
        displayCounts();

    }

    @Override
    public void onPause() {
        super.onPause();

        //Log cat print out
        Log.i(TAG, "onPause called");

       onPauseCounter++;
        displayCounts();
    }

    @Override
    public void onStop() {
        super.onStop();

        //Log cat print out
        Log.i(TAG, "onStop called");

        onStopCounter++;
        displayCounts();
    }

    @Override
    public void onRestart() {
        super.onRestart();

        //Log cat print out
        Log.i(TAG, "onRestart called");

        onRestartCounter++;
        displayCounts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Log cat print out
        Log.i(TAG, "onDestroy called");

        onDestroyCounter++;
        displayCounts();

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CREATE_TAG, onCreateCounter);
        outState.putInt(START_TAG, onStartCounter);
        outState.putInt(STOP_TAG, onStopCounter);
        outState.putInt(RESTART_TAG, onRestartCounter);
        outState.putInt(RESUME_TAG, onResumeCounter);
        outState.putInt(PAUSE_TAG, onPauseCounter);
        outState.putInt(DESTROY_TAG, onDestroyCounter);
    }



    public void launchActivityTwo(View view) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }

    // update the views for counts

    public void displayCounts() {

        onCreateTv.setText("1. No. of onCreate() Method Calls = " + onCreateCounter);
        onStartTv.setText("2. No. of onStart() Method Calls = " + onStartCounter);
        onResumeTv.setText("3. No. of onResume() Method Calls = " + onResumeCounter);
        onPauseTv.setText("4. No. of onPause() Method Calls = " + onPauseCounter);
        onRestartTv.setText("5. No. of onRestart() Method Calls = " + onRestartCounter);
        onStopTv.setText("6. No. of onStop() Method Calls = " + onStopCounter);
        onDestroyTv.setText("7. No. of onDestroy() Method Calls = " + onDestroyCounter);


    }



}
