package com.salesianos.triana.RealState.Recu.RealStateRecu.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Inmobiliaria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas = new ArrayList<>();

    public Inmobiliaria(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void removeInmobiliariaFromViviendas(){
        viviendas.forEach(v -> v.setInmobiliaria(null));
    }




}
