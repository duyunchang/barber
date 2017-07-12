package com.geekcattle.model.valid;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * author 
 */
public class ValidMember {
    @NotEmpty(message="用户名不能为空")
    private String account;

    @NotEmpty(message="密码不能为空")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
