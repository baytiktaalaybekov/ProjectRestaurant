package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubCategory {
    @Id
    @SequenceGenerator(name = "subcategory_id_gen"
            ,sequenceName = "subcategory_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "subcategory_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MenuItem> menuItems =new ArrayList<>();


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonIgnore
    private Category categories;

    public void add(SubCategory subCategory) {
        categories.addSubCategory(subCategory);
    }


    public void setMenuItem(MenuItem menuItem) {

    }
}
