package blueharmel.strokehigh.repository;

import blueharmel.strokehigh.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        return em.createQuery("select u from User u", User.class).getResultList(); //JPQL
    }

    //단건 조회
    public List<User> findByName(String name){
        return em.createQuery("select u from User u where u.nickname = :name",User.class).setParameter("name",name).getResultList();
    }

    // 회원 삭제
    public void delete(User user) {
        em.remove(user);
    }
    // 회원 id로 삭제
    public void deleteById(Long userId) {
        User user = findOne(userId);
        if(user != null) {
            em.remove(user);
        }
    }
    // 여러 회원 삭제
    public void deleteAllByIds(List<Long> userIds) {
        em.createQuery("delete from User u where u.id in :userIds").setParameter("userIds", userIds).executeUpdate();
    }

    // 비활성화된지 30일이상 된 유저 삭제
    public void deleteAllByDeletedDate(LocalDateTime date){
        em.createQuery("delete from User u where u.deletedAt < :date").setParameter("date",date).executeUpdate();
    }
}
