package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.User;
import blueharmel.strokehigh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public Long join(User user){
        user.validate();
        validateDuplicateUser(user);//중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getNickname());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }
}
