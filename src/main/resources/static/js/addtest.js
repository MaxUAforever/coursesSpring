function cancel_test(){
    /*document.getElementById("cancelForm").submit();*/
}

function save_test(){
    var q_num = document.getElementById("q_num").value;

    var elem_from = document.getElementsByClassName("question");
    var elem_to = document.getElementsByClassName("question_area");

    for(var i = 0; i < elem_from.length; i++){
        elem_to[i].value = elem_from[i].innerHTML;
    }

    elem_from = document.getElementsByClassName("answer");
    elem_to = document.getElementsByClassName("answer_area");

    for(var i = 0; i < elem_from.length; i++){
        elem_to[i].value = elem_from[i].innerHTML;
    }

    var quest_cnt = document.getElementById("quest_count_id");
    quest_cnt.value = q_num;

    document.getElementById("rightcol_id").submit();

    /*var heh = document.getElementById("heh");
    heh.innerHTML = elem_from.length.toString();*/
};

/*function fill_area(elem){

    var area = document.getElementById(elem.name);
    area.value = elem.innerHTML;
}*/

function add_open(){
    var q_num = document.getElementById("q_num").value;

    var p = document.getElementById("questContent");
    var new_open = document.createElement("div");
    new_open.setAttribute("id", "edit_open_id");
    new_open.setAttribute("class","edit_open");

    var question_div = document.createElement("div");
    question_div.name = "quest" + q_num;
    question_div.setAttribute("id", "question_id");
    question_div.setAttribute("class", "question");
    question_div.setAttribute("contenteditable","true");
    question_div.setAttribute("data-placeholder", "Enter open question...");
    question_div.setAttribute("role", "textbox");
    question_div.setAttribute("aria-multiline", "true");

    var btn_del = document.createElement("input");
    btn_del.type = "image";
    btn_del.src = "img/dlt.jpg"
    btn_del.setAttribute("type", "image");
    btn_del.setAttribute("id", "buttonDel");
    btn_del.setAttribute("alt", "Delete");
    btn_del.setAttribute("onclick", "this.parentNode.parentNode.removeChild(this.parentNode);delQuest();");

    var question_area = document.createElement("textarea");
    question_area.name = "quest" + q_num;
    question_area.setAttribute("class", "question_area"/* + q_num*/);
    question_area.setAttribute("id", "quest" + q_num);
    question_area.setAttribute("style", "display:none;");

    var lbl2 = document.createElement("label");
    lbl2.name = "lbl2";
    lbl2.setAttribute("class","lbl");
    lbl2.innerHTML = "Grade for question:&nbsp;";

    var mark = document.createElement("input");
    mark.name = "mark" + q_num;
    mark.setAttribute("class","num_input");
    mark.setAttribute("id","number_id");
    mark.setAttribute("type", "number");
    mark.setAttribute("value", "1");
    mark.setAttribute("min", "1");

    p.appendChild(new_open);
    new_open.appendChild(btn_del);
    new_open.appendChild(lbl2);
    new_open.appendChild(mark);
    new_open.appendChild(question_div);
    new_open.appendChild(question_area);

    q_num++;
    document.getElementById("q_num").value = q_num;
};

