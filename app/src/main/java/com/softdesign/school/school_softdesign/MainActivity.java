package com.softdesign.school.school_softdesign;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.school_softdesign.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VISIBLE_KEY = "visible";
    private CheckBox mCheckBox;
    private EditText mEditText;
    private Toolbar mToolbar;
    private int mCurrentColor;
    private int mCurrentColorDark;


    private static final String TAG = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Lg.i(TAG, "Life cycle: onCreate()");
        Lg.d(TAG, "- app is started!");

        /*
        find our buttons by id
         */
        Button mBtnGreen = (Button) findViewById(R.id.btnGreen);
        mBtnGreen.setOnClickListener(this);
        Button mBtnRed = (Button) findViewById(R.id.btnRed);
        mBtnRed.setOnClickListener(this);
        Button mBtnBlue = (Button) findViewById(R.id.btnBlue);
        mBtnBlue.setOnClickListener(this);
        /*
        find our checkbox,edittext and toolbar by id
         */
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.editText2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Call up menu", Toast.LENGTH_LONG).show();
            Lg.d(TAG, "Button Menu was pressed.");
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        /*
        Set icon for Actionbar
         */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*
    Click processing
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.checkBox:
                if (mCheckBox.isChecked()) {
                    Lg.d(TAG, "CheckBox: enabled.");
                    Lg.d(TAG, "- mEditLastName - invisible.");
                    Toast.makeText(this, "CheckBox: Enabled", Toast.LENGTH_LONG).show();
                    mEditText.setVisibility(View.INVISIBLE);
                } else {
                    Lg.d(TAG, "CheckBox: disabled.");
                    Lg.d(TAG, "- mEditLastName - visible.");
                    Toast.makeText(this, "CheckBox: Disable", Toast.LENGTH_LONG).show();
                    mEditText.setVisibility(View.VISIBLE);
                }
                /*
                Paint toolbar and statusbar i selected colors
                 */
            case R.id.btnGreen:
                Lg.d(TAG, "Pressed button: GREEN");
                Lg.i(TAG, "Painted in green color.");
                Toast.makeText(this, "Green button is pressed", Toast.LENGTH_LONG).show();
                mToolbar.setBackgroundColor(this.getResources().getColor(R.color.green));
                getWindow().setStatusBarColor(this.getResources().getColor(R.color.dark_green));
                mCurrentColor = R.color.green;
                mCurrentColorDark = R.color.dark_green;
                break;

            case R.id.btnRed:
                Lg.d(TAG, "Pressed button: RED");
                Lg.i(TAG, "Painted in red color.");
                Toast.makeText(this, "Red button is pressed", Toast.LENGTH_LONG).show();
                mToolbar.setBackgroundColor(this.getResources().getColor(R.color.red));
                getWindow().setStatusBarColor(this.getResources().getColor(R.color.dark_red));
                mCurrentColor = R.color.red;
                mCurrentColorDark = R.color.dark_red;
                break;

            case R.id.btnBlue:
                Lg.d(TAG, "Pressed button: BLUE");
                Lg.i(TAG, "painted in blue color.");
                Toast.makeText(this, "Blue button is pressed", Toast.LENGTH_LONG).show();
                mToolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
                getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
                mCurrentColor = R.color.colorPrimary;
                mCurrentColorDark = R.color.colorPrimaryDark;
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.i(TAG, "Lifecycle: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.i(TAG, "Lifecycle: onResume()");
        Lg.d(TAG, "App working");
        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.i(TAG, "Lifecycle: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.i(TAG, "Lifecycle: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.i(TAG, "Lifecycle: onDestroy()");
    }

    /*
    Saving data for restore
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i(TAG, "Saving data.");
        outState.putBoolean(VISIBLE_KEY, mEditText.getVisibility() == View.VISIBLE);
        outState.putInt("color", mCurrentColor);
        outState.putInt("dark color", mCurrentColorDark);

    }

    /*
    Restore saved data
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.i(TAG, "Restore saved data");
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText.setVisibility(visibleState);
        mCurrentColor = savedInstanceState.getInt("color", mCurrentColor);
        mCurrentColorDark = savedInstanceState.getInt("dark color", mCurrentColorDark);
        mToolbar.setBackgroundColor((mCurrentColor));
        getWindow().setStatusBarColor((mCurrentColorDark));
    }
}