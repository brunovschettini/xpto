// BOOTSTRAP

function bsMask(classid, mask, clearNotMatch) {
    $(classid).mask(mask, {clearIfNotMatch: clearNotMatch});
}

function bsShowModal(classid) {
    bsShowDialog(classid);
}

function bsShowDialog(classid) {
    try {
        $("#" + classid).modal('show');
    } catch (e) {
        console.log(e);
    }
}

function bsHideModal(classid) {
    bsHideDialog(classid);
}
function bsCloseModal(classid) {
    bsHideDialog(classid);
}

function bsCloseDialog(classid) {
    bsHideDialog(classid);
}

var zindexrandom = 0;
function bsVisibleDialog(classid, visible) {
    $(document).ready(function () {
        try {
            zindexrandom = zindexrandom + 1000;
            if (classid !== '' && visible !== 'undefined') {
                if (visible) {
                    $("#" + classid).modal('show');
                } else {
                    $("#" + classid).modal('hide');
                    $("#" + classid).on('hidden.bs.modal', function () {});
                }
            }
        } catch (e) {
            console.log(e);
        }
    });
}

function bsHideDialog(classid) {
    try {
        $("#" + classid).modal('hide');
    } catch (e) {
        console.log(e);
    }
}


function bsOnEvent(data) {
    bsOnEventEval(data, null);
}

function bsOnEventEval(data, runjs) {
    var runjs = runjs;
    if (data === null) {
        if (runjs !== null && runjs !== 'undefined' && runjs !== '') {
            try {
                eval(runjs);
            } catch (e) {

            }
        }
    } else {
        var status = data.status; // Can be "begin", "complete" or "success".
        var wH = $(document.body).height();
        switch (status) {
            case "begin": // Before the ajax request is sent.
                $('<div id="loading-transparency-overlay"></div>').appendTo(document.body).fadeIn(3000);
                // $('#loading-transparency-overlay').fadeIn(3000);
                $('#loading-transparency-overlay').css({height: wH});
                $('#loading-transparency-overlay').html('<img src="/gestor/resources/images/preloader.gif" class="loading-transparency-overlay-img"/>');
                // $('body').css({'overflow':'hidden'});
                break;

            case "complete":
                // After the ajax response is arrived.
                if ($('#loading-transparency-overlay')) {
                    $('#loading-transparency-overlay').html('<center><img src="/gestor/resources/images/preloader.gif" style="margin-top: 100px"/></center>');
                }
                break;

            case "success":
                // After update of HTML DOM based on ajax response..
                $("#loading-transparency-overlay").fadeOut(30).remove();
                // $('body').css({'overflow':'auto'});
                break;

            case "error":
                // After update of HTML DOM based on ajax response..
                alert('Erro');
                break;
        }//
//        switch (status) {
//            case "begin": // Before the ajax request is sent.
//                $('#bsstatus').modal('show');
//                break;
//
//            case "complete": // After the ajax response is arrived.               
//                break;
//
//            case "success": // After update of HTML DOM based on ajax response..
//                $('#bsstatus').modal('hide');
//                if (runjs !== null && runjs !== 'undefined' && runjs !== '') {
//                    try {
//                        eval(runjs);
//                    } catch (e) {
//                        console.log(e);
//                    }
//                }
//                break;
//
//            case "error": // After update of HTML DOM based on ajax response..
//                $('#bsstatus').modal('show');
//                break;
//            case "erro": // After update of HTML DOM based on ajax response..
//                $('#bsstatus').modal('show');
//                break;
//        }

    }

}