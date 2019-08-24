package com.qf.j1904.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class sku implements Serializable {
    private int id;
    private String sName;
    private String title;
    private String images;
    private int price;
    private String des;
}
