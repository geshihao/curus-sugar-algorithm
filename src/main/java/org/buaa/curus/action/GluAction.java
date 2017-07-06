package org.buaa.curus.action;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.curus.io.glu.DrugActionInput;
import org.buaa.curus.io.HttpResponse;
import org.buaa.curus.service.glu.GluService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.file.FileURLConnection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qixiang on 6/28/17.
 */

@RestController
public class GluAction {

    private static Log logger = LogFactory.getLog(GluAction.class);

    @Resource
    GluService gluService;

    private static String count_filename;
    private static AtomicLong drug_adjust_count;

    static {
        count_filename = FileUtils.getTempDirectoryPath()+"/drug-adjust-count.tmp";
        try {
            FileReader reader = new FileReader(count_filename);
            BufferedReader br = new BufferedReader(reader);

            String count = br.readLine();

            if ( count != null ) drug_adjust_count = new AtomicLong(Long.parseLong(count));

            br.close();
            reader.close();
        } catch ( FileNotFoundException e) {
            drug_adjust_count = new AtomicLong(0L);
        } catch ( IOException e) {
            drug_adjust_count = new AtomicLong(0L);
        }
    }

    @RequestMapping(value = "/glu/drug-adjust.action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse DrugAction(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody DrugActionInput input)
    {
        long tmp = drug_adjust_count.incrementAndGet();

        if ( tmp % 63 == 0 )
        {
            try {
                FileWriter fileWriter = new FileWriter(count_filename);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(Long.toString(tmp));
                bufferedWriter.close();
                fileWriter.close();
            } catch ( IOException e ) {
            }
        }
        return gluService.GetDrugAdjust(input);
    }


    @RequestMapping(value = "/glu/drug-adjust-count.action", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse DrugActionCount(HttpServletRequest request,
                                        HttpServletResponse response)
    {
        return new HttpResponse(drug_adjust_count.get());
    }
}
