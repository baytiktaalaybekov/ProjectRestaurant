package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menuItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MenuItem {
    @Id
    @SequenceGenerator(name = "menuItem_id_gen"
            , sequenceName = "menuItem_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "menuItem_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;

    private Boolean isVegetarian;

    @ManyToMany(mappedBy = "menuItems", cascade = {CascadeType.ALL})
    private List<Cheque> cheques = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private Restaurant restaurant;


    @OneToOne(mappedBy = "menuItem", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private StopList stopList;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private SubCategory subcategory;


    public void addCheque(Cheque cheque) {
        cheques.add(cheque);
    }


}
