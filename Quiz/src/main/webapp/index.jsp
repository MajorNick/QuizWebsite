<html>
<title>
    Quiz summary
</title>
    <style>
        .header{
            background-color: #087cfc;
        }
        header {
            color: white;
            font-size: 24px; /* Adjust the value as needed */
        }
        .best_performances{
            display: inline-block;
            border: 1px solid black;
            padding: 10px;
            margin: 5px;
        }
        .highest_scores{
            display: inline-block;
            border: 1px solid black;
            padding: 10px;
            margin: 5px;
        }
        .today_highest_scores{
            display: inline-block;
            border: 1px solid black;
            padding: 10px;
            margin: 5px;
        }
        .last_quiz_takesr{
            display: inline-block;
            border: 1px solid black;
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
    </style>
    <body>
    <div class  = header>
        <header style = "color: white">
            Quiz Summary
        </header>

    </div>

    <div>
        <h2>
            Quiz Description
        </h2>
        <%
            // get description of quiz
            // es texti droebit
        %>
        <p>
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sagittis justo id mauris malesuada, ut condimentum ipsum porttitor. Proin vel elementum felis. Fusce consequat est a mi tristique, eu volutpat velit fermentum. Curabitur et sem tincidunt, efficitur ipsum ut, vehicula enim. Vestibulum eu diam vel orci fringilla dapibus eu nec sem. Etiam non purus vitae tellus aliquet finibus eu at nulla. Sed euismod feugiat quam, eget convallis tellus consequat eget. Mauris gravida felis id purus cursus faucibus. Suspendisse vitae lacus consectetur, aliquam ligula id, volutpat justo. Vivamus ut est eget justo aliquam dignissim. In vestibulum iaculis erat, sit amet egestas quam dignissim in. Donec eu mauris id elit vehicula vehicula. Suspendisse potenti. Cras consectetur augue quis risus hendrerit, eu tempus nisl finibus.
        </p>
    </div>

    <div>
            <p>Quiz Creator: <a href = "https://www.youtube.com/watch?v=dQw4w9WgXcQ">Nika Antarqtikeli</a></p>
    </div>

    <div class = best_performances>
        <h3>Best Performances</h3>
        <%
            // look at history
        %>
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
        </ul>
    </div>
    <div class="highest_scores">
        <h3>Highest Scores</h3>
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
        </ul>
    </div>
    <div class = today_highest_scores>
        <h3>Today's Highest Scores</h3>
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
        </ul>
    </div>
    <div class = last_quiz_takesr>
        <h3>3 Last Quiz Taker</h3>
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
        </ul>
    </div>
    <div>
        <button class="test_start_button">Start</button>
        <button class="test_start_button">Test</button>
    </div>
    </body>
</html>
