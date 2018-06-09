$(document).on('keydown', function (e) {

    if (e.ctrlKey) {
        // ADICIONAR
        if (e.which.toString() === '13') { // Pesquisar (Ctrl + Enter)
            $('.btnadditem').click();
        }
        // REMOVER ULTIMO ITEM ADICIONADO
        if (e.which.toString() === '81') { // Pesquisar (Ctrl + Q)
            $('.btnremovelastitem').click();
        }
        // PESQUISA PESSOA F2
        if (e.which.toString() === '113') { // Pesquisar (Ctrl + F2)
            $('.find_person_ink').click();
            $('.q_person').focus();
        }
        // PESQUISA PESSOA F3
        if (e.which.toString() === '114') { // Pesquisar (Ctrl + F2)
            $('.find_person_simple_ink').click();
            $('.q_simple_person').focus();
        }
        // FOCUS NO ITEM F9
        if (e.which.toString() === '120') { // Pesquisar (Ctrl + F9)
            $(".focusitem").focus();
        }
        // FOCUS NQ QUANTIDADE F10
        if (e.which.toString() === '120') { // Pesquisar (Alt + P)
            $(".focusqtde").focus();
        }
    } else {
        // NOVA VENDA F2
        if (e.which.toString() === '113') { // Pesquisar (Alt + P)
            $('.new_sale').click();
        }
        // NOVO PEDIDO F8
        if (e.which.toString() === '119') { // Pesquisar (Alt + P)
            $('.rollback').click();
        }
        // NOVO PEDIDO F9
        if (e.which.toString() === '120') { // Pesquisar (Alt + P)
            $('.new_order').click();
        }
        // CONCLUIR PEDIDO OU RECEBER F10
        if (e.which.toString() === '121') { // Pesquisar (Alt + P)
            $('.finish').click();
        }
    }

    // console.log(e.which); // Retorna o número código da tecla
    // console.log(e.altKey); // Se o alt foi Pressionado retorna true
//    if ((e.altKey) && (e.which === 120)) { // Pesquisar (Alt + P)
//    }
});


$(document).ready(function () {
//    $(document).on('keyup', 'body', function (e) {
//        e.preventDefault();
//        var code = e.which;
//        //if (code != 13 && $(this).val().length >= 13) {
//        //    loaddelegates($(this).val());
//        // }
//        alert(code);
//        if (e.keyCode === 55) {
//            if($('.focusitembarras').empty()) {
//               $('.focusitembarras').focus();                 
//            }
//        }
//    });
});