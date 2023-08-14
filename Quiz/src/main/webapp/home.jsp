<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Quiz.src.main.java.models.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
    <head>
        <title>Home</title>
    </head>

    <style>
        .navbar {
            background-color: #007bff;
            overflow: hidden;
        }

        .navbar a {
            float: right;
            display: block;
            color: white;
            text-align: center;
            padding: 15px 16px;
            text-decoration: none;
            font-size: 20px;
            font-weight: bold;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
        }

        .left {
            flex: 2;
            padding: 10px;
        }

        .right {
            flex: 1;
            padding: 10px;
        }

        .announcements{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            text-align: center;
            margin: 10px;
        }

        .announcementsList{
            list-style-type: none;
        }

        .popularQuizzes{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .popularQuizzesList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .recentlyCreatedQuizzes{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .recCrQuizList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .recentlyTakenQuizzes{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .recTakenQuizList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .myRecentlyCreatedQuizzes{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .myRecCrQuizList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .notifications{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .notificationsList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .achievements{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .achievementsList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

        .achievement-icon {
            width: 30px;
            height: 30px;
        }

        .friendsActivities{
            border: 5px solid #1c98ff;
            border-radius: 15px;
            padding: 10px;
            margin: 10px;
        }

        .frUsername{
            font-size: 20px;
            font-weight: bold;
        }

        .frAchievementsList {
            list-style-type: none;
            display: flex;
            gap: 20px;
        }

    </style>
    <body>
      <%
        DBConn dbConn=new DBConn();
        int userId=1;
        String TargetId=request.getParameter("id");
        TargetId = TargetId == null ? ""+userId : TargetId;
        int targetId=Integer.parseInt(TargetId);
        ArrayList<User> us = dbConn.getUsers(targetId);
        User u = us.get(0);
        String userName = u.getUsername();
      %>
        <div class="navbar">
            <a>Logged in as <%= userName %></a>
        </div>

        <div class = "announcements">
            <h1>Announcements</h1>
            <ul class="announcementsList">
                <%
                  ArrayList<Announcement> as = dbConn.getAnnouncements();
                  for(Announcement a : as) {
                %>
                  <li> <%= a.getAnnouncementBody() %> </li>
                <%
                  }
                %>
            </ul>
        </div>

        <div class="container">
            <div class="left">
                <div class = "popularQuizzes">
                    <h1>Popular Quizzes</h1>
                    <ul class = "popularQuizzesList">
                        <%
                          ArrayList<Quiz> qs = dbConn.getPopularQuizzes();
                          for(Quiz q : qs) {
                        %>
                        <li> <%= q.quiz_name %> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>

                <div class = "recentlyCreatedQuizzes">
                    <h1>Recently Created Quizzes</h1>
                    <ul class="recCrQuizList">
                        <%
                          ArrayList<Quiz> rcqs = dbConn.getRecentlyCreatedQuizzes(-1);
                          for(Quiz q : rcqs) {
                        %>
                        <li> <%= q.quiz_name %> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>

                <div class = "recentlyTakenQuizzes">
                    <h1>Recently Taken Quizzes</h1>
                    <ul class="recTakenQuizList">
                        <%
                          ArrayList<QuizHistory> tqs = dbConn.getUserRecentQuizHistory(targetId);
                          for(QuizHistory tq : tqs) {
                        %>
                        <li> <%= dbConn.getQuizById(tq.getQuiz_id()).quiz_name %> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>

                <div class = "myRecentlyCreatedQuizzes">
                    <h1>My Recently Created Quizzes</h1>
                    <ul class="myRecCrQuizList">
                        <%
                          ArrayList<Quiz> mqs = dbConn.getRecentlyCreatedQuizzes(targetId);
                          for(Quiz mq : mqs) {
                        %>
                        <li> <%= mq.quiz_name %> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>
            </div>

            <div class="right">
                <div class = "notifications">
                    <h1>Notifications</h1>
                    <ul>
                        <%
                          ArrayList<Notification> ns = dbConn.getNotifications(targetId, -1, "");
                          for(Notification n : ns) {
                        %>
                        <li> <%= n.getNotifBody()%> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>
                <div class = "achievements">
                    <h1>Achievements</h1>
                    <ul class="achievementsList">
                        <%
                          ArrayList<Achievement> uas = dbConn.getUserAchievements(targetId);
                          for(Achievement ua : uas) {
                        %>
                          <li><img class="achievement-icon" src="<%= ua.getAchievementIcon()%>"  title="<%= ua.getAchievementToEarn()%>"/> <br> <%= ua.getAchievementBody()%> </li>
                        <%
                          }
                        %>
                    </ul>
                </div>

                <div class = "friendsActivities">
                    <h1>Friend&rsquo;s Activities</h1>
                    <ul class="frActivitiesList">
                        <%
                          ArrayList<Friend> fs = dbConn.getUserFriends(targetId);
                          for(Friend f : fs) {
                            int friendId = f.getFriend_id();
                            User friend = dbConn.getUsers(friendId).get(0);
                        %>
                          <dl>
                            <dt class="frUsername"><%= friend.getUsername() %></dt>
                            <dd>
                                <h3>Achievements:</h3>
                                <ul class="frAchievementsList">
                                <%
                                   ArrayList<Achievement> fas = dbConn.getUserAchievements(friendId);
                                   for(Achievement fa : fas) {
                                %>
                                <li><img class="achievement-icon" src="<%= fa.getAchievementIcon()%>"  title="<%= fa.getAchievementToEarn()%>"/> <br> <%= fa.getAchievementBody()%> </li>
                                <% } %>
                                </ul>

                                <h3>Created Quizzes:</h3>
                                <ul>
                                <%
                                   ArrayList<Quiz> cqs = dbConn.getRecentlyCreatedQuizzes(friendId);
                                   for(Quiz cq : cqs) {
                                %>
                                <li><%= cq.quiz_name %></li>
                                <% } %>
                                </ul>
                            </dd>

                          </dl>
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
