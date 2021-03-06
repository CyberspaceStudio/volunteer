package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.PictureMapper;
import com.volunteer.volunteer.model.Picture;
import com.volunteer.volunteer.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;

    @Override
    public boolean insertPicture(Picture picture) {
        boolean flag = false;
        try{
            flag = true;
            pictureMapper.insert(picture);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Picture> getPictureByActivityId(int activityId) {
        return pictureMapper.selectByActivityId(activityId);
    }
}
