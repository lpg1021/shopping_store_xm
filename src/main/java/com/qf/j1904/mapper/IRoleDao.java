package com.qf.j1904.mapper;

import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRoleDao {
    //通过roleid 查询一个角色
    public Role loadById(int roleId);

    //查询未分配的角色列表
     public List<Role> noContainRole(int uid);
    //已分配的角色列表
     public List<Role> containRole(int uid);

    //查询所有的角色
    public List<Role>  loadAllRole();
    //修改角色名称
    public int updateRole(Role role);

    //添加一个角色
    public int addRole(Role role);

    //通过 roleId 删除一个用户
    public int deleteRole(int roleId);

    //添加到右边的已分配角色中
    public int addRight(User_Role_Mapping user_role_mapping);
    //删除到左边的未分配角色中
    public int deleteLeft(User_Role_Mapping user_role_mapping);
    //通过roleName 来查询 roleId
    public int queryRidByRoleName(String roleName);

    //获取roleId  获取总条数
    public int getTotalCount();

    //根据roleName 模糊查询
    public List<Role> selectByRoleName(String mh);
}
