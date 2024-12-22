function hideStatus() {
    const statusMessage = document.getElementById("status-message");
    if (statusMessage) {
        statusMessage.hidden = true;
    }
}

function showStatus(message) {
    const statusMessage = document.getElementById("status-message");
    if (statusMessage) {
        statusMessage.hidden = false;
        statusMessage.textContent = message; // Use textContent for better security against XSS
    }
}

function clearFormValue(formId, valueName) {
    const form = document.forms[formId];
    if (form && form[valueName]) {
        form[valueName].value = "";
    }
}

function toggleDiv(id, shouldShow) {
    const div = document.getElementById(id);
    if (div) {
        div.style.display = shouldShow ? "block" : "none";
        div.classList.toggle("d-none", !shouldShow);
    }
}

function hideDiv(id) {
    toggleDiv(id, false);
}

function showDiv(id) {
    toggleDiv(id, true);
}
