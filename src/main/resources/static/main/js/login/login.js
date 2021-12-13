$(function () {
    $('#checkEmailOk').hide();
    $('#checkEmailNull').hide();
    $('#checkNicknameOk').hide();
    $('#checkNicknameNull').hide();
});

function approval(num) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    let email;
    let nickname;
    if (num === 1) {
        email = $('#email1').val();
        if (email !== '' && email !== null) {
            $.ajax({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token)
                },
                url: "/join/",
                data: email,
                type: "GET",
                success: function (result) {
                    if (result === true) {
                        $('#checkEmailOk').show();
                        $('#checkEmailNull').hide();
                        $('#pwd1').keyup(function () {
                            $('#checkEmailOk').remove();
                        });
                    } else {
                        $('#checkEmailOk').hide();
                        $('#checkEmailNull').show();
                        $('#pwd1').keyup(function () {
                            $('#checkEmailNull').remove();
                        });
                    }
                }
            });
        } else {
            alert("이메일이 입력되지 않았습니다.");
        }
    } else {
        nickname = $('#nickname').val();
        if (nickname !== '' && nickname !== null) {
            $.ajax({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token)
                },
                url: "/join/",
                data: nickname,
                type: "GET",
                success: function (result) {
                    if (result === true) {
                        $('#checkNicknameOk').show();
                        $('#checkNicknameNull').hide();
                        $('#phone').keyup(function () {
                            $('#checkNicknameOk').remove();
                        });
                    } else {
                        $('#checkNicknameOk').hide();
                        $('#checkNicknameNull').show();
                        $('#phone').keyup(function () {
                            $('#checkNicknameNull').remove();
                        });
                    }
                }
            });
        } else {
            alert("닉네임이 입력되지 않았습니다.");
        }
    }
}