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
@AllArgsConstructor
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

    @OneToMany(mappedBy = "user")
    private List<MatchParticipation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Rivalry> rivalries = new ArrayList<>();

    @OneToMany(mappedBy = "rival", cascade = CascadeType.ALL)
    List<Rivalry> rivaledBy = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MMR mmr;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Leaderboard> leaderboards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CourtReview> courtReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}