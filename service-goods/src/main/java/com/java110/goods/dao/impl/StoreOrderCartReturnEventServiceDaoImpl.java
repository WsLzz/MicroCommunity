package com.java110.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.java110.utils.constant.ResponseConstant;
import com.java110.utils.exception.DAOException;
import com.java110.utils.util.DateUtil;
import com.java110.core.base.dao.BaseServiceDao;
import com.java110.goods.dao.IStoreOrderCartReturnEventServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 退货事件服务 与数据库交互
 * Created by wuxw on 2017/4/5.
 */
@Service("storeOrderCartReturnEventServiceDaoImpl")
//@Transactional
public class StoreOrderCartReturnEventServiceDaoImpl extends BaseServiceDao implements IStoreOrderCartReturnEventServiceDao {

    private static Logger logger = LoggerFactory.getLogger(StoreOrderCartReturnEventServiceDaoImpl.class);





    /**
     * 保存退货事件信息 到 instance
     * @param info   bId 信息
     * @throws DAOException DAO异常
     */
    @Override
    public void saveStoreOrderCartReturnEventInfo(Map info) throws DAOException {
        logger.debug("保存退货事件信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("storeOrderCartReturnEventServiceDaoImpl.saveStoreOrderCartReturnEventInfo",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存退货事件信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }


    /**
     * 查询退货事件信息（instance）
     * @param info bId 信息
     * @return List<Map>
     * @throws DAOException DAO异常
     */
    @Override
    public List<Map> getStoreOrderCartReturnEventInfo(Map info) throws DAOException {
        logger.debug("查询退货事件信息 入参 info : {}",info);

        List<Map> businessStoreOrderCartReturnEventInfos = sqlSessionTemplate.selectList("storeOrderCartReturnEventServiceDaoImpl.getStoreOrderCartReturnEventInfo",info);

        return businessStoreOrderCartReturnEventInfos;
    }


    /**
     * 修改退货事件信息
     * @param info 修改信息
     * @throws DAOException DAO异常
     */
    @Override
    public void updateStoreOrderCartReturnEventInfo(Map info) throws DAOException {
        logger.debug("修改退货事件信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("storeOrderCartReturnEventServiceDaoImpl.updateStoreOrderCartReturnEventInfo",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改退货事件信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

     /**
     * 查询退货事件数量
     * @param info 退货事件信息
     * @return 退货事件数量
     */
    @Override
    public int queryStoreOrderCartReturnEventsCount(Map info) {
        logger.debug("查询退货事件数据 入参 info : {}",info);

        List<Map> businessStoreOrderCartReturnEventInfos = sqlSessionTemplate.selectList("storeOrderCartReturnEventServiceDaoImpl.queryStoreOrderCartReturnEventsCount", info);
        if (businessStoreOrderCartReturnEventInfos.size() < 1) {
            return 0;
        }

        return Integer.parseInt(businessStoreOrderCartReturnEventInfos.get(0).get("count").toString());
    }


}
