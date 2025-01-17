package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.domain.enums.ParticipationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "match_participation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchParticipation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_participation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Enumerated(EnumType.STRING)
    private ParticipationType participationType; // 참여 역할 [JUDGE,TEAM_A,TEAM_B]
}
