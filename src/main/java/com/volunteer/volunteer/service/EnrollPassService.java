package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.EnrollPass;

public interface EnrollPassService {

    boolean insertMainId(Integer mainId);

    boolean PassOrNot(EnrollPass enrollPass);

    void ManyPassOrNot(int[] mainIds,String department,Integer passNum,Integer statusNum) throws Exception;
}
