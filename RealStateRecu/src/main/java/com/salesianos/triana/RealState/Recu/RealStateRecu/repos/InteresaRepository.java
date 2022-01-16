package com.salesianos.triana.RealState.Recu.RealStateRecu.repos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresaRepository extends JpaRepository<Interesa, Long> {


    @Query(value = """
    SELECT * FROM User user
    WHERE user.id IN (SELECT interesado_id FROM Interesa i GROUP BY interesado_id);
    """, nativeQuery = true)
    List<User> findInteresados();

    @Query(value = """
            SELECT * FROM Vivienda v
            WHERE v.id IN (SELECT viv.id
            FROM Vivienda viv JOIN Interesa inter ON viv.id = inter.vivienda_id
            GROUP BY viv.id
            ORDER BY COUNT(*) DESC
            LIMIT 10);
            """, nativeQuery = true)
    List<Vivienda> top10ViviendasInteresas();


}
