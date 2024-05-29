package ge.epam.staffmanagement.service;


import ge.epam.staffmanagement.entity.Image;
import ge.epam.staffmanagement.entity.Staff;

import java.util.List;


public interface StaffService {

    List<Staff> findAll();

    Staff getStaffById(long id);

    Staff createStaffWithImage(Staff staff, Image image);

    Staff updateStaff(long id,Staff staffDetails);

    void deleteStaff(long id);

}
