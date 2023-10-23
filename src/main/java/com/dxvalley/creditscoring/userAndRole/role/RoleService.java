package com.dxvalley.creditscoring.userAndRole.role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role getRoleByName(String roleName);

}
