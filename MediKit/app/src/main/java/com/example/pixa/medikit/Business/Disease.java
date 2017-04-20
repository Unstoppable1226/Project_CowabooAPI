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
    //private ArrayList<Symptom> symptom;

    public Disease(JSONObject json) throws JSONException {
        name = json.getString(OBSERVATORY);
        //symptom = arrayList;
    }
}
