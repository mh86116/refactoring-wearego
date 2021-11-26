function searchMember() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let data = {
        word: $('#searchKeyword').val()
    };

    if (data.word !== '') {
        $.ajax({
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token)
            },
            url: "/admin/members",
            data: data,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            type: "GET",
        }).done(function (fragment) {
            $('#memberTable').replaceWith(fragment);
        });
    } else {
        alert("검색어를 입력해 주세요!!");
    }
}

function processMember() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let dto = {
        no: $('#no').text()
    };
    console.log(dto.no);

    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/admin/members/" + dto.no,
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



