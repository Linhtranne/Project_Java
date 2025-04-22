package ra.edu.business.service.Course;

import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course.Course;
import ra.edu.business.model.Pagination;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

public class CourseServiceImp implements CourseService {
    private final CourseDAOImp courseDAOImp;
    public CourseServiceImp(){
        courseDAOImp = new CourseDAOImp();
    }
    Pagination pagination = new Pagination();

    @Override
    public Course findByName(String name) {
        return courseDAOImp.findByName(name);
    }

    public List<Course> listCourses(String search) {
        if(search == null){
            return courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
        }else {
            return courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
        }
    }
    private void displayCourseList(Scanner scanner, String search,String sortBy, String sortOrder) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = this.totalCourse();
        pagination.setTotalpages(totalCourse);
        do {
            List<Course> listPagination = null;
            if(sortBy == null){
                listPagination = (search == null) ?
                        courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
                        courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
            }else {
                listPagination = (sortBy.equals("NAME")) ?
                        courseDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
                        courseDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
            }

            if (!listPagination.isEmpty()) {
                System.out.printf("%-10s | %-25s | %-15s | %-25s | %-15s| %-15s|\n",
                        "Mã KH", "Tên khóa học", "Thời lượng (giờ)", "Giảng viên phụ trách", "Ngày thêm", "Trạng thái");
                listPagination.forEach(course -> {
                    System.out.printf("%-10d | %-25s | %-15d | %-25s | %-15s| %-15s|\n",
                            course.getId(), course.getName(), course.getDuration(),
                            course.getInstructor(), course.getCreate_at(), course.getStatus());
                });
                System.out.print("Trang: ");
                if (pagination.getCurrentpage() > 1) {
                    if (pagination.getCurrentpage() >= 3) System.out.print("... ");
                    System.out.print(pagination.getCurrentpage() - 1);
                }
                System.out.print("\u001B[35m" + "    " + pagination.getCurrentpage() + "     " + "\u001B[0m");
                if (pagination.getCurrentpage() < pagination.getTotalpages()) {
                    System.out.print(" " + (pagination.getCurrentpage() + 1));
                    if (pagination.getTotalpages() - pagination.getCurrentpage() >= 2) System.out.print(" ...");
                }
                System.out.println();
                if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
                if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
                System.out.println("1. Chọn trang");
                System.out.println("2. Thoát");
                System.out.print("Lựa chọn: ");

                char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        Exit = true;
                        break;
                    case 'P':
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case 'N':
                        if (pagination.getCurrentpage() < pagination.getTotalpages())
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                }
            } else {
                System.out.println("Không có khóa học nào.");
                break;
            }
        } while (!Exit);
    }

    @Override
    public void listCoursesPagination(Scanner scanner) {
        displayCourseList(scanner, null, null, null);
    }

    @Override
    public int totalCourse() {
        return courseDAOImp.totalCount();
    }

    @Override
    public Course findCourseById(int id) {
        return courseDAOImp.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Course course) {
        return courseDAOImp.save(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDAOImp.update(course);
    }

    @Override
    public boolean delete(Course course) {
        return courseDAOImp.delete(course);
    }

    public void finCourseByNamePagination(Scanner scanner , String search) {
        displayCourseList(scanner, search, null, null);
    }


    @Override
    public void sortByName(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseList(scanner, null, "NAME", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseList(scanner, null, "NAME", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }while(!Exit);
    }

    @Override
    public void sortById(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseList(scanner, null, "ID", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseList(scanner, null, "ID", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }while(!Exit);
    }
}
