package com.rj.controller;

import com.rj.pojo.User;
import com.rj.service.UserService;
import com.rj.utils.AutoLoginCookie;
import com.rj.utils.Base64Utils;
import com.rj.utils.StringUtils;
import com.rj.utils.Upload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public String regist(User user, MultipartFile picture, HttpServletRequest request) throws IOException {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或者密码不能为空");
            return "regist";
        }

        ServletContext application = request.getSession().getServletContext();
        String realPath = application.getRealPath("/WEB-INF/upload");
        String finalPath = "";
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }

        if (picture != null) {
            String filename = picture.getOriginalFilename();
            String ext = FilenameUtils.getExtension(filename);
            if ((!ext.equals("png"))&&(!ext.equals("jpg"))) {
                request.setAttribute("msg", "上传图片类型不正确");
                return "regist";
            }
            String newFilename = Upload.newFileName(ext);
            finalPath = Upload.newPath(realPath, newFilename) + File.separator + newFilename;
            picture.transferTo(new File(finalPath));
            user.setImg(newFilename);
        }

        userService.add(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String loginLogic(User user, String code, String auto , HttpServletRequest request, HttpServletResponse response) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code)) {
            request.setAttribute("msg", "验证码不能为空");
            return "login";
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或者密码不能为空");
            return "login";
        }
        if (!code.equalsIgnoreCase(captcha)) {
            request.setAttribute("msg", "验证码输入不正确");
            return "login";
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//        token.setRememberMe(true);
        subject.login(token);

        if (!StringUtils.isEmpty(auto)) {
            request.getSession().setAttribute("username", user.getUsername());
            String str = user.getUsername() + "#" + user.getPassword();
            String info = Base64Utils.encode(str);
            AutoLoginCookie.loginCookie("info", info, response);
//            Cookie cookie = new Cookie("info", info);
//            cookie.setMaxAge(60*60*24);
//            cookie.setHttpOnly(true);
//            cookie.setPath("/");
//            response.addCookie(cookie);
        }
        return "redirect:/user/index";
    }

    @GetMapping("/img")
    public void img (String username, HttpServletRequest request,HttpServletResponse response) throws IOException {
        User user = userService.findByUsername(username);
        String fileName = user.getImg();
        ServletContext application = request.getSession().getServletContext();
        String realPath = application.getRealPath("/WEB-INF/upload");
        String finalPath = Upload.newPath(realPath, fileName) + File.separator + fileName;
        IOUtils.copy(new FileInputStream(finalPath), response.getOutputStream());
    }

    @GetMapping("/perms/error")
    public String permError() {
        return "permError";
    }

    @GetMapping("/IOError")
    public String ioError() {
        return "ioError";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        Cookie cookie1 = new Cookie("info", "");
//        cookie1.setMaxAge(0);
//        cookie1.setPath("/");
//        cookie1.setHttpOnly(true);
//        response.addCookie(cookie1);
        AutoLoginCookie.logoutCookie("info", response);
        return "index";
    }

    @RequestMapping("/modify")
    @RequiresPermissions("修改信息")
    public void modify() {
        System.out.println("修改信息");
    }
}
