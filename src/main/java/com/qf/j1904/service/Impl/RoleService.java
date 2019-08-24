package com.qf.j1904.service.Impl;

import com.github.pagehelper.PageHelper;
import com.qf.j1904.mapper.IRoleDao;
import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;
import com.qf.j1904.service.IRoleService;
import com.qf.j1904.mapper.IRoleDao;
import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;


    @Override   //未分配角色方法
    public List<Role> noContainRole(int uid) {
        return roleDao.noContainRole(uid);
    }

    @Override  //已分配角色方法
    public List<Role> containRole(int uid) {
        return roleDao.containRole(uid);
    }

    @Override
    public List<Role> getAllRole(int page,int rows) {
        PageHelper.startPage(page,rows);
        return roleDao.loadAllRole();
    }

    @Override  //计算最大页面
    public int calcMaxPage(int rows) {
        int count=roleDao.getTotalCount();
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override
    public List<Role> selectByRoleName(String mh) {
        return roleDao.selectByRoleName(mh);
    }

    @Override  //通过一个roleId  来查询一个角色对象
    public Role getByIdRole(int roleId) {
        return roleDao.loadById(roleId);
    }

    @Override   //修改一个角色
    public boolean updateRole(Role role) {
        int count=roleDao.updateRole(role);
        return count>0?true:false;
    }

    @Override
    public boolean addRole(Role role) {
        int count=roleDao.addRole(role);
        return count>0?true:false;
    }

    @Override
    public boolean deleteRole(int roleId) {
        int count=roleDao.deleteRole(roleId);
        return count>0?true:false;
    }

    @Override   //分配到右边的角色
    public boolean addRightRole(User_Role_Mapping user_role_mapping) {
        int count=roleDao.addRight(user_role_mapping);
        return count>0?true:false;
    }

    @Override  //分配到左边的角色
    public boolean deleteLeftRole(User_Role_Mapping user_role_mapping) {
        int count=roleDao.deleteLeft(user_role_mapping);
        return count>0?true:false;
    }

    @Override  //通过roleName 查询 一个roleId
    public int queryRoleIdByRoleName(String roleName) {
        return roleDao.queryRidByRoleName(roleName);
    }




}
