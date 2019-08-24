package com.qf.j1904.service;

import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;

import java.util.List;

public interface IRoleService {
    //获取未被分配的角色列表
    public List<Role> noContainRole(int uid);
    //获取已分配的角色列表
    public List<Role> containRole(int uid);

    //获取所有的角色
    public List<Role> getAllRole(int page, int rows);

    //通过一个roleId  来查询一个角色对象
    public Role getByIdRole(int roleId);
    //修改一个角色
    public boolean updateRole(Role role);

    //添加一个角色
    public boolean addRole(Role role);
    //删除一个角色
    public boolean deleteRole(int roleId);

    //分配到右边的角色
    public boolean addRightRole(User_Role_Mapping user_role_mapping);
    //分配到左边的角色
    public boolean deleteLeftRole(User_Role_Mapping user_role_mapping);
    //通过roleName 查询 一个roleId
    public int queryRoleIdByRoleName(String roleName);

    //计算最大页面
    public int calcMaxPage(int rows);

    //根据roleName  模糊查询
    public List<Role> selectByRoleName(String mh);
}
