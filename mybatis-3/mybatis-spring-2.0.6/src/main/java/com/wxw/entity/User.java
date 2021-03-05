package com.wxw.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author wxw
 * @since 2020-07-14 21:03:22
 */
public class User implements Serializable {
    private static final long serialVersionUID = 783038016641480725L;

    private Integer id;

    private String userName;

    private String password;

    private String deptment;

    private String phone;

    private String email;

    private Integer status;

    private Date createDate;

    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptment() {
        return deptment;
    }

    public void setDeptment(String deptment) {
        this.deptment = deptment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", userName='" + userName + '\'' +
      ", password='" + password + '\'' +
      ", deptment='" + deptment + '\'' +
      ", phone='" + phone + '\'' +
      ", email='" + email + '\'' +
      ", status=" + status +
      ", createDate=" + createDate +
      ", remark='" + remark + '\'' +
      '}';
  }
}
