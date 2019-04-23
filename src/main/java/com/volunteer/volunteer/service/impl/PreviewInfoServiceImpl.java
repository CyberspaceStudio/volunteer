package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.dto.PreviewInfo;
import com.volunteer.volunteer.mapper.PreviewInfoMapper;
import com.volunteer.volunteer.service.PreviewInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PreviewInfoServiceImpl implements PreviewInfoService {
    @Resource
    PreviewInfoMapper previewInfoMapper;

    @Override
    public List<PreviewInfo> getPreviewMassage(int pageNumber, int pageSize) {
        Map<String,Object> data = new HashMap<>();
        data.put("pageNumber",(pageNumber-1)*pageSize);
        data.put("pageSize",pageSize);
        return previewInfoMapper.selectForPreview(data);
    }

    @Override
    public List<PreviewInfo> getPreviewByMainId(int mainId) {
        return previewInfoMapper.selectByMainId(mainId);
    }

    @Override
    public List<PreviewInfo> getPreviewByDepartment(String department,String timeBeginning,String timeEnding) {
        Map<String,Object> data = new HashMap<>();
        data.put("department",department);
        data.put("timeBeginning",timeBeginning);
        data.put("timeEnding",timeEnding);
        return previewInfoMapper.selectByDepartment(data);
    }
}
