function findPassword() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    let email = $('#email').val();
    console.log(email);
}