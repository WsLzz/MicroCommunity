package com.java110.user.bmo.userQuestionAnswerValue.impl;

import com.java110.core.annotation.Java110Transactional;
import com.java110.intf.user.IUserQuestionAnswerValueInnerServiceSMO;
import com.java110.po.userQuestionAnswerValue.UserQuestionAnswerValuePo;
import com.java110.user.bmo.userQuestionAnswerValue.IUpdateUserQuestionAnswerValueBMO;
import com.java110.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("updateUserQuestionAnswerValueBMOImpl")
public class UpdateUserQuestionAnswerValueBMOImpl implements IUpdateUserQuestionAnswerValueBMO {

    @Autowired
    private IUserQuestionAnswerValueInnerServiceSMO userQuestionAnswerValueInnerServiceSMOImpl;

    /**
     * @param userQuestionAnswerValuePo
     * @return 订单服务能够接受的报文
     */
    @Java110Transactional
    public ResponseEntity<String> update(UserQuestionAnswerValuePo userQuestionAnswerValuePo) {

        int flag = userQuestionAnswerValueInnerServiceSMOImpl.updateUserQuestionAnswerValue(userQuestionAnswerValuePo);

        if (flag > 0) {
            return ResultVo.createResponseEntity(ResultVo.CODE_OK, "保存成功");
        }

        return ResultVo.createResponseEntity(ResultVo.CODE_ERROR, "保存失败");
    }

}
