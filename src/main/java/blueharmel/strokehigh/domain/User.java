package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.domain.enums.SocialType;
import blueharmel.strokehigh.domain.enums.UserState;
import blueharmel.strokehigh.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends DeletedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 15, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    @Builder.Default
    private UserState userState = UserState.ACTIVE; // 유저 상태 [ACTIVE, INACTIVE, BANNED]

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType; // 소셜 로그인 종류 [NAVER,GOOGLE,KAKAO]

    @Column(name = "social_id", length = 50)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    @Builder.Default
    private UserType userType = UserType.COMMON; // 유저 종류 [ADMIN, COMMON]

    //연관관계 필드들(빌더에서 제외)
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<MatchParticipation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Rivalry> rivalries = new ArrayList<>();

    @OneToMany(mappedBy = "rival", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Rivalry> rivaledBy = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MMR mmr;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Leaderboard> leaderboards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CourtReview> courtReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    // 기본 생성 메서드 (필수 매개변수만)
    public static User createUser(String username, String nickname, String email, String password) {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    // 선택적 매개변수 설정 메서드들
    public User withUserState(UserState userState) {
        this.userState = userState;
        return this;
    }

    public User withSocialInfo(SocialType socialType, String socialId) {
        this.socialType = socialType;
        this.socialId = socialId;
        return this;
    }

    public User withUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    // User 엔티티 내부에 검증 로직 추가
    public void validate() {
        if (this.username == null || this.nickname == null || this.email == null || this.password == null) {
            throw new IllegalArgumentException("필수항목(username/nickname/email/password)이 입력되지 않았습니다.");
        }
    }
}