package com.example.gradle.gradledemo;

import com.example.gradle.gradledemo.data.User;
import com.example.gradle.gradledemo.data.UserMapper;
import com.example.gradle.gradledemo.services.LoginService;
import com.example.gradle.gradledemo.services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody Map<String, Object> addNewUser (@RequestBody @Valid User user, BindingResult error) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if(error.hasErrors()) {
            return success("", error.getAllErrors().get(0).getDefaultMessage());
        }

        System.out.println(error.getAllErrors());
        System.out.println(user.getName());

        return success("", "ok");
    }

    @RequiresRoles("admin")
    @GetMapping(path="/all")
    public @ResponseBody Map<String, Object> getAllUsers() throws SQLException {
        // This returns a JSON or XML with the users
        return success(loginService.getAll(), "ok");
    }

    @PostMapping(path="/login")
    public @ResponseBody Map<String, Object> userLogin () {
        Subject currentUser = SecurityUtils.getSubject();

        if ( !currentUser.isAuthenticated() ) {
            UsernamePasswordToken token = new UsernamePasswordToken("张三", "张三");
            token.setRememberMe(true);

            try {
                currentUser.logout();
                currentUser.login(token);
                //if no exception, that's it, we're done!
                return success("登录成功", "ok");
            } catch ( UnknownAccountException uae ) {
                //username wasn't in the system, show them an error message?
                return success("未知用户", "ok");
            } catch ( IncorrectCredentialsException ice ) {
                //password didn't match, try again?
                return success(ice.getMessage(), "ok");
            } catch ( LockedAccountException lae ) {
                //account for that username is locked - can't login.  Show them a message?
                // ... more types exceptions to check if you want ...
                return success("账号已经被锁定", "ok");
            } catch ( AuthenticationException ae ) {
                //unexpected condition - error?
                return success("认证失败", "ok");
            }
        }

        return success("已经登录", "ok");
    }

    @GetMapping(path="/login") // Map ONLY POST Requests
    public @ResponseBody Map<String, Object> userLoginPage (@RequestBody @Valid User user, BindingResult error) {
        Subject currentUser = SecurityUtils.getSubject();

        if ( !currentUser.isAuthenticated() ) {
            //collect user principals and credentials in a gui specific manner
            //such as username/password html form, X509 certificate, OpenID, etc.
            //We'll use the username/password example here since it is the most common.
            //(do you know what movie this is from? ;)
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            currentUser.login(token);
        }

        return success("user.save(false)", "ok");
    }

    private Map<String, Object> success(Object data, String message) {
        Map<String, Object> map = new HashMap<>(3);

        map.put("code", 200);
        map.put("data", data);
        map.put("message", message);

        return map;
    }
}