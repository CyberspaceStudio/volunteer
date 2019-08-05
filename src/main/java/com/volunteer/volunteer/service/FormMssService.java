package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.FormMss;

import java.util.List;
import java.util.Map;

/**
 * @author ：Maolin
 * @className ：FormMssServiceImpl
 * @date ：Created in 2019/7/27 16:25
 * @description：
 * @version: 1.0
 */
public interface FormMssService {

    boolean saveForm(FormMss formMss);

    List<Map<String, Object>> findFormMssByDeadline();

    boolean updateForm(FormMss formMss);

    FormMss findFormMssByMainId(int mainId);

}
