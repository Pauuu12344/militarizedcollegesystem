function checkPassword(confirmPassword) {
    if (confirmPassword.value != document.getElementById('password').value) {
        confirmPassword.setCustomValidity("Las contraseñas no coinciden.");
    } else {
        confirmPassword.setCustomValidity("");
    }
}