package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;

public interface RoleService {

    Response createRole(Request request);

    Response updateRole(Request request);

    Response deleteRole(Request request);
}
