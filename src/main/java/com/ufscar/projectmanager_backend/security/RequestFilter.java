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

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println(token);
            Optional<User> optional = this.userService.findByToken(token);
            System.out.println("optional = " + optional);

            if (optional.isPresent()) {
                User user = optional.get();
                request.setAttribute("userId", user.getId());
                request.setAttribute("username", user.getName());

                filterChain.doFilter(request, response);

                return;
            }
        }
//        se nao tem token ou nao encontra usuario
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
