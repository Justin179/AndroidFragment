package com.delta.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements HeadlinesFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.container)!=null){

            // if were being restored from a previous state, then don't do anything
            if(savedInstanceState!=null){
                return;
            }

            HeadlinesFragment headlinesFragment = new HeadlinesFragment();

            // this activity was started with special instructions from an intent,
            // pass the intent's extras to the fragment as arguments
            headlinesFragment.setArguments(getIntent().getExtras());

            // ask the fragment manager to add it to the framelayout
            getFragmentManager().beginTransaction().add(R.id.container,headlinesFragment).commit();

        }

    }

    @Override
    public void onArticleSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);
        if(articleFragment!=null){
            // we must be in two pane layout
            articleFragment.updateArticleView(position);
        } else {
            // we must be in one pane layout

            ArticleFragment swapFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION,position);
            swapFragment.setArguments(args);

            // now that the Fragment is prepared, swap it
            getFragmentManager().beginTransaction().replace(R.id.container,swapFragment)
                    .addToBackStack(null).commit();
        }
    }
}
