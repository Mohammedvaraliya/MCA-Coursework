<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring MVC Quiz</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .quiz-container { background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 700px; margin: auto; }
        .question { font-size: 1.2em; margin-bottom: 15px; }
        .options button { display: block; margin: 10px 0; padding: 10px; width: 100%; border: 1px solid #ccc; border-radius: 5px; cursor: pointer; background: #f9f9f9; transition: background 0.3s; }
        .options button:hover { background: #eaeaea; }
        .feedback { font-weight: bold; margin-top: 10px; }
        .next-btn, .start-btn, .restart-btn { margin-top: 20px; padding: 10px 20px; background: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; }
        .next-btn, .restart-btn { display: none; }
        .scoreboard { margin-top: 20px; font-weight: bold; }
        #timer { margin-top: 10px; font-weight: bold; color: #d9534f; }
        h2 { color: #28a745; }
    </style>
</head>
<body>
<div class="quiz-container" id="quizContainer">
    <button class="start-btn" id="startBtn">Start Quiz</button>
    <div class="question" id="question" style="display:none"></div>
    <div class="options" id="options"></div>
    <div class="feedback" id="feedback"></div>
    <%--<div class="scoreboard" id="scoreboard"></div>
    <div id="timer"></div>--%>
    <button class="next-btn" id="nextBtn">Next</button>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const quiz = ${quizJson};

        console.log("Loaded quiz:", quiz);

        if (!Array.isArray(quiz) || quiz.length === 0) {
            document.getElementById("quizContainer").innerHTML =
                "<div style='color: red;'>Error: Quiz data not loaded properly.</div>";
            return;
        }

        let current = 0;
        let score = 0;
        let timer;
        let timeLeft = 15;

        document.getElementById("startBtn").addEventListener("click", startQuiz);
        document.getElementById("nextBtn").addEventListener("click", nextQuestion);

        function startQuiz() {
            document.getElementById("startBtn").style.display = "none";
            document.getElementById("question").style.display = "block";
            current = 0;
            score = 0;
            loadQuestion();
            updateScoreboard();
        }

        function startTimer() {
            clearInterval(timer);
            timeLeft = 15;
            document.getElementById("timer").innerText = `⏱ Time Left: ${timeLeft}s`;

            timer = setInterval(() => {
                timeLeft--;
                document.getElementById("timer").innerText = `⏱ Time Left: ${timeLeft}s`;
                if (timeLeft <= 0) {
                    clearInterval(timer);
                    document.getElementById("feedback").innerText = "⏰ Time's up!";
                    document.getElementById("nextBtn").style.display = "inline-block";
                    disableOptionButtons();
                }
            }, 1000);
        }

        function loadQuestion() {
            const q = quiz[current];
            document.getElementById("question").innerText = q.question || "Question not available";

            const optionsElement = document.getElementById("options");
            optionsElement.innerHTML = "";

            if (Array.isArray(q.options)) {
                q.options.forEach((option, index) => {
                    const btn = document.createElement("button");
                    btn.className = "option-btn";
                    btn.setAttribute("data-index", index);
                    btn.textContent = option;
                    btn.onclick = () => selectOption(index);
                    optionsElement.appendChild(btn);
                });
            } else {
                optionsElement.innerHTML = "<p>Error loading options</p>";
            }

            document.getElementById("feedback").innerText = "";
            document.getElementById("nextBtn").style.display = "none";
            //startTimer();
        }

        function disableOptionButtons() {
            document.querySelectorAll('.option-btn').forEach(btn => btn.disabled = true);
        }

        function selectOption(selected) {
            clearInterval(timer);
            disableOptionButtons();

            const correctIndex = quiz[current].answer;
            const isCorrect = selected === correctIndex;

            document.getElementById("feedback").innerText = isCorrect ? "✅ Correct!" : "❌ Incorrect.";
            if (isCorrect) score++;
            updateScoreboard();
            document.getElementById("nextBtn").style.display = "inline-block";
        }

        function updateScoreboard() {
            document.getElementById("scoreboard").innerText = `📊 Score: ${score} / ${quiz.length}`;
        }

        function nextQuestion() {
            current++;
            if (current < quiz.length) {
                loadQuestion();
            } else {
                finishQuiz();
            }
        }

        function finishQuiz() {
            document.getElementById("quizContainer").innerHTML = `
                <h2>🎉 Quiz Completed!</h2>
                <p>You scored ${score} out of ${quiz.length}.</p>
                <button class="restart-btn" id="restartBtn">Restart Quiz</button>
            `;
            document.getElementById("restartBtn").addEventListener("click", () => window.location.reload());
        }
    });
</script>
</body>
</html>