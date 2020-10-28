package ru.geekbrains.cycleactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "[CycleActivity]";
    private TextView firstRunTextView;
    private MySimpleFragment mFragment;
    private boolean isShowFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI(savedInstanceState);
        makeMessage("onCreate()");
    }

    private void initUI(Bundle savedInstanceState) {
        firstRunTextView = (TextView) findViewById(R.id.textViewInfo);
        mFragment = new MySimpleFragment();
        if (savedInstanceState == null){
            firstRunTextView.setText("Первый запуск!");
        }
        else{
            firstRunTextView.setText("Повторный запуск!");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeMessage("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        boolean isAttachedFragment = saveInstanceState.getBoolean("KEY_SHOW_ATTACHED_FRAGMENT", false);
        if (!isAttachedFragment){
            showFragment();
            mFragment.onViewStateRestored(saveInstanceState);
        }
        firstRunTextView.setText("Повторный запуск!!");
        makeMessage("onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putBoolean("KEY_SHOW_ATTACHED_FRAGMENT", !mFragment.isDetached());
        if (!mFragment.isDetached()){
            mFragment.onSaveInstanceState(saveInstanceState);
        }
        makeMessage("onSaveInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeMessage("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        makeMessage("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        makeMessage("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeMessage("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeMessage("onDestroy()");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonExit:
                finish();
                break;
            case R.id.btnShowFragment:
                showFragment();
                isShowFragment = true;
                break;
            default:
                break;
        }
    }

    private void showFragment() {
        if (!isCreatedFragment("simple_fragment")){
            makeMessage("create new fragment");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.containerFragment, mFragment, "simple_fragment")
                    .commit();
        } else {
            makeMessage("show created fragment");
            Fragment simpleFragment = getSupportFragmentManager().findFragmentByTag("simple_fragment");
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(simpleFragment)
                    .commit();
        }
    }

    private boolean isCreatedFragment(String tag){
        Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
        return f != null;
    }

    private void makeMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.i(TAG, message);
    }
}