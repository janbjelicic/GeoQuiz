package com.example.jan.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GeoActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;

    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true),
        new TrueFalse(R.string.question_mideast, false),
        new TrueFalse(R.string.question_oceans, true)
    };
    private int mCurrentIndex = 0;

    private void updateQuestion(){
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_acitivity);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if(userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;
        Toast.makeText(GeoActivity.this,
                messageResId,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geo_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
