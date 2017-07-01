package org.buaa.curus.service.glu;

import org.buaa.curus.io.HttpResponse;
import org.buaa.curus.io.glu.DrugActionInput;

/**
 * Created by qixiang on 6/29/17.
 */
public interface GluService {

    HttpResponse GetDrugAdjust(DrugActionInput drugActionInput);

}
