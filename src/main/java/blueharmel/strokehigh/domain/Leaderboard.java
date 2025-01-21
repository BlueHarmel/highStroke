package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "leaderboard", indexes = {
        @Index(name = "idx_user_season", columnList = "user_id,season"),
        @Index(name = "idx_player_rank", columnList = "player_rank")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leaderboard extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaderboard_id")
    private Long id;

    @Column(name = "season", nullable = false)
    private int season;

    @ColumnDefault("0")
    @Column(name = "win_count", nullable = false)
    private int winCount;

    @ColumnDefault("0")
    @Column(name = "lose_count", nullable = false)
    private int loseCount;

    @ColumnDefault("0")
    @Column(name = "win_streak", nullable = false)
    private int winStreak;

    @ColumnDefault("0")
    @Column(name = "win_rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal winRate;

    @Column(name = "player_rank")
    private int playerRank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
