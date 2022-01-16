package com.salesianos.triana.RealState.Recu.RealStateRecu.model;


import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "grafo-inmo-viviendas",
                attributeNodes = {
                        @NamedAttributeNode("viviendas")
                }

        )
)
public class Inmobiliaria {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String phone;

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria", fetch = FetchType.EAGER)
    private List<User> gestores = new ArrayList<>();

    public Inmobiliaria(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void removeInmobiliariaFromViviendas(){
        viviendas.forEach(v -> v.setInmobiliaria(null));
    }

    public void addGestor(User gestor){
        this.getGestores().add(gestor);
        gestor.setInmobiliaria(this);
    }

    public void removeGestor(User gestor){
        if (this.gestores != null)
            gestor.setInmobiliaria(null);
            this.gestores.remove(this);
    }




}
