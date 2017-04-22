package com.example.pixa.medikit.Presentation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.example.pixa.medikit.R;

/**
 * Created by Joel Marques on 20.04.2017.
 */

public class SearchActivity extends AppCompatActivity {

    private MultiAutoCompleteTextView multiAc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_diseases);
        multiAc = (MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView);
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, );

    }

}
