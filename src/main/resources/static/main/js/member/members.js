function noBack() {
    window.history.forward();
}

function reset() {
    location.href = "/";
}

$(function () {
    $('#alert-success').hide();
    $('#alert-danger').hide();
    $('input').keyup(function () {
        var pwd1 = $('#memberPwd').val();
        var pwd2 = $('#memberPwd2').val();
        if (pwd1 !== "" || pwd2 !== "") {

            if (pwd1 === pwd2) {
                $('#alert-success').show();
                $('#alert-danger').hide();
                $('#submit').removeAttr('disabled');
            } else {
                $('#alert-danger').show();
                $('#alert-success').hide();
                $('#submit').attr('disabled', 'disabled');
            }
        }
    });
});

function patchMember() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let dto = {
        no: $('#memberNo').val(),
        nickname: $('#nickname').val(),
        phone: $('#phone').val()
    };
    console.log(dto.phone);
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        // contentType: "application/json",
        url: "/member/update/" + dto.no,
        data: dto,
        type: "PATCH",
        success: function () {
            alert("변경 처리 되었습니다.");
            location.href = "/member/myPage";
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}