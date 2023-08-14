<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Quiz.src.main.java.models.Achievement" %>
<%@ page import="Quiz.src.main.java.models.User" %>
<%@ page import="Quiz.src.main.java.models.Notification" %>
<%@ page import="Quiz.src.main.java.models.QuizHistory" %>
<%@ page import="Quiz.src.main.java.models.Quiz" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
  <title>User</title>
  <!-- <link rel="stylesheet" href="styles.css"> -->
</head>
<body>
  <style>.navbar {
    background-color: #007bff;
    align-items: center;
    display: flex;
}

.navbarItem {
    padding: 5px;
}

input.navbarSubItem {
    border: 2px solid #ccc;
    resize: none;
    padding: 5px;
}

button.navbarItem {
    padding: 20px;
    background-color: transparent;
    border: none;
    color: #ffffff;
    cursor: pointer;
    font-size: 20px;
    font-weight: bold;
}

.block-container {
    display: flex;
    /* align-items: center; */
    justify-content: center;
    margin-top: 10px;
    padding: 20px;
    border: 2px solid #007bff;
    border-radius: 12px;
}

.block-contents {
    display: flex;
    flex-direction: column;
    width: 100%;
}

.block-title {
    font-size: 40px;
}

.block-items {
    font-size: 25px;
}

.left-side {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-right: 20px;
}

.profile-image {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 5%;
}

.username {
    font-size: 24px;
    margin-top: 10px;
}

.right-side {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

.action-button {
    background-color: #2b91fe;
    color: white;
    border: 1px solid #000000;
    border-radius: 12px;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 5px;
    margin: 3px 3px 0 0;
}

.action-button:hover {
    background-color: #9dff0a;
    /* Change to the color you want on hover */
}

.remove-friend-button {
    background-color: #2b91fe;
    color: white;
    /* border: none; */
    border: 1px solid #000000;
    border-radius: 12px;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 5px;
}

.remove-friend-button:hover {
    background-color: #ff2c2c;
    /* Change to the color you want on hover */
}

.note_text {
    margin-top: 10px;
    margin-bottom: 0px;
    resize: none;
    font-size: 18px;
}

.achievement-icon {
    width: 30px;
    height: 30px;
}</style>
  <!-- get userId from context !!! -->
  <% 
    DBConn dbConn=new DBConn();
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/loginPage.jsp");
        return;
    }
    int userId= user.getId();
    String TargetId = request.getParameter("id");
    TargetId = TargetId == null ? ""+userId : TargetId;
    int targetId=Integer.parseInt(TargetId);

    String AddFriendText = request.getParameter("addfriendtext");
    AddFriendText = AddFriendText == null ? "Add friend" : AddFriendText;

    String friendButtonClass = "action-button";
    boolean friends = dbConn.areFriends(userId, targetId);
    String removeParam = "add";

    if (friends) { 
      AddFriendText = "Remove Friend";
      friendButtonClass = "remove-friend-button";
      removeParam = "remove";
    }

    String NotePHText = request.getParameter("notephtext");
    NotePHText = NotePHText == null ? "Send a note to user" : NotePHText;

    String ChallengeText = request.getParameter("challengetext");
    ChallengeText = ChallengeText == null ? "Challenge user to Quiz" : ChallengeText;

    ArrayList<User> us = dbConn.getUsers(targetId);
    User u = us.get(0);
    String userName = u.getUsername();
    String PfpLink = u.getPfpLink();
    PfpLink = PfpLink == null || PfpLink == "" ? "https://via.placeholder.com/200x200" : PfpLink;
  %>
  <div class="navbar">
    <a href="./home.jsp">
      <button class="navbarItem">Home</button>
    </a>
    <form class="navbarItem" action="./SearchUser" method="post">
      <input class="navbarSubItem" name="search_text" rows="1" cols="30" placeholder="Look up user"/>
      <button class="action-button">Search User</button>
    </form>

  </div>
  <div class="block-container">
    <div class="left-side">
      <img class="profile-image" src="<%= PfpLink %>" alt="Profile Image">
      <div class="username">
        <%= userName%>
      </div>
      <div class="user-id">User Id: <%= targetId %>
      </div>
    </div>
    <div class="right-side">
      <form action="./AddFriend" method="post">
        <button class="<%= friendButtonClass %>"><%= AddFriendText%></button>
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="targetId" value="<%= targetId %>">
        <input type="hidden" name="removeFriend" value="<%= removeParam %>">
      </form>
      <form action="./Challenge" method="post">
        <input name="quizId" class="note_text" placeholder="<%= ChallengeText%>" type="number" step="1"></input>
        <br>
        <button class="action-button">Challenge</button>
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="targetId" value="<%= targetId %>">
      </form>
      <form action="./Note" method="post">
        <textarea name="note_text" class="note_text" placeholder="<%= NotePHText %>" rows="4" cols="50"></textarea>
        <br>
        <button class="action-button">Send Note</button>
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="targetId" value="<%= targetId %>">
      </form>
    </div>
  </div>
  <div class="block-container">
    <div class="block-contents">
      <div class="block-title">History</div>
      <div class="block-items">
        <ul>
          <%
            ArrayList<QuizHistory> qhs = dbConn.GetUserQuizHistory(targetId);
            for(QuizHistory qh : qhs) {
              Quiz q = qh.getQuiz(); 
          %> 
          <li><%= String.format("%2.1f on '%s' by %s ", qh.getScore(), q.quiz_name, q.creatorName) %> </li> 
          <% 
            }
          %>
        </ul>
      </div>
    </div>
  </div>
  <div class="block-container">
    <div class="block-contents">
      <div class="block-title">Achievements</div>
      <div class="block-items">
        <ul>
          <%
            ArrayList<Achievement> uas = dbConn.getUserAchievements(targetId);
            for(Achievement ua : uas) {
          %> 
          <li><img class="achievement-icon" src="<%= ua.getAchievementIcon()%>"  title="<%= ua.getAchievementToEarn()%>"/> <%= ua.getAchievementBody()%> </li> 
          <% 
            } 
          %>
        </ul>
      </div>
    </div>
  </div>
  <% dbConn.closeDBConn(); %>
</body>
</html>
