$(function () {
    $('.date').datepicker({
        format: "yyyy-mm-dd",
        startDate: '+0d',
        title: '날짜 선택',
        autoclose: true,
        language: "ko"
    });
});

    let idCount = 0;
function addOption() {
    var option = $("#option tr:last");
    var insertTr = "";
    insertTr += '<tr id="optionList_"' + (idCount++) + '">';
    insertTr += '<td><label>옵션명&ensp;' + '<input class="summerOption optionName" type="text" id="optionName" name="optionName"></label>&ensp;';
    insertTr += '<label>가격&ensp;<input class="summerPrice" type="text" id="optionPrice" name="optionPrice"></label>&ensp;';
    insertTr += '<button type="button" class="summerBtn" onclick="deleteRow(this);">삭제</button>' + '</td>';
    insertTr += '</tr>';
    option.after(insertTr);
}

function deleteRow(obj) {
    $(obj).parent().parent().remove();
}