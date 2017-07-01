package org.buaa.curus.io.glu;

import org.buaa.curus.io.drug.DrugAdjust;

import java.util.List;

/**
 * Created by qixiang on 6/28/17.
 */
public class DrugActionOutput {

    private List<DrugAdjust> adjusts;

    public List<DrugAdjust> getAdjusts() {
        return adjusts;
    }

    public void setAdjusts(List<DrugAdjust> adjusts) {
        this.adjusts = adjusts;
    }

    @Override
    public String toString() {
        return "DrugActionOutput{" +
                "adjusts=" + adjusts +
                '}';
    }
}
