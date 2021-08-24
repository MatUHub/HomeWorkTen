package com.example.homeworkten;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Navigation navigation;

    public Publisher publisher = new Publisher();

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        navigation = new Navigation(getSupportFragmentManager());
        if (savedInstanceState == null) {
            navigation.addFragment(com.example.homeworkten.FragmentNote.newInstance(), false);
        }
    }

    public Navigation getNavigation(){
        return navigation;
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }


}