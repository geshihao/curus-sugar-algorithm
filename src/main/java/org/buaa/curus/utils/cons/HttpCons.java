package org.buaa.curus.utils.cons;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qixiang on 6/28/17.
 */
public class HttpCons {

    public static final int S_OK = 0;
    public static final int S_ERROR = -1;
    public static final int S_PE = -2;

    public static final Map<Integer,String> StatusInfo = new HashMap<Integer, String>() {{
       put(S_OK,"Success");
       put(S_ERROR,"Error");
       put(S_PE,"Parameters Error - ");
    }};

}
