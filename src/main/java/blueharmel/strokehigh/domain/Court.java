package blueharmel.strokehigh.domain;

import blueharmel.strokehigh.exception.NotEnoughScoreException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Court extends DeletedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Column(name = "opening_hours",length = 30, nullable = false)
    private LocalTime openingHours;

    @ColumnDefault("'URL이 존재하지 않습니다.'")
    @Column(length = 30, nullable = false)
    private String url;

    @ColumnDefault("0")
    @Column(name = "total_score", nullable = false)
    private Float totalScore;

    @ColumnDefault("0")
    @Column(name = "comment_count", nullable = false)
    private Integer commentCnt;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<CourtReview> courtReviews = new ArrayList<>();

    // 비즈니스 로직
    public void addComment(float newScore){
        this.totalScore+=newScore;
        ++commentCnt;
    }

    public void removeComment(float currScore){
        float restScore = this.totalScore - currScore;
        if(restScore < 0){
            throw new NotEnoughScoreException("need more score");
        }
        this.totalScore = restScore;
        --commentCnt;
    }
}
