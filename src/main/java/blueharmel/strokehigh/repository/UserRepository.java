package blueharmel.strokehigh.repository;

import blueharmel.strokehigh.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    //회원 목록 조회
    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class).getResultList(); //JPQL
    }

    //단건 조회
    public List<User> findByName(String name){
        return em.createQuery("select m from User m where m.nickname = :name",User.class).setParameter("name",name).getResultList();
    }

}
