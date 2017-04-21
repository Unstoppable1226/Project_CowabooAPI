package com.example.pixa.medikit.Presentation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pixa.medikit.Business.Disease;
import com.example.pixa.medikit.Business.Symptom;
import com.example.pixa.medikit.Business.Treatment;
import com.example.pixa.medikit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joel Marques on 21.04.2017.
 */

public class DataBaseActivity extends AppCompatActivity {

    private static final String[] FROM = {"icon", "nameDisease", "valueSymptoms", "valueTreatments"};
    private static final int[] TO = {R.id.img_icon, R.id.tv_disease, R.id.tv_symptoms, R.id.tv_treatments};
    private ListView lvDiseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);
        defineVariables();
        creationAdapter();
    }

    private void defineVariables() {
        lvDiseases = (ListView) findViewById(R.id.lv_disease);
    }

    private void creationAdapter() {
        List<HashMap<String, Object>> data = new ArrayList<>();
        for (Disease d: MainActivity.diseases.getDiseases()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(FROM[0], R.drawable.symptomes);
            map.put(FROM[1], d.getName());
            Symptom symp = d.getSymptoms().getSymptoms().iterator().next();
            map.put(FROM[2], "Symptômes : " + symp.getName());
            Treatment treat = d.getTreatments().getTreatments().iterator().next();
            map.put(FROM[3], "Traitements : " + treat.getName());
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.one_line, FROM, TO);
        lvDiseases.setAdapter(adapter);
    }

}
