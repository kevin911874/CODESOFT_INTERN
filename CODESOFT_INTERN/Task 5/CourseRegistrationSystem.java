import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    List<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean isAvailable() {
        return enrolledStudents.size() < capacity;
    }

    public void display() {
        System.out.println(code + " - " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available slots: " + (capacity - enrolledStudents.size()));
        System.out.println("-----------------------------");
    }
}

class Student {
    String id, name;
    List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    static Map<String, Course> courseDB = new HashMap<>();
    static Map<String, Student> studentDB = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initCourses();

        System.out.print("Enter your Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        studentDB.putIfAbsent(id, new Student(id, name));
        Student student = studentDB.get(id);

        while (true) {
            System.out.println("\n--- Course Registration Menu ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    viewRegisteredCourses(student);
                    break;
                case 5:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void initCourses() {
        courseDB.put("CS101", new Course("CS101", "Intro to CS", "Basics of programming", 3, "Mon-Wed 10:00"));
        courseDB.put("MA102", new Course("MA102", "Calculus", "Mathematics for engineers", 2, "Tue-Thu 9:00"));
        courseDB.put("PH103", new Course("PH103", "Physics", "Mechanics and motion", 2, "Fri 11:00"));
    }

    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDB.values()) {
            course.display();
        }
    }

    static void registerCourse(Student student) {
        System.out.print("Enter Course Code to register: ");
        String code = scanner.nextLine().toUpperCase();
        Course course = courseDB.get(code);

        if (course == null) {
            System.out.println("Course not found!");
        } else if (!course.isAvailable()) {
            System.out.println("Course is full!");
        } else if (student.registeredCourses.contains(code)) {
            System.out.println("Already registered for this course.");
        } else {
            course.enrolledStudents.add(student.id);
            student.registeredCourses.add(code);
            System.out.println("Successfully registered for " + course.title);
        }
    }

    static void dropCourse(Student student) {
        System.out.print("Enter Course Code to drop: ");
        String code = scanner.nextLine().toUpperCase();

        if (!student.registeredCourses.contains(code)) {
            System.out.println("You are not registered in this course.");
            return;
        }

        Course course = courseDB.get(code);
        course.enrolledStudents.remove(student.id);
        student.registeredCourses.remove(code);
        System.out.println("Successfully dropped " + course.title);
    }

    static void viewRegisteredCourses(Student student) {
        if (student.registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
        } else {
            System.out.println("Your Registered Courses:");
            for (String code : student.registeredCourses) {
                Course c = courseDB.get(code);
                System.out.println(code + " - " + c.title);
            }
        }
    }
}
