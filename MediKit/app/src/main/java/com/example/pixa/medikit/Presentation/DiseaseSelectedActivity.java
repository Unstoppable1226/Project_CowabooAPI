package com.example.pixa.medikit.Presentation;

import android.os.Bundle;

import com.example.pixa.medikit.Business.Data;
import com.example.pixa.medikit.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Virus on 12.05.17.
 */

public class DiseaseSelectedActivity extends AppCompatActivity{

    private Data data;

    /*Variables*/
    private TextView tvTitle, tvSymptoms, tvTreatments;
    private EditText etSymptoms, etTreatments;

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
        etSymptoms = (EditText)findViewById(R.id.etSymptoms);
        etTreatments = (EditText)findViewById(R.id.etTreatments);
    }

    private void showInfo(){
        tvTitle.setText(data.getActualDisease().getName());
        //tvSymptoms.setText(getString(R.string.symptoms));
    }

}
