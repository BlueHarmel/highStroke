package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "set_scores")
public class SetScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "team_a_score")
    private Integer teamAScore;

    @Column(name = "team_b_score")
    private Integer teamBScore;

}
