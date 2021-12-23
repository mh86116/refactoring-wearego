function processGoodsApproval(result, name) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let no = $('#no').text();
    let dto = {
        value: result,
        reject: $('#reject').val()
    };
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/admin/goods/" + no,
        data: dto,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        type: "PATCH",
        success: function () {
            location.reload();
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}