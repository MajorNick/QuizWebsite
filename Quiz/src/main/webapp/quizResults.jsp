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
        }
        header {
            background-color: #007bff;
            color: #ffffff;
            text-align: center;
            padding: 10px;
        }
        .result-summary {
            text-align: center;
            margin: 20px;
        }
        .answer-details {
            margin: 20px;
        }
        .comparison-table {
            margin: 20px;
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }
        .comparison-table th, .comparison-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        footer {
            background-color: #f4f4f4;
            text-align: center;
            padding: 10px;
        }
    </style>
</head>

<body>
    <header>
        <h1>Quiz Results</h1>
    </header>

    <section class="result-summary">
        <h2>Summary</h2>
        <p>Your Score: <span id="user-score">X / Y</span></p>
        <p>Time Taken: <span id="time-taken">Z minutes</span></p>
    </section>

    <section class="answer-details">
        <h2>Your Answers and Correct Answers</h2>
        <table class="comparison-table">
            <thead>
                <tr>
                    <th>Question</th>
                    <th>Your Answer</th>
                    <th>Correct Answer</th>
                </tr>
            </thead>
            <tbody>
                <!-- Generate table rows dynamically using JavaScript or your backend language -->
                <!-- Example row:
                <tr>
                    <td>Question 1</td>
                    <td>Your choice</td>
                    <td>Correct choice</td>
                </tr>
                -->
            </tbody>
        </table>
    </section>

    <section class="performance-comparison">
        <h2>Performance Comparison</h2>
        <table class="comparison-table">
            <thead>
                <tr>
                    <th>Comparison</th>
                    <th>Your Score</th>
                    <th>Top Performer's Score</th>
                    <th>Your Friends' Average Score</th>
                </tr>
            </thead>
            <tbody>
                <!-- Generate table rows dynamically using JavaScript or your backend language -->
                <!-- Example row:
                <tr>
                    <td>Quiz Score</td>
                    <td>Your score</td>
                    <td>Top performer's score</td>
                    <td>Your friends' average score</td>
                </tr>
                -->
            </tbody>
        </table>
    </section>

</body>
</html>
