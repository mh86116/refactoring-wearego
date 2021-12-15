$(function () {
    $('#checkEmailOk').hide();
    $('#checkEmailNull').hide();
    $('#checkNicknameOk').hide();
    $('#checkNicknameNull').hide();
    $('#joinOk').show();
});

let count1 = 0;
let count2 = 0;
function approval(num) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    let email;
    let nickname;
    let value = $('#checkEmail').val();
    if (num === 1) {
        email = $('#email1').val();
        if (email !== '' && email !== null) {
            $.ajax({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token)
                },
                url: "/joins",
                data: {email: email},
                type: "POST",
                success: function (result) {
                    if (result === true) {
                        count1++;
                        $('#checkEmailNull').show();
                        $('#checkEmailOk').hide();
                        $('#pwd1').keyup(function () {
                            $('#checkEmailNull').remove();
                        });
                    } else {
                        $('#checkEmailOk').show();
                        $('#checkEmailNull').hide();
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
                url: "/joins",
                data: {nickname: nickname},
                type: "POST",
                success: function (result) {
                    if (result === true) {
                        count2++;
                        $('#checkNicknameNull').show();
                        $('#checkNicknameOk').hide();
                        $('#phone').keyup(function () {
                            $('#checkNicknameNull').remove();
                        });
                    } else {
                        $('#checkNicknameOk').show();
                        $('#checkNicknameNull').hide();
                    }
                }
            });
        } else {
            alert("닉네임이 입력되지 않았습니다.");
        }
    }
}



function checkForm() {
    if ((count1 !== 1 && count2 !== 1) || (count1 === 1 && count2 === 0) || (count1 === 0 && count2 === 1)) {
        alert("중복확인 해주세요");
        return false;
    } else if (count1 === 1 && count2 === 1) {
        joinOk();
        // return true;
    }
}

function joinOk() {
    // $('#join-modal').show();
        $('#joinOk').click();
}