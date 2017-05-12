package com.example.pixa.medikit.Application;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.example.pixa.medikit.Business.Disease;
import com.example.pixa.medikit.Business.Symptom;
import com.example.pixa.medikit.Business.Treatment;
import com.example.pixa.medikit.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pixa on 22.04.2017.
 */

public class ListDiseases {
    private static final String REF_DISEASE = "Ref Disease";
    private static final String[] FROM = {"icon", "nameDisease", "valueSymptoms", "valueTreatments"};
    private static final int[] TO = {R.id.img_icon, R.id.tv_disease, R.id.symptoms, R.id.tv_treatments};

    private List<HashMap<String, Object>> dataTout;
    private SimpleAdapter adapter;


    public ListDiseases (Context context, Collection<Disease> diseases) {
        dataTout = new ArrayList<HashMap<String, Object>>(diseases.size());
        /* Création de la liste des données */
        for (Disease d: diseases) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(FROM[0], R.drawable.symptomes);
            map.put(FROM[1], d.getName());
            Symptom symp = d.getSymptoms().getSymptoms().iterator().next();
            map.put(FROM[2], "Symptômes : " + symp.getName());
            Treatment treat = d.getTreatments().getTreatments().iterator().next();
            map.put(FROM[3], "Traitements : " + treat.getName());
            map.put(REF_DISEASE, d);
            dataTout.add(map);
        }
        adapter = new SimpleAdapter(context, dataTout, R.layout.one_line, FROM, TO);
    } // Constructeur

    public SimpleAdapter getAdapter(){
        return adapter;
    }

    public Disease getDisease(AdapterView<?> parent, int position){
        HashMap<String, Object> hm = (HashMap<String, Object>) parent.getItemAtPosition(position);
        Disease d = (Disease) hm.get(REF_DISEASE);
        return d;
    }
}
