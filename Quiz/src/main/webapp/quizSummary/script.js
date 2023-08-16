document.addEventListener('DOMContentLoaded', function() {
    const checkbox = document.getElementById('singlePageCheckbox');
    const quizForm = document.getElementById('quizForm');
    const quizPracticeForm = document.getElementById('quizPracticeForm');
    checkbox.addEventListener('change', function () {
        const isChecked = checkbox.checked;
        console.log("SFASFA")
        quizForm.action = isChecked ? '/quizSinglePage' : '/quiz';
        quizPracticeForm.action = isChecked ? '/quizPracticeSinglePage' : '/quizPractice';
    });

});