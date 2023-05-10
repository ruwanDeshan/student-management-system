package lk.developpersstack.lms.dao;

import lk.developpersstack.lms.dao.custom.impl.StudentDaoImpl;
import org.omg.CORBA.PUBLIC_MEMBER;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        BOOK,STUDENT,PROGRAM,LAPTOP
    }
    public static DaoFactory getInstance(){
        return daoFactory==null?daoFactory=new DaoFactory():daoFactory;
    }
    public <T> T getDao(DaoType type){
        switch (type){
            case STUDENT:
                return (T) new StudentDaoImpl();
            case BOOK:
                return null;
            case LAPTOP:
                return null;
            case PROGRAM:
                return null;
            default:
                return null;
        }
    }

}
