package ge.epam.staffmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Long departmentId;
    private BigDecimal amount;
    private Currency currency;
}
