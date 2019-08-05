package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.EnrollPassMapper;
import com.volunteer.volunteer.mapper.EnrollPersonMapper;
import com.volunteer.volunteer.model.EnrollPass;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.service.EnrollPassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @description: 记录志愿是否通过
 * @author: maolin
 * @create: 2019-05-06 20:30
 **/

@Service("EnrollPassService")
@Slf4j
public class EnrollPassServiceImpl implements EnrollPassService {

    @Resource
    private EnrollPassMapper enrollPassMapper;

    @Resource
    private EnrollPersonMapper enrollPersonMapper;

    /**
     * @Description: 报名第一次插入信息，mainId插入
     * @Param: [mainId]
     * @return: boolean
     */
    @Override
    public boolean insertMainId(Integer mainId) {
        return enrollPassMapper.insertMainId(mainId) > 0;
    }

    /**
     * @Description: 是否通过 更新操作
     * @Param: [enrollPass]
     * @return: boolean
     */
    @Override
    public boolean PassOrNot(EnrollPass enrollPass) {
        return enrollPassMapper.updateByMainId(enrollPass) > 0;
    }


    /**
     * @Description: 多数据通用更新操作
     * @Param: [int[] mainIds,String department,Integer passNum,Integer statusNum]
     * @return: void
     * @atention: 如果传入的参数 statusNum 为0 此时不进行 EnrollPerson 的更新
     */

    @Transactional
    public void ManyPassOrNot(int[] mainIds, String department, Integer passNum, Integer statusNum) throws Exception {
        for (int mainId : mainIds) {
            if (statusNum != 0) {
                EnrollPass enrollPass = enrollPassMapper.selectByMainId(mainId);
                EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
                if (department.equals(enrollPerson.getFirstChoice())) {
                    enrollPass.setFirstPass(passNum);
                    enrollPerson.setEnrollStatus("" + statusNum);
                } else if (department.equals(enrollPerson.getSecondChoice())) {
                    enrollPass.setSecondPass(passNum);
                    enrollPerson.setEnrollStatus("" + statusNum);
                } else if (department.equals(enrollPerson.getThirdChoice())) {
                    enrollPass.setThirdPass(passNum);
                    enrollPerson.setEnrollStatus("" + statusNum);
                } else {
                    throw new SQLException("查询出错");
                }
                enrollPassMapper.updateByMainId(enrollPass);
                enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson);
            } else {
                EnrollPass enrollPass = enrollPassMapper.selectByMainId(mainId);
                EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
                if (department.equals(enrollPerson.getFirstChoice())) {
                    enrollPass.setFirstPass(passNum);
                } else if (department.equals(enrollPerson.getSecondChoice())) {
                    enrollPass.setSecondPass(passNum);
                } else if (department.equals(enrollPerson.getThirdChoice())) {
                    enrollPass.setThirdPass(passNum);
                } else {
                    throw new SQLException("查询出错");
                }
                enrollPassMapper.updateByMainId(enrollPass);
            }
        }
    }

    /**
     * @Description: 多数据通用更新操作
     * @Date: 10:13 2019/8/5
     * @Param: [mainId, department]
     * @return: void
     */
    @Transactional
    public void ManyUpdateFinalDepartment(int[] mainIds, String department) throws Exception {
        for (int mainId : mainIds) {
            EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
            enrollPerson.setFinalDepartment(department);
            enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson);
        }
    }
}
