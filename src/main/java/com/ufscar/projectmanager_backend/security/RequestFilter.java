package com.ufscar.projectmanager_backend.security;

import com.ufscar.projectmanager_backend.models.User;
import com.ufscar.projectmanager_backend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String path = request.getRequestURI();
        String authHeader = request.getHeader("Authorization");

        if (path.equals("/auth/login")) {
            filterChain.doFilter(request, response);
        } else if  (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Optional<User> optional = this.userService.findByToken(token);

            if (optional.isPresent()) {
                User user = optional.get();
                request.setAttribute("userId", user.getId());
                request.setAttribute("username", user.getName());
            }
        } else {
            System.out.println("403!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
