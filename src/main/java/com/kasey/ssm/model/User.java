package com.kasey.ssm.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private Integer id;
    private String name;
    private String sex;
    private String password;
    private String desc;
}
