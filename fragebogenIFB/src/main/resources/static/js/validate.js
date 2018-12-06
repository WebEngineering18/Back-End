function valCheckbox() {
    var checkBoxes = document.getElementsByClassName('uk-checkbox');
    var isChecked = false;
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    if (!isChecked) {
        alert('Sie muessen bei der ersten Frage mindestens ein Feld markieren!');
    }
}

function sliderRange() {

    var slider = document.getElementById("myRange");
    var output = document.getElementById("demo");

    output.innerHTML = slider.value;

    slider.oninput = function () {
        output.innerHTML = this.value;
    }
}

function valTextarea() {
    if (document.getElementById("textarea2").value == "") {
        alert("Enter the text");
        document.getElementById("textarea2").focus();
    }
    else {
        alert(document.getElementById("textarea2").value);
    }
}
