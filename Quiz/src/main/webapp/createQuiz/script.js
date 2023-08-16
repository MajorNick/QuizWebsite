document.addEventListener("DOMContentLoaded", function () {
    var globalDivCount = 0;
    var questionsDeleted = 0;

    document.getElementById("addQuestion").addEventListener("click", function (event) {
        event.preventDefault();
        var questionAnswerCount = 0;
        var selectedVal = 0;

        globalDivCount++;
        var divNum = globalDivCount - questionsDeleted;

        var newQuestion = document.createElement("div");
        newQuestion.className = "block-container";
        newQuestion.id = `questionBlock${divNum}`;

        addQuestionDiv(divNum, newQuestion);

        var container = document.getElementById("quiz-contents");

        var addButton = document.getElementById("addQuestion");

        container.insertBefore(newQuestion, addButton);

        document.getElementById("maxQuestionIndex").value = globalDivCount;

        document.getElementById(`select${divNum}`).addEventListener("change", function () {
            var addAnswerButton = document.getElementById(`addAnswer${divNum}`);
            addAnswerButton.classList.remove("hidden");
            selectedVal = this.selectedIndex - 1;
            var ansContainer = document.getElementById(`answerList${divNum}`);
            ansContainer.innerHTML = "";

            questionAnswerCount = 1;
            addAnswerBox(selectedVal, divNum, questionAnswerCount, newQuestion, addAnswerButton);
        });

        document.getElementById(`addAnswer${divNum}`).addEventListener("click", function (event) {
            event.preventDefault();

            questionAnswerCount++;
            addAnswerBox(selectedVal, divNum, questionAnswerCount, newQuestion, null);
        });

        document.getElementById(`deleteQuestion${divNum}`).addEventListener("click", function (event) {
            event.preventDefault();

            var questionBlock = document.getElementById(`questionBlock${divNum}`);
            questionBlock.remove();

            for (let i = divNum + 1; i <= globalDivCount; i++) {
                var nextQuestionBody = document.getElementById(`question${i}`);
                if (nextQuestionBody === null) {
                    continue;
                }
                nextQuestionBody.innerHTML = `Question ${i - 1}`;
            }
            questionsDeleted++;
        });
    });
});

function addAnswerBox(selectedVal, divNum, answerNum, newQuestion, addAnswerButton) {
    var newAnswer = document.createElement("div");
    newAnswer.className = "block-items";

    if (selectedVal == 0 || selectedVal == 3) {
        newAnswer.innerHTML = `
            <textarea name="q${divNum}" class="note_text" placeholder="Answer" type="text" rows="1" cols="50"></textarea>
        `;
        if (selectedVal == 3) {
            var questionTextCont = document.getElementById(`question-text${divNum}`);
            questionTextCont.placeholder = "Image URL";
        }
        addAnswerButton.classList.add("hidden");
    } else if (selectedVal == 2) {
        var rbParams = "";
        if (answerNum == 1) {
            rbParams = " checked=\"checked\"";
        }
        newAnswer.innerHTML = `
            <input type="radio" name="rb${divNum}" value=${answerNum} ${rbParams}>
            <input name="q${divNum}-ans${answerNum}" class="note_text" placeholder="Answer ${answerNum}" type="text"></input>
        `;
    } else if (selectedVal == 5) {
        newAnswer.innerHTML = `
            <input type="checkbox" name="cb${divNum}-ans${answerNum}">
            <input name="q${divNum}-ans${answerNum}" class="note_text" placeholder="Answer ${answerNum}" type="text"></input>
        `;
    } else if (selectedVal == 4) {
        newAnswer.innerHTML = `
            <input name="q${divNum}-ans${answerNum}" class="note_text" placeholder="Answer ${answerNum}" type="text"></input>
        `;
    } else if (selectedVal == 1) {
        newAnswer.innerHTML = `
            ${answerNum}:
            <input name="q${divNum}-ans${answerNum}" class="note_text" placeholder="Blank ${answerNum}" type="text"></input>
        `;
    }
    var answerCountInput = document.getElementById(`answerCount${divNum}`);
    answerCountInput.value = answerNum;

    var ansContainer = document.getElementById(`answerList${divNum}`);
    ansContainer.appendChild(newAnswer);
}

function addQuestionDiv(divNum, newQuestion) {
    newQuestion.innerHTML = `
        <div class="block-contents">
            <div class="block-items"> 
                <span id="question${divNum}"> Question ${divNum} </span>
                <button class="action-button" id="deleteQuestion${divNum}">Delete</button>
            </div>
            <br>
            <input type="hidden" id="answerCount${divNum}" name="answerCount${divNum}" value="0">
            <select name="select${divNum}" id="select${divNum}" class="question-form">
                <option value="" disabled selected>Select Question Type</option>
                <option value="0">Question Response</option>
                <option value="1">Fill in the Blank</option>
                <option value="2">Multiple Choise</option>
                <option value="3">Picture Response</option>
                <option value="4">Multiple Answer</option>
                <option value="5">Multiple Choise & Answer</option>
            </select>
            <textarea id="question-text${divNum}" name="question${divNum}" class="note_text" placeholder="Question" rows="4" cols="50"></textarea>
            <div id="answerList${divNum}" class="answer-list">
            </div>
            <div id="answers${divNum}" class="block-items">
                <button class="action-button hidden" id="addAnswer${divNum}">Add Answer</button>
            </div>
        </div>
    `;
}