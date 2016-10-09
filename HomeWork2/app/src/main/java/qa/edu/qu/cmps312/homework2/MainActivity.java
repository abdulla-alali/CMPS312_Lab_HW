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

        /* TODO:
            1. update the appropriate count variable
            2. update the view by calling displayCounts()
        */

        displayCounts();
    }

    // lifecycle callback overrides

    @Override
    public void onStart() {
        super.onStart();

        //Log cat print out
        Log.i(TAG, "onStart called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()

    }

    @Override
    public void onResume() {
        super.onResume();

        //Log cat print out
        Log.i(TAG, "onResume called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()
    }

    @Override
    public void onPause() {
        super.onPause();

        //Log cat print out
        Log.i(TAG, "onPause called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()
    }

    @Override
    public void onStop() {
        super.onStop();

        //Log cat print out
        Log.i(TAG, "onStop called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()
    }

    @Override
    public void onRestart() {
        super.onRestart();

        //Log cat print out
        Log.i(TAG, "onRestart called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Log cat print out
        Log.i(TAG, "onDestroy called");

        //TODO:
        //update the appropriate count variable
        //update the view by calling the displayCounts()


    }

    //TODO:
    // Override the appropriate method that you need to save the Activity values before it is distroyed
    // Hint : onSaveInstanceState
    // state information with a collection of key-value pairs
    // 7 lines of code, each for every count variable


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
