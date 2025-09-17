
package cn.violetgarden.blog.controller.filters;

import java.io.IOException;

import cn.violetgarden.blog.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/api/*")
public class FilterToken implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑（可选）
        System.out.println("FilterToken initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 设置允许的源、方法、头等
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
/*         Access-Control-Max-Age 指定了预检请求（OPTIONS）结果可以被缓存多长时间（以秒为单位）。
        这里设置为3600秒（1小时），表示浏览器在1小时内对同一请求不会再次发送预检请求，从而减少跨域请求的次数，提高性能。 */ 
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Token, X-Requested-With");

        // 如果是OPTIONS请求，直接返回200
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        //Token
        String token = request.getHeader("token");
        if(token==null||token.length()==0){
            // 如果没有 token，返回错误响应
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"success\":false,\"message\":\"Token is missing or invalid.\"}");
            return;
        }

        try {
            Jwts.parserBuilder()
                .setSigningKey(UserService.key) // 使用相同的密钥
                .build()
                .parseClaimsJws(token);
            // 可以根据需要从claims中获取用户信息
            chain.doFilter(request, response);
        } catch (io.jsonwebtoken.JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"success\":false,\"message\":\"Invalid or expired token.\"}");
        }

    }

    @Override
    public void destroy() {
        // 销毁逻辑（可选）
    }
}