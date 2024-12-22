const EXERCISES_API_PATH = "/user/exercises";

// Helper function to handle AJAX requests
function sendRequest(method, url, data = null, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    xhr.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE) {
            if (this.status === 200) {
                callback(null, this.responseText);
            } else {
                callback(this.responseText, null);
            }
        }
    };

    xhr.send(data ? JSON.stringify(data) : null);
}

// Show exercises in the table
function showExercises() {
    sendRequest("GET", EXERCISES_API_PATH, null, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
            return;
        }

        const data = JSON.parse(response);
        const tbody = document.getElementById("exercise-table").getElementsByTagName("tbody")[0];

        clearTable(tbody); // Clear the table before adding new rows

        let rows = data.map((exercise, index) => {
            const exerciseId = exercise.id;
            return `
                <tr>
                    <th>${index + 1}</th>
                    <td id="exercise-name-${exerciseId}">${exercise.name}</td>
                    <td id="exercise-actions-${exerciseId}">
                        <a href="#" title="Edit" class="edit-btn"><i class="bi bi-pencil-fill icon-grey me-3 fs-5"></i></a>
                        <a href="#" title="Delete" class="delete-btn"><i class="bi bi-trash-fill icon-red fs-5"></i></a>
                    </td>
                </tr>`;
        }).join('');

        tbody.innerHTML = rows;

        // Add event listeners for edit and delete buttons
        document.querySelectorAll(".edit-btn").forEach(btn => {
            btn.addEventListener("click", (e) => {
                const exerciseId = e.target.closest('tr').querySelector('td').id.split('-')[2];
                editExercise(exerciseId);
            });
        });

        document.querySelectorAll(".delete-btn").forEach(btn => {
            btn.addEventListener("click", (e) => {
                const exerciseId = e.target.closest('tr').querySelector('td').id.split('-')[2];
                deleteExercise(exerciseId);
            });
        });
    });
}

// Clear all rows in the table
function clearTable(tbody) {
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }
}

// Delete an exercise
function deleteExercise(exerciseId) {
    sendRequest("DELETE", EXERCISES_API_PATH + "/" + exerciseId, null, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
        } else {
            console.log("Deleted exercise: " + response);
            showExercises(); // Refresh the list
        }
    });
}

// Add a new exercise
function onAddExercise() {
    const name = document.forms["new-exercise-form"]["name"].value.trim();
    if (!name) return;

    const data = { name };

    sendRequest("POST", EXERCISES_API_PATH, data, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
        } else {
            console.log("Added exercise: " + response);
            showExercises(); // Refresh the list
        }
    });

    document.forms["new-exercise-form"]["name"].value = ""; // Clear the input field
}

// Edit an exercise
function editExercise(exerciseId) {
    const exerciseNameElement = document.getElementById("exercise-name-" + exerciseId);
    const actionsElement = document.getElementById("exercise-actions-" + exerciseId);
    const exerciseName = exerciseNameElement.textContent;

    // Replace exercise name with an input field
    exerciseNameElement.innerHTML = `<input type="text" value="${exerciseName}" autofocus required>`;

    // Remove edit button and add a save button
    actionsElement.innerHTML = `
        <a href="#" title="Save" class="save-btn"><i class="bi bi-check-square-fill icon-green me-3 fs-5"></i></a>
        ${actionsElement.innerHTML}`;

    document.querySelector(".save-btn").addEventListener("click", () => {
        saveExercise(exerciseId);
    });
}

// Save the edited exercise
function saveExercise(exerciseId) {
    const exerciseNameElement = document.getElementById("exercise-name-" + exerciseId);
    let exerciseName = exerciseNameElement.firstChild.value.trim();

    const data = { name: exerciseName };

    sendRequest("PUT", EXERCISES_API_PATH + "/" + exerciseId, data, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
        } else {
            console.log("Saved exercise: " + response);
            showExercises(); // Refresh the list
        }
    });
}
