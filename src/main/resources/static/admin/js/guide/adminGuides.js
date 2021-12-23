function searchGuide() {
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
            url: "/admin/guides",
            data: data,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            type: "GET",
        }).done(function (fragment) {
            $('#guideTable').replaceWith(fragment);
        });
    } else {
        alert("검색어를 입력해 주세요!!");
    }
}

function searchGuides() {
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
            url: "/admin/waitingGuides",
            data: data,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            type: "GET",
        }).done(function (fragment) {
            $('#guideTable').replaceWith(fragment);
        });
    } else {
        alert("검색어를 입력해 주세요!!");
    }
}

function processApproval(result, name) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    let no = $('#no').text();
    let dto = {
        value: result,
        reject: $('#reject').val()
    };

    if (name === 'guide') {
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/admin/guide/" + no,
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
    } else {
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

}
