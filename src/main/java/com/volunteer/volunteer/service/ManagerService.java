package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.Manager;

public interface ManagerService {
    boolean insertManager(Manager manager);

     Manager findManagerByName(String name);
}
