package com.delta.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Justin179 on 2017/2/10.
 */

public class ArticleFragment extends Fragment{

    final static String ARG_POSITION = "position";
    private int currentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // inflate the view for this fragment
        View myFragmentView = inflater.inflate(R.layout.article_fragment,container,false);
        return myFragmentView;
    }


    public void updateArticleView(int position){
        View v = getView();
        TextView article = (TextView) v.findViewById(R.id.article);
        String[] data = Ipsum.Articles;
        article.setText(data[position]);
        currentPosition = position;
    }

    @Override
    public void onStart() {
        super.onStart();
        // during startup, we should check if there are arguments(data) passed to this fragment
        Bundle args = getArguments();
        if(args!=null){
            updateArticleView(args.getInt(ARG_POSITION));
        } else if(currentPosition!=-1){
            //
             updateArticleView(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current selection for later recreation of this fragment
        outState.putInt(ARG_POSITION,currentPosition);
    }
}
