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
    .header {
        background-color: #087cfc;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
    }

    .lft {
        flex: 1;
    }

    .rgt {
        display:flex;
        margin-left: 10px;
    }
    .center {
        flex: 2;
        text-align: center;
    }

    .user-link {
        color: white;
        font-size: 20px;
        font-weight: bold;
        text-decoration: none;
    }


    .action-button {
        background-color: #e74c3c;
        color: white;
        padding: 10px 20px;
        text-align: center;
        display: inline-block;
        font-size: 15px;
        border-radius: 5px;
    }

    .action-button:hover {
        background-color: #c0392b;
    }

    header {
        color: white;
        font-size: 24px;
        font-family: Courier, monospace;
    }
    body {
        font-family: Courier, monospace;
    }
    .navbarItem {
        padding: 5px;
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


<body>
<%
    User user1 = (User) session.getAttribute("user");
    if (user1 == null) {
        response.sendRedirect(request.getContextPath() + "/MainPageServlet");
        return;
    }
%>

</div>
<%


    int userId = user1.getId();
    int id = Integer.parseInt(request.getParameter("id"));

    //int id = 1;
    //int userid=1;
    DBConn con = new DBConn();
    Quiz quiz = con.getQuiz(id);
    ArrayList<User> u = con.getUsers(userId);

    User user = u.get(0);
    User creator = con.getUsers(quiz.creator_id).get(0);
    int quizid = quiz.id;
    HttpSession ses = request.getSession();
    ses.setAttribute("iterator",null);

%>



<div class  = header>
    <div class= "lft" >
    <header style = "color: white">
        Quiz Summary
    </header>
    </div>
    <div class="center">
        <a href="/userProfile.jsp" class="user-link">Logged in as <%= user1.getUsername() %></a>
    </div>
    <div class= "rgt">
        <% if ((user1.isAdmin())) { %>
            <form class="navbarItem" action="./RemoveQuizHistory" method="post">
              <button id = "id" class="action-button">Remove QuizHistory</button>
              <input type="hidden" name="quizid" value="<%= quizid %>">
            </form>
            <form class="navbarItem" action="./RemoveQuiz" method="post">
              <button id = "id1" class="action-button">Remove Quiz</button>
              <input type="hidden" name="quizid" value="<%= quizid %>">
            </form>
        <% } %>
    </div>

</div>
<% if (quiz.description != null){ %>
<div>
    <h2>
        Quiz Description
    </h2>
    <p>
        <%=quiz.description%>
    </p>
<%}%>
</div>

<div>
    <p>Quiz Creator: <a href = <%="../userProfile.jsp?id="+quiz.creator_id%>><%=creator.getUsername()%></a></p>
</div>

<div class = best_performances>
    <h3>Best Performances</h3>
    <%

    %>
    <%
        ArrayList<Integer> best = con.getYourBestPerformance(id,userId);
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

    <form id="quizForm" action=<%=!quiz.is_single_page?"/quiz.jsp?id=" +quizid:"/quizSinglePage.jsp?id="+quizid%> method="POST">
        <button class="test_start_button">Start</button>
    </form>
    <% if (quiz.can_be_practiced) { %>
    <form id="quizPracticeForm" action=<%="/preparePractice?id="+quiz.id%> method="POST">
        <button class="test_start_button">Test</button>
    </form>
    <% } %>
    <% if (quiz.is_single_page) { %>
    <label>
        <input type="checkbox" id="immediateCorrection" name="checkType" value="immediateCorrection"> Immediate Correction
    </label>
    <% }
    if (quiz.is_single_page){
    %>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const checkbox = document.getElementById('immediateCorrection');
            const quizForm = document.getElementById('quizForm');
            const quizPracticeForm = document.getElementById('quizPracticeForm');
            checkbox.addEventListener('change', function () {
                const isChecked = checkbox.checked;
                quizForm.action = isChecked ? '<%="/quizSinglePage.jsp?id="+quiz.id+"&correction=true"%>' :'<%="/quizSinglePage.jsp?id="+quiz.id%>';
                quizPracticeForm.action = isChecked ? '/preparePractice' : '/preparePractice';
            });

        });

    </script>
    <% } %>

</div>


</body>
</html>
