package com.salesianos.triana.RealState.Recu.RealStateRecu.model;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Interesa {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();


    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    @CreatedDate
    private LocalDateTime createdDate;

    @Lob
    private String message;

    @ManyToOne
    @MapsId("interesado_id")
    @JoinColumn(name = "interesado_id")
    private User interesado;

    public void addInteresado(User inter){
        this.interesado = inter;
        interesado.getInteresas().add(this);
    }

    public void addVivienda(Vivienda viv){
        this.vivienda=viv;
        viv.getInteresas().add(this);
    }

}
