package com.qf.j1904.controller;

import com.qf.j1904.pojo.User;
import com.qf.j1904.service.IRoleService;
import com.qf.j1904.service.IUserService;
import com.qf.j1904.pojo.User;
import com.qf.j1904.pojo.User_Role_Mapping;
import com.qf.j1904.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Ids;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;

    @RequestMapping("loginHandler1")
    public String loginHandler1(User user){
        boolean bool=userService.loadUser(user);
        return bool?"redirect:loadBook":"error";
    }

    @RequestMapping("loginHandler")
    public String loginHandler(User user){   //通过获取用户信息  登录
        boolean bool=userService.loadUser(user);//html 页面 获取的身份与 admin 比较 如果是admin 跳转admin 不是
        if(bool){
            return  user.getIdentity().equals("admin")? "main":"member";
        }else {
            return "error";
        }
    }


    @RequestMapping("/user")     //获取所有的用户
    public String loadUserAll(@RequestParam(required = false,defaultValue = "1") int page,
                              @RequestParam(required = false,defaultValue = "6") int rows,
                              Model model){

        int maxPage=userService.calcMaxPage(rows);    //计算最大页面
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        List<User> userList=userService.loadUserAll(page,rows);
        model.addAttribute("userList",userList);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "user";
    }
    @RequestMapping("regUser")
    public String regUser(User user){
        boolean b = userService.addUser(user);
        return b?"redirect:loginview":"error";
    }

    @RequestMapping("addUser")    //添加一个用户
    public String addUser(User user){
        boolean bool=userService.addUser(user);
        return bool?"redirect:user":"error";
    }

    @RequestMapping("edit")       //获取页面
    public String edit(int uid,Model model){
        User u=userService.loadByIdUser(uid);      //通过id来获取一个用户

        model.addAttribute("user",u);
        return "edit";
    }


    @RequestMapping("update")           //修改用户
    public String update(User user){
        boolean bool=userService.updateUser(user); //修改一个用户
        return bool?"redirect:user":"error";
    }

    @RequestMapping("deleteUser")   //删除一个对象
    public String deleteUser(int uid){
        boolean bool=userService.deleteUser(uid);
        return bool?"redirect:user":"error";
    }

    @RequestMapping("mhSelect")   //模糊查询   获取模糊查询的值
    public String selectUser(String dimValue,Model model){
        //System.out.println(dimValue);
        List<User> userList=userService.selectAll(dimValue);
        //System.out.println(userList);
        model.addAttribute("users",userList);  //储存userList 数据 以便前端页面获取数据
        return "dimSelect";
    }

    @ResponseBody
    @RequestMapping("batchDelete") //批量删除操作
    public boolean batchDelete(String ids){
        //boolean b1=false;   //先将b1定义为  false
        boolean b2=false;    //先将b2d定义为 false
        String[] idss=ids.split(",");    //将数据库切割成  数组单个形式
        for(int i=0;i< idss.length;i++){        //将数组进行遍历
            if(!idss.equals("on")){
                //b1=roleService.deleteRole(Integer.parseInt(idss[i]));  //根据uid删除 对应的角色
                b2=userService.deleteUser(Integer.parseInt(idss[i]));  //根据id删除 用户对象
            }
        }
        return b2;
    }



}
