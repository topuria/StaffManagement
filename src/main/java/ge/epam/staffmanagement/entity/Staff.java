package ge.epam.staffmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("staff") // Ignore staff property to break the infinite loop
    private Department department;

    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("staff")
    private Image image;
}
