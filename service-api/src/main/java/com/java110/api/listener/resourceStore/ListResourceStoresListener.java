package com.java110.api.listener.resourceStore;

import com.alibaba.fastjson.JSONObject;
import com.java110.api.listener.AbstractServiceApiListener;
import com.java110.core.annotation.Java110Listener;
import com.java110.core.context.DataFlowContext;
import com.java110.core.event.service.api.ServiceDataFlowEvent;
import com.java110.dto.resourceStore.ResourceStoreDto;
import com.java110.dto.storehouse.StorehouseDto;
import com.java110.intf.store.IResourceStoreInnerServiceSMO;
import com.java110.utils.constant.ServiceCodeResourceStoreConstant;
import com.java110.utils.util.Assert;
import com.java110.utils.util.BeanConvertUtil;
import com.java110.vo.api.resourceStore.ApiResourceStoreDataVo;
import com.java110.vo.api.resourceStore.ApiResourceStoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询小区侦听类
 */
@Java110Listener("listResourceStoresListener")
public class ListResourceStoresListener extends AbstractServiceApiListener {

    @Autowired
    private IResourceStoreInnerServiceSMO resourceStoreInnerServiceSMOImpl;

    @Override
    public String getServiceCode() {
        return ServiceCodeResourceStoreConstant.LIST_RESOURCESTORES;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    public IResourceStoreInnerServiceSMO getResourceStoreInnerServiceSMOImpl() {
        return resourceStoreInnerServiceSMOImpl;
    }

    public void setResourceStoreInnerServiceSMOImpl(IResourceStoreInnerServiceSMO resourceStoreInnerServiceSMOImpl) {
        this.resourceStoreInnerServiceSMOImpl = resourceStoreInnerServiceSMOImpl;
    }

    @Override
    protected void validate(ServiceDataFlowEvent event, JSONObject reqJson) {
        super.validatePageInfo(reqJson);
        Assert.hasKeyAndValue(reqJson, "communityId", "请求报文中未包含小区ID");
        Assert.hasKeyAndValue(reqJson, "storeId", "请求报文中未包含商户ID");
    }

    @Override
    protected void doSoService(ServiceDataFlowEvent event, DataFlowContext context, JSONObject reqJson) {
        ResourceStoreDto resourceStoreDto = BeanConvertUtil.covertBean(reqJson, ResourceStoreDto.class);

        if (StorehouseDto.SH_TYPE_COMMUNITY.equals(resourceStoreDto.getShType())) {
            resourceStoreDto.setShType(StorehouseDto.SH_TYPE_COMMUNITY);
            resourceStoreDto.setShObjId(reqJson.getString("communityId"));
        }
        int count = resourceStoreInnerServiceSMOImpl.queryResourceStoresCount(resourceStoreDto);
        List<ApiResourceStoreDataVo> resourceStores = new ArrayList<>();
        if (count > 0) {
            List<ApiResourceStoreDataVo> apiResourceStoreDataVos = BeanConvertUtil.covertBeanList(resourceStoreInnerServiceSMOImpl.queryResourceStores(resourceStoreDto), ApiResourceStoreDataVo.class);
            for (ApiResourceStoreDataVo apiResourceStoreDataVo : apiResourceStoreDataVos) {
                if (apiResourceStoreDataVo.getOutLowPrice().equals(apiResourceStoreDataVo.getOutHighPrice())) {
                    apiResourceStoreDataVo.setOutPrice(apiResourceStoreDataVo.getOutLowPrice() + "元");
                } else {
                    apiResourceStoreDataVo.setOutPrice(apiResourceStoreDataVo.getOutLowPrice() + "元-" + apiResourceStoreDataVo.getOutHighPrice() + "元");
                }
                resourceStores.add(apiResourceStoreDataVo);
            }
        } else {
            resourceStores = new ArrayList<>();
        }
        ApiResourceStoreVo apiResourceStoreVo = new ApiResourceStoreVo();
        apiResourceStoreVo.setTotal(count);
        apiResourceStoreVo.setRecords((int) Math.ceil((double) count / (double) reqJson.getInteger("row")));
        apiResourceStoreVo.setResourceStores(resourceStores);
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(JSONObject.toJSONString(apiResourceStoreVo), HttpStatus.OK);
        context.setResponseEntity(responseEntity);
    }
}
