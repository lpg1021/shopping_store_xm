package com.qf.j1904.service.Impl;

import com.qf.j1904.mapper.IPermissionDao;
import com.qf.j1904.pojo.Role_Permission;
import com.qf.j1904.service.IPermissionService;
import com.qf.j1904.mapper.IPermissionDao;
import com.qf.j1904.pojo.Permission;
import com.qf.j1904.pojo.Role_Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override   //获取所有的权限
    public List<Permission> loadAll() {
        return permissionDao.loadAll();
    }

    @Override  //通过角色来获取拥有的权限
    public List<Permission> loadByRoleId(int roleId) {
        return permissionDao.loadByRoleId(roleId);
    }

    @Override   //根据rid 来删除 role_permission表中 pid 对应的权限
    public int deleteRidByPid(int rid) {
        return permissionDao.deletePidByRid(rid);
    }

    @Override   //根据role_permission关系表 添加最终 所勾选的权限
    public int addPermission(Role_Permission role_permission) {
        return permissionDao.addPermission(role_permission);
    }

    @Override  //通过pname  来获取 id
    public int queryPidByPname(String pname) {
        return permissionDao.queryPidByPname(pname);
    }
}
