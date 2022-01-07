package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto userToGetUserDto(User u){
        return GetUserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .role(u.getRoles())
                .build();
    }

    public CreateUserDto userToCreateUser(User u){
        return CreateUserDto.builder()
                .name(u.getName())
                .surnames(u.getSurname())
                .adress(u.getAdress())
                .password(u.getPassword())
                .password2(u.getPassword())
                .email(u.getEmail())
                .build();
    }

}
