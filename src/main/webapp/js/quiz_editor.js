function selectOnChange() {
    var textToAdd = '';

    var end = $('#s1 option:selected').text();
    for (let i = 1; i <= parseInt(end); i++) {
        textToAdd += createQuestionCreator(i)
    }
    $('.creator_block').html(textToAdd)
}

function a(doc, number) {
    if (doc.checked) {
        $(`.question_creator_body${number}`).html(
            `<span style="margin: 10px"><label for="i2">Answer:<input id="i2" name="correct_answer${number}" class="writable_answer" type="text" required></label></span>`
        );
    } else {
        $(`.question_creator_body${number}`).html(
           `<span class="option"><input name="r${number}" type="radio" value="1" required> Option 1: <input name="Option1ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="2" required> Option 2: <input name="Option2ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="3" required> Option 3: <input name="Option3ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="4" required> Option 4: <input name="Option4ForQuestion${number}" type="text"><br/></span>
                `
        );
    }
}

function createQuestionCreator(number) {
    return `<div class="question_creator">
                <label>
                    Question ${number}:
                    <input type="text" name="question_title${number}" maxlength="145">
                </label><hr>
                <div class="question_creator_body${number}">
                    <span class="option"><input name="r${number}" type="radio" value="1" required> Option 1: <input name="Option1ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="2" required> Option 2: <input name="Option2ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="3" required> Option 3: <input name="Option3ForQuestion${number}" type="text"><br/></span>
                    <span class="option"><input name="r${number}" type="radio" value="4" required> Option 4: <input name="Option4ForQuestion${number}" type="text"><br/></span>
                </div>
                <hr>
                <span class="creator_footer"><label for="i3${number}">Writable:<input id="i3${number}" type="checkbox" class="type" name="question_type${number}" onchange="a(this, ${number})"></label></span>
            </div>`
}