<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="Quiz.src.main.java.models.*" %>
<%@ page import="Quiz.src.main.java.models.enums.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="Quiz.src.main.java.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.Duration" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="Quiz.src.main.java.models.Question" %>
<%@ page import="Quiz.src.main.java.models.Answer" %>
<%@ page import="Quiz.src.main.java.models.enums.QuestionType" %>
<%@ page import="Quiz.src.main.java.HelperMethods.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Results</title>
      <style>
          body {
              font-family: Arial, sans-serif;
              margin: 0;
              padding: 0;
              background-color: #f4f4f4;
          }
        .container {
            max-width: 1600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
          header {
              background-color: #007bff;
              color: #ffffff;
              text-align: center;
              padding: 10px;
              margin-bottom: 20px;
              border-top-left-radius: 10px;
              border-top-right-radius: 10px;
          }
          .score {
              text-align: center;
              font-size: 24px;
              color: #007bff;
              margin-top: 20px;
              font-weight: bold;
          }
          h2 {
              margin-top: 20px;
              text-align: center;
          }
          .section {
              background-color: #f8f8f8;
              padding: 15px;
              border-radius: 5px;
              margin-bottom: 20px;
              box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
          }
          table {
              width: 100%;
              border-collapse: collapse;
              margin-top: 10px;
          }
          th, td {
              padding: 10px;
              border-bottom: 1px solid #dddddd;
          }
          th {
              background-color: #f5f5f5;
              text-align: left;
              color: #333333;
          }
          footer {
              background-color: #f4f4f4;
              text-align: center;
              padding: 10px;
              margin-top: 20px;
              border-bottom-left-radius: 10px;
              border-bottom-right-radius: 10px;
          }
          .time-taken {
              font-weight: bold;
              color: black;
          }
          .tables-row {
              display: flex;
              align-items: center;
          }
          .table-container {
              flex: 1;
              margin-right: 20px;
          }
      </style>
</head>

<%
DBConn con = new DBConn();
int quizID = Integer.parseInt(request.getParameter("id"));
int totalScore = 0;
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect(request.getContextPath() + "/MainPageServlet");
    return;
}

Quiz quiz = con.getQuiz(quizID);
HttpSession ses = request.getSession();
ArrayList<Question> questions = (ArrayList)ses.getAttribute("shuffledQuestions");
List<Answer> correctAnswers1 = new ArrayList<Answer>();
for(int i = 0; i < questions.size(); i++){
    List<Answer> correctAnswers2 = con.getAnswers(questions.get(i).id,true);
    correctAnswers1.addAll(correctAnswers2);
}

int esa = -1;

for(int i = 0; i < questions.size(); i++){
    Question question = questions.get(i);
    QuestionType questionType = question.type;
    List<Answer> correctAnswers = con.getAnswers(questions.get(i).id,true);


    if (questionType == QuestionType.QUESTION_RESPONSE){
        String answer =  request.getParameter("question" + i);
        if(answer != null)
        if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

    } else if(questionType == QuestionType.FILL_IN_THE_BLANK){
        String answer =  request.getParameter("question" + i);
        if(answer != null)
        if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

    } else if(questionType == QuestionType.MULTIPLE_CHOICE){
        String answer =  request.getParameter("question" + i);
        if(answer != null)
        if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

    } else if(questionType == QuestionType.PICTURE_RESPONSE) {
        String answer = request.getParameter("question" + i);
        if(answer != null)
        if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

    } else if (questionType == QuestionType.MULTI_ANSWER){
        ArrayList<String> userAnswers = new ArrayList<String>();
        for(int j = 0; j < correctAnswers.size(); j++){
            userAnswers.add(request.getParameter("question" + i +"_"+ j));
        }
        Map<String, Integer> correctFrequencyMap = new HashMap<String, Integer>();
        for (Answer curAnswer : correctAnswers) {
            correctFrequencyMap.put(curAnswer.answer, correctFrequencyMap.getOrDefault(curAnswer.answer, 0) + 1);
        }

        for (int k = 0; k < userAnswers.size(); k++) {
            String userAnswer = userAnswers.get(k);

            if(userAnswer != null)
            if (correctFrequencyMap.containsKey(userAnswer) && correctFrequencyMap.get(userAnswer) > 0) {
                correctFrequencyMap.put(userAnswer, correctFrequencyMap.get(userAnswer) - 1);
                totalScore++;
            }
        }

    } else if (questionType == QuestionType.MULTI_AN_CHOICE){
        ArrayList<String> userAnswers = new ArrayList<String>();
        for(int j = 0; j < correctAnswers.size(); j++){
            userAnswers.add(request.getParameter("question" + i +"_"+j));
        }

        for(int k = 0; k < userAnswers.size(); k++){
            for(int j = 0; j < correctAnswers.size(); j++){
                if(userAnswers.get(k) != null)
                if(userAnswers.get(k).equalsIgnoreCase(correctAnswers.get(j).answer)){
                    totalScore++;
                }
            }
        }
    }
}
%>

<%

con.insertQuizHistory(new QuizHistory(1, totalScore, quizID, user.getId(), (int)((Duration)session.getAttribute("time")).toSeconds()));

%>



<body>
    <div class="container">
        <header>
            <h1>Quiz Results</h1>
        </header>
        <div class="score">
            Your Score: <%=totalScore%> out of <%=correctAnswers1.size()%>
             <br>
            <span class="time-taken">Time taken: <%=time_taken%>> minutes</span>
        </div>

        <div class="tables-row">
            <div class="table-container">
                <h2>Your Answers and Correct Answers:</h2>
                <table class="answers-details">
                    <tr>
                        <th>Question #</th>
                        <th>Your Answer </th>
                        <th>Correct Answer</th>
                    </tr>
                    <%
                    for(int i = 0; i < 1; i++) {%>
                        <tr>
                            <td>1</td>
                            <td>Option B</td>
                            <td>Option A</td>
                        </tr>
                    <% }%>
                </table>
            </div>
            <div class="table-container">
                <h2>Your Last Tries:</h2>
                <br>
                <table class="last-tries">
                    <tr>
                        <th>Name</th>
                        <th>Score</th>
                        <th>Time Taken</th>
                    </tr>
                    <tr>
                        <td>Attempt 1</td>
                        <td><%=totalScore%>/<%=correctAnswers1.size()%></td>
                        <td>12 minutes <%=esa%></td>
                    </tr>
                </table>
            </div>
            <div class="table-container">
                <h2>Top Scorers:</h2>
                <br>
                <table class="top-scorers">
                    <tr>
                        <th>Name</th>
                        <th>Score</th>
                        <th>Time Taken</th>
                    </tr>
                    <tr>
                        <td>Top Scorer 1</td>
                        <td>9/10</td>
                        <td>9 minutes</td>
                    </tr>
                </table>
            </div>
            <div class="table-container">
                <h2>Your Friends:</h2>
                <br>
                <table class="friends">
                    <tr>
                        <th>Name</th>
                        <th>Score</th>
                        <th>Time Taken</th>
                    </tr>
                    <tr>
                        <td>Your Friend 1</td>
                        <td>9/10</td>
                        <td>9 minutes</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div>
        <h2>Rate and Review</h2>
            <form action="./SaveReview" method="post">
                <input type="hidden" name="quizId" value="<%= 1 %>">
                <input type="hidden" name="userId" value="<%= 1 %>">
                <label for="rating">Rating:</label>
                <select id="rating" name="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <br>
                <label for="review">Review:</label>
                <textarea id="review" name="review" rows="4" cols="50"></textarea>
                <br>
                <input type="submit" value="Submit">
            </form>
    </div>
</body>
</html>
