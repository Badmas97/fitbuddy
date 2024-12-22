const HISTORY_API_PATH = "/user/history";
const EXERCISES_API_PATH = "/user/exercises";

/* CALENDAR functions */

function resetCalendar() {
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0]; // YYYY-MM-DD format
    document.getElementById("calendar").value = formattedDate;
}

function onCalendarChange() {
    showHistory();
}

function stepUpCalendar() {
    document.getElementById("calendar").stepUp();
    onCalendarChange();
}

function stepDownCalendar() {
    document.getElementById("calendar").stepDown();
    onCalendarChange();
}

/* WORKOUT LOG functions */

function showHistory() {
    refreshExerciseOptions();

    const date = document.getElementById("calendar").value;
    fetchData(HISTORY_API_PATH + "/" + date, renderHistory);
}

function renderHistory(data) {
    const tbody = document.getElementById("history-table").getElementsByTagName("tbody")[0];
    tbody.innerHTML = ""; // Clear existing rows

    data.forEach((entry, index) => {
        const historyId = entry.id;
        const actionsHtml = `
            <a href='#' title='Edit'>
                <i class='bi bi-pencil-fill icon-grey me-3 fs-5' onclick='editHistory("${historyId}")'></i>
            </a>
            <a href='#' title='Delete'>
                <i class='bi bi-trash-fill icon-red fs-5' onclick='deleteHistory("${historyId}")'></i>
            </a>
        `;

        tbody.innerHTML += `
            <tr>
                <th>${index + 1}</th>
                <td>${entry.exerciseName}</td>
                <td id='history-weight-${historyId}'>${entry.weight}</td>
                <td id='history-reps-${historyId}'>${entry.reps}</td>
                <td id='history-actions-${historyId}'>${actionsHtml}</td>
            </tr>
        `;
    });
    hideStatus();
}

function refreshExerciseOptions() {
    fetchData(EXERCISES_API_PATH, updateExerciseSelect);
}

function updateExerciseSelect(data) {
    const exerciseSelect = document.getElementById("exercise-select");
    exerciseSelect.innerHTML = data.map(exercise => `<option value='${exercise.name}'>${exercise.name}</option>`).join('');
}

function fetchData(url, callback) {
    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch data");
            return response.json();
        })
        .then(callback)
        .catch(error => console.error("ERROR: ", error));
}

function deleteHistory(historyId) {
    fetch(HISTORY_API_PATH + "/" + historyId, { method: 'DELETE' })
        .then(response => {
            if
