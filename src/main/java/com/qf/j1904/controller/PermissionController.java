package com.qf.j1904.controller;
import com.qf.j1904.service.IPermissionService;
import com.qf.j1904.pojo.Permission;
import com.qf.j1904.pojo.Role_Permission;
import com.qf.j1904.service.IPermissionService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    private int currentRid;
    @ResponseBody  //返回生成json数据
    @RequestMapping("/loadPermissionAll")
    public List<Permission> loadPermissionAll(){
        return permissionService.loadAll();
    }

    @RequestMapping("rolePermission")
    public String rolePermissionview(int rid){
        currentRid=rid;
        return "rolePermission";
    }

    @ResponseBody  //返回生成json数据
    @RequestMapping("/assignpermission")
    public List<Permission> loadByRoleId(){   //获取所勾选的权限

        List<Permission> allList=permissionService.loadAll();
        List<Permission> roleList=permissionService.loadByRoleId(currentRid);

        for(Permission rolepermission: roleList){      //角色权限遍历
             for (Permission allpermission: allList){  //所有权限遍历
                 //角色拥有的权限 与 获取所有的全权限相等时  checked 设置true  默认是false 不勾选
                 if (rolepermission.getId()==allpermission.getId()){
                      allpermission.setChecked("true");
                     break;
                 }
             }
        }

        allList.forEach(System.out::println);
        return allList;
    }

    @ResponseBody
    @RequestMapping("assignPermissionx")
    //判断所勾选的权限是否分配成功
    public boolean assignPermission(String pids){
        //System.out.println(pids);
        String[] ps=pids.split("-");  //字符串拆分成数组
        //根据role_permission关系表 删除当前角色 所拥有的权限
        int count=permissionService.deleteRidByPid(currentRid);
        int count1=0;
        int count2=0;
        Role_Permission role_permission=null;
        for (int i = 0; i <ps.length ; i++) {
            role_permission=new Role_Permission();  //封装数据
            role_permission.setRid(currentRid);
            role_permission.setPid(Integer.parseInt(ps[i]));
            count1=permissionService.addPermission(role_permission);
            if(count1>0){
                count2++;
            }
        }
        return  count2>0?true:false;
    }
}
