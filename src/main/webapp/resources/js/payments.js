$(document).on('keydown', function (e) {

    if (e.ctrlKey) {
        // REMOVER ULTIMO ITEM ADICIONADO
        if (e.which.toString() === '81') { // Pesquisar (Ctrl + Q)
            $('.btnremovelastitem').click();
        }
        // REMOVER ULTIMO ITEM ADICIONADO
        if (e.which.toString() === '121') { // Pesquisar (Ctrl + Q)
            alert('teste');
        }
    } else {
        // VOLTAR F2
        if (e.which.toString() === '113') { // Pesquisar (Alt + P)
            $('.payment_back').click();
        }
        // CONCLUIR PEDIDO OU RECEBER F9
        if (e.which.toString() === '120') { // Pesquisar (Alt + P)
            $('.payment_confirm').click();
        }
        // CONFIRMA PAGAMENTO F10
        if (e.which.toString() === '121') { // Pesquisar (Alt + P)
            $('.payment_complete').click();
        }

        // console.log(e.which); // Retorna o número código da tecla
        // console.log(e.altKey); // Se o alt foi Pressionado retorna true
        //    if ((e.altKey) && (e.which === 120)) { // Pesquisar (Alt + P)
        //    }

    }

});