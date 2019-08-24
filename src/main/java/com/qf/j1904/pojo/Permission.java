package com.qf.j1904.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Permission implements Serializable {
    private int id;
    private int pId;
    private String pname;
    private String url;
    private String icon;
    private String checked="false";   //默认多选框不勾选  为false

}
