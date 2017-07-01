package org.buaa.curus.algorithm.glu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.curus.algorithm.glu.impl.GluLevelImpl;
import org.buaa.curus.io.drug.Drug;
import org.buaa.curus.io.drug.DrugAdjust;
import org.buaa.curus.io.glu.GluValue;
import org.buaa.curus.utils.cons.DrugCons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qixiang on 6/29/17.
 */
public class GluAlgorithm {

    private static Log logger = LogFactory.getLog(GluAlgorithm.class);


    static public double[] GetGluLevels(List<GluValue> values)
    {
        double[] bg_values = new double[DrugCons.I_3am+1];
        bg_values[0] = Double.NaN;

        for ( GluValue value : values ) {
            int time = value.getTime();
            if ( time >= 0 && time <= DrugCons.I_3am )
                bg_values[time] = value.getValue();
        }

        if ( Double.isNaN(bg_values[0]) ) {
            logger.warn(String.format("failure to find the fbg - %s",values));
            return null;
        }

        double[] levels = new double[DrugCons.I_3am+1];

        for ( int i = 0; i <= DrugCons.I_3am; ++ i )
            levels[i] = GetGluLevel(levels[0], i, bg_values[i]);

        return levels;
    }

    static public double GetGluLevel(double fbg_level, int time, double value)
    {
        switch ( time )
        {
            case DrugCons.I_before_breakfase: return GluLevelImpl.Get_fbg_Level(value);
            case DrugCons.I_after_breakfast: return GluLevelImpl.Get_2hbg_breakfast_level(fbg_level,value);
            case DrugCons.I_before_lunch: return GluLevelImpl.Get_bg_before_lunch(fbg_level,value);
            case DrugCons.I_after_lunch: return GluLevelImpl.Get_2hbg_lunch(fbg_level,value);
            case DrugCons.I_before_dinner: return GluLevelImpl.Get_bg_before_dinner(fbg_level, value);
            case DrugCons.I_after_dinner: return GluLevelImpl.Get_2hbg_dinner(fbg_level, value);
            case DrugCons.I_before_sleep: return GluLevelImpl.Get_bg_before_sleep(fbg_level, value);
            case DrugCons.I_3am: return GluLevelImpl.Get_bg_3am(fbg_level, value);
            default: return Double.NaN;
        }
    }

    static public List<DrugAdjust> GetDrugAdjusts(double[] levels, List<Drug> drugs){
        double min_level = GetMinGluLevel(levels,drugs);

        int[] abs_drug_adjust = GetAbsoluteDrugAdjusts(levels,min_level,drugs);

        return GetAdjustSuggestions(abs_drug_adjust,drugs);
    }

    static public double GetMinGluLevel(double[] levels, List<Drug> drugs) {

        boolean[] min_flags = new boolean[DrugCons.I_3am+1];

        min_flags[DrugCons.I_before_breakfase] = true;
        min_flags[DrugCons.I_after_breakfast] = false;
        min_flags[DrugCons.I_before_lunch] = true;
        min_flags[DrugCons.I_after_lunch] = false;
        min_flags[DrugCons.I_before_dinner] = true;
        min_flags[DrugCons.I_after_dinner] = false;
        min_flags[DrugCons.I_before_sleep] = true;
        min_flags[DrugCons.I_3am] = true;

        for ( Drug drug : drugs ) {
            if (drug.getType() == DrugCons.T_LONG)
                continue;
            else if ( drug.getType() == DrugCons.T_REGULAR ) {
                switch( drug.getTime() )
                {
                    case DrugCons.I_before_breakfase:
                    case DrugCons.I_before_lunch:
                    case DrugCons.I_before_dinner:
                        min_flags[drug.getTime()+2] = false; break;
                    default: logger.warn(String.format("error drug time:[%s]",drug));
                }
            } else logger.warn(String.format("error drug type:[%s]",drug));
        }

        double min_level = levels[0];
        for ( int i = 0 ; i < levels.length; ++ i )
        {
            if ( min_flags[i] && min_level > levels[i] )
                min_level = levels[i];
        }
        return min_level;
    }

    static public int[] GetAbsoluteDrugAdjusts(double[] levels, double min_level, List<Drug> drugs)
    {
        int[] adjust_values = new int[drugs.size()];

        for ( int i = 0; i < drugs.size(); ++ i )
        {
            Drug drug = drugs.get(i);

            switch( drug.getType() )
            {
                case DrugCons.T_LONG:
                    if ( min_level <= 0.0 ) {
                        adjust_values[i] = new Double(min_level*2).intValue();
                    } else {
                        adjust_values[i] = new Double(min_level*2-1).intValue();
                    }
                    break;
                case DrugCons.T_REGULAR:
                    switch( drug.getTime() )
                    {
                        case DrugCons.I_before_breakfase:
                            adjust_values[i] = new Double( 2 * (levels[DrugCons.I_before_lunch] - min_level)).intValue();
                            break;
                        case DrugCons.I_before_lunch:
                            adjust_values[i] = new Double( 2 * (levels[DrugCons.I_before_dinner] - min_level)).intValue();
                            break;
                        case DrugCons.I_before_dinner:
                            adjust_values[i] = new Double( 2 * (levels[DrugCons.I_before_sleep] - min_level)).intValue();
                            break;
                        default:
                            logger.warn(String.format("error drug time:[%s]", drug));
                    }
                default:
                    logger.warn(String.format("error drug type:[%s]",drug));
            }
        }

        return adjust_values;
    }

    static public List<DrugAdjust> GetAdjustSuggestions(int[] abs_adjust_values, List<Drug> drugs)
    {
        List<DrugAdjust> drugAdjusts = new ArrayList<DrugAdjust>();

        for ( int i = 0; i < abs_adjust_values.length; ++ i )
        {
            Drug drug = drugs.get(i);
            if ( drug.getValue() == 0 )
            {
                if ( abs_adjust_values[i] >= 3 ) drugAdjusts.add(new DrugAdjust(drug,DrugCons.T_INCREMENT, abs_adjust_values[i]));
                else  drugAdjusts.add(new DrugAdjust(drug, DrugCons.T_FIXED, /*abs_adjust_value*/ 0));
            } else if ( drug.getValue() + abs_adjust_values[i] <= 1 )
                drugAdjusts.add(new DrugAdjust(drug,DrugCons.T_STOP,0));
            else if ( abs_adjust_values[i] > 0 ) drugAdjusts.add(new DrugAdjust(drug, DrugCons.T_INCREMENT,abs_adjust_values[i]));
            else if ( abs_adjust_values[0] == 0 ) drugAdjusts.add(new DrugAdjust(drug, DrugCons.T_FIXED, 0));
            else drugAdjusts.add(new DrugAdjust(drug, DrugCons.T_DECREMENT, -1*abs_adjust_values[i]));
        }

        return drugAdjusts;
    }
}
