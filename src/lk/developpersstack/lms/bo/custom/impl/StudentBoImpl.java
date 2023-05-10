package lk.developpersstack.lms.bo.custom.impl;

import lk.developpersstack.lms.bo.custom.StudentBo;
import lk.developpersstack.lms.dao.DaoFactory;
import lk.developpersstack.lms.dao.custom.StudentDao;
import lk.developpersstack.lms.dto.StudentDto;
import lk.developpersstack.lms.entity.Student;

import java.sql.SQLException;

public class StudentBoImpl implements StudentBo {

    private final StudentDao studentDao=DaoFactory.getInstance().getDao(DaoFactory.DaoType.STUDENT);
    @Override
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {
        Student student=new Student();
        student.setName(dto.getName());
        student.setContact(dto.getContact());
        studentDao.save(student);
    }
}
