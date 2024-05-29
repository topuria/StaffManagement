package ge.epam.staffmanagement.model;

import ge.epam.staffmanagement.entity.Department;
import ge.epam.staffmanagement.entity.Image;
import ge.epam.staffmanagement.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Staff staff;
    private Image image;
    private Department department;
}
