package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.domain.enums.SocialType;
import blueharmel.strokehigh.domain.enums.UserState;
import blueharmel.strokehigh.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(length = 15)
    private String username;

    @Column(length = 20)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state")
    private UserState userState;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_id", length = 50)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

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
}