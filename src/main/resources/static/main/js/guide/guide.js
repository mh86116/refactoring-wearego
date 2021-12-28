function updateGuide() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var no = document.getElementById('guideId').innerText;
    console.log(no);

    let dto = {
        email: document.getElementById('guideEmail').innerText,
        bank: document.getElementById('guideBank').innerText,
        account: document.getElementById('guideAccount').innerText
}
    console.log(dto.bank);

    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        url: "/guide/updateGuide/" + no,
        data: dto,
        type: "PATCH",
        success: function () {
            alert("변경 처리 되었습니다.");
            // location.href = "/member/myPage";
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });

}