package com.qf.j1904.mapper;

import com.qf.j1904.pojo.User;
import com.qf.j1904.pojo.User_Role_Mapping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {
    //获取当前用户对象
    public User loadUser(User user);
    //获取所有的用户
    public List<User> loadUserAll();
    //添加一个用户
    public int addUser(User user);

    //通过一个id获取用户对象
    public User getUserById(int userId);
    //修改一个用户
    public int updateUser(User user);
    //通过用户id 删除一个用户
    public int deleteUser(int userId);

    //通过一个用户Id来查询 一个用户
    public User loadByIdUser(int userId);
    //查询所有用户
    public List<User> loadAll();

    //获取userId 的 总条数
    public int getTotalCount();

    //根据userName  模糊查询
    public List<User> selectByUserName(String mh);
    //根据identity  模糊查询
    public List<User> selectByIdentity(String mh);
    //根据mailbox  模糊查询
    public List<User> selectByMailbox(String mh);


}
