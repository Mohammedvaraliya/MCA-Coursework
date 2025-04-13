package org.controller.quiz;

import org.controller.quiz.models.Quiz;
import org.controller.quiz.services.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");
    }

    @GetMapping("/list")
    public String listQuizzes(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "quiz-list";
    }

    @GetMapping("/{id}")
    public String showQuiz(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return "redirect:/quiz/list";
        }

        model.addAttribute("quiz", quiz);
        return "quiz-take";
    }

    @PostMapping("/{id}/submit")
    public String submitQuiz(@PathVariable("id") int id,
                             HttpServletRequest request,
                             Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        List<Integer> answers = new ArrayList<>();
        Quiz quiz = quizService.getQuizById(id);
        int totalQuestions = quiz.getQuestions().size();

        for (int i = 0; i < totalQuestions; i++) {
            String answerParam = request.getParameter("answer" + i);
            if (answerParam != null && !answerParam.isEmpty()) {
                try {
                    answers.add(Integer.parseInt(answerParam));
                } catch (NumberFormatException e) {
                    // Skip invalid answers
                }
            }
        }

        int score = quizService.evaluateQuiz(id, answers);

        model.addAttribute("quiz", quiz);
        model.addAttribute("score", score);
        model.addAttribute("total", quiz.getQuestions().size());

        return "quiz-result";
    }
}