<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="Quiz.src.main.java.models.User" %>
<html>
<title>
    Quiz summary
</title>
<link rel="stylesheet" href = "style.css">
<body>
<div class  = header>
    <header style = "color: white">
        Quiz Summary
    </header>

</div>
<%
    int id = 1;
    int userid=1;
    DBConn con = new DBConn();
    Quiz quiz = con.getQuiz(id);
    ArrayList<User> u = con.getUsers(id);
    if (u.size() == 0){
        //handle
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
    <form action = "./quiz" method = "GET">
        <button class="test_start_button">Start</button>
     </form>
    <form action = "./quizPractice" method = "GET">
        <button class="test_start_button">Test</button>
    </form>
</div>
</body>
</html>
