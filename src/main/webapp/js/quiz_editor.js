function selectOnChange() {
    var textToAdd = '';

    var end = $('#s1 option:selected').text();
    for (let i = 0; i < parseInt(end); i++) {
        textToAdd += createQuestionCreator(i)
    }
    $('.creator_block').html(textToAdd)
}

function createQuestionCreator(number) {
    return `<div class="question_creator">
                <label>
                    Question ${number + 1}:
                    <input type="text" maxlength="145">
                </label><hr>
                <span class="option">Option 1: <input name="Option1ForQuestion${number}" type="text"><br/></span>
                <span class="option">Option 2: <input name="Option2ForQuestion${number}" type="text"><br/></span>
                <span class="option">Option 3: <input name="Option3ForQuestion${number}" type="text"><br/></span>
                <span class="option">Option 4: <input name="Option4ForQuestion${number}" type="text"><br/></span>
            </div>`
}