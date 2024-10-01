document.addEventListener('DOMContentLoaded', () => {
    const periodSelect = document.getElementById('period');
    const careerSelect = document.getElementById('career');
    const groupSelect = document.getElementById('group');

    function fetchGroups() {
        const periodId = periodSelect.value;
        const careerId = careerSelect.value;

        if (periodId !== "" && careerId !== "") {
            window.location.href = `/grades?periodId=${periodId}&careerId=${careerId}`;
        }
    }

    function fetchGrades() {
        const groupId = groupSelect.value;
        const periodId = periodSelect.value;
        const careerId = careerSelect.value;

        if (groupId !== "") {
            window.location.href = `/grades?groupId=${groupId}&periodId=${periodId}&careerId=${careerId}`;
        }
    }

    periodSelect.addEventListener('change', fetchGroups);
    careerSelect.addEventListener('change', fetchGroups);
    groupSelect.addEventListener('change', fetchGrades);
});

function saveGrade(id) {
    const row = document.querySelector(`tr[data-grade-id="${id}"]`);
    const firstPartial = row.querySelector('input[name="firstPartial"]').value;
    const secondPartial = row.querySelector('input[name="secondPartial"]').value;
    const thirdPartial = row.querySelector('input[name="thirdPartial"]').value;

    const periodSelect = document.getElementById('period');
    const careerSelect = document.getElementById('career');
    const groupSelect = document.getElementById('group');

    const groupId = groupSelect.value;
    const periodId = periodSelect.value;
    const careerId = careerSelect.value;

    const grade = {
        gradeId: id,
        firstPartial: parseFloat(firstPartial),
        secondPartial: parseFloat(secondPartial),
        thirdPartial: parseFloat(thirdPartial),
        groupId: parseInt(groupId),
        periodId: parseInt(periodId),
        careerId: parseInt(careerId)
    };

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


    fetch(`/grades`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify(grade)
    });
}