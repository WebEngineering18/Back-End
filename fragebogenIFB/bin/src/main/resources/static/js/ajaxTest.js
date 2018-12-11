var frage = 1;
var letzteFrage = 20;
var answers = {
    data: []
};
var percentage = 0;
var slider = 4;

$(document).ready(function () {
    $("#next").click(function (e) {
        sumbitForm();
        changeCategory();
    });
    $("#back").click(function (e) {
        prevQuestion();
        changeCategory();
    });
    $("#input15").blur(function () {
        if ($("#input15").val() != "") {
            $("#eachYearQ15").show();
        }
        if ($("#input15").val() == "") {
            $("#eachYearQ15").hide();
        }
    });
    $("#input16").blur(function () {
        if ($("#input16").val() != "") {
            $("#eachYearQ16").show();
        }
        if ($("#input16").val() == "") {
            $("#eachYearQ16").hide();
        }
    });
    $("#input18").blur(function () {
        if ($("#input18").val() != "") {
            $("#eachYearQ18").show();
        }
        if ($("#input18").val() == "") {
            $("#eachYearQ18").hide();
        }
    });
    $('#everyYearQ5').change(function () {
        $('#eachYearQ5').show();
        $('#input5').hide();
    });
    $('#allYearsQ5').change(function () {
        $('#input5').show();
        $('#eachYearQ5').hide();
    });
    $('#allYearsQ9').change(function () {
        $('#input9').show();
        $('#eachYearQ9').hide();
    });
    $('#everyYearQ9').change(function () {
        $('#eachYearQ9').show();
        $('#input9').hide();
    });
    $('#6_1_1').change(function () {
        $('#6_1').toggle();
    });
    $('#6_2_1').change(function () {
        $('#6_2').toggle();
    });
    $('#6_3_1').change(function () {
        $('#6_3').toggle();
    });
    $('#6_4_1').change(function () {
        $('#6_4').toggle();
    });

});


function sumbitForm() {
    if (frage == 1) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getCheckboxData()
        });
    } else if (frage == 2) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getRadioData()
        });
    } else if (frage == 3) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getRadioData()
        });
    } else if (frage == 4) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "Eigenplanung": $('#input4').val() + "%",
                "Fremdplanung": $('#input4_2').val() + "%",
            }
        });
    }
    else if (frage == 5) {
        if ($('#allYearsQ5').is(':checked')) {
            answers.data.push({
                "querstion_id": frage,
                "answer": {
                    "2013 - 2017": $('#input5').val()
                }
            });
        } else if ($('#everyYearQ5').is(':checked')) {
            answers.data.push({
                "querstion_id": frage,
                "answer": {
                    "2013": $('#input5_1').val(),
                    "2014": $('#input5_2').val(),
                    "2015": $('#input5_3').val(),
                    "2016": $('#input5_4').val(),
                    "2017": $('#input5_5').val(),
                }
            });
        }
    }
    else if (frage == 6) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "Entwurfsplanung": $('#input6_1').val() + "%",
                "Genehmigungsplanung": $('#input6_2').val() + "%",
                "Ausfuehrungsplanung": $('#input6_3').val() + "%",
            }
        });
    }
    else if (frage == 7) {
        answers.data.push({
            "querstion_id": frage,
            "answer": $('#input7').val()
        });
    }
    else if (frage == 8) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getCheckboxData() //todo sonstiges text hinzufügen
        });
    }
    else if (frage == 9) {
        if ($('#allYearsQ9').is(':checked')) {
            answers.data.push({
                "querstion_id": frage,
                "answer": {
                    "2013 - 2017": $('#input9').val()
                }
            });
        } else if ($('#everyYearQ9').is(':checked')) {
            answers.data.push({
                "querstion_id": frage,
                "answer": {
                    "2013": $('#input9_1').val(),
                    "2014": $('#input9_2').val(),
                    "2015": $('#input9_3').val(),
                    "2016": $('#input9_4').val(),
                    "2017": $('#input9_5').val(),
                }
            });
        }
    }
    else if (frage == 10) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "Baustelle": $('#input10').val() + "%",
                "Erdberuehrte Bauteile": $('#input10_2').val() + "%",
                "Gebaeudehuelle": $('#input10_3').val() + "%",
                "Gebaeudeinnenbereich": $('#input10_4').val() + "%",
                "Daemmung": $('#input10_5').val() + "%",
                "Brandschutz": $('#input10_6').val() + "%",
                "Luftdichte Ebene": $('#input10_7').val() + "%",
                "Auszenanlagen, Terrassen": $('#input10_8').val() + "%",
                "Haustechnik": $('#input10_7').val() + "%",
            }
        });
    }
    else if (frage == 11) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getCheckboxData()
        });
    }
    else if (frage == 12) {
        answers.data.push({
            "querstion_id": frage,
            "answer": $('#input12').val()
        });
    }
    else if (frage == 13) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getCheckboxData()
        });
    }
    else if (frage == 14) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "0-2 Jahre": $('#input14').val() + "%",
                "3-4 Jahre": $('#input14_2').val() + "%",
                "ab 5 Jahre": $('#input14_3').val() + "%",
            }
        });
    }
    else if (frage == 15) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "2013 - 2017": $('#input15').val(), //todo andere Jahre einfügen
            }
        });
    }
    else if (frage == 16) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "2013 - 2017": $('#input16').val(), //todo andere Jahre einfügen
            }
        });
    }
    else if (frage == 17) {
        answers.data.push({
            "querstion_id": frage,
            "answer":  $('#input17').val()
        });
    }
    else if (frage == 18) {
        answers.data.push({
            "querstion_id": frage,
            "answer": {
                "2013 - 2017": $('#input18').val(), //todo andere Jahre einfügen
            }
        });
    }
    else if (frage == 19) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getRadioData()
        });
    }
    else if (frage == 20) {
        answers.data.push({
            "querstion_id": frage,
            "answer": getRadioData()
        });
    }
    else {

    }

    console.log(answers);
    console.log(JSON.stringify(answers));
    nextQuestion();

}

