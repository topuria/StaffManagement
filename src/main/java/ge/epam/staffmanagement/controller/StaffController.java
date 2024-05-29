package ge.epam.staffmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.epam.staffmanagement.entity.Image;
import ge.epam.staffmanagement.entity.Staff;
import ge.epam.staffmanagement.model.RequestDTO;
import ge.epam.staffmanagement.service.DepartmentService;
import ge.epam.staffmanagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;
    private final DepartmentService departmentService;
    private final ObjectMapper objectMapper;

    @Autowired
    public StaffController(StaffService staffService, DepartmentService departmentService, ObjectMapper objectMapper) {
        this.staffService = staffService;
        this.departmentService = departmentService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    public Staff createStaff(@RequestPart String requestDto,
                             @RequestPart() MultipartFile image) throws IOException {
        RequestDTO request = objectMapper.readValue(requestDto, RequestDTO.class);
        Staff staffDetails = DtoToObject(request, image);
        return staffService.createStaffWithImage(staffDetails, staffDetails.getImage());
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id,
                             @RequestPart String requestDto,
                             @RequestPart(required = false) MultipartFile image) throws IOException {
        RequestDTO request = objectMapper.readValue(requestDto, RequestDTO.class);
        Staff staffDetails = DtoToObject(request, image);
        return staffService.updateStaff(id, staffDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }

    private Staff DtoToObject(RequestDTO requestDto, MultipartFile image) throws IOException {
        Staff staffDetails = new Staff();
        staffDetails.setFirstName(requestDto.getFirstName());
        staffDetails.setLastName(requestDto.getLastName());
        staffDetails.setEmail(requestDto.getEmail());
        staffDetails.setContactNumber(requestDto.getContactNumber());
        staffDetails.setDepartment(departmentService.getDepartmentById(requestDto.getDepartmentId()));
        if(image!=null) {
            Image img = new Image();
            img.setName(image.getOriginalFilename());
            img.setData(image.getBytes());
            staffDetails.setImage(img);
        }
        return staffDetails;
    }
}
