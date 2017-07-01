package org.buaa.curus.io.glu;

import org.buaa.curus.io.drug.Drug;

import java.util.List;

/**
 * Created by qixiang on 6/28/17.
 */
public class DrugActionInput {

    private List<Drug> drugs;
    private List<GluValue> values;

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<GluValue> getValues() {
        return values;
    }

    public void setValues(List<GluValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "DrugActionInput{" +
                "drugs=" + drugs +
                ", values=" + values +
                '}';
    }
}
