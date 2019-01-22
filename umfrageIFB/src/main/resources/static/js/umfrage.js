window.onload = function () {
    var app = new Vue({
        el: '#fragebogen',
        data: {
            questionIndex: 1,
            /**
             * Das JSONArray, welches am Ende der Umfrage alle Antworten beinhaltet.
             * Pro Frage gibt es ein JSONObject mit der ID der Frage und den Antworten.
             * 
             */
            answers: [
                { question_id: 1, answer: [], type: "checkbox" }, //checkbox
                { question_id: 2, answer: [], type: "radio" }, //radio
                { question_id: 3, answer: [], type: "radio" }, //radio
                { question_id: 4, answer: ["0", "0"], type: "100%" }, //2 numbers
                { question_id: 5, answer: [], type: "year-choose" }, //every year or all years
                { question_id: 6, answer: ["0", "0", "0", "0"], no_answer: [], type: "100%" }, //4 numbers choose
                { question_id: 7, answer: [], type: "number" }, //number
                { question_id: 8, answer: [], other: "", type: "checkbox" }, //checkbox with other text
                { question_id: 9, answer: [], type: "year-choose" }, //every year or all years
                { question_id: 10, answer: ["0", "0", "0", "0", "0", "0", "0", "0", "0"], type: "100%" }, //2 numbers
                { question_id: 11, answer: [], type: "checkbox" }, //checkbox
                { question_id: 12, answer: [], type: "number" }, //checkbox
                { question_id: 13, answer: [], other: "", type: "checkbox" }, //checkbox with other text
                { question_id: 14, answer: ["0", "0", "0"], type: "100%" }, //3 numbers
                { question_id: 15, answer: [], type: "number" }, //all years and every year
                { question_id: 16, answer: [], type: "number" }, //all years and every year
                { question_id: 17, answer: [], type: "number" }, //number
                { question_id: 18, answer: [], type: "number" }, //all years and every year
                { question_id: 19, answer: [], type: "radio" }, //radio
                { question_id: 20, answer: [], type: "radio" }, //radio
            ],
            headline: 'Allgemeines',
            otherTextStatus: false,
            errorMessage: "",
            error: false,
            endDate: '2019-02-28',
            textLimit: 150,
            instructionText: "",
        },
        methods: {

            /**
             * Methode um zur nächsten Frage zu springen. Hier wird die validate()-Methode aufgerufen und der questionIndex erhöht sich,
             * bis die letzte Frage erreicht wurde, woraufhin die sendServer()-Methode aufgerufen wird.
             * 
             * @param {*} event - Event wird beim Klicken des Buttons 'Weiter' ausgeführt
             */
            next: function (event) {
                this.validate();

                if (!this.error) {
                    if (this.questionIndex == 20) {
                        this.sendServer();
                    } else {
                        this.questionIndex++;
                    }
                }



            },

            /**
             * Methode um zur vorherigen Frage zu springen. der questionIndex wird um eins verringert.
             * @param {*} event - Event wird beim Klicken des Buttons 'Zurück' ausgeführt
             */
            back: function (event) {
                this.questionIndex--;
            },

            /**
             * Methode die den Post Request mit den ganzen Antworten abschickt.
             */
            sendServer: async function () {

                const body = JSON.stringify(
                    this.answers
                );

                const config = {
                    headers: { 'Content-Type': 'application/json; charset=utf-8' },
                };

                try {
                    const response = await axios.post('http://localhost:8080/umfrageIFB/saveAnswers', body, config);
                    if (response.data == false) {
                        this.error = true;
                        this.errorMessage = "Sie haben die Umfrage bereits durchgeführt!";
                    } else {
                        this.questionIndex++;
                    }
                    console.log(response);
                } catch (error) {
                    console.error(error);
                }


            },

            /**
             * Da die Fragen in Kategorien eingeteilt sind, wird der Name der Kategorie je nach Frage geändert.
             */
            checkHeadline: function () {
                if (this.questionIndex < 4) {
                    this.headline = "Allgemeines";
                } else if (this.questionIndex > 3 && this.questionIndex < 7) {
                    this.headline = "Planungsprozess";
                } else if (this.questionIndex > 6 && this.questionIndex < 9) {
                    this.headline = "Ausschreibung und Vergabe";
                } else if (this.questionIndex > 8 && this.questionIndex < 12) {
                    this.headline = "Ausführung";
                } else if (this.questionIndex > 11 && this.questionIndex < 15) {
                    this.headline = "Abnahme / Gewährleistung";
                } else if (this.questionIndex > 14 && this.questionIndex < 19) {
                    this.headline = "Schadenkosten";
                } else if (this.questionIndex > 18 && this.questionIndex < 21) {
                    this.headline = "Entwicklung der Bauqualität";
                } else if (this.questionIndex == 21) {
                    this.headline = "Institut für Bauforschung e. V.";
                }
            },

            /**
             * Mit dieser Methode wird immer überprüft ob jede Frage "richtig" beantwortet wurde, das heißt ob alle notwendigen Eingaben getätigt wurden.
             */
            validate: function () {
                if (this.answers[this.questionIndex - 1].type == "100%") {
                    if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                        this.errorMessage = "Alle Werte müssen zusammen 100 ergeben!";
                        this.error = true;
                    } else {
                        let check = 0;
                        this.answers[this.questionIndex - 1].answer.forEach(function (entry) {
                            if (entry != "Ohne") {
                                if (entry != "") {
                                    check = check + parseInt(entry);
                                }
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
                } else if (this.answers[this.questionIndex - 1].type == "checkbox") {
                    if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                        this.errorMessage = "Sie müssen mindestens ein Feld markieren!";
                        this.error = true;
                    } else {
                        this.error = false;
                    }
                } else if (this.answers[this.questionIndex - 1].type == "year-choose" || this.answers[this.questionIndex - 1].type == "everyYear" || this.answers[this.questionIndex - 1].type == "2013-2017") {
                    if (this.answers[this.questionIndex - 1].answer.length <= 0) {
                        this.errorMessage = "Sie müssen ein Feld markieren und ausfüllen!";
                        this.error = true;
                    } else {
                        this.error = false;
                    }
                } else if (this.answers[this.questionIndex - 1].type == "number") {
                    if (this.answers[this.questionIndex - 1].answer.length <= 0 || this.answers[this.questionIndex - 1].answer[0] == "") {
                        this.errorMessage = "Sie müssen eine Zahl eingeben!";
                        this.error = true;
                    } else {
                        this.error = false;
                    }
                }

                //data format improvments for database
                this.checkOtherText();
                this.instructionText = "";
                this.otherTextStatus = false;
            },

            /**
             * Methode um die Fehlermeldung bei einer Falscheingabe zu schließen.
             * @param {*} event - Event zum Schließen des Errors
             */
            closeError: function (event) {
                this.error = false;
            },

            /**
             * Methode um eine Antwort im JSONArray zurückzusetzen.
             * @param {*} indexReset 
             */
            resetAnswer: function (indexReset) {
                this.answers.forEach((el, index) => {
                    if (index === indexReset) {
                        el.answer = [];
                    }
                });
            },

            /**
             * Methode um die Textboxen hervorzuheben, sobald die richtigen Checkboxen angeklickt wurden.
             */
            toogleOtherText: function () {
                if (this.otherTextStatus === false) {
                    this.otherTextStatus = true;
                } else {
                    this.otherTextStatus = false;
                }
            },

            /**
             * Überprüft ob die Textfelder einen Eintrag besitzen und fügt diesen dann dem JSONObject hinzu.
             */
            checkOtherText: function () {
                if (this.answers[this.questionIndex - 1].hasOwnProperty('other')) {
                    if (this.answers[this.questionIndex - 1].other != "" && Array.isArray(this.answers[this.questionIndex - 1].answer)) {
                        this.answers[this.questionIndex - 1].answer.forEach((el, index) => {
                            if (el === "Sonstiges") {
                                this.answers[this.questionIndex - 1].answer[index] = this.answers[this.questionIndex - 1].other;
                            }
                        });
                    }
                }
            },

            /**
             * Wird bei Frage 6 angewandt, um die Slider auszublenden wenn die "Ohne..." Checkboxen gecheckt sind.
             * @param {*} question_index - Index der Frage
             * @param {*} answer_index - Index der Antwort
             */
            noAnswer: function (question_index, answer_index) {
                this.answers.forEach((el, index) => {
                    if (index === question_index) {
                        el.answer[answer_index] = "Ohne";
                    }
                });
            },

            /**
             * Überprüft das Textlimit (150 Zeichen) der Textfelder.
             * 
             * @method checkTextLimit
             */
            checkTextLimit: function () {
                if (this.answers[this.questionIndex - 1].hasOwnProperty('other')) {
                    this.answers.forEach((el, index) => {
                        if (index === this.questionIndex - 1) {
                            let remaining = this.textLimit - el.other.length <= 0 ? 0 : this.textLimit - el.other.length;
                            this.instructionText = remaining + ' Zeichen übrig';
                            el.other = el.other.substr(0, this.textLimit);
                        }
                    });
                }
            },
        },

        computed: {

            /**
             * Überprüft ob der User die Umfrage im gültigen Zeitraum ausfüllt.
             * Liefert true, wenn der Fragebogen noch aktiv ist.
             * 
             * @method checkAviable
             */
            checkAviable: function () {
                let endDate = new Date(this.endDate);
                let currentDate = new Date();

                endDate = endDate.getFullYear() + "-" + endDate.getMonth() + "-" + endDate.getDate();
                currentDate = currentDate.getFullYear() + "-" + currentDate.getMonth() + "-" + currentDate.getDate();

                return endDate > currentDate;
            }
        },
        watch: {

            /**
             * Überprüft die Headline.
             * 
             * @method questionIndex
             */
            questionIndex: function () {
                this.checkHeadline();
            }


        }
    }
    );
};