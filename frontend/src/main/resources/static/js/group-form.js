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
})