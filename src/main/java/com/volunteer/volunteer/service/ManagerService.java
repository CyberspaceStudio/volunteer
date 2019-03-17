package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;

public interface ManagerService {
    boolean insertManager(Manager manager);

    UniversalResponseBody findManagerByName(String name);
}
