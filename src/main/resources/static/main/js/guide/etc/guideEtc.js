function clickImg() {
    $('#thumbnailImg1').click();
}

function content1() {
    $('#thumbnailImg2').click();
}
function content2() {
    $('#thumbnailImg3').click();
}

function loadImg(value, num) {
    if (value.files && value.files[0]) {
        const reader = new FileReader();

        reader.onload = function (ev) {
            switch (num) {
                case 1:
            document.getElementById('titleImg').src = ev.target.result;
            break;
                case 2:
                    document.getElementById('contentImg1').src = ev.target.result;
                    break;
                case 3:
                    document.getElementById('contentImg2').src = ev.target.result;
                    break;

            }
        };
        reader.readAsDataURL(value.files[0]);

    }
}

