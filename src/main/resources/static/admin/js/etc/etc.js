//member 정렬
$(document).ready(function () {
    $('#memberTable th').each(function (column) {
        $(this).click(function () {
            if ($(this).is('.asc')) {
                $(this).removeClass('asc');
                $(this).addClass('desc');
                sortdir = -1;
            } else {
                $(this).addClass('asc');
                $(this).removeClass('desc');
                sortdir = 1;
            }
            $(this).siblings().removeClass('asc');
            $(this).siblings().removeClass('desc');

            var rec = $('#memberTable').find('tbody>tr').get();
            rec.sort(function (a, b) {
                var val1 = $(a).children('td').eq(column).text().toUpperCase();
                var val2 = $(b).children('td').eq(column).text().toUpperCase();
                return (val1 < val2) ? -sortdir : (val1 > val2) ? sortdir : 0;
            });
            $.each(rec, function (index, row) {
                $('#memberTable tbody').append(row);
            });
        });
    });
});

//테이블 정렬
$(document).ready(function () {
    $('#hidden-table-info th').each(function (column) {
        $(this).click(function () {
            if ($(this).is('.asc')) {
                $(this).removeClass('asc');
                $(this).addClass('desc');
                sortdir = -1;
            } else {
                $(this).addClass('asc');
                $(this).removeClass('desc');
                sortdir = 1;
            }
            $(this).siblings().removeClass('asc');
            $(this).siblings().removeClass('desc');

            var rec = $('#hidden-table-info').find('tbody>tr').get();
            rec.sort(function (a, b) {
                var val1 = $(a).children('td').eq(column).text().toUpperCase();
                var val2 = $(b).children('td').eq(column).text().toUpperCase();
                return (val1 < val2) ? -sortdir : (val1 > val2) ? sortdir : 0;
            });
            $.each(rec, function (index, row) {
                $('#hidden-table-info tbody').append(row);
            });
        });
    });
});