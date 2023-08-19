<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.*" %>
<%@ page import="Quiz.src.main.java.models.enums.QuestionType" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Answer" %>
<%@ page import="Quiz.src.main.java.HelperMethods.AnswerChecker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>

    <style>
        .header {
            background-color: #087cfc;
        }

        header {
            color: white;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<style>
    .submit_button{
        background-color: #087cfc;
        border: none;
        margin-top: 10px;
        padding: 10px 15px;

        color: white;
        cursor: pointer;
        border-radius: 5px;
    }
</style>
<body>
<div class="header">
    <header style="color: white">
        Quiz
    </header>
</div>

<%
    HttpSession ses = request.getSession();
    Integer iterator = (Integer) ses.getAttribute("iterator");

    User user1 = (User) session.getAttribute("user");
    if (user1 == null) {
       response.sendRedirect(request.getContextPath() + "/MainPageServlet");
       return;
    }

    Boolean correction  = Boolean.valueOf(request.getParameter("correction"));
    int quizID = Integer.parseInt(request.getParameter("id"));;
    DBConn con = new DBConn();

    ArrayList<Question> questions = con.getQuestions(quizID);

    if(iterator == null) {
        iterator = 0;
    }

    String action = request.getParameter("action");
    if ("Next".equals(action)) {
        iterator++;
    } else if ("Prev".equals(action)) {
        iterator--;
    }else if ("submit".equals(action)) {
        %>
    <%
            if (questions.get(iterator).type == QuestionType.QUESTION_RESPONSE){
                String responseAnswer = request.getParameter("response_answer");
                double score = AnswerChecker.checkAnswer(questions.get(iterator).id,responseAnswer);
            }
    }else if( action != null){
        try {
            int selected = Integer.parseInt(action);
            iterator = selected - 1;
        } catch (NumberFormatException e) {
            %> <h1> erroooooooor while parsing request</h1> <%
        }
    }
    session.setAttribute("iterator", iterator);



    if (iterator >= 0 && iterator < questions.size()) {
        Question question = questions.get(iterator);
        QuestionType questionType = question.type;
%>

<form action=<%="quizSinglePage.jsp?id="+quizID+(correction?"&correction=true":"")%> method="post">
    <p>Question <%= iterator + 1 %>: <%= question.question %></p>

    <% if (questionType == QuestionType.QUESTION_RESPONSE) { %>
    <input type="text" name="response_answer">

    <% } else if (questionType == QuestionType.FILL_IN_THE_BLANK) { %>
    <input type="text" name="fill_in_the_blank_answer">

    <% } else if (questionType == QuestionType.MULTIPLE_CHOICE) { %>
    <%
        ArrayList<Answer> answers = con.getAnswers(question.id,false);
        for (int j = 0; j < answers.size(); j++) {
    %>
    <input type="radio" name="multiple_choice_answer" value="<%= answers.get(j).answer %>">
    <%= answers.get(j).answer %><br>
    <%
        }
    %>

    <% } else if (questionType == QuestionType.PICTURE_RESPONSE) { %>
    <input type="text" name="picture_response_answer">

    <% } else if (questionType == QuestionType.MULTI_ANSWER) { %>
    <%
        ArrayList<Answer> answers = con.getAnswers(question.id,false);
        for (int j = 0; j < answers.size(); j++) {
    %>
    <input type="text" name="multi_answer<%= j %>_1">
    <%
        }
    %>

    <% } else if (questionType == QuestionType.MULTI_AN_CHOICE) { %>
    <%
        ArrayList<Answer> answers = con.getAnswers(question.id,false);
        for (int j = 0; j < answers.size(); j++) {
    %>
    <input type="checkbox" name="multi_choice_answer" value="<%= answers.get(j).answer %>">
    <%= answers.get(j).answer %><br>
    <%
        }
    %>
    <% } %>

    <div>
        <input class="submit_button" type="submit" name="action" value="Prev">
        <input class="submit_button" type="submit" name="action" value="Next">
        <% if (correction) { %>
        <input class="submit_button" type="submit" name="action" value="submit">
        <%}%>
    </div>
    <div>
        <%
            for(int i=0;i<questions.size();i++){ %>
        <input class="submit_button" type="submit" name="action" value=<%=Integer.toString(i+1)%>>
        <%
            }
        %>
    </div>

</form>
<%
} else {
        ses.setAttribute("iterator",null);
%>

<p>Quiz completed.</p>
<%
    }
%>
</body>
</html>