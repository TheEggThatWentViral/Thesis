package com.example.webshopbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webshopbackend.config.security.NotionConfigurationProperties;
import com.example.webshopbackend.domain.Role;
import com.example.webshopbackend.domain.User;
import com.example.webshopbackend.config.security.TokenGenerator;
import com.example.webshopbackend.service.UserService;
import com.example.webshopbackend.util.ExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();
    private final NotionConfigurationProperties notionConfigurationProperties;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUsers(
            @RequestBody @Valid User user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> messages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                if (error instanceof FieldError fe) {

                    String sb = fe.getField() +
                            ": " +
                            fe.getDefaultMessage();

                    messages.add(sb);
                }
            }

            return ResponseEntity.badRequest().body(messages);
        }

        userService.saveUser(user);

        return ResponseEntity.ok().body("Successful registration!");
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/role/save").toUriString()
        );
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/add_to_user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                String code = notionConfigurationProperties.codeForHashing();
                Algorithm algorithm = Algorithm.HMAC256(code.getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);

                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                TokenGenerator tokenGenerator = new TokenGenerator(
                        false,
                        user.getUsername(),
                        user
                            .getRoles()
                            .stream()
                            .map(Role::getName)
                            .collect(Collectors.toList()),
                        request.getRequestURL().toString(),
                        notionConfigurationProperties
                );

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokenGenerator.getTokens());

            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                exceptionHandler.sendResponseWithError(
                        HttpServletResponse.SC_FORBIDDEN,
                        exception,
                        response
                );
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
