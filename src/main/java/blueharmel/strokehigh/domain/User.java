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

    @OneToMany(mappedBy = "team1Player1")
    List<Match> team1Player1Matches = new ArrayList<>();

    @OneToMany(mappedBy = "team1Player2")
    List<Match> team1Player2Matches = new ArrayList<>();

    @OneToMany(mappedBy = "team2Player1")
    List<Match> team2Player1Matches = new ArrayList<>();

    @OneToMany(mappedBy = "team2Player2")
    List<Match> team2Player2Matches = new ArrayList<>();

    @OneToMany(mappedBy = "judge")
    List<Match> judgeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Rivalry> rivalries = new ArrayList<>();

    @OneToMany(mappedBy = "rival")
    List<Rivalry> rivaledBy = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private MMR mmr;

    @OneToMany(mappedBy = "user")
    private List<Leaderboard> leaderboards = new ArrayList<>();
}