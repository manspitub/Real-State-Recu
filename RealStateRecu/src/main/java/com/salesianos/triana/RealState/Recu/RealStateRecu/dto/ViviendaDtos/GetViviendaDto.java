package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetViviendaDto {

    private Long id;
    private String descripcion;
    private String titulo;
    private String avatar;
    private String lating;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;
    private String tipo;
    private double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanios;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private User propietario;
    private Inmobiliaria inmo;

}
