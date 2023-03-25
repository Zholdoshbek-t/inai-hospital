package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;

public interface PermissionService {

    Response createPermission(Request request);

    Response updatePermission(Request request);

    Response deletePermission(Request request);

    Response addPermissionToRole(Request request);

    Response deletePermissionFromRole(Request request);
}
