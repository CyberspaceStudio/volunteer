package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.EnrollPersonMapper;
import com.volunteer.volunteer.mapper.UserInformationMapper;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 报名表业务层
 * @author: maolin
 * @create: 2019-05-02 15:37
 **/

@Service
@Slf4j
public class EnrollPersonServiceImpl implements EnrollPersonService {
    @Resource
    private EnrollPersonMapper enrollPersonMapper;

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public boolean insert(EnrollPerson enrollPerson) {
        return enrollPersonMapper.insert(enrollPerson) > 0;
    }

    /**
     * @Description: 已通过面试的进行数据插入
     * @Param: [enrollPerson]
     * @return: boolean
     */
    @Override
    public boolean saveInformation(EnrollPerson enrollPerson) {
        UserInformation user = userInformationMapper.selectByPrimaryKey(enrollPerson.getMainId());

        user.setRealName(enrollPerson.getRealName());
        user.setSex(enrollPerson.getSex());
        user.setTelNo(enrollPerson.getTelNo());
        user.setWechat(enrollPerson.getWechat());
        user.setSchool(enrollPerson.getSchool());
        user.setOrganization(enrollPerson.getOrganization());
        user.setDepartment(enrollPerson.getFinalDepartment());
        user.setPosition("3");

        return userInformationMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public EnrollPerson findByMainId(Integer mainId) {
        return enrollPersonMapper.selectByPrimaryKey(mainId);
    }


    /**
     * @Description: 面试打分
     * @Param: [enrollPerson, department, score, impression]
     * @return: boolean
     */
    @Override
    public boolean updateByDepartment(EnrollPerson enrollPerson, String department, String score, String impression) {
        try {
            if (department.equals(enrollPerson.getFirstChoice())) {
                enrollPerson.setFirstInterviewScore(score);
                enrollPerson.setFirstInterviewImpression(impression);
            } else if (department.equals(enrollPerson.getSecondChoice())) {
                enrollPerson.setSecondInterviewScore(score);
                enrollPerson.setSecondInterviewImpression(impression);
            } else if (department.equals(enrollPerson.getThirdChoice())) {
                enrollPerson.setThirdInterviewScore(score);
                enrollPerson.setThirdInterviewImpression(impression);
            } else {
                throw new Exception("志愿和部门发生错误或对应关系错误!");
            }
            return enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】插入失败!");
            return false;
        }
    }

    /**
     * @Description: 报名总人数
     * @Param: []
     * @return: int
     */
    @Override
    public int enrollTotal() {
        try {
            return enrollPersonMapper.enrollTotal();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!");
        }
        return -1;
    }

    /**
     * @Description: 各部门报名人数
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> departmentEnrollTotal() {
        Map<String, Integer> res = new HashMap<>();
        String[] departments = new String[]{"秘书处", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};
        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.departmentEnrollTotal(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!");
        }
        return null;
    }

    /**
    * @Description: 跨部门人数
    * @Param: []
    * @return: Map<String,Integer>
    */
    @Override
    public Map<String, Integer> crossDepartmentTotal() {
        Map<String, Integer> res = new HashMap<>();
        String[] departments = new String[]{"秘书处", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};
        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.crossDepartmentTotal(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!");
        }
        return null;
    }
}
