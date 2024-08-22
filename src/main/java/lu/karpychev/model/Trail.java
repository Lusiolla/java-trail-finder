package lu.karpychev.model;

import lombok.*;

import javax.persistence.*;

/*@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trails")*/
public class Trail {
   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToMany
    @JoinTable(
            //name = "points_of_trails",
            //joinColumns = {@JoinColumn(name = "trails_id")},
            //inverseJoinColumns = {@JoinColumn(name = "points_id")}
    )

    private Collection<Point> points;*/
}
