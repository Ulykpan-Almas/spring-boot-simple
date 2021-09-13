package kz.special.specialfirstspring.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "t_foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name="price")
    private int price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "added_date")
    private Date addedDate;


    @ManyToOne(fetch = FetchType.EAGER)
    private Countries country;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Categories> categories;


}
