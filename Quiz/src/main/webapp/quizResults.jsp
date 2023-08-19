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
          }
          .table-container {
              flex: 1;
              margin-right: 20px;
          }
      </style>
</head>

<body>
    <div class="container">
        <header>
            <h1>Quiz Results</h1>
        </header>
        <div class="score">
            Your Score: 8 out of 10
             <br>
            <span class="time-taken">Time taken: 12 minutes</span>
        </div>

        <div class="tables-row">
            <div class="table-container">
                <h2>Your Answers and Correct Answers:</h2>
                <table class="answers-details">
                    <tr>
                        <th>Question #</th>
                        <th>Your Answer</th>
                        <th>Correct Answer</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Option B</td>
                        <td>Option A</td>
                    </tr>
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
                        <td>8/10</td>
                        <td>12 minutes</td>
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
</body>
</html>
