function guideRegister() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let dto = {
        no: $('#memberId').val(),
        name: $('#name').val(),
        bank: $('#bank').val(),
        account: $('#account').val(),
        guideIntro: $('#guideIntro').val()
    }
    if (dto !== '') {
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        contentType: "application/json",
        url: "/guide/register/" + dto.no,
        data: dto,
        type: "POST"
    });

    }


}