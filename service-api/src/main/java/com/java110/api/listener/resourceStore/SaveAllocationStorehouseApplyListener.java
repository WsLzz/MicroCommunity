package com.java110.api.listener.resourceStore;

import com.alibaba.fastjson.JSONObject;
import com.java110.api.bmo.allocationStorehouseApply.IAllocationStorehouseApplyBMO;
import com.java110.api.listener.AbstractServiceApiPlusListener;
import com.java110.core.annotation.Java110Listener;
import com.java110.core.context.DataFlowContext;
import com.java110.core.event.service.api.ServiceDataFlowEvent;
import com.java110.utils.constant.ServiceCodeAllocationStorehouseApplyConstant;
import com.java110.utils.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

/**
 * 保存商户侦听
 * add by wuxw 2019-06-30
 */
@Java110Listener("saveAllocationStorehouseApplyListener")
public class SaveAllocationStorehouseApplyListener extends AbstractServiceApiPlusListener {

    @Autowired
    private IAllocationStorehouseApplyBMO allocationStorehouseApplyBMOImpl;

    @Override
    protected void validate(ServiceDataFlowEvent event, JSONObject reqJson) {
        //Assert.hasKeyAndValue(reqJson, "xxx", "xxx");

        Assert.hasKeyAndValue(reqJson, "startUserId", "请求报文中未包含startUserId");
        Assert.hasKeyAndValue(reqJson, "startUserName", "请求报文中未包含startUserName");
        Assert.hasKeyAndValue(reqJson, "remark", "请求报文中未包含remark");
        Assert.hasKeyAndValue(reqJson, "storeId", "请求报文中未包含storeId");

    }

    @Override
    protected void doSoService(ServiceDataFlowEvent event, DataFlowContext context, JSONObject reqJson) {
        allocationStorehouseApplyBMOImpl.addAllocationStorehouseApply(reqJson, context);
    }

    @Override
    public String getServiceCode() {
        return ServiceCodeAllocationStorehouseApplyConstant.ADD_ALLOCATIONSTOREHOUSEAPPLY;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

}
