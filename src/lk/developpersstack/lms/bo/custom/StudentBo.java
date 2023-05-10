package lk.developpersstack.lms.bo.custom;

import lk.developpersstack.lms.dto.StudentDto;

import java.sql.SQLException;

public interface StudentBo {
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException;
}
