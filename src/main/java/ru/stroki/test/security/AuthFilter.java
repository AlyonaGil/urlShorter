package ru.stroki.test.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.stroki.test.dto.ExceptionDto;
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

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String hash = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<User> user = Optional.empty();
        if (hash != null && hash.startsWith("Basic ")) {
            user = userService.getByLogin(AuthUtil.getLogin(hash.split(" ")[1]));
            if (user.isPresent()) {
                request.setAttribute("user", user.get());
                filterChain.doFilter(request, response);
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(dtoMapper.getExceptionDto(401, "Not Authorized")));
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
