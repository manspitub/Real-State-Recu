package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetViviendaPropietario
{
    private String descripcion;
    private String titulo;
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
    private String namePropietario;
    private String surnamesPropietario;
    private String emailPropietario;
    private String phonePropietario;
    private UserRoles roles;

}
