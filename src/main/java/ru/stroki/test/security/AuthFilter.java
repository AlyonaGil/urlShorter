package ru.stroki.test.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.impl.UserServiceImpl;
import ru.stroki.test.utils.AuthUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);
    private final UserServiceImpl userService;

    public AuthFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String hash = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<User> user = Optional.empty();
        if (hash != null) {
            user = userService.getByLogin(AuthUtil.getLogin(hash.split(" ")[1]));
        }
        if (user.isPresent()) {
            request.setAttribute("user", user.get());
            logger.info("Success");
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not Authorized");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();
        return "/urlShorter/user".equals(path) && "POST".equals(method) || "/urlShorter/redirect".equals(path);
    }
}