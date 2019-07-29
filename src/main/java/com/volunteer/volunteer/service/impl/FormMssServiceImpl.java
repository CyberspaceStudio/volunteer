package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.FormMssMapper;
import com.volunteer.volunteer.model.FormMss;
import com.volunteer.volunteer.service.FormMssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Maolin
 * @className ：FormMssServiceImpl
 * @date ：Created in 2019/7/27 16:26
 * @description： FormMssServiceImpl
 * @version: 1.0
 */
@Service
@Slf4j
public class FormMssServiceImpl implements FormMssService {

    @Resource
    private FormMssMapper formMssMapper;

    public boolean saveForm(FormMss formMss){
        return formMssMapper.insert(formMss)>0;
    }

    public boolean updateForm(FormMss formMss){
        return formMssMapper.updateByPrimaryKey(formMss)>0;
    }

    public List<FormMss> findFormMssByDeadline(){
        List<FormMss> res = formMssMapper.selectFormMssByDeadline();
        try{
            if ( res != null){
                return res;
            }else{
                log.info("【form表】查找为空");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("【form表】查询失败！");
            return null;
        }
    }
}
