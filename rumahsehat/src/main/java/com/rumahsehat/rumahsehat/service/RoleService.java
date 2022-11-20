package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.RoleModel;
import java.util.List;

public interface RoleService {
    List<RoleModel> findAllRole();
    RoleModel getById(Long id);
}
