package org.buaa.curus.io.drug;

/**
 * Created by qixiang on 6/28/17.
 */
public class Drug {

    private String name;
    private int time;
    private int type;
    private int value;

    public Drug() {
    }

    public Drug(Drug drug)
    {
        this.name = drug.name;
        this.time = drug.time;
        this.type = drug.type;
        this.value = drug.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
