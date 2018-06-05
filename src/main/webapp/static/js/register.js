$(".type-account-box").click(function() {
  $(".type-account-box.active").removeClass("active");
  $(this).addClass("active");
});

$(".login-form-inputs").submit(function(event) {
  var name = $("#materialFormLoginName").val();
  var lastName = $("#materialFormLoginSurname").val();
  var email = $("#materialFormLoginEmail").val();
  var password = $("#materialFormLoginPassword").val();
  var repassword = $("#materialFormLoginPasswordConfirm").val();
  var dataAccountType = $(".type-account-box.active").attr("data-account-type");
  var arenaOwner = false;
  if (dataAccountType === 1) {
    arenaOwner = true;
  }

  if (password !== repassword) {
    displayError();
    event.preventDefault();
  }

  initiateLoading();

  $.ajax({
    url: "/api/register",
    method: "POST",
    data: JSON.stringify({
      name: name,
      lastName: lastName,
      email: email,
      password: password,
      arenaOwner: arenaOwner
    }),
    contentType: "application/json",
    success: function(result) {
      stopLoading();
      if (result === null || result === "null" || result === "") {
        alert("first if");
        displayEmailTakenError();
      } else {
        alert("second if");
        window.location.href = "/login";
      }
    }
  });

  event.preventDefault();
});

function initiateLoading() {
  $("#proceed").html('Продължи <i class="fas fa-circle-notch fa-spin"></i>');
}

function stopLoading() {
  $("#proceed").html("Продължи");
}

function displayEmailTakenError() {
  iziToast.error({
    title: "Error",
    message: "Вече съществува акаунт с този емайл",
    position: "topRight"
  });
}

function displayError() {
  iziToast.error({
    title: "Error",
    message: "Паролите не съвпадат",
    position: "topRight"
  });
}

$("#back").click(function() {
  window.history.back();
});
