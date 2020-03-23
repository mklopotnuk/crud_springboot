function putValues(id, name, password, barcode, windowtype) {
    if (windowtype === "info") {
        let img = "data:image/png;base64, " + barcode
        $("#id-info").text(id);
        $("#username-info").text(name);
        $("#password-info").text(password);
        $("#barcode-info").attr("src", img);
    }
    if (windowtype === "edit") {
        $('#id-input-edit').val(id);
        $('#id-input-edit-hidden').val(id);
        $('#username-input-edit').val(name);
        $('#password-input-edit').val(password);
    }
}

$(document).ready(function () {
    $.ajax({
        url: "/allusers",
        type: "GET",
        async: false,
        success: function (data) {
            let userData = "";
            $.each(data, function (key, value) {
                userData += `<tr><td>${value.id}</td><td>${value.name}</td><td>${value.password}</td><td>`;
                $.each(value.roles, function (key1, value1) {
                    userData += `${value1.name} `;
                });
                userData += `</td><td><button value="${value.id}" class="btn btn-primary btn-info btn-sm text-white info">Profile</button>
                                <button value="${value.id}" class="btn btn-primary btn-sm text-white edit">Edit</button>
                                <button value="${value.id}" class="btn btn-sm btn-danger delete">Delete</button>
                                </td></tr>`;

            });
            // console.log(userData);
            $("#users").append(userData);
        },
        error: function (error) {
            console.log(error.responseText);
        }
    });
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

$(document).on("click", ".edit", function () {
    $.ajax({
        url: "/customer?id=" + $(this).val(),
        type: "GET",
        async: false,
        success: function (data) {
            editButton(data.id, data.name);
        },

        error: function (error) {
            console.log(error.responseText);
        }
    })
});

$(document).on("click", ".info", function () {
    $.ajax({
        url: "/customer?id=" + $(this).val(),
        type: "GET",
        async: false,
        success: function (data) {
            showProfile(data.id, data.name, data.password, data.barcode);
        },

        error: function (error) {
            console.log(error.responseText);
        }
    })
});


$(document).on("click", ".delete", function () {
    // alert($(this).val())
    $.ajax({
        url: "/customer?id=" + $(this).val(),
        type: "DELETE",
        async: false,
        success: function () {
            location.reload();
        },
        error: function (error) {
            console.log(error.responseText);
        }
    })
});

function showProfile(id, username, password, barcode) {
    putValues(id, username, password, barcode, "info");
    $("#showUserForm").modal('show');
}

function editButton(id, username) {
    putValues(id, username, "", "", "edit");
    $("#editUserForm").modal('show');
}

function onSubmit() {
    let formData;
    let requestType;
    let id = $("#id-input-edit").val();
    if (id !== "") {
        formData = `{
            "id": "${id}",
            "name": "${$("#username-input-edit").val()}",
            "password": "${$("#password-input-edit").val()}",
            "roles": [
                     {
                     "id": "${$("#role-input-edit").val()}"
                     }
                     ]
                     }`;
        requestType = "PUT";
    } else {
        formData = `{
            "name": "${$("#username-input-add").val()}",
            "password": "${$("#password-input-add").val()}",
            "roles": [
                     {
                     "id": "${$("#role-input-add").val()}"
                     }
                     ]
                     }`;
        requestType = "POST";

    }

    // alert(formData);

     $.ajax({
        url: "/customer",
        type: requestType,
        async: true,
        data: formData,
        dataType: 'json',
        contentType: 'application/json',
        success: function () {
            // alert("!");
            // location.reload(true);
            // $("#showUserForm").modal('close');
0            //  window.location("http://127.0.0.1:8080/admin");
        },
        error: function (error) {
            // window.location("http://127.0.0.1:8080/admin");
            // alert(error.responseText);
            console.log(error.responseText);
        },
        complete: function (object, error) {
            location.reload(true)

        }
    })


    // alert(formData);
    // alert(requestType);
    // window.location("http://127.0.0.1:8080/admin")
}


// let name = 'Bill';
// console.log(upperName`Hello ${name}!`);
//
// function upperName(literals, value) {
//     return literals[0] + value.toUpperCase() + literals[1];
// }