function getNextQuestion() {
    $('#frage_' + frage).hide();
    frage++;
    $('#frage_' + frage).show();
    progressBarNext();
}

function nextQuestion() {
    if (frage == 1 || frage == 8 || frage == 11 || frage == 13) {
        validateQuestionCB(frage);
    } else if (frage == 4 || frage == 6 || frage == 10 || frage == 14) {
        validateQuestionRange(frage);
    } else if (frage == 5 || frage == 7 || frage == 9 || frage == 12 || frage == 15 || frage == 16 || frage == 17 || frage == 18) {
        validateQuestionNumber(frage);
    } else {
        getNextQuestion();
    }

}

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
        alert('Sie müssen mindestens ein Feld markieren!');
    } else {
        getNextQuestion();
    }
}

function validateQuestionNumber(input) {
    if (!$("#input" + input).val()) {
        alert("Bitte geben Sie was ein!");
    } else {
        getNextQuestion();
    }
}

function validateQuestionRange(frage) {
    let combined;
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
    if (combined == 100) {
        getNextQuestion();
    } else {
        alert("Alle Werte müssen zusammen 100 ergeben!");
    }
}

function prevQuestion() {
    if (frage > 1) {
        $('#frage_' + frage).hide();
        frage--;
        $('#frage_' + frage).show();
        progressBarBack();
    }
}

function sendToServer() {
    for (var i = 1; i <= letzteFrage; i++) {
        $.ajax({
            url: 'http://localhost/edit',
            dataType: "json",
            type: 'POST',
            data: {
                frage_id: i,
                antwort: antworten[i]
            },
            error: function () {
                console.log("Fehler aufgetreten!")
            }
            ,
            success: function (data) {
                console.log("Frage wurden an Server geschickt!")
            }
        });
    }
}

function getCheckboxData() {
    return $("#frage_" + frage + " input:checkbox:checked").map(function () {
        return $(this).val();
    }).get();
}

function getRadioData() {
    return $("#frage_" + frage + " input:radio:checked").map(function () {
        return $(this).val();
    }).get();
}

function getNumberData(offset) {
    return $("#frage_" + frage + " input").val();
}

function changeCategory() {
    if (frage < 4) {
        $("#category").html("Allgemeines");
    }
    else if (frage > 3 && frage < 7) {
        $("#category").html("Planungsprozess");
    }
    else if (frage > 6 && frage < 9) {
        $("#category").html("Ausschreibung und Vergabe");
    }
    else if (frage > 8 && frage < 12) {
        $("#category").html("Ausführung");
    }
    else if (frage > 11 && frage < 15) {
        $("#category").html("Abnahme / Gewährleistung");
    }
    else if (frage > 14 && frage < 19) {
        $("#category").html("Schadenkosten");
    }
    else if (frage > 18 && frage < 21) {
        $("#category").html("Entwicklung der Bauqualität");
    }
    if (frage == 20) {
        $("#next .uk-button").html("Beenden");
    }
    if (frage == 19) {
        $("#next .uk-button").html("Weiter");
    }
    if (frage > 1) {
        $("#back").show();
    }
    if (frage == 1) {
        $("#back").hide();
    }
    else if (frage == 21) {
        $("#category").html("Institut für Bauforschung");
        $("#buttons").hide();

    }

}

function progressBarNext() {
    var progressValue = document.getElementById("progress");
    ++progressValue.value;
    var progressPercentage = document.getElementById("percentage");
    percentage += 5;
    progressPercentage.innerHTML = percentage + "% (" + progressValue.value + "/20)";
}

function progressBarBack() {
    var progressValue = document.getElementById("progress");
    --progressValue.value;
    var progressPercentage = document.getElementById("percentage");
    percentage -= 5;
    progressPercentage.innerHTML = percentage + "% (" + progressValue.value + "/20)";
}



