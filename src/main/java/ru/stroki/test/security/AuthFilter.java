package ru.stroki.test.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.stroki.test.entity.User;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.services.impl.UserServiceImpl;
import ru.stroki.test.utils.AuthUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
@Order(1)
public class AuthFilter extends OncePerRequestFilter {
    private final UserServiceImpl userService;

    private final DtoMapper dtoMapper;

    private ObjectMapper objectMapper;

    private void unauthorized(HttpServletResponse response) throws IOException{
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(dtoMapper.getExceptionDto(401, "Not Authorized")));
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String hash = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<User> user;
        if (hash != null && hash.startsWith("Basic ")) {
            user = userService.getByLogin(AuthUtil.getLogin(hash.split(" ")[1]));
            if (user.isPresent()) {
                request.setAttribute("user", user.get());
                filterChain.doFilter(request, response);
            }
            else{
                unauthorized(response);
            }
        }
        else {
            unauthorized(response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();
        return "/urlShorter/user".equals(path) && "POST".equals(method)
                || path.startsWith("/urlShorter/redirect")
                || path.contains("/v3/api-docs")
                || path.contains("/swagger-ui");
    }
}
