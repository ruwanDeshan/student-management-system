package lk.developpersstack.lms.bo.custom;

import lk.developpersstack.lms.dto.StudentDto;
import lk.developpersstack.lms.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo {
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException;
    public List<StudentDto> findAllStudent() throws SQLException, ClassNotFoundException;
    public void deleteStudentById(long id) throws SQLException, ClassNotFoundException;
}
