package ge.epam.staffmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import ge.epam.staffmanagement.entity.Department;
import ge.epam.staffmanagement.entity.Staff;
import ge.epam.staffmanagement.repository.DepartmentRepository;
import ge.epam.staffmanagement.repository.StaffRepository;
import ge.epam.staffmanagement.service.impl.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.mockito.Mockito.when;

public class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private StaffServiceImpl staffService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Department department = new Department();
        department.setName("IT Department");
        departmentRepository.save(department);

        Staff staff = new Staff();
        staff.setFirstName("Tekla");
        staff.setLastName("Topuria");
        staff.setEmail("TeklaTopuria@gmail.com");
        staff.setContactNumber("123123123");
        staff.setDepartment(department);


        Page<Staff> staffPage = new PageImpl<>(Collections.singletonList(staff), PageRequest.of(0, 10), 1);

        when(staffRepository.searchStaff("tekla", PageRequest.of(0, 10))).thenReturn(staffPage);
    }

    @Test
    public void testSearchStaff() {
        Page<Staff> result = staffService.searchStaff("tekla", PageRequest.of(0, 10));
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getFirstName()).isEqualTo("Tekla");
        assertThat(result.getContent().get(0).getDepartment().getName()).isEqualTo("IT Department");
    }
}
