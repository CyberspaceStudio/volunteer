package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.MessageForShowMapper;
import com.volunteer.volunteer.model.MessageForShow;
import com.volunteer.volunteer.service.MessageForShowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MessageForShowServiceImpl implements MessageForShowService {
    @Resource
    private MessageForShowMapper messageForShowMapper;

    @Override
    public int addNewMessage(MessageForShow messageForShow) {
        int res;
        try{
            messageForShowMapper.insert(messageForShow);
            res = messageForShowMapper.selectLastId();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    //使用改方法前，先检测用户登录状态的mainId与库中mainId是否吻合
    @Override
    public boolean deleteMessage(int activityId, int mainId) {
        if(messageForShowMapper.selectByPrimaryKey(activityId).getMainId().equals(mainId)){
            try {
                messageForShowMapper.deleteByPrimaryKey(activityId);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public MessageForShow getMessageDetail(int activityId) {
        return messageForShowMapper.selectByPrimaryKey(activityId);
    }

    /**
     * 该接口需要实现动态sql数据加载，以5或十条为新加载的数量
     * ！！！！！！改方法被Preview方法取代
     * number 开始加载数据的起点，开始为0，加载十条后为11，以此类推
     * @return 返回一次加载的结果集
     *//*
    @Override
    public List<MessageForShow> indexMessageForShow(int numbers) {
        return null;
    }*/

    /**
     * 该方法被Preview中方法取代
     * @param mainId
     * @return
     */
    @Override
    public List<MessageForShow> personalMessageForShow(int mainId) {
        return null;
    }

    /**
     * 该方法被Preview中方法取代
     * @param department
     * @return
     */
    @Override
    public List<MessageForShow> memoryMessageForShow(String department) {
        return messageForShowMapper.selectByDepartment(department);
    }
}
