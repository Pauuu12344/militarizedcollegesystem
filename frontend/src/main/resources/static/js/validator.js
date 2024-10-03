function validate(form) {
    const curpInput = document.getElementById("curp");
    const curpError = document.getElementById("curpError");
    const curpValue = curpInput.value;
    const emailInput = document.getElementById("email");
    const emailError = document.getElementById("emailError");
    const emailValue = emailInput.value;
    const errors = [];

    emailError.textContent = "";
    curpError.textContent = "";

    const curpCheck = fetch(`/validate?curp=${curpValue}`, {
        method: 'POST',
        body: JSON.stringify({curp: curpValue}),
    }).then(response => response.json())
        .then(data => {
            if (data === true) {
                curpError.textContent = "La CURP ya existe en la base de datos.";
                errors.push("CURP");
            }
        });

    const emailCheck = fetch(`/validate?email=${emailValue}`, {
        method: 'POST',
    }).then(response => response.json())
        .then(data => {
            if (data === true) {
                emailError.textContent = "El correo electrónico ya existe en la base de datos.";
                errors.push("Email");
            }
        });

    Promise.all([curpCheck, emailCheck]).then(() => {
        if (errors.length === 0) {
            form.submit();
        }
    }).catch(error => {
        console.error('Error:', error);
    });

    return false;
}