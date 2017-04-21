package com.example.pixa.medikit.Business;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Joel Marques on 21.04.2017.
 */

public class DiseaseList {
    private Collection<Disease> diseases = new ArrayList<Disease>();
    public void addDisease(Disease d) {diseases.add(d);}
    public Disease getDisease(String name) {
        for ( Disease d : diseases) {
            if (d.equals(new Disease(name))) {
                return d;
            }
        }
        return null;
    }
    public Collection<Disease> getDiseases() {return diseases;}

}
