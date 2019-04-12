package com.volunteer.volunteer.service;

import com.volunteer.volunteer.dto.PreviewInfo;

import java.util.List;
import java.util.Map;

public interface PreviewInfoService {
    List<PreviewInfo> getPreviewMassage(int pageNumber,int pageSize);

    List<PreviewInfo> getPreviewByMainId(int mainId);

    List<PreviewInfo> getPreviewByDepartment(String department);
}
