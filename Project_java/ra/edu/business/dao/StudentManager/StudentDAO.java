package ra.edu.business.dao.StudentManager;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Student.Student;

import java.util.List;

public interface StudentDAO extends AppDAO<Student> {
    Student findByName(String name);
    List<Student> listPagination(int limit, int page);
    int totalCount();
    Student findById(int id);
    List<Student> findByNamePagination(String name, int limit, int page);
    List<Student> sortByName(int limit, int page, String sortBy);
    List<Student> sortById(int limit, int page, String sortBy);
}