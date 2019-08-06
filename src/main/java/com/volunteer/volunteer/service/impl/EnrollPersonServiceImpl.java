package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.EnrollPassMapper;
import com.volunteer.volunteer.mapper.EnrollPersonMapper;
import com.volunteer.volunteer.mapper.UserInformationMapper;
import com.volunteer.volunteer.model.EnrollPass;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @Resource
    private EnrollPassMapper enrollPassMapper;

    @Override
    public boolean insert(EnrollPerson enrollPerson) {
        return enrollPersonMapper.insert(enrollPerson) > 0;
    }

    @Override
    public boolean updateStatusByMainId(Integer mainId, Integer status) {
        EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
        enrollPerson.setEnrollStatus("" + status);
        return enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson) > 0;
    }

    @Override
    public EnrollPerson findByMainId(Integer mainId) {
        try {
            EnrollPerson res = enrollPersonMapper.selectByPrimaryKey(mainId);
            if (res != null) {
                return res;
            } else {
                log.error("【数据库】查询出错,无此数据!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询出错!", e);
            return null;
        }
    }


    /**
     * @Description: 面试打分
     * @Param: [enrollPerson, department, score, impression]
     * @return: boolean
     */
    @Override
    public boolean updateScoreAndImpression(Integer mainId, String department, String score, String impression) {
        try {
            EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
            EnrollPass enrollPass = enrollPassMapper.selectByMainId(mainId);
            //enrollStatus 501 正在一面
            if (department.equals(enrollPerson.getFirstChoice())) {
                enrollPerson.setFirstInterviewScore(score);
                enrollPerson.setFirstInterviewImpression(impression);
                enrollPerson.setEnrollStatus("501");
                enrollPass.setFirstPass(1);
            } else if (department.equals(enrollPerson.getSecondChoice())) {
                enrollPerson.setSecondInterviewScore(score);
                enrollPerson.setSecondInterviewImpression(impression);
                enrollPerson.setEnrollStatus("501");
                enrollPass.setSecondPass(1);
            } else if (department.equals(enrollPerson.getThirdChoice())) {
                enrollPerson.setThirdInterviewScore(score);
                enrollPerson.setThirdInterviewImpression(impression);
                enrollPerson.setEnrollStatus("501");
                enrollPass.setThirdPass(1);
            } else {
                throw new Exception("志愿和部门发生错误或对应关系错误!");
            }
            return enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson) > 0 && enrollPassMapper.updateByMainId(enrollPass) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】更新失败!", e);
            return false;
        }
    }

    /**
     * @Description: 总队报名总人数
     * @Param: []
     * @return: int
     */
    @Override
    public int enrollTotal() {
        try {
            return enrollPersonMapper.enrollTotal();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return -1;
        }

    }

    /**
     * @Description: 各部门报名人数
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> departmentEnrollTotal() {
        Map<String, Integer> res = new TreeMap<>();
        String[] departments = new String[]{"统事部", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};

        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.departmentEnrollTotal(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: 各部门跨部人数
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> crossDepartmentTotal() {
        Map<String, Integer> res = new TreeMap<>();
        String[] departments = new String[]{"统事部", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};
        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.crossDepartmentTotal(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: 单个部门报名及跨部人数
     * @Param: [department]
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> oneDepartmentEnrollData(String department) {
        Map<String, Integer> res = new TreeMap<>();
        try {
            res.put("enrollNumber", enrollPersonMapper.departmentEnrollTotal(department));
            res.put("crossNumber", enrollPersonMapper.crossDepartmentTotal(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: 单部门招新男女数据
     * @Param: [department]
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> departmentEnrollDataBySex(String department) {
        Map<String, Integer> res = new TreeMap<>();
        try {
            res.put("man", enrollPersonMapper.departmentEnrollByMan(department));
            res.put("woman", enrollPersonMapper.departmentEnrollByWoman(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: 面试数据
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> interviewData() {
        try {
            Map<String, Integer> res = new TreeMap<>();
            res.put("interviewed", enrollPersonMapper.interviewData());
            res.put("noInterview", enrollPersonMapper.notInterviewData());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: 各部门已面试人数
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> departmentInterviewData() {
        Map<String, Integer> res = new TreeMap<>();
        String[] departments = new String[]{"统事部", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};
        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.departmentInterviewData(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: 单个部门一面数据
     * @Param: [department]
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> oneDepartmentInterviewData(String department) {
        Map<String, Integer> res = new TreeMap<>();
        try {
            res.put("interviewed", enrollPersonMapper.departmentInterviewData(department));
            res.put("noInterview", enrollPersonMapper.notDepartmentInterviewData(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: 各部门未面试人数(一面)
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> notDepartmentInterviewData() {
        Map<String, Integer> res = new TreeMap<>();
        String[] departments = new String[]{"统事部", "网络技术工作室", "交流部", "支教部", "项目部", "宣传部", "环保部", "培训部", "活动部", "红十字会"};
        try {
            for (String department : departments) {
                res.put(department, enrollPersonMapper.notDepartmentInterviewData(department));
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: 单个部门二面面试数据
     * @Param: []
     * @return: Map<String, Integer>
     */
    @Override
    public Map<String, Integer> secondDepartmentInterviewData(String department) {
        Map<String, Integer> res = new TreeMap<>();
        try {
            res.put("interviewed", enrollPersonMapper.secondDepartmentInterviewData(department));
            res.put("noInterview", enrollPersonMapper.notSecondDepartmentInterviewData(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: PC端:一面情况待面试人员
     * @Param: [department]
     * @return: List<Map < String, Object>>
     */
    @Override
    public Map<String, Object> pcWaitFirstInterview(String department, int pageNumber) {
        Map<String, Object> res = new HashMap<>();
        try {
            pageNumber--;
            res.put("data", enrollPersonMapper.PcWaitFirstInterview(department, pageNumber * 10));
            res.put("total", enrollPersonMapper.PcWaitFirstInterviewNumber(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: 用于导出待面试名单
     * @Date:        16:02 2019/8/5
     * @Param:       [department]
     * @return:      java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public List<EnrollPerson> PcWaitFirstInterviewList(String department){
        return enrollPersonMapper.PcWaitFirstInterviewList(department);
    }


    /**
     * @Description: PC端:一面已面试人员
     * @Param: [department]
     * @return: Map < String, Object>
     */
    @Override
    public Map<String, Object> PcFirstInterviewed(String department) {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("data", enrollPersonMapper.PcFirstInterviewed(department));
            res.put("total", enrollPersonMapper.PcFirstInterviewedNumber(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }

    /**
     * @Description: PC端:一面已通过面试人员
     * @Param: [department]
     * @return: Map < String, Object>
     */
    @Override
    public Map<String, Object> firstInterviewPass(String department) {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("data", enrollPersonMapper.PcFirstInterviewPass(department));
            res.put("total", enrollPersonMapper.PcFirstInterviewPassNumber(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: PC端:跨部人员
     * @Param: [department]
     * @return: Map<String, Object>
     */
    @Override
    public Map<String, Object> crossDepartment(String department, int pageNumber) {
        Map<String, Object> res = new HashMap<>();
        try {
            pageNumber--;
            res.put("data", enrollPersonMapper.crossDepartment(department, pageNumber * 10));
            res.put("total", enrollPersonMapper.crossDepartmentNumber(department));
            return res;
            //return enrollPersonMapper.crossDepartment(department);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: PC端：二面待面试人员
     * @Param: [department]
     * @return: Map<String, List < Map < String, Object>>>
     */
    @Override
    public Map<String, Object> PcWaitSecondInterviewed(String department, int pageNumber) {
        Map<String, Object> res = new HashMap<>();
        try {
            pageNumber--;
            //res.put("data","test");
            res.put("data", enrollPersonMapper.PcWaitSecondInterviewed(department, pageNumber * 10));
            res.put("total", enrollPersonMapper.PcWaitSecondInterviewedNumber(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: PC端：二面已面试人员
     * @Param: [department]
     * @return: Map < String, Object>
     */
    @Override
    public Map<String, Object> PcSecondInterviewed(String department) {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("data", enrollPersonMapper.PcSecondInterviewed(department));
            res.put("total", enrollPersonMapper.PcSecondInterviewedNumber(department));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】查询失败!", e);
            return null;
        }
    }


    /**
     * @Description: PC端：跨部协调
     * @Param: [department]
     * @return: Map<String, List < Map < String, Object>>>
     */
    @Override
    public boolean transferDepartment(int mainId, String department) {
        try {
            EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
            enrollPerson.setEnrollStatus("501");
            enrollPerson.setFinalDepartment(department);
            return enrollPersonMapper.updateByPrimaryKeySelective(enrollPerson) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【数据库】更新失败!", e);
            return false;
        }
    }


    /**
     * @Description: 成为部员多成员数据插入user
     * @Param: [enrollPerson]
     * @return: boolean
     * user.position :TODO 具体职位看管理员权限的字段
     */
    @Transactional
    public void saveManyInformation(int[] mainIds) throws Exception {

        for (int mainId : mainIds) {
            EnrollPerson enrollPerson = enrollPersonMapper.selectByPrimaryKey(mainId);
            UserInformation user = userInformationMapper.selectByPrimaryKey(enrollPerson.getMainId());
            user.setRealName(enrollPerson.getRealName());
            user.setSex(enrollPerson.getSex());
            user.setTelNo(enrollPerson.getTelNo());
            user.setWechat(enrollPerson.getWechat());
            user.setSchool(enrollPerson.getSchool());
            user.setOrganization(enrollPerson.getOrganization());
            user.setDepartment(enrollPerson.getFinalDepartment());
            user.setPosition("1");

            userInformationMapper.updateByPrimaryKey(user);
        }
    }

}
