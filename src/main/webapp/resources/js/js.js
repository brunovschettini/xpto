$(function () {
    $('.back-to-top').click(function (e) {
        e.preventDefault();
        $('body').animate({
            scrollTop: 0
        }, 700);
        return;
    });
    $(".focus_item").focus();
});



function statusbar(data) {
    var status = data.status; // Can be "begin", "complete" or "success".

    switch (status) {
        case "begin": // Before the ajax request is sent.
            $('#statusbar').modal('show');
            break;

        case "complete": // After the ajax response is arrived.
            $('#statusbar').modal('show');
            break;

        case "success": // After update of HTML DOM based on ajax response..
            $('#statusbar').modal('hide');
            break;

        case "errp": // After update of HTML DOM based on ajax response..
            $('#statusbar').modal('show');
            break;
    }
}

function statusbaroverlay(data) {
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
    }
}

function overlayrun(data, runjs) {
    alert(runjs);
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
            if (runjs !== null && runjs !== 'undefined' && runjs !== '') {
                try {
                    alert(runjs);
                    eval(runjs);
                } catch (e) {

                }
            }
            // After update of HTML DOM based on ajax response..
            $("#loading-transparency-overlay").fadeOut(30).remove();
            // $('body').css({'overflow':'auto'});
            break;

        case "error":
            // After update of HTML DOM based on ajax response..
            alert('Erro');
            break;
    }
}


function load_masks() {
    $(document).ready(function () {
        $(".mask_cep").mask("99.999-999", {clearIfNotMatch: true});
        $(".mask_mobile").mask("(99)99999-9999", {clearIfNotMatch: true});
        $(".mask_phone").mask("(99)9999-9999", {clearIfNotMatch: true});
        $(".mask_date").mask("99/99/9999", {clearIfNotMatch: true});
        $(".mask_cpf").mask("999.999.999-99", {clearIfNotMatch: true});
        $(".mask_cnpj").mask("99.999.999/9999-99", {clearIfNotMatch: true});
    });
}

$(document).ready(function () {
    var max_index = null;
    $('.modal.fade.in').not($(this)).each(function (index, value) {
        if (max_index < parseInt($(this).css("z-index")))
            max_index = parseInt($(this).css("z-index"));
    });
    if (max_index !== null) {
        $(this).css("z-index", max_index + 1);
    }
//    $('.modal').on('hidden.bs.modal', function (event) {
//        $(this).removeClass('fv-modal-stack');
//        $('body').data('fv_open_modals', $('body').data('fv_open_modals') - 1);
//    });
//
//
//    $('.modal').on('shown.bs.modal', function (event) {
//
//        // keep track of the number of open modals
//
//        if (typeof ($('body').data('fv_open_modals')) == 'undefined')
//        {
//            $('body').data('fv_open_modals', 0);
//        }
//
//
//        // if the z-index of this modal has been set, ignore.
//
//        if ($(this).hasClass('fv-modal-stack'))
//        {
//            return;
//        }
//
//        $(this).addClass('fv-modal-stack');
//
//        $('body').data('fv_open_modals', $('body').data('fv_open_modals') + 1);
//
//        $(this).css('z-index', 1040 + (10 * $('body').data('fv_open_modals')));
//
//        $('.modal-backdrop').not('.fv-modal-stack')
//                .css('z-index', 1039 + (10 * $('body').data('fv_open_modals')));
//
//
//        $('.modal-backdrop').not('fv-modal-stack')
//                .addClass('fv-modal-stack');
//
//    });
});


function preloadpage() {
    // To make sure loader will fadeOut.
    //<![CDATA[
    $(window).load(function () { // makes sure the whole site is loaded
        var wH = $(document.body).height();
        $('body').css({'overflow': 'hidden'});
        $('<div id="loading-transparency-overlay">aaa</div>').appendTo(document.body);
        $('#loading-transparency-overlay').css({height: wH});
        $('#loading-transparency-overlay').html('<img src="resources/images/preloader.gif" class="loading-transparency-overlay-img"/>');
        $('#loading-transparency-overlay').fadeOut(3000, function () {
            $('body').css({'overflow': 'visible'});
            $("#loading-transparency-overlay").remove();

        });
    });
    //]]>    
}
