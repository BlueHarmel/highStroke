package blueharmel.strokehigh.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long courtId;

    @Column(length = 30)
    private String name;

    @Embedded
    @Column(length = 30)
    private Address address;

    @Column(name = "opening_hours",length = 30)
    private String openingHours;

    @Column(length = 30)
    private String url;
}
