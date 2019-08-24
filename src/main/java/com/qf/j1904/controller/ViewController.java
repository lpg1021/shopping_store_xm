package com.qf.j1904.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("login")
    public String loginview2(){
        return "购物车";
    }

    @RequestMapping("loginview1")
    public String loginview1(){
        return "确认订单";
    }
    @RequestMapping("loginview3")
    public String loginview3(){
        return "商品详情";
    }

    @RequestMapping("loginview")
    public String loginview(){
        return "login";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("permission")
    public String permissionview(){
        return "permission";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("minecrowdfunding")
    public String minecrowdfunding(){
        return "minecrowdfunding";
    }

    @RequestMapping("reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("add")
    public String add(){
        return "add";
    }

     @RequestMapping("auth_cert")
    public String auth_cert(){
        return "auth_cert";
    }
    @RequestMapping("auth_adv")
    public String auth_adv(){
        return "auth_adv";
    }
    @RequestMapping("auth_project")
    public String auth_project(){
        return "auth_project";
    }

    @RequestMapping("accttype")
    public String accttype(){
        return "accttype";
    }

    @RequestMapping("apply")
    public String apply(){
        return "apply";
    }

    @RequestMapping("apply-1")
    public String apply1(){
        return "apply-1";
    }

    @RequestMapping("apply-2")
    public String apply2(){
        return "apply-2";
    }

    @RequestMapping("apply-3")
    public String apply3(){
        return "apply-3";
    }

    @RequestMapping("cert")
    public String cert(){
        return "cert";
    }
    @RequestMapping("type")
    public String type(){
        return "type";
    }
    @RequestMapping("process")
    public String process(){
        return "process";
    }
    @RequestMapping("advertisement")
    public String advertisement(){
        return "advertisement";
    }
    @RequestMapping("message")
    public String message(){
        return "message";
    }
    @RequestMapping("project_type")
    public String project_type(){
        return "project_type";
    }
    @RequestMapping("tag")
    public String tag(){
        return "tag";
    }
    @RequestMapping("param")
    public String param(){
        return "param";
    }
}
