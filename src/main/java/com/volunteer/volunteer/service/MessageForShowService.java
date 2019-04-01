package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.MessageForShow;

import java.util.List;

public interface MessageForShowService {
    boolean addNewMessage(MessageForShow messageForShow);

    /**
     * 该接口需要实现动态sql数据加载，以5或十条为新加载的数量
     * @param numbers 开始加载数据的起点，开始为0，加载十条后为11，以此类推
     * @return 返回一次加载的结果集
     */
    List<MessageForShow> indexMessageForShow(int numbers);


    List<MessageForShow> personalMessageForShow(int mainId);

    List<MessageForShow> memoryMessageForShow(String department);


}
