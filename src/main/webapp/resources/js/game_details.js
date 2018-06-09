var timer;
update_game_details();
function update_game_details() {
    timer = setInterval(function () {
        $('#id_start_update').click();
    }, 5000);
}
