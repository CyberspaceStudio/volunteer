package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.SuperManager;

public interface SuperManagerService {
    public SuperManager selectSManagerByName(String name);

    public boolean insertSManager(SuperManager superManager);
}
