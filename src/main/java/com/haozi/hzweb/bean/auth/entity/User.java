package com.haozi.hzweb.bean.auth.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    private Long userId;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Byte status;

    private Long userIdCreate;

    private Date gmtCreate;

    private Date gmtModified;

    private String name;

    private List<Long> roleIds;

}
