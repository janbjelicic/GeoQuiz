package com.example.jan.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CheatActivity extends ActionBarActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    public TextView mAnswerTextView, mAndroidVersionTextView;
    public Button mShowAnswerButton;

    private boolean mIsTrueAnswer;
    private boolean mIsAnswerShown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if(savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
            setAnswerShownResult(mIsAnswerShown);
        }
        mIsTrueAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        if(mIsAnswerShown) {
            mAnswerTextView.setText(Boolean.toString(mIsTrueAnswer));
        }
        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(Boolean.toString(mIsTrueAnswer));
                mIsAnswerShown = true;
                setAnswerShownResult(mIsAnswerShown);
            }
        });
        mAndroidVersionTextView = (TextView)findViewById(R.id.androidVersionTextView);
        mAndroidVersionTextView.setText("API level " + Integer.toString(Build.VERSION.SDK_INT));

    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
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
