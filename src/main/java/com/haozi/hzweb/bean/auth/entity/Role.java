package com.haozi.hzweb.bean.auth.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Role {

    private Long roleId;

    private String roleName;

    private String roleSign;

    private String remark;

    private Long userIdCreate;

    private Date gmtCreate;

    private Date gmtModified;

    private List<Long> menuIds;

}
