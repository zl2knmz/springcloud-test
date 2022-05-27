package com.liang.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 快捷获取HttpServletRequest,HttpServletResponse Created By Reese In 2019/1/14
 */
public class HttpContext {

    public static String getIp() {
        return HttpContext.getRequest().getRemoteHost();
    }

    public static HttpServletResponse getResponse() throws NullPointerException {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpServletRequest getRequest() throws NullPointerException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static String readCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) return cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int time) {
        String cvalue = value;
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            cvalue = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, cvalue);
        // tomcat下多应用共享
        //cookie.setPath("/");
        // 单位：秒
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
    }


}
