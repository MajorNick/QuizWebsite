<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.enums.QuestionType" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Answer" %>
<%@ page import="Quiz.src.main.java.HelperMethods.AnswerChecker" %>
<%@ page import="java.time.LocalTime" %>
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

    boolean correction  = Boolean.parseBoolean(request.getParameter("correction"));
    int quizID = Integer.parseInt(request.getParameter("id"));;
    DBConn con = new DBConn();

    ArrayList<Question> questions = con.getQuestions(quizID);

    if(iterator == null) {
        LocalTime quizStartTime = LocalTime.now();
        ses.setAttribute("quizStartTime", quizStartTime);
        iterator = 0;
    }else{
        Question quest = questions.get(iterator);
        if(quest.isMultiAnswerType()){

            ArrayList<Answer> arr = con.getAnswers(quest.id,false);
            ArrayList<String> tmp =new ArrayList<String>();

            for(int i=0;i<arr.size();i++){
                String key = String.format("question%d_%d",iterator,i);
                tmp.add(request.getParameter(key));

            }

            ses.setAttribute("question"+iterator,tmp);
        }else{

            ArrayList<String> tmp =new ArrayList<String>();
            tmp.add(request.getParameter("question"+iterator));
            ses.setAttribute("question"+iterator,tmp);
        }
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
    }else if("End Quiz".equals(action)){
        ses.setAttribute("iterator",null);
        response.sendRedirect("ProcessAnswers");
        }else if( action != null){
        try {
            int selected = Integer.parseInt(action);
            iterator = selected - 1;
        } catch (NumberFormatException e) {
            %> <h1> erroooooooor while parsing request</h1> <%
        }
    }
    ses.setAttribute("iterator", iterator);

    if (iterator >= 0 && iterator < questions.size()) {
        Question question = questions.get(iterator);
        QuestionType questionType = question.type;
%>

<form action=<%="quizSinglePage.jsp?id="+quizID+(correction?"&correction=true":"")%> method="post">
    <p>Question <%= iterator + 1 %>: <%= question.question %></p>

    <% if (questionType == QuestionType.QUESTION_RESPONSE) {
        ArrayList<String> answ = (ArrayList) ses.getAttribute("question"+iterator);
        String v = "";
        if(answ != null) {
             v = answ.get(0) == null ? "" : answ.get(0);
        }
    %>
    <input type="text" name=<%="question"+iterator%>  value=<%=v%> >

    <% } else if (questionType == QuestionType.FILL_IN_THE_BLANK) {
    ArrayList<String> answ = (ArrayList) ses.getAttribute("question"+iterator);
    String v = "";
    if(answ != null) {
        v = answ.get(0) == null ? "" : answ.get(0);
    } %>
    <input type="text" name=<%="question"+iterator%> value=<%=v%>>

    <% } else if (questionType == QuestionType.MULTIPLE_CHOICE) { %>
    <%
        ArrayList<Answer> answers = con.getAnswers(question.id,false);
        ArrayList<String> selected = (ArrayList<String>) ses.getAttribute("question" + iterator);
        String selectedAnswer = selected==null ?"":selected.get(0);
        for (int j = 0; j < answers.size(); j++) {

            boolean isSelected = answers.get(j).answer.equals(selectedAnswer);
    %>
    <input type="radio" name=<%="question"+iterator%> value="<%= answers.get(j).answer %>" <%= isSelected ? "checked" : "" %>>
    <%= answers.get(j).answer %><br>
    <%
        }
    %>

    <% } else if (questionType == QuestionType.PICTURE_RESPONSE) {
        ArrayList<String> answ = (ArrayList) ses.getAttribute("question"+iterator);
        String v = "";
        if(answ != null) {
            v = answ.get(0) == null ? "" : answ.get(0);
        } %>
    <input type="text" name=<%="question"+iterator%> value=<%=v%>>

    <% } else if (questionType == QuestionType.MULTI_ANSWER) { %>
    <%

        ArrayList<Answer> answers = con.getAnswers(question.id,true);
        ArrayList<String> definedAnswers = (ArrayList) ses.getAttribute("question"+iterator);

        for (int j = 0; j < answers.size(); j++) {
            String v = "";
            if(definedAnswers != null) {

                v = definedAnswers.get(j) == null ? "" : definedAnswers.get(j);

            } %>
    <input type="text" name=<%=String.format("question%d_%d",iterator,j)%> value=<%=v%>>
    <%
        }
    %>

    <% } else if (questionType == QuestionType.MULTI_AN_CHOICE) { %>
    <%
        ArrayList<String> selected = (ArrayList<String>) ses.getAttribute("question" + iterator);
        ArrayList<Answer> answers = con.getAnswers(question.id,false);

        for (int j = 0; j < answers.size(); j++) {
            boolean isSelected = selected != null && selected.contains(answers.get(j).answer);
    %>
    <input type="checkbox" name=<%="question"+iterator+"_"+j%> value="<%= answers.get(j).answer %>"<%= isSelected ? "checked" : "" %>>
    <%= answers.get(j).answer %><br>
    <%
        }
    %>
    <% } %>

    <div>
        <%
            if (iterator != 0){
        %>
        <input class="submit_button" type="submit" name="action" value="Prev">
        <% } %>
        <%
            if (iterator != questions.size()-1){
        %>
        <input class="submit_button" type="submit" name="action" value="Next">
        <% } %>
        <% if (correction) { %>
        <input class="submit_button" type="submit" name="action" value="submit">
        <%}%>
        <input class="submit_button" type="submit" name="action" value="End Quiz">
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