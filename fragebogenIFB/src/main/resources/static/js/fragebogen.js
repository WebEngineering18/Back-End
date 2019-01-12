window.onload = function () {
    var app = new Vue({
            el: '#fragebogen',
            data: {
                questionIndex: 1,
                answers: [
                    {question_id: 1, answer: [], type: "checkbox"}, //checkbox
                    {question_id: 2, answer: [], type: "radio"}, //radio
                    {question_id: 3, answer: [], type: "radio"}, //radio
                    {question_id: 4, answer: [], type: "100%"}, //2 numbers
                    {question_id: 5, answer: [], type: "year-choose"}, //every year or all years
                    {question_id: 6, answer: [], no_answer: [], type: "100%"}, //4 numbers choose
                    {question_id: 7, answer: [], type: "number"}, //number
                    {question_id: 8, answer: [], other: "", type: "checkbox"}, //checkbox with other text
                    {question_id: 9, answer: [], type: "year-choose"}, //every year or all years
                    {question_id: 10, answer: [], type: "100%"}, //2 numbers
                    {question_id: 11, answer: [], type: "checkbox"}, //checkbox
                    {question_id: 12, answer: [], type: "number"}, //checkbox
                    {question_id: 13, answer: [], other: "", type: "checkbox"}, //checkbox with other text
                    {question_id: 14, answer: [], type: "100%"}, //3 numbers
                    {question_id: 15, answer: [], type: "number"}, //all years and every year
                    {question_id: 16, answer: [], type: "number"}, //all years and every year
                    {question_id: 17, answer: [], type: "number"}, //number
                    {question_id: 18, answer: [], type: "number"}, //all years and every year
                    {question_id: 19, answer: [], type: "radio"}, //radio
                    {question_id: 20, answer: [], type: "radio"}, //radio
                ],
                headline: 'Allgemeines',
                errorMessage: "",
                error: false,
            },
            methods: {
                next: function (event) {
                    this.validate();

                    if (!this.error) {
                        if (this.questionIndex == 20) {
                            this.sendServer()
                        }
                        this.questionIndex++;
                    }
                },
                back: function (event) {
                    this.questionIndex--;
                },
                sendServer: function () {

                    const body = JSON.stringify(
                        this.answers
                    );
                    console.log(body);

                    const config = {
                        headers: {'Content-Type': 'application/json; charset=utf-8'},
                    };

                    axios.post('http://localhost:8080/testAnswer', body, config)
                        .then(function (response) {
                            console.log('saved successfully')
                        });
                },
                checkHeadline: function () {
                    if (this.questionIndex < 4) {
                        this.headline = "Allgemeines";
                    }
                    else if (this.questionIndex > 3 && this.questionIndex < 7) {
                        this.headline = "Planungsprozess";
                    }
                    else if (this.questionIndex > 6 && this.questionIndex < 9) {
                        this.headline = "Ausschreibung und Vergabe";
                    }
                    else if (this.questionIndex > 8 && this.questionIndex < 12) {
                        this.headline = "Ausführung";
                    }
                    else if (this.questionIndex > 11 && this.questionIndex < 15) {
                        this.headline = "Abnahme / Gewährleistung";
                    }
                    else if (this.questionIndex > 14 && this.questionIndex < 19) {
                        this.headline = "Schadenkosten";
                    }
                    else if (this.questionIndex > 18 && this.questionIndex < 21) {
                        this.headline = "Entwicklung der Bauqualität";
                    }
                    else if (this.questionIndex == 21) {
                        this.headline = "Institut für Bauforschung e. V.";
                    }
                },
                validate: function () {
                    if (this.answers[this.questionIndex - 1].type == "100%") {
                        if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                            this.errorMessage = "Alle Werte müssen zusammen 100 ergeben!";
                            this.error = true;
                        } else {
                            let check = 0;
                            this.answers[this.questionIndex - 1].answer.forEach(function (entry) {
                                if (entry != "") {
                                    check = check + parseInt(entry);
                                }
                            });
                            if (check != 100) {
                                this.errorMessage = "Alle Werte müssen zusammen 100 ergeben!";
                                this.error = true;
                            } else {
                                this.error = false;
                            }
                        }
                    } else if (this.answers[this.questionIndex - 1].type == "radio") {
                        if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                            this.errorMessage = "Sie müssen ein Feld markieren!";
                            this.error = true;
                        } else {
                            this.error = false;
                        }
                    }
                    else if (this.answers[this.questionIndex - 1].type == "checkbox") {
                        if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                            this.errorMessage = "Sie müssen mindestens ein Feld markieren!";
                            this.error = true;
                        }
                        else {
                            this.error = false;
                        }
                    } else if (this.answers[this.questionIndex - 1].type == "year-choose" || this.answers[this.questionIndex - 1].type == "everyYear" || this.answers[this.questionIndex - 1].type == "2013-2017") {
                        if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                            this.errorMessage = "Sie müssen ein Feld markieren und ausfüllen!";
                            this.error = true;
                        }
                        else {
                            this.error = false;
                        }
                    } else if (this.answers[this.questionIndex - 1].type == "number") {
                        if (this.answers[this.questionIndex - 1].answer.length <= 0 || this.answers[this.questionIndex - 1].answer[0] == "") {
                            this.errorMessage = "Sie müssen eine Zahl eingeben!";
                            this.error = true;
                        }
                        else {
                            this.error = false;
                        }
                    }
                },
                closeError: function (event) {
                    this.error = false;
                },
                resetAnswer: function (indexReset) {
                    this.answers.forEach((el, index) => {
                        if (index === indexReset) {
                            el.answer = [];
                        }
                    });
                }
            },
            watch: {
                questionIndex: function () {
                    this.checkHeadline();
                }
            }
        }
    );
};