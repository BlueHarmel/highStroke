package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.User;
import blueharmel.strokehigh.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = User.builder().nickname("kim").build();
        //when
        Long savedId = userService.join(user);
        //then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = User.builder().nickname("kim").build();
        User user2 = User.builder().nickname("kim").build();
        //when, then
        userService.join(user1);
        assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
    }
}