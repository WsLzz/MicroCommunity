package com.java110.web.components.service;


import com.java110.core.context.IPageData;
import com.java110.web.smo.service.IListServiceImplsSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * 应用组件管理类
 * <p>
 * add by wuxw
 * <p>
 * 2019-06-29
 */
@Component("chooseServiceImpl")
public class ChooseServiceImplComponent {

    @Autowired
    private IListServiceImplsSMO listServiceImplsSMOImpl;

    /**
     * 查询应用列表
     *
     * @param pd 页面数据封装
     * @return 返回 ResponseEntity 对象
     */
    public ResponseEntity<String> list(IPageData pd) {
        return listServiceImplsSMOImpl.listServiceImpls(pd);
    }

    public IListServiceImplsSMO getListServiceImplsSMOImpl() {
        return listServiceImplsSMOImpl;
    }

    public void setListServiceImplsSMOImpl(IListServiceImplsSMO listServiceImplsSMOImpl) {
        this.listServiceImplsSMOImpl = listServiceImplsSMOImpl;
    }
}
