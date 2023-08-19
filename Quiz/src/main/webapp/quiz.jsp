<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.enums.QuestionType" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Answer" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: majornick
  Date: 11.08.23
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    body {
        font-family: Courier, monospace;
    }

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
<html>
<style>
    .header{
        background-color: #087cfc;
    }
    header {
        color: white;
        font-size: 24px;
    }
</style>
<body>
<div class  = header>
    <header style = "color: white">
        Quiz
    </header>
</div>

<form action="ProcessAnswers" method="post">
    <%
        int quizID = Integer.parseInt( request.getParameter("id"));
        DBConn con = new DBConn();


        ArrayList<Question> questions = con.getQuestions(quizID);
        Quiz quiz = con.getQuiz(quizID);
        if (!quiz.rand_question_order){
            Collections.shuffle(questions);
        }
        %> <p>length is :  <%=questions.size() %></p>
    <%
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            QuestionType questionType = question.type;
    %>


    <% if (questionType == QuestionType.QUESTION_RESPONSE) { %>
    <p>Question <%= i + 1 %>: <%= question.question %></p>
    <input type="text" name="respons_q<%= i %>">

    <% } else if (questionType == QuestionType.FILL_IN_THE_BLANK) { %>
    <p>Question <%= i + 1 %>: <%= question.question %></p>
    <input type="text" name="fill_in_the_blank_q<%= i %>">

    <% } else if (questionType == QuestionType.MULTIPLE_CHOICE) { %>
    <p>Question <%= i + 1 %>: <%= question.question %></p>
    <%
        ArrayList < Answer>  answers = con.getAnswers(questions.get(i).id,false);
        for(int j=0;j<answers.size();j++){
            %>
            <input type="radio" name="multiple_choice_q<%= i %>" value=<%=answers.get(j).answer%>> <%=answers.get(j).answer%><br>

  <%
     }
  }else if (questionType == QuestionType.PICTURE_RESPONSE) { %>

    <input type="text" name="picture_response_q<%= i %>">

    <% } else if (questionType == QuestionType.MULTI_ANSWER) { %>
    <p>Question <%= i + 1 %>: <%= question.question %></p>
    <%
        ArrayList < Answer>  answers = con.getAnswers(questions.get(i).id,false);
        for(int j=0;j<answers.size();j++){
    %>
    <input type="text" name="multi_answer_q<%= j %>_1">


    <% }
        } else if (questionType == QuestionType.MULTI_AN_CHOICE) { %>
    <p>Question <%= i + 1 %>: <%= question.question %></p>
    <%
        ArrayList < Answer>  answers = con.getAnswers(questions.get(i).id,false);
        for(int j=0;j<answers.size();j++){
    %>
    <input type="checkbox" name="multi_choice_q<%= i %>_a" value="<%= answers.get(j).answer %>"> <%= answers.get(j).answer %><br>


    <% }} }%>


    <div>
        <input class=submit_button type="submit" value="Submit">
    </div>

</form>
</body>
</html>