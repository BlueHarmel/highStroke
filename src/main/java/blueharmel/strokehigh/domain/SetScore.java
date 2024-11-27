package blueharmel.strokehigh.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "set_scores")
public class SetScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id")
    private Long setId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "team_a_score")
    private Integer teamAScore;

    @Column(name = "team_b_score")
    private Integer teamBScore;

    // 생성자, getter, setter 등 생략
}
