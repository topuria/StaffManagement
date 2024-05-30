package ge.epam.staffmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "staff_id")
    @JsonIgnoreProperties("image")
    private Staff staff;

}
