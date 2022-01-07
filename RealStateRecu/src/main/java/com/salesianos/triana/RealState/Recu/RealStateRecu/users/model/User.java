package com.salesianos.triana.RealState.Recu.RealStateRecu.users.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Email
    @NaturalId
    @Column(unique = true, updatable = false)
    private String email;

    private String name;

    private String surname;

    private String adress;

    @NumberFormat
    private String phone;

    @URL
    private String avatar;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    @ManyToOne
    @JoinColumn(name = "inmo_id")
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "interesado", fetch = FetchType.EAGER)
    private List<Interesa> interesas = new ArrayList<>();



    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vivienda> viviendas = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.name()));
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addInmobiliaria(Inmobiliaria inmo){
        this.inmobiliaria = inmo;

    }


}
