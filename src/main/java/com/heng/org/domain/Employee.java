package com.heng.org.domain;/**
 * @author shkstart
 * @create 2023-03-01 14:33
 */

import lombok.Data;

/**
 *@Auther:Jarvis
 *@Date:2023年03月2023/3/1日14:33
 *@Description:
 */
@Data
public class Employee {
    private Long id;
    private String nickName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String salt;
    private Long age;
    private Long sex;
    private Long state;
    private Department department;
    private Long logininfoId;
    private Shop shop;
}
