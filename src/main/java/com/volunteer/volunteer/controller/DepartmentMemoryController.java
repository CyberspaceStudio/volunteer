package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.service.PreviewInfoService;
import com.volunteer.volunteer.util.DateStringUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Calendar;

@RestController
@RequestMapping("/memory")
public class DepartmentMemoryController {
    @Resource
    private PreviewInfoService previewInfoService;

    /**
     *
     * @param departmentCode
     * @param day
     * @param month
     * @param year 请输入：1、当前日期年份；2、起始日期年份
     * @return
     */
    @GetMapping("/myDepartment")
    public UniversalResponseBody getMyDepartmentMemory(
            @RequestParam("department")int departmentCode,@RequestParam("day")int day,
            @RequestParam("month")int month,@RequestParam("year")int year){
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH ) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentDay = Calendar.getInstance().get(Calendar.DATE);
        String timeBeginning = "";
        String timeEnding = "";
        if(year == currentYear){
            if(month < 9) {
                currentYear -= 1;
            }
            timeBeginning = currentYear + "-" + "09-01 00:00:00";
            timeEnding = year + "-" + DateStringUtil.dateDealer(month) +
                    "-" + DateStringUtil.dateDealer(day) + " 00:00:00";
        }else{
            timeBeginning = year + "-" + "09-01 00:00:00";
            year += 1;
            timeEnding = year + "-" + "08-31 00:00:00";
        }
        return new UniversalResponseBody<>(0,"成功",previewInfoService.getPreviewByDepartment(DepartmentEnum.getDepartment(departmentCode),timeBeginning,timeEnding));
    }
}
