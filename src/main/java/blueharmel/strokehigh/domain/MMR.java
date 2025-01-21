package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "mmr")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MMR extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mmr_id")
    private Long id;

    @ColumnDefault("0")
    @Column(name = "current_mmr", nullable = false)
    private int currentMMR;

    @ColumnDefault("0")
    @Column(name = "highest_mmr", nullable = false)
    private int highestMMR;

    @ColumnDefault("0")
    @Column(name = "lowest_mmr", nullable = false)
    private int lowestMMR;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
