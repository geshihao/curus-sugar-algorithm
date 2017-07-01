package org.buaa.curus.algorithm.glu.impl;

/**
 * Created by qixiang on 6/29/17.
 */
public class GluLevelImpl {

    public static double Get_before(double fbg_level, double value)
    {
        // IF(D3≤0,B5,
        // IF(AND(D3>0,D3<2.8),−2,
        // IF(AND(D3≥2.8,D3<3.9),−1,
        // IF(AND(D3≥3.9,D3<4.4),−0.5,
        // IF(AND(D3≥4.4,D3<6.1),0,
        // IF(AND(D3≥6.1,D3<7),0.5,
        // IF(AND(D3≥7,D3<8.3),1,
        // IF(AND(D3≥8.3,D3<200÷18),1.5,
        // ROUNDDOWN(D3×18÷50,0)÷2))))))))
        if ( value <= 0.0 ) return fbg_level;
        if ( value <  2.8 ) return -2.0;
        if ( value <  3.9 ) return -1.0;
        if ( value <  4.4 ) return -0.5;
        if ( value <  6.1 ) return  0.0;
        if ( value <  7.0 ) return  0.5;
        if ( value <  8.3 ) return  1.0;
        if ( value < 200.0/18) return 1.5;
        return Math.floor(value * 18 / 50) / 2;
    }

    public static double Get_2hbg(double fbg_level, double value)
    {
        // IF(AND(C3≤0,B5≥0),B5,
        // IF(AND(C3≤0,B5<0),0,
        // IF(AND(C3>0,C3<7.8),0,
        // IF(AND(C3≥7.8,C3<10),0.5,
        // IF(AND(C3≥10,C3<200÷18),0.8,
        // ROUNDDOWN(C3×18÷50,0)÷2−1)))))
        if ( value <= 0.0 ) {
            if (fbg_level >= 0.0) return fbg_level;
            else return 0.0;
        }

        if ( value < 7.8 ) return 0.0;
        if ( value < 10.0 ) return 0.5;
        if ( value < 200.0/18 ) return 0.8;
        return Math.floor(value * 18 / 50) / 2 - 1;
    }


    public static double Get_fbg_Level(double value)
    {
        // IF(B3≤0,0,IF(AND(B3>0,B3<2.8),−2,
        // IF(AND(B3≥2.8,B3<3.9),−1,
        // IF(AND(B3≥3.9,B3<4.4),−0.5,
        // IF(AND(B3≥4.4,B3<6.1),0,
        // IF(AND(B3≥6.1,B3<7),0.5,
        // IF(AND(B3≥7,B3<8.3),1,
        // IF(AND(B3≥8.3,B3<200÷18),1.5,
        // ROUNDDOWN(B3×18÷50,0)÷2))))))))
        if ( value <= 0.0 ) return 0.0;
        if ( value <  2.8 ) return -2.0;
        if ( value <  3.9 ) return -1.0;
        if ( value <  4.4 ) return -0.5;
        if ( value <  6.1 ) return  0.0;
        if ( value <  7.0 ) return  0.5;
        if ( value <  8.3 ) return  1.0;
        if ( value <  200.0/18 ) return 1.5;
        return Math.floor(value * 18 / 50) / 2;
    }

    public static double Get_2hbg_breakfast_level(double fbg_level, double value)
    {
        return Get_2hbg(fbg_level,value);
    }

    public static double Get_bg_before_lunch(double fbg_level, double value)
    {
        return Get_before(fbg_level,value);
    }

    public static double Get_2hbg_lunch(double fbg_level, double value)
    {
        return Get_2hbg(fbg_level,value);
    }


    public static double Get_bg_before_dinner(double fbg_level, double value)
    {
        return Get_before(fbg_level, value);
    }

    public static double Get_2hbg_dinner(double fbg_level, double value)
    {
        return Get_2hbg(fbg_level, value);
    }

    public static double Get_bg_before_sleep(double fbg_level, double value)
    {
        return Get_before(fbg_level, value);
    }

    public static double Get_bg_3am(double fbg_level, double value)
    {
        return Get_before(fbg_level, value);
    }
}
