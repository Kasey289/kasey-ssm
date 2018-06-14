package com.kasey.ssm.controller;


import com.kasey.ssm.model.User;
import com.kasey.ssm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("Hello SpringMvc");
    }

    @RequestMapping(value = "/home.html",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/add.html",method = RequestMethod.GET)
    public String add(){
        return "add";
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAll(ModelMap map){
        List<User> userList =userService.findAll();
        map.put("userList",userList);
        return "list";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void addUser(User user, HttpServletResponse response){

        System.out.println(user);
        int i = userService.addUser(user);

        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            if(i > 0){
                out.print("success");
            }else{
                out.print("error");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/removeUserById/{id}",method = RequestMethod.GET)
    public String removeUserById(@PathVariable("id") String id){
        int i = userService.removeUserById(Integer.parseInt(id));
        return "redirect:/user/findAll";
    }

//    @RequestMapping(value = "/pay",method = RequestMethod.PUT)
//    public String xPayAdd(){
//
//    }





}
