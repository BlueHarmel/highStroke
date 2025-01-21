package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.User;
import blueharmel.strokehigh.domain.enums.UserState;
import blueharmel.strokehigh.domain.enums.UserType;
import blueharmel.strokehigh.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
        User user = User.builder().username("홍길동").nickname("kim").email("dldjdtjr@naver.com").password("1234").userState(UserState.valueOf("ACTIVE")).userType(UserType.valueOf("COMMON")).build();
        //when
        Long savedId = userService.join(user);
        //then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = User.builder().username("홍길동").nickname("kim").email("dldjdtjr@naver.com").password("1234").userState(UserState.valueOf("ACTIVE")).userType(UserType.valueOf("COMMON")).build();
        User user2 = User.builder().username("홍길동").nickname("kim").email("dldjdtjr@naver.com").password("1234").userState(UserState.valueOf("ACTIVE")).userType(UserType.valueOf("COMMON")).build();
        //when, then
        userService.join(user1);
        assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
    }

    @Test
    public void 필수요소_제외_후_가입시도() throws Exception {
        //given
        User user1 = User.builder().username("홍길동").nickname("kim").email("dldjdtjr@naver.com").password("1234").build(); // Enum은 설정된 Default 값이 반영
        User user2 = User.builder().username("홍길동").build();
        //when,then
        userService.join(user1);
        assertThrows(DataIntegrityViolationException.class, () -> {
            userService.join(user2);
        });
    }
}