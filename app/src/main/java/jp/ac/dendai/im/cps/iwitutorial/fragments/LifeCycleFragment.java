package jp.ac.dendai.im.cps.iwitutorial.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.dendai.im.cps.iwitutorial.R;

public class LifeCycleFragment extends Fragment {
    private static final String TAG = LifeCycleFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public LifeCycleFragment() {
        // Required empty public constructor
    }

    public static LifeCycleFragment newInstance(String param1, String param2) {
        LifeCycleFragment fragment = new LifeCycleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        mListener.onFragmentLogMessage(TAG, "onAttach() called with: " + "context = [" + context + "]");
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener.onFragmentLogMessage(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        mListener.onFragmentLogMessage(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");

        TextView param1Text = (TextView) v.findViewById(R.id.param_1);
        TextView param2Text = (TextView) v.findViewById(R.id.param_2);

        param1Text.setText(mParam1);
        param2Text.setText(mParam2);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.onFragmentLogMessage(TAG, "onActivityCreated() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onStart() {
        super.onStart();
        mListener.onFragmentLogMessage(TAG, "onStart() called with: " + "");
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentLogMessage(TAG, "onResume() called with: " + "");
    }

    @Override
    public void onPause() {
        super.onPause();
        mListener.onFragmentLogMessage(TAG, "onPause() called with: " + "");
    }

    @Override
    public void onStop() {
        super.onStop();
        mListener.onFragmentLogMessage(TAG, "onStop() called with: " + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener.onFragmentLogMessage(TAG, "onDestroyView() called with: " + "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener.onFragmentLogMessage(TAG, "onDestroy() called with: " + "");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onFragmentLogMessage(TAG, "onDetach() called with: " + "");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentLogMessage(String tag, String message);
    }
}
