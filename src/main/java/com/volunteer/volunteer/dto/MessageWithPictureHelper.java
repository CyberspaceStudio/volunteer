package com.volunteer.volunteer.dto;


import com.volunteer.volunteer.model.MessageForShow;
import com.volunteer.volunteer.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * 本文件未被调用，是否需要存在商榷
 */

public class MessageWithPictureHelper{
    private MessageForShow messageInfo;

    public MessageForShow getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(MessageForShow messageInfo) {
        this.messageInfo = messageInfo;
    }

    private List<Picture> pictureList;

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public MessageWithPictureHelper(MessageForShow messageInfo, List<Picture> pictureList) {
        this.messageInfo = messageInfo;
        this.pictureList = pictureList;
    }

    public List<String> toUrlList(){
        List<String> urlList = new ArrayList<>();
        for (Picture picture: pictureList
             ) {
            urlList.add(picture.getPictureUrl());
        }
        return urlList;
    }
    
}
