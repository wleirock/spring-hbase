package org.wl.api.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wl.base.entity.MessageText;
import org.wl.base.entity.ResponseEntity;
import org.wl.hbase.service.HbaseCommonService;
import org.wl.user.entity.PatientInfo;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlatformApiController {

    @Autowired
    private HbaseCommonService hbaseCommonService;

    @RequestMapping(value="index",method = RequestMethod.GET)
    public ResponseEntity index(){
        return new ResponseEntity("200","welcome");
    }

    @RequestMapping(value = "patientInfo/v1.0/get", method = RequestMethod.GET)
    public ResponseEntity patientInfoGet(@RequestParam(value = "id", required = true) String id) {
        List<PatientInfo> pList = hbaseCommonService.getBeanByRowKeys(new PatientInfo(), id);
        return new ResponseEntity("200", MessageText.QUERY_SUCCES.getText(), pList);
    }
}
