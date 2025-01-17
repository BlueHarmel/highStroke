package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.domain.enums.MatchType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_type")
    private MatchType matchType; // 매치 종류 [SINGLE, DOUBLE]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;

    @OneToMany(mappedBy = "match")
    private List<MatchParticipation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "match")
    private List<SetScore> setScores = new ArrayList<>();
}