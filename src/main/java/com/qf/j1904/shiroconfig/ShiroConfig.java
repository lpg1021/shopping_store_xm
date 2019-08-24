package com.qf.j1904.shiroconfig;

import com.qf.j1904.realm.MyRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

public class ShiroConfig {

    //1.配置安全管理器
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        return defaultWebSecurityManager;
    }

    //2.创建myRealm 自定义认证，授权
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
}
