package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.MatchParticipation;
import blueharmel.strokehigh.domain.User;
import blueharmel.strokehigh.domain.enums.UserState;
import blueharmel.strokehigh.domain.enums.UserType;
import blueharmel.strokehigh.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = User.createUser("홍길동","kim","dldjtjr@naver.com", "1234").withUserState(UserState.ACTIVE).withUserType(UserType.COMMON);
        //when
        Long savedId = userService.join(user);
        //then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = User.createUser("홍길동","kim","dldjtjr@naver.com", "1234").withUserState(UserState.ACTIVE).withUserType(UserType.COMMON);
        User user2 = User.createUser("홍길동","kim","dldjtjr@naver.com", "1234").withUserState(UserState.ACTIVE).withUserType(UserType.COMMON);
        //when, then
        userService.join(user1);
        assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
    }

    @Test
    public void 필수요소_모두선택_후_가입시도() throws Exception {
        //given
        User user = User.createUser("홍길동","kim","dldjtjr@naver.com", "1234"); // Enum은 설정된 Default 값이 반영
        //when, then
        userService.join(user);


    }
    @Test
    public void 필수요소_제외_후_가입시도() throws Exception {
        //given
        User user = User.builder().username("홍길동").build();
        //when,then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.join(user);
        });
    }

    @Test
    public void 신규등록_매치참여_리스트_상태확인() throws Exception {
        //given
        User user = User.createUser("홍길동","kim","dldjtjr@naver.com", "1234"); // Enum은 설정된 Default 값이 반영
        //when
        List<MatchParticipation> participations = user.getParticipations();
        //then
        assertEquals(0, participations.size()); // 아직 참여한 매치 없음
        logger.info("participations = " + participations);
    }
}