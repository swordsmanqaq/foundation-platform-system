package com.heng.auth.controller;/**
 * @author shkstart
 * @create 2023-03-07 17:03
 */

import com.heng.auth.dto.LoginDTO;
import com.heng.auth.service.ILoginService;
import com.heng.base.utils.AjaxResult;
import com.heng.base.utils.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *@Auther:Jarvis
 *@Date:2023年03月2023/3/7日17:03
 *@Description:
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public AjaxResult loginIn(@RequestBody LoginDTO dto){
        try {
            Map<String, Object> loginUser = loginService.loginIn(dto);
            return AjaxResult.me().setResultObj(loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }
    }

    @PostMapping("/logout")
    //获取请求头的信息
    public AjaxResult loginOut(HttpServletRequest httpServletRequest){
        try {
            String token = httpServletRequest.getHeader("token");
            BaseMap.map.remove(token);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("退出登录失败" + e.getMessage());
        }
    }
}
