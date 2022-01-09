package com.salesianos.triana.RealState.Recu.RealStateRecu.security;

import com.salesianos.triana.RealState.Recu.RealStateRecu.security.dto.JwtUserResponse;
import com.salesianos.triana.RealState.Recu.RealStateRecu.security.dto.LoginDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.security.jwt.JwtProvider;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        User user =(User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(user, jwt));

    }

    private JwtUserResponse convertUserToJwtUserResponse(User user, String jwt) {
        return JwtUserResponse.builder()
                .name(user.getName())
                .surnames(user.getSurname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRoles().name())
                .avatar(user.getAvatar())
                .token(jwt)
                .build();
    }

}
