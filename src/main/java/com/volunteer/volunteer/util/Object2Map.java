package com.volunteer.volunteer.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Object2Map {
    /**
     * 实体对象转成Map
     * @param obj 实体对象
     */
    public static Map<String, Object> object2map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 自定义查询List<Object> 转换为Map
     * @param list
     */
    public static List<Map<String,Object>> object2MapList(List list){
        List<Map<String,Object>> lists = new ArrayList<>();
        if (list == null){
            return lists;
        }
        try{
            for (Object obj : list){
                lists.add(object2map(obj));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lists;
    }
}
