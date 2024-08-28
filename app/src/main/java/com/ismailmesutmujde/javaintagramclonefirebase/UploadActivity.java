package com.ismailmesutmujde.javaintagramclonefirebase;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ismailmesutmujde.javaintagramclonefirebase.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {

    private ActivityUploadBinding uploadBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uploadBinding = ActivityUploadBinding.inflate(getLayoutInflater());
        View uploadView = uploadBinding.getRoot();
        setContentView(uploadView);


    }

    public void uploadButtonClicked(View view) {

    }

    public void selectImageClicked(View view) {

    }

}