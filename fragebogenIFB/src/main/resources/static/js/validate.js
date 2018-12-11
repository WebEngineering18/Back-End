function validateQuestionCB(curFrage) {
    var checkBoxes = document.getElementsByClassName('cb' + curFrage);
    var isChecked = false;
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    if (!isChecked) {
        showError('Sie müssen mindestens ein Feld markieren!');
    } else {
        getNextQuestion();
        removeDiv();
    }
}

function validateQuestionRB(curFrage) {
    var radioBoxes = document.getElementsByClassName('rb' + curFrage);
    var isChecked = false;
    for (var i = 0; i < radioBoxes.length; i++) {
        if (radioBoxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    if (!isChecked) {
        showError('Sie müssen ein Feld markieren!');
    } else {
        getNextQuestion();
        removeDiv();
    }
}

function validateQuestion6CB(curFrage) {
    var checkBoxes = document.getElementsByClassName('cb' + curFrage);
    var isChecked = true;
    for (var i = 0; i < checkBoxes.length; i++) {
        if (!checkBoxes[i].checked) {
            isChecked = false;
            break;
        }
    }
    return isChecked;
}

function validateQuestionNumberXOR(frage) {
    if (!$("#input" + frage).val() && (!$("#input" + frage + "_1").val() || !$("#input" + frage + "_2").val() || !$("#input" + frage + "_3").val()
        || !$("#input" + frage + "_4").val() || !$("#input" + frage + "_5").val())) {
        showError("Bitte geben Sie was ein!");
    } else {
        getNextQuestion();
        removeDiv();
    }
}

function validateQuestionNumber(frage) {
    if (!$("#input" + frage).val()) {
        showError("Bitte geben Sie was ein!");
    } else {
        getNextQuestion();
        removeDiv();
    }
}

function validateQuestionRange(frage) {
    let combined;
    let validateCB;
    let range, range2, range3, range4, range5, range6, range7, range8, range9;

    if (frage == 4) {
        range = $("#range4").val();
        range2 = $("#range4_2").val();

        combined = +range + +range2;
    } else if (frage == 6) {
        range = $("#range6_1").val();
        range2 = $("#range6_2").val();
        range3 = $("#range6_3").val();
        range4 = $("#range6_4").val();

        combined = +range + +range2 + +range3 + +range4;
        validateCB = validateQuestion6CB(frage);
    } else if (frage == 10) {
        range = $("#range10").val();
        range2 = $("#range10_2").val();
        range3 = $("#range10_3").val();
        range4 = $("#range10_4").val();
        range5 = $("#range10_5").val();
        range6 = $("#range10_6").val();
        range7 = $("#range10_7").val();
        range8 = $("#range10_8").val();
        range9 = $("#range10_9").val();

        combined = +range + +range2 + +range3 + +range4 + +range5 + +range6 + +range7 + +range8 + +range9;
    } else if (frage == 14) {
        range = $("#range14").val();
        range2 = $("#range14_2").val();
        range3 = $("#range14_3").val();

        combined = +range + +range2 + +range3;
    }
    if (combined == 100 || (frage == 6 && validateCB)) {
        getNextQuestion();
        removeDiv();

    }
     else {
        showError("Alle Werte müssen zusammen 100 ergeben!");
    }
}

function removeDiv() {
    $(".uikit-alert").remove();
}


function showError(msg) {
    var errorContainer = '<div class="uk-container uikit-alert" style="margin-top: 25px;"><div uk-alert class="uk-alert-danger">' + msg + '<a class="uk-alert-close" uk-close onclick="removeDiv();"></a></div></div>';
    $('.uk-heading-divider').after(errorContainer);
}