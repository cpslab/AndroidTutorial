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

    private static final String ARG_SUBTITLE = "sub_title";
    private static final String ARG_MESSAGE = "message";

    private String subTitle;
    private String message;

    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public LifeCycleFragment() {
        // Required empty public constructor
    }

    public static LifeCycleFragment newInstance(String subTitle, String message) {
        LifeCycleFragment fragment = new LifeCycleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SUBTITLE, subTitle);
        args.putString(ARG_MESSAGE, message);
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

        mListener.onFragmentLogMessage(TAG, "onAttach()");
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener.onFragmentLogMessage(TAG, "onCreate()");

        if (getArguments() != null) {
            subTitle = getArguments().getString(ARG_SUBTITLE);
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        mListener.onFragmentLogMessage(TAG, "onCreateView()");

        TextView subTitleTextView = (TextView) v.findViewById(R.id.sub_title);
        TextView messageTextView = (TextView) v.findViewById(R.id.message);

        subTitleTextView.setText(subTitle);
        messageTextView.setText(message);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.onFragmentLogMessage(TAG, "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        mListener.onFragmentLogMessage(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentLogMessage(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        mListener.onFragmentLogMessage(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        mListener.onFragmentLogMessage(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener.onFragmentLogMessage(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener.onFragmentLogMessage(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onFragmentLogMessage(TAG, "onDetach()");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentLogMessage(String tag, String message);
    }
}
