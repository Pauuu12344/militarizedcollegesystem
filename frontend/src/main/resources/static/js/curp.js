function checkCurp(form) {
    const curpInput = document.getElementById("curp");
    const curpError = document.getElementById("curpError");
    const curpValue = curpInput.value;

    curpError.textContent = "";

    fetch(`/validations?curp=${curpValue}`, {
        method: 'GET',
    }).then(response => response.json())
        .then(data => {
            if (data === false) {
                form.submit();
            } else if (data === true) {
                curpError.textContent = "La CURP ya existe en la base de datos.";
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

    return false;
}