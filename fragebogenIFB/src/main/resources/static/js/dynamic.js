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
        if (!$('#input5').val()) {
            $('#input5').hide();
        }
    });
    $('#allYearsQ5').change(function () {
        $('#input5').show();
        if (!$('#input5_1').val() || !$('#input5_2').val() || !$('#input5_3').val() || !$('#input5_4').val() || !$('#input5_5').val()) {
            $('#eachYearQ5').hide();
        }

    });
    $('#allYearsQ9').change(function () {
        $('#input9').show();
        if (!$('#input9_1').val() || !$('#input9_2').val() || !$('#input9_3').val() || !$('#input9_4').val() || !$('#input9_5').val()) {
            $('#eachYearQ9').hide();
        }
    });
    $('#everyYearQ9').change(function () {
        $('#eachYearQ9').show();
        if (!$('#input9').val()) {
            $('#input9').hide();
        }
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