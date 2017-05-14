package com.example.pixa.medikit.Presentation;

import android.os.Bundle;

import com.example.pixa.medikit.Business.Data;
import com.example.pixa.medikit.Business.Symptom;
import com.example.pixa.medikit.Business.Treatment;
import com.example.pixa.medikit.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collection;

/**
 * Created by Virus on 12.05.17.
 */

public class DiseaseSelectedActivity extends AppCompatActivity{

    private Data data;

    /*Variables*/
    private TextView tvTitle, tvSymptoms, tvSymptomsDet, tvTreatmentsDet, tvTreatments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);
        data = Data.getInstance();
        defineVariables();
        showInfo();
        //Si vous trouvez que les informations vous ont aidées, alors ca met des points stellar
    }

    private void defineVariables(){
        tvTitle = (TextView)findViewById(R.id.title);
        tvSymptoms = (TextView)findViewById(R.id.symptoms);
        tvTreatments = (TextView)findViewById(R.id.treatments);
        tvSymptomsDet = (TextView)findViewById(R.id.symptDet);
        tvTreatmentsDet = (TextView)findViewById(R.id.treatDet);
    }

    private void showInfo(){
        tvTitle.setText(data.getActualDisease().getName());
        tvSymptoms.setText(getString(R.string.symptoms));
        showSymptoms();
        showTreatments();
        tvTreatments.setText(getString(R.string.treatments));
    }

    private void showSymptoms(){
        Symptom symp = data.getActualDisease().getSymptoms().getSymptoms().iterator().next();
        tvSymptomsDet.setText(symp.getName());
    }

    private void showTreatments(){
        Treatment treat = data.getActualDisease().getTreatments().getTreatments().iterator().next();
        tvTreatmentsDet.setText(treat.getName());
    }
}
