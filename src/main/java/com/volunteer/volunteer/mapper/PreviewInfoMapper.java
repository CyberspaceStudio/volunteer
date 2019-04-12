package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.dto.PreviewInfo;

import java.util.List;
import java.util.Map;

public interface PreviewInfoMapper {
    List<PreviewInfo> selectForPreview(Map<String,Object> data);

    List<PreviewInfo> selectByMainId(Integer mainId);

    List<PreviewInfo> selectByDepartment(String department);
}
