package com.qf.j1904.service;

import com.qf.j1904.pojo.User;
import com.qf.j1904.pojo.User_Role_Mapping;

import java.util.List;

public interface IUserService {
    //通过用户名和密码 来获取用户
    public boolean loadUser(User user);
    //获取所有的用户
    public List<User> loadUserAll(int page, int rows);

    //通过id来获取一个用户
    public User loadByIdUser(int userId);
    //修改一个用户
    public boolean updateUser(User user);

    //添加一个用户
    public boolean addUser(User user);

    //通过用户id 删除一个用户
    public boolean deleteUser(int userId);

    //计算最大页面
    public int calcMaxPage(int rows);

    // 模糊查询
    public List<User> selectAll(String mh);


}
