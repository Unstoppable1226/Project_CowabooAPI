package com.example.pixa.medikit.Business;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.pixa.medikit.Data.NameWebService.*;

/**
 * Created by Pixa on 20.04.2017.
 */

public class Disease {

    private String name;
    private SymptomList symptoms;
    private TreatmentList treatments;

    public Disease(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymptomList getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(SymptomList symptoms) {this.symptoms = symptoms;}

    public TreatmentList getTreatments() {
        return treatments;
    }

    public void setTreatments(TreatmentList treatments) {
        this.treatments = treatments;
    }

    public boolean equals(Object obj) {
        return this.name.equals(((Disease)obj).getName());
    }


}
