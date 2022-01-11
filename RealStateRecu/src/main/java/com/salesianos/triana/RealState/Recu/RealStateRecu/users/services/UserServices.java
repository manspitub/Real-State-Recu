package com.salesianos.triana.RealState.Recu.RealStateRecu.users.services;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.InmoService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.base.BaseService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.CreateUserDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.CreateUserGestorDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.GetUserDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.UserDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service(value = "userService")
public class UserServices extends BaseService<User, UUID, UserRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmoService inmoService;
    private final UserDtoConverter converter;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }

    public List<GetUserDto> userToGetUserDto(List<User> users){
        List<GetUserDto> getUserDtos = new ArrayList<>();

        users.stream().forEach(u ->{
            getUserDtos.add(converter.userToGetUserDto(u));
        });
        return getUserDtos;
    }


    public User saveProp(CreateUserDto newUser){

        if (newUser.getPassword().contentEquals(newUser.getPassword2()) || newUser.getEmail().equals(this.repositorio.findFirstByEmail(newUser.getEmail()).toString())) {
            User usuario = User.builder()
                    .name(newUser.getName())
                    .surname(newUser.getSurnames())
                    .adress(newUser.getAdress())
                    .email(newUser.getEmail())
                    .phone(newUser.getPhone())
                    .avatar(newUser.getAvatar())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .roles(UserRoles.PROPIETARIO)
                    .build();

            return save(usuario);
        }else {
            throw new UsernameNotFoundException(newUser.getEmail() + "ya existe");
        }


    }

    public User saveGestor(CreateUserGestorDto gestor){
        if (gestor.getPassword().contentEquals(gestor.getPassword2()) || gestor.getEmail().equals(this.repositorio.findFirstByEmail(gestor.getEmail()).toString())) {

            User user = User.builder()
                    .name(gestor.getName())
                    .surname(gestor.getSurnames())
                    .adress(gestor.getAddress())
                    .avatar(gestor.getAvatar())
                    .email(gestor.getEmail())
                    .phone(gestor.getPhone())
                    .roles(UserRoles.GESTOR)
                    .password(passwordEncoder.encode(gestor.getPassword()))
                    .inmobiliaria(null)
                    .build();

            Optional<Inmobiliaria> inmobiliariaABuscar = inmoService.findById(gestor.getIdInmobiliaria());

            inmobiliariaABuscar.ifPresent(user::addInmobiliaria);
            return save(user);

        } else {
            throw new UsernameNotFoundException(gestor.getEmail() + "ya existe");
        }
    }

    public User saveAdmin(CreateUserDto admin){

        if (admin.getPassword().contentEquals(admin.getPassword2()) || admin.getEmail().equals(this.repositorio.findFirstByEmail(admin.getEmail()).toString())) {
            User user = User.builder()
                    .name(admin.getName())
                    .surname(admin.getSurnames())
                    .adress(admin.getAdress())
                    .email(admin.getEmail())
                    .phone(admin.getPhone())
                    .avatar(admin.getAvatar())
                    .password(passwordEncoder.encode(admin.getPassword()))
                    .roles(UserRoles.ADMIN)
                    .build();
            return save(user);
        } else {
            throw new UsernameNotFoundException(admin.getEmail() + "ya existe");
        }
    }





}
