package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.domain.enums.MatchType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team1_player1_id")
    private User team1Player1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team1_player2_id")
    private User team1Player2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team2_player1_id")
    private User team2Player1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team2_player2_id")
    private User team2Player2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judge_id")
    private User judge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;

    @Column(name = "team1_score")
    private int team1Score;

    @Column(name = "team2_score")
    private int team2Score;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;
}
