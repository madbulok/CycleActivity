package ru.geekbrains.cycleactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MySimpleFragment extends Fragment implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private int mProgressLoadingAnyTask = 0;
    private TextView tvFragment;
    private Button btnFragment;

    public MySimpleFragment() {
    }

    private void makeMessage(String message){
        if (getContext() !=null){
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        makeMessage("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeMessage("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        makeMessage("onCreateView");
        View v = inflater.inflate(R.layout.fragment_layout, null);
        tvFragment = (TextView) v.findViewById(R.id.textView);
        btnFragment = (Button) v.findViewById(R.id.btnCounter);

        btnFragment.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        makeMessage("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        makeMessage("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        makeMessage("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        makeMessage("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        makeMessage("onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("KEY_PROGRESS", mProgressLoadingAnyTask);
        super.onSaveInstanceState(outState);
        makeMessage("onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("KEY_PROGRESS")){
            mProgressLoadingAnyTask = savedInstanceState.getInt("KEY_PROGRESS", 0);
            tvFragment.setText(getString(R.string.restored_string, mProgressLoadingAnyTask));
        }
        makeMessage("onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        makeMessage("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        makeMessage("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        makeMessage("onDetach");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCounter:
                mProgressLoadingAnyTask++;
                tvFragment.setText(String.valueOf(mProgressLoadingAnyTask));
                break;
            default:
                break;
        }
    }
}
