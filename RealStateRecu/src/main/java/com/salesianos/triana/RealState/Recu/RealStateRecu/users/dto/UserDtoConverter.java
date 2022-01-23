package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto userToGetUserDto(User u){
        return GetUserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .surnames(u.getSurname())
                .address(u.getAdress())
                .phone(u.getPhone())
                .avatar(u.getAvatar())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .phone(u.getPhone())
                .surnames(u.getSurname())
                .address(u.getAdress())
                .role(u.getRoles())
                .build();
    }

    public GetUserGestorDto userToCreateUserGestorDto(User user){
        return GetUserGestorDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surnames(user.getSurname())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAdress())
                .role(UserRoles.GESTOR)
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

    public CreateUserGestorDto userToCreateGestorDto(User user){
        return CreateUserGestorDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .surnames(user.getSurname())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .address(user.getAdress())
                .idInmobiliaria(user.getInmobiliaria().getId())
                .password(user.getPassword())
                .password2(user.getPassword())
                .build();
    }



}
