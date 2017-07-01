package org.buaa.curus.io.glu;

/**
 * Created by qixiang on 6/28/17.
 */
public class GluValue {

    private int time;
    private double value;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GluValue{" +
                "time=" + time +
                ", value=" + value +
                '}';
    }
}
