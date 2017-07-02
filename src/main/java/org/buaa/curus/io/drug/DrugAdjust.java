package org.buaa.curus.io.drug;

/**
 * Created by qixiang on 6/30/17.
 */
public class DrugAdjust extends Drug {

    private int adjust_type;
    private long adjust_value;

    public DrugAdjust(Drug drug, int adjust_type, long adjust_value)
    {
        super(drug);
        this.adjust_type = adjust_type;
        this.adjust_value = adjust_value;
    }

    public int getAdjust_type() {
        return adjust_type;
    }

    public void setAdjust_type(int adjust_type) {
        this.adjust_type = adjust_type;
    }

    public long getAdjust_value() {
        return adjust_value;
    }

    public void setAdjust_value(long adjust_value) {
        this.adjust_value = adjust_value;
    }

    @Override
    public String toString() {
        return "DrugAdjust{" +
                "adjust_type=" + adjust_type +
                ", adjust_value=" + adjust_value +
                '}';
    }
}
