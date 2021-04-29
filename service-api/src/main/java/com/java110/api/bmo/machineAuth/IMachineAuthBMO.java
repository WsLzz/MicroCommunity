package com.java110.api.bmo.machineAuth;

import com.alibaba.fastjson.JSONObject;
import com.java110.api.bmo.IApiBaseBMO;
import com.java110.core.context.DataFlowContext;

public interface IMachineAuthBMO extends IApiBaseBMO {


    /**
     * 添加设备权限
     * @param paramInJson
     * @param dataFlowContext
     * @return
     */
     void addMachineAuth(JSONObject paramInJson, DataFlowContext dataFlowContext);

    /**
     * 添加设备权限信息
     *
     * @param paramInJson     接口调用放传入入参
     * @param dataFlowContext 数据上下文
     * @return 订单服务能够接受的报文
     */
     void updateMachineAuth(JSONObject paramInJson, DataFlowContext dataFlowContext);

    /**
     * 删除设备权限
     *
     * @param paramInJson     接口调用放传入入参
     * @param dataFlowContext 数据上下文
     * @return 订单服务能够接受的报文
     */
     void deleteMachineAuth(JSONObject paramInJson, DataFlowContext dataFlowContext);



}
