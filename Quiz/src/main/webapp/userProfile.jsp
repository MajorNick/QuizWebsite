<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Quiz.src.main.java.models.Achievement" %>
<%@ page import="Quiz.src.main.java.models.DBConn" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
  <title>User</title>
</head>
<style>
  .navbar {
    background-color: #007bff;
    align-items: center;
    display: flex;
  }

  .navbarItem {
    padding: 5px;
  }

  input.navbarItem {
    border: 2px solid #ccc;
    resize: none;
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
    font-size: 20px;
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
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    margin-top: 10px;
    cursor: pointer;
    border-radius: 5px;
  }

  textarea.note_text {
    margin-top: 10px;
    margin-bottom: -10px;
    resize: none;
  }
</style>

<body>
  <% int userId=1;%>
  <% String TargetId=request.getParameter("id"); %>
  <% int targetId=Integer.parseInt(TargetId); %>
  <% //DBConn dbConn=new DBConn(); //ArrayList<User> us = dbConn.getUsers(targetId);
    //User u = us.get(0);
    //String userName = u.getUsername();
    String userName = "Josh Smith";
    //dbConn.closeDBConn();
  %>
  <div class="navbar">
    <button class="navbarItem">Home</button>
  </div>
  <div class="block-container">
    <div class="left-side">
      <img class="profile-image" src="https://via.placeholder.com/200x200" alt="Profile Image">
      <div class="username">
        <%= userName%>
      </div>
      <div class="user-id">User Id: <%= targetId %>
      </div>
    </div>
    <div class="right-side">
      <form action="./AddFriend" method="post">
        <button class="action-button">Add friend</button>
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="targetId" value="<%= targetId %>">
      </form>
      <form action="./Challenge" method="post">
        <textarea name="note_text" class="note_text" placeholder="Challenge user to Quiz Name / URL"
          rows="1" cols="50"></textarea>
        <br>
        <button class="action-button">Challenge</button>
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="targetId" value="<%= targetId %>">
      </form>
      <form action="./Note" method="post">
        <textarea name="note_text" class="note_text" placeholder="Send a note to user" rows="4"
          cols="50"></textarea>
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
        <ol>
          <li>List here</li>
          <li>List here</li>
          <li>List here</li>
          <li>List here</li>
        </ol>
      </div>
    </div>
  </div>
  <div class="block-container">
    <div class="block-contents">
      <div class="block-title">Achievements</div>
      <div class="block-items">
        <ol>
          <% DBConn dbConn=new DBConn(); ArrayList<Achievement> uas =
            dbConn.getUserAchievements(targetId);
            for(Achievement ua : uas) {
            %> <li> <%= ua.getAchievementBody()%> </li> <% 
          } %>
        </ol>
      </div>
    </div>
  </div>
</body>

</html>