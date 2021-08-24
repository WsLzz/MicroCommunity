package com.java110.oa.bmo.oaWorkflow.impl;

import com.java110.dto.oaWorkflow.OaWorkflowDto;
import com.java110.intf.oa.IOaWorkflowInnerServiceSMO;
import com.java110.oa.bmo.oaWorkflow.IGetOaWorkflowBMO;
import com.java110.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getOaWorkflowBMOImpl")
public class GetOaWorkflowBMOImpl implements IGetOaWorkflowBMO {

    @Autowired
    private IOaWorkflowInnerServiceSMO oaWorkflowInnerServiceSMOImpl;

    /**
     * @param oaWorkflowDto
     * @return 订单服务能够接受的报文
     */
    public ResponseEntity<String> get(OaWorkflowDto oaWorkflowDto) {


        int count = oaWorkflowInnerServiceSMOImpl.queryOaWorkflowsCount(oaWorkflowDto);

        List<OaWorkflowDto> oaWorkflowDtos = null;
        if (count > 0) {
            oaWorkflowDtos = oaWorkflowInnerServiceSMOImpl.queryOaWorkflows(oaWorkflowDto);
        } else {
            oaWorkflowDtos = new ArrayList<>();
        }

        ResultVo resultVo = new ResultVo((int) Math.ceil((double) count / (double) oaWorkflowDto.getRow()), count, oaWorkflowDtos);

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(resultVo.toString(), HttpStatus.OK);

        return responseEntity;
    }

}