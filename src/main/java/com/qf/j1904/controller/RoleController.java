package com.qf.j1904.controller;

import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;
import com.qf.j1904.service.IRoleService;
import com.qf.j1904.pojo.Role;
import com.qf.j1904.pojo.User_Role_Mapping;
import com.qf.j1904.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;
    //定义一个当前用户uid  修改当前用户的角色 curUid
    private int curUid;

    @RequestMapping("assignRole")  //权限角色分配
    public String getRole(Model model,int uid){
        curUid=uid;
        List<Role> noContainRole=roleService.noContainRole(uid);
        //System.out.println(noContainRole);
        List<Role> containRole=roleService.containRole(uid);
        //System.out.println(containRole);
        model.addAttribute("noContainRole",noContainRole);
        model.addAttribute("containRole",containRole);
        return "assignRole";
    }

    @RequestMapping("role")
    public String roleview(@RequestParam(required = false,defaultValue = "1") int page,
                           @RequestParam(required = false,defaultValue = "5") int rows,
                           Model model){
        int maxPage=roleService.calcMaxPage(rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        List<Role> roleList=roleService.getAllRole(page,rows);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("roleList",roleList);
        return "role";
    }

    @RequestMapping("update1")
    public String update(int rid,Model model){
      Role role=roleService.getByIdRole(rid);
      model.addAttribute("role",role);
      return "update";
    }

    @RequestMapping("updateRole")   //修改一个角色
    public String updateRole(Role role){
        boolean bool=roleService.updateRole(role);
        System.out.println(bool);
        return bool?"redirect:role":"error";
    }

    @RequestMapping("addview")    //显示添加角色视图
    public String roleview(){
        return "addRole";
    }

    @RequestMapping("addRole")
    public String addRole(Role role){  //添加一个角色
        boolean bool=roleService.addRole(role);
        System.out.println(bool);
        return bool?"redirect:role":"error";
    }

    @RequestMapping("deleteRole")   //根据id 删除一个角色
    public String deleteRole(int rid){
        boolean bool=roleService.deleteRole(rid);
        return bool?"redirect:role":"error";
    }

    @ResponseBody   //返回给前端页面 为 Json数据
    @RequestMapping("/addRightRole")    //分配到右边的角色
    public boolean addRightRole(String opss){   //传过来一个字符串
        //当前想要添加的角色
        String[] opArray=opss.split("_");    //将字符串 分割成数组
        //定义一个关系类对象 接收要储存的信息
        User_Role_Mapping user_role_mapping=null;
        //定义一个参数判断存储是否成功
        int s=0;
        //将添加的分配到右边的角色  存储到关系表中
        for(int i=0;i<opArray.length;i++){
            user_role_mapping=new User_Role_Mapping();  //定义一个关系对象 来封装 uid 与 rid
            user_role_mapping.setUid(curUid);           //获取当前页面  用户uid
            //通过roleName查询  roleId
            user_role_mapping.setRid(roleService.queryRoleIdByRoleName(opArray[i]));

            boolean b = roleService.addRightRole(user_role_mapping);   //根据uid与rid来添加
            if(b){
                s++;
            }

        }
        return s>0? true:false;
    }

    @ResponseBody
    @RequestMapping("/deleteLeftRole")  //分配到左边的角色
    public boolean deleteLeftRole(String opss){
        //System.out.println(opss);
         //当前要删除的角色
        String[] opArray=opss.split("_");    //将字符串 分割成数组
        //定义一个关系类 接收要删除的信息
        User_Role_Mapping user_role_mapping=null;
        int s=0;  //定义一个参数判断存储是否成功
        //将新的角色分配信息存储到关系表中
        for (int i = 0; i < opArray.length; i++) {
            user_role_mapping=new User_Role_Mapping();
            user_role_mapping.setUid(curUid);
            user_role_mapping.setRid(roleService.queryRoleIdByRoleName(opArray[i]));
            //System.out.println(user_role_mapping);

            boolean b=roleService.deleteLeftRole(user_role_mapping);
            //System.out.println(b);
            if(b){
                s++;
            }
        }
        return  s>0?true:false;

    }

    @RequestMapping("mhSelectRole")       //模糊查询操作
    public String mhSelect(String mhValue,Model model){
        List<Role> roleList=roleService.selectByRoleName(mhValue);
        System.out.println(roleList);
        model.addAttribute("roles",roleList);
        return "dimSelect1";
    }

}
