package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.model.MessageForShow;
import com.volunteer.volunteer.service.MessageForShowService;

import java.util.List;

public class MessageForShowServiceImpl implements MessageForShowService {
    @Override
    public boolean addNewMessage(MessageForShow messageForShow) {
        return false;
    }

    /**
     * 该接口需要实现动态sql数据加载，以5或十条为新加载的数量
     *
     * @param numbers 开始加载数据的起点，开始为0，加载十条后为11，以此类推
     * @return 返回一次加载的结果集
     */
    @Override
    public List<MessageForShow> indexMessageForShow(int numbers) {
        return null;
    }

    @Override
    public List<MessageForShow> personalMessageForShow(int mainId) {
        return null;
    }

    @Override
    public List<MessageForShow> memoryMessageForShow(String department) {
        return null;
    }
}
