package blueharmel.strokehigh.repository;

import blueharmel.strokehigh.domain.Court;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourtRepository {

    private final EntityManager em;

    public void save(Court court) {
        if(court.getId() == null){
            em.persist(court);
        } else {
            em.merge(court);
        }
    }

    public Court findOne(Long id){
        return em.find(Court.class, id);
    }

    public List<Court> findAll(){
        return em.createQuery("select c from Court c", Court.class).getResultList();
    }
}
