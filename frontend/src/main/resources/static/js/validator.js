function validate(form) {
    const personIdInput = document.getElementById("personId");
    const personIdValue = personIdInput ? personIdInput.value : null;
    const curpInput = document.getElementById("curp");
    const curpError = document.getElementById("curpError");
    const curpValue = curpInput.value;
    const emailInput = document.getElementById("email");
    const emailError = document.getElementById("emailError");
    const emailValue = emailInput.value;
    const errors = [];

    emailError.textContent = "";
    curpError.textContent = "";

    const curpCheck = fetch(`/validate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: personIdValue, curp: curpValue })
    }).then(response => response.json())
        .then(data => {
            if (data === true) {
                curpError.textContent = "La CURP ya existe en la base de datos.";
                errors.push("CURP");
            }
        });

    const emailCheck = fetch(`/validate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: personIdValue, email: emailValue })
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