package com.ismailmesutmujde.javaintagramclonefirebase;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ismailmesutmujde.javaintagramclonefirebase.databinding.ActivityFeedBinding;


public class FeedActivity extends AppCompatActivity {

    private ActivityFeedBinding feedBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedBinding = ActivityFeedBinding.inflate(getLayoutInflater());
        View feedView = feedBinding.getRoot();
        setContentView(feedView);

    }
}