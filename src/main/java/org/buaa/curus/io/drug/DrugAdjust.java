package org.buaa.curus.io.drug;

/**
 * Created by qixiang on 6/30/17.
 */
public class DrugAdjust extends Drug {

    private int adjust_type;
    private long adjust_value;
    private long adjust_final_value;
    private String adjust_suggestion;

    public DrugAdjust(Drug drug, int adjust_type, long adjust_value, long adjust_final_value, String adjust_suggestion)
    {
        super(drug);
        this.adjust_type = adjust_type;
        this.adjust_value = adjust_value;
        this.adjust_final_value = adjust_final_value;
        this.adjust_suggestion = adjust_suggestion;
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

    public long getAdjust_final_value() {
        return adjust_final_value;
    }

    public void setAdjust_final_value(long adjust_final_value) {
        this.adjust_final_value = adjust_final_value;
    }

    public String getAdjust_suggestion() {
        return adjust_suggestion;
    }

    public void setAdjust_suggestion(String adjust_suggestion) {
        this.adjust_suggestion = adjust_suggestion;
    }

    @Override
    public String toString() {
        return "DrugAdjust{" +
                "adjust_type=" + adjust_type +
                ", adjust_value=" + adjust_value +
                ", adjust_final_value=" + adjust_final_value +
                ", adjust_suggestion='" + adjust_suggestion + '\'' +
                '}';
    }
}
