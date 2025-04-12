package org.controller.quiz.services;

import org.controller.quiz.model.Question;
import org.controller.quiz.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    private List<Quiz> quizzes;

    public QuizService() {
        // Initialize with some sample quizzes
        quizzes = new ArrayList<>();

        // Create a Java quiz
        List<Question> javaQuestions = new ArrayList<>();

        javaQuestions.add(new Question(1,
                "Which of the following is not a Java feature?",
                Arrays.asList("Object-Oriented", "Use of Pointers", "Portable", "Dynamic"),
                1));

        javaQuestions.add(new Question(2,
                "What is the output of System.out.println(\"Hello\" + 1 + 2 + 3)?",
                Arrays.asList("Hello123", "Hello6", "6Hello", "Error"),
                0));

        javaQuestions.add(new Question(3,
                "Which of these keywords is used to define interfaces in Java?",
                Arrays.asList("intf", "Intf", "interface", "Interface"),
                2));

        Quiz javaQuiz = new Quiz(1, "Java Basics Quiz", javaQuestions);

        // Create a Spring quiz
        List<Question> springQuestions = new ArrayList<>();

        springQuestions.add(new Question(1,
                "What is the role of DispatcherServlet in Spring MVC?",
                Arrays.asList("Manages the database", "Serves static files", "Acts as the front controller and routes requests to handlers", "Compiles JSP files"),
                2));

        springQuestions.add(new Question(2,
                "Where is the DispatcherServlet configured in a Spring MVC application?",
                Arrays.asList("pom.xml", "application.properties", "web.xml", "dispatcher-servlet.xml"),
                2));

        springQuestions.add(new Question(3,
                "What file maps logical view names to JSP files?",
                Arrays.asList("web.xml", "pom.xml", "dispatcher-servlet.xml", "index.jsp"),
                2));

        springQuestions.add(new Question(4,
                "What tag in web.xml defines the servlet mapping for Spring’s front controller?",
                Arrays.asList("context-param", "filter", "servlet-mapping", "listener"),
                2));

        springQuestions.add(new Question(5,
                "Which method in the controller handles GET requests in Spring MVC?",
                Arrays.asList("@PostMapping", "@GetMapping", "@PutMapping", "@DeleteMapping"),
                1));

        springQuestions.add(new Question(6,
                "What is the default behavior when no view resolver is defined in Spring MVC?",
                Arrays.asList("Error", "Renders a static page", "Looks for a JSP in /WEB-INF/views/", "Can't resolve views properly"),
                3));

        springQuestions.add(new Question(7,
                "Which file is responsible for scanning controller classes in a Spring MVC app?",
                Arrays.asList("web.xml", "application.properties", "dispatcher-servlet.xml", "index.jsp"),
                2));

        springQuestions.add(new Question(8,
                "What is the purpose of <context:component-scan> in Spring config?",
                Arrays.asList("To load external properties", "To scan packages for annotated components", "To configure the web server", "To register JSP pages"),
                1));

        springQuestions.add(new Question(9,
                "Which annotation is used to define a Spring MVC controller?",
                Arrays.asList("@Service", "@Component", "@Controller", "@Repository"),
                2));

        springQuestions.add(new Question(10,
                "What is the correct suffix configured in the view resolver for JSP files?",
                Arrays.asList(".html", ".java", ".jsp", ".xml"),
                2));

        springQuestions.add(new Question(11,
                "How can Spring MVC handle form data sent from a JSP page?",
                Arrays.asList("With @RestController", "By using command objects in @ModelAttribute", "By calling a database", "With XML parsing"),
                1));

        springQuestions.add(new Question(12,
                "What happens if a request does not match any controller method?",
                Arrays.asList("It is ignored silently", "A 404 error is returned", "It is logged only", "It returns an empty view"),
                1));

        springQuestions.add(new Question(13,
                "In Spring MVC, what does @RequestMapping(\"/home\") do?",
                Arrays.asList("It sets the default view", "It maps HTTP requests to handler methods", "It configures a new servlet", "It adds a new context"),
                1));

        Quiz springQuiz = new Quiz(2, "Spring Framework Quiz", springQuestions);

        // Create a Hibernate quiz
        List<Question> hibernateQuestions = new ArrayList<>();

        hibernateQuestions.add(new Question(1,
                "What does Hibernate ORM stand for?",
                Arrays.asList("Object-Relational Mapping", "Object-Relational Model", "Original Relational Mapping", "Object Manager"),
                0));

        hibernateQuestions.add(new Question(2,
                "Which method is used to save an object in Hibernate?",
                Arrays.asList("save()", "persist()", "insert()", "create()"),
                0));

        hibernateQuestions.add(new Question(3,
                "What annotation is used for mapping a class in Hibernate?",
                Arrays.asList("@Entity", "@Table", "@MappedSuperclass", "@JoinColumn"),
                0));

        hibernateQuestions.add(new Question(4,
                "In Hibernate, what is a SessionFactory used for?",
                Arrays.asList("To manage the lifecycle of Hibernate sessions", "To cache entities", "To validate entities", "To monitor SQL queries"),
                0));

        hibernateQuestions.add(new Question(5,
                "Which of the following is used to define a primary key in Hibernate?",
                Arrays.asList("@Id", "@PrimaryKey", "@GeneratedValue", "@Key"),
                0));

        hibernateQuestions.add(new Question(6,
                "In Hibernate, what does HQL stand for?",
                Arrays.asList("Hibernate Query Language", "Hypertext Query Language", "Hibernate Query List", "Hibernate Quality Language"),
                0));

        hibernateQuestions.add(new Question(7,
                "What is the default fetching strategy in Hibernate?",
                Arrays.asList("Lazy Loading", "Eager Loading", "Manual Loading", "On-demand Loading"),
                0));

        hibernateQuestions.add(new Question(8,
                "Which of the following is a valid Hibernate annotation for mapping a one-to-many relationship?",
                Arrays.asList("@OneToMany", "@ManyToOne", "@OneToOne", "@ManyToMany"),
                0));

        hibernateQuestions.add(new Question(9,
                "What does the @GeneratedValue annotation in Hibernate do?",
                Arrays.asList("Generates primary key values automatically", "Generates foreign key values", "Generates unique constraints", "Generates database tables"),
                0));

        hibernateQuestions.add(new Question(10,
                "Which file is used to configure Hibernate settings?",
                Arrays.asList("hibernate.cfg.xml", "application.properties", "web.xml", "context.xml"),
                0));

        hibernateQuestions.add(new Question(11,
                "What is the purpose of the Hibernate Query Language (HQL)?",
                Arrays.asList("To perform database operations using Hibernate", "To manage SQL connections", "To define entity mappings", "To configure the session factory"),
                0));

        Quiz hibernateQuiz = new Quiz(3, "Hibernate Quiz", hibernateQuestions);

        quizzes.add(javaQuiz);
        quizzes.add(springQuiz);
        quizzes.add(hibernateQuiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizzes;
    }

    public Quiz getQuizById(int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id) {
                return quiz;
            }
        }
        return null;
    }

    public int evaluateQuiz(int quizId, List<Integer> answers) {
        Quiz quiz = getQuizById(quizId);
        if (quiz == null) {
            return 0;
        }

        int correctAnswers = 0;
        List<Question> questions = quiz.getQuestions();

        for (int i = 0; i < Math.min(questions.size(), answers.size()); i++) {
            if (questions.get(i).isCorrectAnswer(answers.get(i))) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }
}