package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cheque {
    @Id
    @SequenceGenerator(name = "cheque_id_gen"
            , sequenceName = "cheque_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "cheque_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private int priceAverage;

    private ZonedDateTime createdAt;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private User users;

    @ManyToMany( cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<MenuItem> menuItems;


    public void setUser(User user) {
        this.users=user;
    }


}
