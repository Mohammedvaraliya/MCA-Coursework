class Student {
    int roll_no;
    String name;
    static String college = "abc";

    static void change() {
        college = "DIET";
    }

    Student(int r, String n) {
        roll_no = r;
        name = n;
    }

    void display() {
        System.out.println(roll_no + " " + name + " " + college);
    }
}

class TestStaticMethod {
    public static void main(String args[]) {
        Student s1 = new Student(111, "Tom");
        Student s2 = new Student(222, "Jerry");

        s1.display();
        s2.display();
        Student.change();
        System.out.println("\nAfter calling function");
        s1.display();
        s2.display();
    }
}