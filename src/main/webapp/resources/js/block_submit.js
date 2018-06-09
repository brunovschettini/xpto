function arrumaEnter(field, event) {
    alert('aaa');
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < field.form.elements.length; i++)
            if (field == field.form.elements[i])
                break;
        i = (i + 1) % field.form.elements.length;
        field.form.elements[i].focus();
        return false;
    }
    return true;
}


function bloquearCtrlJ() {   // Verificação das Teclas
    var tecla = window.event.keyCode;   //Para controle da tecla pressionada
    var ctrl = window.event.ctrlKey;    //Para controle da Tecla CTRL
    if (ctrl && tecla == 74) {    //Evita teclar ctrl + j
        event.keyCode = 0;
        event.returnValue = false;
    }
}

function Verificar() {   // Verificação das Teclas
    var tecla = window.event.keyCode;
    var ctrl = window.event.ctrlKey;    //  Para Controle da Tecla CTRL
    if (ctrl && tecla == 106)    //Evita teclar ctrl + j
    {
        event.keyCode = 116;
        event.returnValue = false;
    }
}

function bloquear_ctrl_j() {
    if (window.event.ctrlKey && window.event.keyCode == 74) {
        event.keyCode = 0;
        event.returnValue = false;
    }
}

function loadBarCodeProduct(e) {
// Trava a tecla ctrl e J impossibilitando a abertura do painel de gerenciamento de download do Chrome e abertura da pagina de busca do google no firefox 5
    if (e.keyCode == 17 || e.keyCode == 74 || e.keyCode == 13) {
        e.keyCode = 0;
        e.returnValue = false;        
        document.getElementById('form_product:i_load_product').click();
        return false;
    }
}