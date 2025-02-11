package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.User;
import blueharmel.strokehigh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
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
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    //회원 탈퇴
    @Transactional
    public void softDeleteUser(Long userId){
        User user = userRepository.findOne(userId);
        user.softDelete();
    }

    // 회원 삭제
    @Transactional
    public void hardDeleteUsers(List<Long> userIds){
        for (Long userId:userIds) {
            User user = userRepository.findOne(userId);
            user.hardDelete();
        }
        userRepository.deleteAllByIds(userIds);
    }

    // 회원 탈퇴한지 30일이 지났으면 삭제
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    @Transactional
    public void deleteOldInactiveUsers() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        userRepository.deleteAllByDeletedDate(thirtyDaysAgo);
    }
}
