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
        color: white;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 20px;
    }

    .navbar a {
        color: white;
        text-decoration: none;
        margin-left: 20px;
        font-size: 18px;
    }
    .navbar {
        background-color: #007bff;
        overflow: hidden;
    }

    .link {
        color: black;
        text-decoration: none;
        margin-left: 20px;
        font-size: 18px;
        font-weight: bold;
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

    .navbarItem {
        padding: 5px;
    }

    .middle-button {
        color: white;
        text-align: center;
        padding: 10px 20px;
        text-decoration: none;
        font-size: 18px;
        font-weight: bold;
        background-color: #000000;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .middle-button:hover {
        background-color: #ff0000;
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

    .announcements {
        border: 5px solid #1c98ff;
        border-radius: 15px;
        text-align: center;
        margin: 10px;
    }

    .announcementsList {
        list-style-type: none;
    }

    .box {
        border: 5px solid #1c98ff;
        border-radius: 15px;
        padding: 10px;
        margin: 10px;
    }

    .achievement-icon {
        width: 30px;
        height: 30px;
    }

    .frUsername {
        font-size: 20px;
        font-weight: bold;
    }

    .list {
        list-style-type: none;
        display: flex;
        gap: 20px;
    }

    .quizButton {
        background-color: #007bff;
        color: white;
        padding: 8px 16px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    }

    .quizButton:hover {
        background-color: #0056b3;
    }
</style>

<body>
    <%  DBConn dbConn=new DBConn();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/MainPageServlet");
            return;
        }
        ArrayList<User> users = dbConn.getUsersByUsername(user.getUsername());
        if(users.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/MainPageServlet");
            return;
        }
        int userId= user.getId();
        String TargetId = request.getParameter("id");
        TargetId = TargetId == null ? ""+userId : TargetId;
        int targetId=Integer.parseInt(TargetId);
        // targetId = 1;
        String userName = user.getUsername();
        %>

         <div class="navbar">
            <a class = "link" href="<%= request.getContextPath() %>/userProfile.jsp">Logged in as <%= userName %></a>
            <form class="navbarItem" action="./SearchUser" method="post">
              <input class="navbarSubItem" name="search_text" rows="1" cols="30" placeholder="Look up user"/>
              <button class="action-button">Search User</button>
            </form>
            <a href="<%= request.getContextPath() %>/createQuiz/index.jsp" class="middle-button"> Create Quiz </a>
            <form class="navbarItem" action="./AddCategory" method="post">
              <input class="navbarSubItem" name="add_text" rows="1" cols="30" placeholder="Add quiz category"/>
              <button class="action-button">Add Category</button>
            </form>
            <a href="<%= request.getContextPath() %>/LogoutServlet">Log Out</a>
        </div>

        <div class="announcements">
            <h1>Announcements</h1>
            <ul class="announcementsList">
                <% ArrayList<Announcement> as = dbConn.getAnnouncements();
                    for(Announcement a : as) {
                    %>
                    <li>
                        <%= a.getAnnouncementBody() %>
                    </li>
                    <% } %>
            </ul>
        </div>

        <div class="container">
            <div class="left">
                <div class="box">
                    <h1>Popular Quizzes</h1>
                    <ul class="list">
                        <% ArrayList<Quiz> qs = dbConn.getPopularQuizzes();
                            for(Quiz q : qs) {
                            %>
                            <li> <a href="<%= " quizSummary.jsp?id=" + q.id %>">
                                    <button class="quizButton">
                                        <%= q.quiz_name %>
                                    </button>
                                </a>
                            </li>
                            <% } %>
                    </ul>
                </div>

                <div class="box">
                    <h1>Recently Created Quizzes</h1>
                    <ul class="list">
                        <% ArrayList<Quiz> rcqs = dbConn.getRecentlyCreatedQuizzes(-1);
                            for(Quiz q : rcqs) {
                            %>
                            <li> <a href="<%= " quizSummary.jsp?id=" + q.id %>">
                                     <button class="quizButton">
                                         <%= q.quiz_name %>
                                     </button>
                                 </a>
                            </li>
                            <% } %>
                    </ul>
                </div>

                <div class="box">
                    <h1>Recently Taken Quizzes</h1>
                    <ul class="list">
                        <% ArrayList<QuizHistory> tqs = dbConn.getUserRecentQuizHistory(targetId);
                            for(QuizHistory tq : tqs) {
                            %>
                            <li> <a href="<%= " quizSummary.jsp?id=" + tq.getQuiz_id() %>">
                                     <button class="quizButton">
                                         <%= dbConn.getQuizById(tq.getQuiz_id()).quiz_name %>
                                     </button>
                                 </a>
                            </li>
                            <% } %>
                    </ul>
                </div>

                <div class="box">
                    <h1>My Recently Created Quizzes</h1>
                    <ul class="list">
                        <% ArrayList<Quiz> mqs = dbConn.getRecentlyCreatedQuizzes(targetId);
                            for(Quiz mq : mqs) {
                            %>
                            <li> <a href="<%= " quizSummary.jsp?id=" + mq.id %>">
                                     <button class="quizButton">
                                         <%= mq.quiz_name %>
                                     </button>
                                 </a>
                            </li>
                            <% } %>
                    </ul>
                </div>
            </div>

            <div class="right">
                <div class="box">
                    <h1>Notifications</h1>
                    <ul>
                        <% ArrayList<Notification> ns = dbConn.getNotifications(targetId, -1, "");
                            for(Notification n : ns) {
                            %>
                            <li>
                                <%= n.getNotifBody()%>
                            </li>
                            <% } %>
                    </ul>
                </div>
                <div class="box">
                    <h1>Achievements</h1>
                    <ul class="list">
                        <% ArrayList<Achievement> uas = dbConn.getUserAchievements(targetId);
                            for(Achievement ua : uas) {
                            %>
                            <li><img class="achievement-icon" src="<%= ua.getAchievementIcon()%>"
                                    title="<%= ua.getAchievementToEarn()%>" /> <br>
                                <%= ua.getAchievementBody()%>
                            </li>
                            <% } %>
                    </ul>
                </div>

                <div class="box">
                    <h1>Friend&rsquo;s Activities</h1>
                    <ul class="frActivitiesList">
                        <% ArrayList<Friend> fs = dbConn.getUserFriends(targetId);
                            for(Friend f : fs) {
                            int friendId = f.getFriend_id();
                            User friend = dbConn.getUsers(friendId).get(0);
                            %>
                            <dl>
                                <dt class="frUsername">
                                    <a href="userProfile.jsp?id=<%= friend.getId() %>">
                                        <%= friend.getUsername() %>
                                    </a>
                                </dt>
                                <dd>
                                    <h3>Achievements:</h3>
                                    <ul class="list">
                                        <% ArrayList<Achievement> fas =
                                            dbConn.getUserAchievements(friendId);
                                            for(Achievement fa : fas) {
                                            %>
                                            <li><img class="achievement-icon"
                                                    src="<%= fa.getAchievementIcon()%>"
                                                    title="<%= fa.getAchievementToEarn()%>" /> <br>
                                                <%= fa.getAchievementBody()%>
                                            </li>
                                            <% } %>
                                    </ul>

                                    <h3>Created Quizzes:</h3>
                                    <ul class = "list">
                                        <% ArrayList<Quiz> cqs = dbConn.getRecentlyCreatedQuizzes(friendId);
                                            for(Quiz cq : cqs) {
                                            %>
                                            <li> <a href="<%= " quizSummary.jsp?id=" + cq.id %>">
                                                     <button class="quizButton">
                                                         <%= cq.quiz_name %>
                                                     </button>
                                                 </a>
                                            </li>
                                            <% } %>
                                    </ul>
                                </dd>

                            </dl>
                            <% } %>
                    </ul>
                </div>
            </div>
        </div>
        <% dbConn.closeDBConn(); %>
</body>

</html>