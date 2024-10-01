document.addEventListener('DOMContentLoaded', () => {
    const periodSelect = document.getElementById('period');
    const careerSelect = document.getElementById('career');

    function fetchGroupData() {
        const periodId = periodSelect.value;
        const careerId = careerSelect.value;

        if (periodId !== "" && careerId !== "") {
            const path = `/groups/new?periodId=${periodId}&careerId=${careerId}`;
            window.location.href = path;
        }
    }

    periodSelect.addEventListener('change', fetchGroupData);
    careerSelect.addEventListener('change', fetchGroupData);
});

function addStudentToGroup(button) {
    const studentId = button.getAttribute('data-student-id');
    const studentName = button.getAttribute('data-student-name');
    const studentEnrollment = button.getAttribute('data-student-enrollment');
    const groupStudentsTable = document.getElementById('group-students').getElementsByTagName('tbody')[0];

    console.log(studentName);

    const newRow = groupStudentsTable.insertRow();
    newRow.innerHTML = `
        <td>${studentId}</td>
        <td>${studentName}</td>
        <td>${studentEnrollment}</td>
        <td>
            <button type="button" class="btn btn-sm btn-outline-danger" onclick="removeStudentFromGroup(this)" data-student-id="${studentId}">
                Eliminar
            </button>
        </td>`;

    button.disabled = true;
    updateStudentsInput();
}

function removeStudentFromGroup(button) {
    const studentId = button.getAttribute('data-student-id');
    const availableStudentsTable = document.getElementById('available-students').getElementsByTagName('tbody')[0];
    const rows = availableStudentsTable.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const addButton = rows[i].getElementsByTagName('button')[0];
        if (addButton.getAttribute('data-student-id') === studentId) {
            addButton.disabled = false;
            break;
        }
    }

    button.closest('tr').remove();
    updateStudentsInput();
}

function addSubjectToGroup(button) {
    const subjectId = button.getAttribute('data-subject-id');
    const subjectName = button.getAttribute('data-subject-name');
    const groupSubjectsTable = document.getElementById('group-subjects').getElementsByTagName('tbody')[0];

    const newRow = groupSubjectsTable.insertRow();
    newRow.innerHTML = `
        <td>${subjectId}</td>
        <td>${subjectName}</td>
        <td>
            <button type="button" class="btn btn-sm btn-outline-danger" onclick="removeSubjectFromGroup(this)" data-subject-id="${subjectId}">
                Eliminar
            </button>
        </td>
    `;

    button.disabled = true;
    updateSubjectsInput();
}

function removeSubjectFromGroup(button) {
    const subjectId = button.getAttribute('data-subject-id');
    const availableSubjectsTable = document.getElementById('available-subjects').getElementsByTagName('tbody')[0];
    const rows = availableSubjectsTable.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const addButton = rows[i].getElementsByTagName('button')[0];
        if (addButton.getAttribute('data-subject-id') === subjectId) {
            addButton.disabled = false;
            break;
        }
    }

    button.closest('tr').remove();
    updateSubjectsInput();
}

function updateStudentsInput() {
    const groupStudentsTable = document.getElementById('group-students').getElementsByTagName('tbody')[0];
    const rows = groupStudentsTable.getElementsByTagName('tr');
    const studentIds = [];

    for (let i = 0; i < rows.length; i++) {
        const button = rows[i].getElementsByTagName('button')[0];
        studentIds.push(button.getAttribute('data-student-id'));
    }

    document.getElementById('students-input').value = studentIds.join(',');
}

function updateSubjectsInput() {
    const groupSubjectsTable = document.getElementById('group-subjects').getElementsByTagName('tbody')[0];
    const rows = groupSubjectsTable.getElementsByTagName('tr');
    const subjectIds = [];

    for (let i = 0; i < rows.length; i++) {
        const button = rows[i].getElementsByTagName('button')[0];
        subjectIds.push(button.getAttribute('data-subject-id'));
    }

    document.getElementById('subjects-input').value = subjectIds.join(',');
}

function submitGroupForm() {
    updateStudentsInput();
    updateSubjectsInput();
}

function validateForm() {
    const groupStudentsTable = document.getElementById('group-students').getElementsByTagName('tbody')[0];
    const groupSubjectsTable = document.getElementById('group-subjects').getElementsByTagName('tbody')[0];
    const errors = [];

    if (groupStudentsTable.rows.length === 0) {
        document.getElementById('students-error').innerText = 'Debe seleccionar al menos un estudiante';
        errors.push('Debe agregar al menos un estudiante.');
    }

    if (groupSubjectsTable.rows.length === 0) {
        document.getElementById('subjects-error').innerText = 'Debe seleccionar al menos una materia';
        errors.push('Debe agregar al menos una materia.');
    }

    return errors.length === 0;
}