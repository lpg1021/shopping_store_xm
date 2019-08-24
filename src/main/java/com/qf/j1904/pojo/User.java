package com.qf.j1904.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class User implements Serializable {
    private int userId;
    private String userName;
    private String userPwd;
    private String identity;
    private String mailbox;
    private List<Role> roleList=new ArrayList<>();

}
