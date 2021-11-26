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