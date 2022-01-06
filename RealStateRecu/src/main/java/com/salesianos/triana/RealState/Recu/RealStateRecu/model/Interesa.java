package com.salesianos.triana.RealState.Recu.RealStateRecu.model;

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
}
