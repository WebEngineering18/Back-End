var percentage = 0;

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