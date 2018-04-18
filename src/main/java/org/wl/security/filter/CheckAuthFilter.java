package org.wl.security.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.wl.base.entity.ResponseEntity;
import org.wl.security.service.AuthTokenService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @className: CheckAuthFilter.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年4月18日 下午3:55:27
 * @description: 权限过滤器
 */
public class CheckAuthFilter implements Filter {

    private AuthTokenService authTokenService;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        ServletContext context = request.getServletContext();
        if(context.getAttribute("authPath")!=null){
            Map<String,String> authMap = (Map<String,String>)context.getAttribute("authPath");
            boolean ifAuth = authValid(request,authMap);
            if(!ifAuth){
                response.setStatus(401);
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(new ResponseEntity("401","invalid token")));
                writer.flush();
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean authValid(HttpServletRequest request, Map<String,String> authMap) {
        String servletPath = request.getServletPath();
        if(!authMap.isEmpty()){
            for(Map.Entry<String,String> entry : authMap.entrySet()){
                String accessRole = entry.getValue();
                String pattern = entry.getKey();
                try {
                    if(Pattern.matches(pattern,servletPath)){
                        //进行校验
                        String token = request.getParameter("access_token");
                        return authTokenService.validAuth(token,accessRole);
                    }
                }catch (PatternSyntaxException e){
                    return false;
                }

            }
        }
        return true;
    }

    public void destroy() {

    }
    public void init(FilterConfig filterConfig) throws ServletException {
        //加载service
        ServletContext sc = filterConfig.getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);

        if(cxt != null && cxt.getBean("authTokenService") != null && authTokenService == null){
            authTokenService = (AuthTokenService) cxt.getBean("authTokenService");
        }
    }

}
