<html>
    <head>
        <title>Home</title>
    </head>

        <style>
            .navbar {
                background-color: #007bff;
                overflow: hidden;
            }

            .navbar a {
                float: left;
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            .navbar a.right {
                float: right;
            }

            /* Style the dropdown menu */
            .dropdown {
                float: right;
                overflow: hidden;
            }

            .dropdown .dropbtn {
                font-size: 16px;
                border: none;
                outline: none;
                color: white;
                padding: 14px 16px;
                background-color: inherit;
                font-family: inherit;
                margin: 0;
            }

            .navbar a:hover, .dropdown:hover .dropbtn {
                background-color: #1c98ff;
                color: black;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                float: none;
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
            }

            .dropdown-content a:hover {
                background-color: #ddd;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .announcments{
                border: 5px solid #1c98ff;
                border-radius: 15px;
                text-align: center;
                margin: 10px;
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

            .friendsActivities{
                border: 5px solid #1c98ff;
                border-radius: 15px;
                padding: 10px;
                margin: 10px;
            }

            .frActivitiesList {
                list-style-type: none;
                display: flex;
                gap: 20px;
            }

        </style>
    </head>
    <body>

    <div class="navbar">
        <a href="#home">Home</a>
        <div class="dropdown">
            <button class="dropbtn">Messages</button>
            <div class="dropdown-content">
                <a href="#message1">Message 1</a>
                <a href="#message2">Message 2</a>
                <a href="#message3">Message 3</a>
            </div>
        </div>
    </div>

    <div class = "announcments">
        <h1>Announcments</h1>
        <p>New quiz added</p>
        <p>Create quiz and get achievement badge</p>
    </div>

    <div class = "popularQuizzes">
        <h1>Popular Quizzes</h1>
        <ul class="popularQuizzesList">
            <li>Quiz 1</li>
            <li>Quiz 2</li>
            <li>Quiz 3</li>
        </ul>
    </div>

    <div class = "recentlyCreatedQuizzes">
        <h1>Recently Created Quizzes</h1>
        <ul class="recCrQuizList">
            <li>Quiz 1</li>
            <li>Quiz 2</li>
            <li>Quiz 3</li>
        </ul>
    </div>

    <div class = "recentlyTakenQuizzes">
        <h1>Recently Taken Quizzes</h1>
        <ul class="recTakenQuizList">
            <li>Quiz 1</li>
            <li>Quiz 2</li>
            <li>Quiz 3</li>
        </ul>
    </div>

    <div class = "myRecentlyCreatedQuizzes">
        <h1>My Recently Created Quizzes</h1>
        <ul class="myRecCrQuizList">
            <li>Quiz 1</li>
            <li>Quiz 2</li>
            <li>Quiz 3</li>
        </ul>
    </div>

    <div class = "achievements">
        <h1>Achievements</h1>
        <ul class="achievementsList">
            <li><img src="badge1.png" alt="Badge 1"></li>
            <li><img src="badge1.png" alt="Badge 2"></li>
            <li><img src="badge1.png" alt="Badge 3"></li>
        </ul>
    </div>

    <div class = "friendsActivities">
        <h1>Friend&rsquo;s Activities</h1>
        <ul class="frActivitiesList">
            <li>Friend 1</li>
            <li>Friend 2</li>
            <li>Friend 3</li>
        </ul>
    </div>


    </body>
</html>
