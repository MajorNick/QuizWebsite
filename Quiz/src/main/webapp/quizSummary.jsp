<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="Quiz.src.main.java.models.User" %>
<html>
<title>
    Quiz summary
</title>
<style>
    .header{
        background-color: #087cfc;
    }
    header {
        color: white;
        font-size: 24px;
        font-family: Courier, monospace;
    }
    body {
        font-family: Courier, monospace;
    }

    .best_performances{
        display: inline-block;
        border: 1px solid #087cfc;
        border-radius: 10px;
        padding: 10px;
        margin: 5px;
    }
    .highest_scores{
        display: inline-block;
        border: 1px solid #087cfc;
        border-radius: 10px;
        padding: 10px;
        margin: 5px;
    }
    .today_highest_scores{
        display: inline-block;
        border: 1px solid #087cfc;
        border-radius: 10px;
        padding: 10px;
        margin: 5px;
    }
    .last_quiz_takers{
        display: inline-block;
        border: 1px solid #087cfc;
        border-radius: 10px;
        padding: 10px;
        margin: 5px;
    }
    .test_start_button{
        background-color: #087cfc;
        border: none;
        margin-top: 10px;
        padding: 10px 15px;

        color: white;
        cursor: pointer;
        border-radius: 5px;
    }
    .flex-container {
        display: flex;
        align-items: center;
        gap: 10px;
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const checkbox = document.getElementById('singlePageCheckbox');
        const quizForm = document.getElementById('quizForm');
        const quizPracticeForm = document.getElementById('quizPracticeForm');
        checkbox.addEventListener('change', function () {
            const isChecked = checkbox.checked;
            quizForm.action = isChecked ? '/quizSinglePage.jsp' : '/quiz.jsp';
            quizPracticeForm.action = isChecked ? '/quizPracticeSinglePage.jsp' : '/quizPractice.jsp';
        });

    });
</script>
<body>

<div class  = header>
    <header style = "color: white">
        Quiz Summary
    </header>

</div>
<%

    HttpSession ses = request.getSession();
    Integer userID = (Integer) ses.getAttribute("userId");
    Integer quizId = Integer.parseInt(request.getParameter("quizId"));
    int id = 1;
    int userid=1;
    DBConn con = new DBConn();
    Quiz quiz = con.getQuiz(id);
    ArrayList<User> u = con.getUsers(id);
    if (u.size() == 0){

        return;
    }
    User user = u.get(0);

%>
<div>
    <h2>
        Quiz Description
    </h2>
    <p>
        <%=quiz.description%>
    </p>

</div>

<div>
    <p>Quiz Creator: <a href = "../userProfile.jsp?id=%d"><%=user.getUsername()%></a></p>
</div>

<div class = best_performances>
    <h3>Best Performances</h3>
    <%
        // look at history
    %>
    <%
        ArrayList<Integer> best = con.getYourBestPerformance(id,userid);
    %>
    <ul>
        <% for(int i : best){
            %>
        <li> <%=i%></li>
        <% } %>
    </ul>
</div>
<div class="highest_scores">
    <h3>Highest Scores</h3>

    <%
        ArrayList<Integer> bestUsers = con.getBestPerformance(id,false);
    %>

    <ul>
        <% for(int i : bestUsers){
            User us = con.getUsers(i).get(0);%>
            <li> <%=us.getUsername()%></li>
        <% } %>
    </ul>
</div>
<div class = today_highest_scores>
    <h3>Today's Highest Scores</h3>
    <%
         bestUsers = con.getBestPerformance(id,false);
    %>

    <ul>
        <% for(int i : bestUsers){
            User us = con.getUsers(i).get(0);%>
        <li> <%=us.getUsername()%></li>
        <% } %>
    </ul>
</div>
<div class = last_quiz_takers>
    <h3>3 Last Quiz Taker</h3>
    <%
        ArrayList<Integer> lastTaker= con.getLastQuizPerformers(id);
    %>

    <ul>
        <% for(int i : lastTaker){
            User us = con.getUsers(i).get(0);%>
        <li> <%=us.getUsername()%></li>
        <% } %>
    </ul>
</div>
<div class="flex-container">
    <form id="quizForm" action="/quiz.jsp" method="GET">
        <button class="test_start_button">Start</button>
    </form>

    <form id="quizPracticeForm" action="./quizPractice.jsp" method="GET">
        <button class="test_start_button">Test</button>
    </form>

    <label>
        <input type="checkbox" id="singlePageCheckbox" name="quizType" value="singlePage"> Single Page
    </label>
</div>

</body>
</html>
