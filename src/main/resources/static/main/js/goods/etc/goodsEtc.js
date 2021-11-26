function goodsImage() {
    $('#thumbnailImg11').click();
}

function loadImg(value, num) {
    if (value.files && value.files[0]) {
        const reader = new FileReader();

        reader.onload = function (ev) {
            switch (num) {
                case 1:
                    document.getElementById('titleImage').src = ev.target.result;
                    break;
                case 2:
                    document.getElementById('bodyImg1').src = ev.target.result;
                    break;
                case 3:
                    document.getElementById('bodyImg2').src = ev.target.result;
                    break;

            }
        };
        reader.readAsDataURL(value.files[0]);

    }
}