function noBack() {
    window.history.forward();
}

function reset() {
    location.href = "/";
}

$(function() {
    $('#alert-success').hide();
    $('#alert-danger').hide();
    $('input').keyup(function () {
        var pwd1 = $('#pwd1').val();
        var pwd2 = $('#pwd2').val();
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

    let no = $('#memberNo').val();
    let dto = {
        nickname: $('#nickname').val(),
        phone: $('#phone').val()
    };
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/member/update/" + no,
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

function pwdUpdate() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let no = $('#memberNo').val();
    let dto = {
        pwd: $('#pwd1').val()
    }
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/member/pwdUpdate/" + no,
        data: dto,
        type: "PATCH",
        success: function () {
            alert("변경 처리 되었습니다. 다시 로그인 해주세요.");
            location.href = "/logout";
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });


}