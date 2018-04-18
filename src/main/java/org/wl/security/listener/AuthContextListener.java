package org.wl.security.listener;

import org.apache.commons.lang.StringUtils;
import org.wl.util.authPathUtil;
import org.xml.sax.SAXException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
/**
 * @className: CheckAuthFilter.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年4月18日 下午3:45:20
 * @description: 权限监听器
 */
public class AuthContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String xmlPath = servletContextEvent.getServletContext().getInitParameter("authConfigLocation");
        if(StringUtils.isBlank(xmlPath)){
            throw new RuntimeException("没有找到auth-config配置文件");
        }

        //解析配置文件并加载至内存中
        InputStream in = servletContextEvent.getServletContext().getResourceAsStream(xmlPath);
        try {
            Map<String, String> map  = authPathUtil.getAuthConfig(in);
            servletContextEvent.getServletContext().setAttribute("authPath", map);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("权限配置文件解析错误");
        } catch (SAXException e) {
            e.printStackTrace();
            throw new RuntimeException("权限配置文件解析错误");
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
