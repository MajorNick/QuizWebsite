document.addEventListener("DOMContentLoaded", function () {
    var divCount = 1;

    document.getElementById("addQuestion").addEventListener("click", function (event) {
        event.preventDefault();

        var newQuestion = document.createElement("div");
        newQuestion.className = "block-container";
        newQuestion.innerHTML = `
            <div class="block-contents">
                <div id="question${divCount}" class="block-items">Question ${divCount}</div>
                <br>
                <select id="select${divCount}" class="question-form">
                    <option value="" disabled selected>Select Question Type</option>
                    <option value="actual value 1">Question Response</option>
                    <option value="actual value 2">Fill in the Blank</option>
                    <option value="actual value 3">Multiple Choise</option>
                    <option value="actual value 3">Picture Response</option>
                    <option value="actual value 3">Multiple Answer</option>
                    <option value="actual value 3">Multiple Choise & Answer</option>
                </select>
                <textarea id="question-text${divCount}" name="note_text" class="note_text" placeholder="Question" rows="4" cols="50"></textarea>
                <div class="answer-list">
                </div>
                <div id="answers${divCount}" class="block-items">
                    <button class="action-button hidden" id="addAnswer${divCount}">Add Answer</button>
                </div>
            </div>
        `;

        var container = document.getElementById("quiz-contents");

        var addButton = document.getElementById("addQuestion");

        container.insertBefore(newQuestion, addButton);

        var answerCount = 1;

        var selectedVal = 0;

        document.getElementById(`select${divCount}`).addEventListener("change", function () {
            var addAnswerButton = newQuestion.querySelector(".action-button");
            addAnswerButton.classList.remove("hidden");
            selectedVal = this.selectedIndex - 1;
            var ansContainer = newQuestion.querySelector(".answer-list");
            ansContainer.innerHTML = "";
            var answerCount = 1;
            if (selectedVal == 0 || selectedVal == 3) {
                var newAnswer = document.createElement("div");
                newAnswer.className = "block-items";
                newAnswer.innerHTML = `
                    <textarea name="q-${divCount}_ans${answerCount}" class="note_text" placeholder="Answer" type="text" rows="1" cols="50"></textarea>
                `;
                ansContainer.appendChild(newAnswer);
                if (selectedVal == 3) {
                    var questionTextCont = newQuestion.querySelector(".note_text");
                    questionTextCont.placeholder = "Image URL";
                }
                addAnswerButton.classList.add("hidden");
            }
        });

        document.getElementById(`addAnswer${divCount}`).addEventListener("click", function (event) {
            event.preventDefault();
            var ansContainer = newQuestion.querySelector(".answer-list");
            var newAnswer = document.createElement("div");
            newAnswer.className = "block-items";
            if (selectedVal == 2 || selectedVal == 4) {
                newAnswer.innerHTML = `
                    <input type="radio" name="q-${divCount}_corr${answerCount}">
                    <input name="q-${divCount}_ans${answerCount}" class="note_text" placeholder="Answer ${answerCount}" type="text"></input>
                `;
                ansContainer.appendChild(newAnswer);
                answerCount++;
            } else if (selectedVal == 1) {
                newAnswer.innerHTML = `
                    ${answerCount}:
                    <input name="q-${divCount}_ans${answerCount}" class="note_text" placeholder="Blank ${answerCount}" type="text"></input>
                `;
                ansContainer.appendChild(newAnswer);
                answerCount++;
            }
        });

        divCount++;
    });
});