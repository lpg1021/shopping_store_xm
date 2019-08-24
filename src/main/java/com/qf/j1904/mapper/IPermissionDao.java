package com.qf.j1904.mapper;

import com.qf.j1904.pojo.Permission;
import com.qf.j1904.pojo.Role_Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPermissionDao {
    //获取所有的权限
    public List<Permission> loadAll();
    //通过角色来获取拥有的权限
    public List<Permission> loadByRoleId(int roleId);

    //根据rid 来删除 role_permission表中 pid 对应的权限
    public int deletePidByRid(int rid);
    //根据role_permission关系表 添加最终 所勾选的权限
     public int addPermission(Role_Permission role_permission);
     //通过pname  来获取 id
     public int queryPidByPname(String pname);
}
