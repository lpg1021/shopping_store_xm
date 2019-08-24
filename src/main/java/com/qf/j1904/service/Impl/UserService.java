package com.qf.j1904.service.Impl;

import com.github.pagehelper.PageHelper;
import com.qf.j1904.service.IUserService;
import com.qf.j1904.mapper.IUserDao;
import com.qf.j1904.mapper.IUserDao;
import com.qf.j1904.pojo.User;
import com.qf.j1904.pojo.User_Role_Mapping;
import com.qf.j1904.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;


    @Override   //通过用户名和密码 来获取用户
    public boolean loadUser(User user) {
        String userName=user.getUserName();            //获取的客户端传过来的 name 参数
        String identitys = user.getIdentity();         //获取客户端传过来的身份 参数
        User u = userDao.loadUser(user);

        if(u!=null && userName.equals("admin")){
            return true;
        }   //判断       数据库中获取的身份 与  客户端提交的身份比较 相同 true  否则 false
        return (u!=null && u.getIdentity().equals(identitys))? true : false;

    }

    @Override //获取所有的用户
    public List<User> loadUserAll(int page,int rows) {
        PageHelper.startPage(page,rows);
        return userDao.loadUserAll();
    }

    @Override
    public int calcMaxPage(int rows) {
        int count=userDao.getTotalCount();
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override  // 模糊查询
    public List<User> selectAll(String mh) {
         List<User> userList=new ArrayList<>();  //创建一个list集合
         List<User> users1=userDao.selectByUserName(mh);  //根据userName  模糊查询
         if(users1!=null){
             for(User user:users1){
                 userList.add(user);
             }
         }
         List<User> users2=userDao.selectByIdentity(mh); //根据identity  模糊查询
         if(users2!=null){
             for(User user:users2){
                 userList.add(user);
             }
         }

         List<User> users3=userDao.selectByMailbox(mh); //根据mailbox  模糊查询
         if(users3!=null){
             for(User user:users3){
                 userList.add(user);
             }
         }
         return userList;
    }

    @Override //通过id来获取一个用户
    public User loadByIdUser(int userId) {
        return userDao.getUserById(userId);
    }

    @Override  //修改一个用户
    public boolean updateUser(User user) {
        /*int userId=user.getUserId();
        String userName=user.getUserName();
        String userPwd=user.getUserPwd();
        String identity=user.getIdentity();
        String mailbox=user.getMailbox();
        System.out.println(userId);
        System.out.println(mailbox);*/

        int count=userDao.updateUser(user);
        System.out.println(count);
        return count>0?true:false;
    }

    @Override //添加一个用户
    public boolean addUser(User user) {
        int count=userDao.addUser(user);
        return count>0?true:false;
    }

    @Override
    public boolean deleteUser(int userId) {
        int count=userDao.deleteUser(userId);
        return count>0?true:false;
    }




}
