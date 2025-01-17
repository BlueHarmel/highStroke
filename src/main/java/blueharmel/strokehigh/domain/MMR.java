package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mmr")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MMR extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mmr_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "current_mmr")
    private int currentMMR;

    @Column(name = "highest_mmr")
    private int highestMMR;

    @Column(name = "lowest_mmr")
    private int lowestMMR;
}
