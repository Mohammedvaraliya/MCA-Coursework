import university.department.DepartmentGoal;
import university.department.Staffs;

public class MyOwnPackageExample {
    public static void main(String[] args) {

        DepartmentGoal dept = new DepartmentGoal();
        dept.university();

        Staffs myStaff = new Staffs();
        myStaff.AddStaff();
        myStaff.RemoveStaff();
    }
}
