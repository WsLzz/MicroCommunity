package com.java110.front.smo.fee.impl;

import com.alibaba.fastjson.JSONObject;
import com.java110.core.component.AbstractComponentSMO;
import com.java110.core.context.IPageData;
import com.java110.utils.constant.ServiceConstant;
import com.java110.utils.util.Assert;
import com.java110.front.smo.fee.IAddFeeConfigSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * 添加小区服务实现类
 * add by wuxw 2019-06-30
 */
@Service("addFeeConfigSMOImpl")
public class AddFeeConfigSMOImpl extends AbstractComponentSMO implements IAddFeeConfigSMO {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void validate(IPageData pd, JSONObject paramIn) {

        //super.validatePageInfo(pd);

        //Assert.hasKeyAndValue(paramIn, "xxx", "xxx");
        Assert.hasKeyAndValue(paramIn, "feeTypeCd", "必填，请选择费用类型");
        Assert.hasKeyAndValue(paramIn, "feeName", "必填，请填写收费项目");
        Assert.hasKeyAndValue(paramIn, "feeFlag", "必填，请选择费用标识");
        Assert.hasKeyAndValue(paramIn, "startTime", "必填，请选择计费起始时间");
        Assert.hasKeyAndValue(paramIn, "endTime", "必填，请选择计费终止时间");
        Assert.hasKeyAndValue(paramIn, "computingFormula", "必填，请填写附加费用");
        Assert.hasKeyAndValue(paramIn, "squarePrice", "必填，请填写计费单价");
        Assert.hasKeyAndValue(paramIn, "additionalAmount", "必填，请填写附加费用");
        Assert.hasKeyAndValue(paramIn, "communityId", "未包含小区ID");
        Assert.hasKeyAndValue(paramIn, "billType", "未包含出账类型");
        Assert.hasKeyAndValue(paramIn, "billType", "未包含出账类型");
        Assert.hasKeyAndValue(paramIn, "paymentCd", "付费类型不能为空");
        Assert.hasKeyAndValue(paramIn, "paymentCycle", "缴费周期不能为空");

        //super.checkUserHasPrivilege(pd, restTemplate, PrivilegeCodeConstant.AGENT_HAS_LIST_FEECONFIG);

    }

    @Override
    protected ResponseEntity<String> doBusinessProcess(IPageData pd, JSONObject paramIn) {
        ResponseEntity<String> responseEntity = null;
        super.validateStoreStaffCommunityRelationship(pd, restTemplate);

        responseEntity = this.callCenterService(restTemplate, pd, paramIn.toJSONString(),
                ServiceConstant.SERVICE_API_URL + "/api/feeConfig.saveFeeConfig",
                HttpMethod.POST);
        return responseEntity;
    }

    @Override
    public ResponseEntity<String> saveFeeConfig(IPageData pd) {
        return super.businessProcess(pd);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