function add_close() {
    var q_num = document.getElementById("q_num").value;

    var p = document.getElementById("questContent");
    var new_test = document.createElement("div");
    new_test.name = "test" + q_num;
    new_test.setAttribute("class", "edit_test");

    var question_div = document.createElement("div");
    question_div.name = "quest" + q_num;
    question_div.setAttribute("id", "question_id");
    question_div.setAttribute("class","question");
    question_div.setAttribute("contenteditable","true");
    question_div.setAttribute("data-placeholder", "Enter question...");
    question_div.setAttribute("role", "textbox");
    question_div.setAttribute("aria-multiline", "true");

    var btn_del = document.createElement("input");
    btn_del.type = "image";
    btn_del.src = "img/dlt.jpg"
    btn_del.setAttribute("type", "image");
    btn_del.setAttribute("id", "buttonDel");
    btn_del.setAttribute("alt", "Delete");
    btn_del.setAttribute("onclick", "this.parentNode.parentNode.removeChild(this.parentNode);delQuest();");

    var question_area = document.createElement("textarea");
    question_area.name = "quest" + q_num;
    question_area.setAttribute("class", "question_area"/* + q_num*/);
    question_area.setAttribute("id", "quest" + q_num);
    question_area.setAttribute("style", "display:none;");

    var lbl = document.createElement("label");
    lbl.name = "lbl1";
    lbl.setAttribute("class","lbl");
    lbl.setAttribute("id", "lbl_ans_num");
    lbl.innerHTML = "Number of answers (2-10):&nbsp;";

    var numb = document.createElement("input");
    numb.name = "ans_cnt" + q_num;
    numb.setAttribute("class","num_input");
    numb.setAttribute("id","number_id");
    numb.setAttribute("onchange", "add_answers(this);");
    //numb.setAttribute("onclick", "add_answers(this);");
    //numb.setAttribute("onkeypress", "add_answers(this);");
    numb.setAttribute("type", "number");
    numb.setAttribute("value", "2");
    numb.setAttribute("min", "2");
    numb.setAttribute("max", "10");

    var lbl2 = document.createElement("label");
    lbl2.name = "lbl2";
    lbl2.setAttribute("class","lbl");
    lbl2.setAttribute("id", "lbl_quest_mark");
    lbl2.innerHTML = "Grade for question:&nbsp;";

    var mark = document.createElement("input");
    mark.name = "mark" + q_num;
    mark.setAttribute("class","num_input");
    mark.setAttribute("id","number_id");
    mark.setAttribute("type", "number");
    mark.setAttribute("value", "1");
    mark.setAttribute("min", "1");

    new_test.appendChild(btn_del);
    new_test.appendChild(lbl2);
    new_test.appendChild(mark);
    new_test.appendChild(question_div);
    new_test.appendChild(lbl);
    new_test.appendChild(numb);
    new_test.appendChild(question_area);

    for (var i = 0; i < 2; i++) {
        var answer_div = document.createElement("div");
        answer_div.name = i + "ans" + q_num;
        answer_div.setAttribute("id", "answer_id");
        answer_div.setAttribute("class", "answer");
        answer_div.setAttribute("contenteditable", "true");
        answer_div.setAttribute("data-placeholder", "Enter answer...");
        answer_div.setAttribute("role", "textbox");
        answer_div.setAttribute("aria-multiline", "false");

        var answer_area = document.createElement("textarea");
        answer_area.name = i + "ans" + q_num;
        answer_area.setAttribute("class", "answer_area"/* + q_num*/);
        answer_area.setAttribute("id", "ans" + q_num);
        answer_area.setAttribute("style", "display:none;");

        var lbl2 = document.createElement("label");
        lbl2.name = "lbl2";
        lbl2.setAttribute("class","lbl");
        lbl2.setAttribute("id", "lbl_ans_mark");
        lbl2.innerHTML = "Coef (0..1):&nbsp;";

        var coef = document.createElement("input");
        coef.name = i + "coef" + q_num;
        coef.setAttribute("class","num_input");
        coef.setAttribute("id","number_id");
        coef.setAttribute("type", "number");
        coef.setAttribute("value", "0");
        coef.setAttribute("min", "0");
        coef.setAttribute("step", "0.1");

        new_test.appendChild(answer_div);
        new_test.appendChild(answer_area);
        new_test.appendChild(lbl2);
        new_test.appendChild(coef);
    }

    p.appendChild(new_test);

    q_num++;
    document.getElementById("q_num").value = q_num;
} ;

function delQuest() {
    var q_num = document.getElementById("q_num").value;
    q_num--;
    document.getElementById("q_num").value = q_num;
}

function add_answers(elem){
    var par = elem.parentElement;
    var cnt = par.childElementCount - 6;
    var cnt_new = elem.value*4;
    var flag = true;
    var cur_q_num = par.name.substring(4);

    var lbl = document.createElement("label");
    lbl.name = "lbl1";
    lbl.innerHTML = (cnt_new).toString() + " - " + (cnt).toString();

    //par.appendChild(lbl);

    if (cnt_new >= cnt) {
        flag = true;
        for (var i = cnt; i <= cnt_new; i=i+4) {
            if (flag == true) {
                var answer_div = document.createElement("div");
                answer_div.name = ((i-1)/4) + "ans" + cur_q_num;
                answer_div.setAttribute("id", "answer_id");
                answer_div.setAttribute("class", "answer");
                answer_div.setAttribute("contenteditable", "true");
                answer_div.setAttribute("data-placeholder", "Enter answer...");
                answer_div.setAttribute("role", "textbox");
                answer_div.setAttribute("aria-multiline", "false");

                var answer_area = document.createElement("textarea");
                answer_area.name = ((i-1)/4) + "ans" + cur_q_num;
                answer_area.setAttribute("class", "answer_area"/* + q_num*/);
                answer_area.setAttribute("id", "ans" + q_num);
                answer_area.setAttribute("style", "display:none;");

                var lbl2 = document.createElement("label");
                lbl2.name = "lbl2";
                lbl2.setAttribute("class","lbl");
                lbl2.setAttribute("id", "lbl_ans_mark");
                lbl2.innerHTML = "Coef (0..1):&nbsp;";

                var coef = document.createElement("input");
                coef.name = ((i-1)/4) + "coef" + cur_q_num;
                coef.setAttribute("class","num_input");
                coef.setAttribute("id","number_id");
                coef.setAttribute("type", "number");
                coef.setAttribute("value", "0");
                coef.setAttribute("min", "0");
                coef.setAttribute("step", "0.1");

                par.appendChild(answer_div);
                par.appendChild(answer_area);
                par.appendChild(lbl2);
                par.appendChild(coef);
            }
            else
                flag = true;
        }
    }
    else {
        for (var i = (cnt); i > (cnt_new); i--) {
            if (flag == false)
                par.children[i+4].remove();
            else
                flag = false;
        }
    }

    par.update();
};
