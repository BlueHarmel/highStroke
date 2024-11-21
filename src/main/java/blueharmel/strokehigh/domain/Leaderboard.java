package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "leaderboard", indexes = {
        @Index(name = "idx_user_season", columnList = "user_id,season"),
        @Index(name = "idx_player_rank", columnList = "player_rank")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaderboard_id")
    private Long leaderboardId;

    @Column(name = "season")
    private int season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "win_count")
    private int winCount;

    @Column(name = "lose_count")
    private int loseCount;

    @Column(name = "win_streak")
    private int winStreak;

    @Column(name = "win_rate", precision = 5, scale = 2)
    private BigDecimal winRate;

    @Column(name = "player_rank")
    private int playerRank;

}
