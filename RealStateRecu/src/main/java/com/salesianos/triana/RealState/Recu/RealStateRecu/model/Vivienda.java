package com.salesianos.triana.RealState.Recu.RealStateRecu.model;

import com.salesianos.triana.RealState.Recu.RealStateRecu.anotations.ValidLating;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vivienda {


    @Id
    @GeneratedValue
    private Long id;


    private String titulo;
    @Lob
    private String descripcion;

    @URL
    private String avatar;

    @ValidLating
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

    /**
     * Se elimina en cascada
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "propietario_id")
    private User propietario;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "inmobiliaria_id")
    private Inmobiliaria inmobiliaria;

    @OneToMany(mappedBy = "vivienda")
    @Builder.Default
    private List<Interesa> interesas = new ArrayList<>();

    public Vivienda(String titulo, String descripcion, String avatar, String lating, String direccion,
                    String codigoPostal, String poblacion, String provincia, String tipo, double precio,
                    int numHabitaciones, double metrosCuadrados, int numBanios, boolean TienePiscina,
                    boolean tieneAscensor, boolean tieneGaraje) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.lating = lating;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipo = tipo;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.numBanios = numBanios;
        this.tienePiscina = TienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }

    public void addInmobiliaria(Inmobiliaria inmo){
        inmobiliaria = inmo;
        if (inmo.getViviendas() == null) {
            inmo.setViviendas(new ArrayList<>());
        }
        inmo.getViviendas().add(this);
    }
    public void removeInmobiliaria(){
        if (this.inmobiliaria != null)
            this.inmobiliaria.getViviendas().remove(this);
        this.setInmobiliaria(null);

    }

    public void addPropietario(User prop){
        this.propietario = prop;
        if (prop.getViviendas() == null){
            prop.setViviendas(new ArrayList<>());
            prop.getViviendas().add(this);
        }
    }

    public void removePropietario(){
        if (this.propietario != null)
            this.propietario.getViviendas().remove(this);
        this.setPropietario(null);
    }






}
