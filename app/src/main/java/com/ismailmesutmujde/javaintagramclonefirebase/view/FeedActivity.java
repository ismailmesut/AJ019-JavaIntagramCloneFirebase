package com.ismailmesutmujde.javaintagramclonefirebase.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.ismailmesutmujde.javaintagramclonefirebase.R;
import com.ismailmesutmujde.javaintagramclonefirebase.databinding.ActivityFeedBinding;
import com.ismailmesutmujde.javaintagramclonefirebase.model.Post;

import java.util.ArrayList;
import java.util.Map;


public class FeedActivity extends AppCompatActivity {

    private ActivityFeedBinding feedBinding;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feedBinding = ActivityFeedBinding.inflate(getLayoutInflater());
        View feedView = feedBinding.getRoot();
        setContentView(feedView);

        postArrayList = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();
    }

    private void getData() {
        //DocumentReference documentReference = firebaseFirestore.collection("Posts").document("fkdsf");
        //CollectionReference collectionReference = firebaseFirestore.collection("Posts");
        firebaseFirestore.collection("Posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(FeedActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        // Casting
                        String userEmail = (String) data.get("useremail");
                        String comment = (String) data.get("comment");
                        String downloadUrl = (String) data.get("downloadUrl");

                        Post post = new Post(userEmail, comment, downloadUrl);
                        postArrayList.add(post);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_post) {
            // Upload Activity
            Intent intentToUpload = new Intent(FeedActivity.this, UploadActivity.class);
            startActivity(intentToUpload);
        } else if (item.getItemId() == R.id.signout) {
            // Signout
            auth.signOut();
            Intent intentToMain = new Intent(FeedActivity.this, MainActivity.class);
            startActivity(intentToMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}