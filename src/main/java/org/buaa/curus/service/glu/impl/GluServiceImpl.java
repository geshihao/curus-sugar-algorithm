package org.buaa.curus.service.glu.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.curus.algorithm.glu.GluAlgorithm;
import org.buaa.curus.io.HttpResponse;
import org.buaa.curus.io.drug.DrugAdjust;
import org.buaa.curus.io.glu.DrugActionInput;
import org.buaa.curus.service.glu.GluService;

import java.util.List;

import static org.buaa.curus.utils.cons.HttpCons.S_ERROR;
import static org.buaa.curus.utils.cons.HttpCons.S_PE;


/**
 * Created by qixiang on 6/29/17.
 */
public class GluServiceImpl implements GluService {

    private static Log logger = LogFactory.getLog(GluServiceImpl.class);

    @Override
    public HttpResponse GetDrugAdjust(DrugActionInput drugActionInput) {

        double[] levels = GluAlgorithm.GetGluLevels(drugActionInput.getValues());
        if ( levels == null ) return new HttpResponse(S_PE);

        List<DrugAdjust> drugAdjustList = GluAlgorithm.GetDrugAdjusts(levels,drugActionInput.getDrugs());
        if ( drugAdjustList != null ) return new HttpResponse(drugAdjustList);
        else return new HttpResponse(S_ERROR);
    }

}
