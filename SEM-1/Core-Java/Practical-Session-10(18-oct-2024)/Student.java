class AgeNotWithinRangeException extends Exception {
    public AgeNotWithinRangeException(String message) {
        super(message);
    }
}

class NameNotValidException extends Exception {
    public NameNotValidException(String message) {
        super(message);
    }
}

class Student {
    private int rollNo;
    private String name;
    private int age;
    private String course;

    public Student(int rollNo, String name, int age, String course)
            throws AgeNotWithinRangeException, NameNotValidException {

        if (age < 14 || age > 21) {
            throw new AgeNotWithinRangeException("Age is not within the valid range (14 to 21 years).");
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new NameNotValidException("Name contains invalid characters. Only alphabets are allowed.");
        }

        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
    }

    public static void main(String[] args) {
        try {
            Student student1 = new Student(54, "Mohammed Varaliya", 19, "MCA");
            student1.displayDetails();

            // Invalid name example
            Student student2 = new Student(14, "Kunal guhagarkar KTG56", 20, "MSC. CS");

        } catch (AgeNotWithinRangeException | NameNotValidException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Invalid age example
            Student student3 = new Student(39, "Aaditya987", 22, "Mathematics");
        } catch (AgeNotWithinRangeException | NameNotValidException e) {
            System.out.println(e.getMessage());
        }
    }
}