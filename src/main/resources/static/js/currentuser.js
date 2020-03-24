$(document).ready(function () {
    $.ajax({
        url: "/currentuser",
        type: "GET",
        async: false,
        success: function (data) {
            $("#current-user").text(data.username);
        },
        error: function (error) {
            console.log(error.responseText);
        }
    })
});
