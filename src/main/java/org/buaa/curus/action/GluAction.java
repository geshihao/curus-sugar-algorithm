package org.buaa.curus.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.curus.io.glu.DrugActionInput;
import org.buaa.curus.io.HttpResponse;
import org.buaa.curus.service.glu.GluService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qixiang on 6/28/17.
 */

@RestController
public class GluAction {

    private static Log logger = LogFactory.getLog(GluAction.class);

    @Resource
    GluService gluService;

    @RequestMapping(value = "/glu/drug-adjust.action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse DrugAction(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody DrugActionInput input)
    {
        return gluService.GetDrugAdjust(input);
    }

}
