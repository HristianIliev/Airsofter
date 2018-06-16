$("#names-form").submit(function(event) {
  var name = $("#name").val();
  var lastName = $("#lastName").val();

  startLoadingOnNames();

  $.ajax({
    url: "/api/changeNames",
    method: "POST",
    data: JSON.stringify({
      name: name,
      lastName: lastName
    }),
    contentType: "application/json",
    success: function(result) {
      stopLoadingOnNames();
      if (result === null || result === "null" || result === "") {
        iziToast.error({
          title: "Error",
          message: "Имената не успяха да бъдат сменени.",
          position: "topRight"
        });
      } else {
        iziToast.success({
          title: "OK",
          message: "Успешно променихте имената си",
          position: "topRight"
        });
      }
    }
  });

  event.preventDefault();
});

function startLoadingOnNames() {
  $("#submit-names").html(
    '<i class="fa fa-check"></i> Промени данните <i class="fas fa-circle-notch fa-spin"></i>'
  );
}

function stopLoadingOnNames() {
  $("#submit-names").html('<i class="fa fa-check"></i> Промени данните');
}

$("#password-form").submit(function(event) {
  var oldPassword = $("#old-password").val();
  var newPassword = $("#new-password").val();
  var reNewPassword = $("#re-new-password").val();

  if (newPassword !== reNewPassword) {
    iziToast.error({
      title: "Error",
      message: "Паролите не съвпадат",
      position: "topRight"
    });

    event.preventDefault();
    return false;
  }

  startLoadingOnPasswords();

  $.ajax({
    url: "/api/changePassword",
    method: "POST",
    data: JSON.stringify({
      oldPassword: oldPassword,
      newPassword: newPassword
    }),
    contentType: "application/json",
    success: function(result) {
      stopLoadingOnPasswords();
      if (result === null || result === "null" || result === "") {
        iziToast.error({
          title: "Error",
          message: "Старата парола е грешна",
          position: "topRight"
        });
      } else {
        iziToast.success({
          title: "OK",
          message: "Успешно променихте паролата си",
          position: "topRight"
        });
      }
    }
  });

  event.preventDefault();
});

function startLoadingOnPasswords() {
  $("#submit-passwords").html(
    '<i class="fa fa-check"></i> Смени паролата <i class="fas fa-circle-notch fa-spin"></i>'
  );
}

function stopLoadingOnPasswords() {
  $("#submit-passwords").html('<i class="fa fa-check"></i> Смени паролата');
}
