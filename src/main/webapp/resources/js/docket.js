var timer;
var final_time;
function atualizarTimer() {
    var disabled = $('.class_time_disabled').val();
    var time_game = $('.class_time_game').val();
    if (!disabled) {
        clearInterval(timer);
        return false;
    }    
    var time = $('.class_time_count').val();
    var time_count_1 = $('#id_count_1').val();
    if(time_count_1 !== '0:00') {
        time = time_count_1;
    }
    if (time === undefined || time === null || time === '') {
        return false;
    }
    time = time.split(':');
    var m = time[0];
    var mstr = m;
    var s = time[1];
    var sstr = s;        
    timer = setInterval(function () {
        disabled = $('.class_time_disabled').val();
        if (!disabled) {
            clearInterval(timer);
            return false;
        }
        if (s > 59) {
            s = 0;
            m++;
        }
        if (s < 10) {
            sstr = "0" + s;
        } else {
            sstr = s;
        }
        mstr = m;
        s++;
        final_time = mstr + ":" + sstr;
        $('.class_time_count').val(final_time);
        $('#id_count_1').val(final_time);
        $('#id_count').click();
        if (m === time_game) {
            clearTimeout(timer);
            return false;
        }
    }, 1000);
    if (disabled === true) {
        clearTimeout(timer);
        return false;
    }
}

function stopTimer() {
    clearTimeout(timer);
    $('.class_time_count').val(final_time);
    $('#id_count_1').val(final_time);
}

$(document).ready(function () {
    $(".mask_time").setMask('time').val('hh:mm');
});

