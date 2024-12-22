const ACCOUNT_API_PATH = "/user/account";

// Helper function to send requests (GET, PUT)
function sendRequest(method, url, data = null, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    xhr.onreadystatechange = function () {
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

// Show user name
function showUserName() {
    sendRequest("GET", ACCOUNT_API_PATH, null, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
        } else {
            let data = JSON.parse(response);
            document.getElementById("logged-in-name").innerHTML += data.name;
        }
    });
}

// Show account details
function showAccount() {
    sendRequest("GET", ACCOUNT_API_PATH, null, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
        } else {
            clearFormValues("account-form", "old-password");
            clearFormValues("account-form", "new-password");
            clearFormValues("account-form", "confirm-new-password");
        }
    });
}

// Update account details
function updateAccount() {
    let oldPassword = document.forms["account-form"]["old-password"].value.trim();
    let newPassword = document.forms["account-form"]["new-password"].value.trim();
    let confirmNewPassword = document.forms["account-form"]["confirm-new-password"].value.trim();

    if (!validatePasswords(newPassword, confirmNewPassword)) {
        return; // Stop if passwords don't match
    }

    let data = { "oldPassword": oldPassword, "newPassword": newPassword };

    sendRequest("PUT", ACCOUNT_API_PATH, data, (error, response) => {
        if (error) {
            console.error("ERROR: " + error);
            showStatus(error); // Display error message
        } else {
            console.log("Account updated successfully: " + response);
            showAccount(); // Refresh account details
        }
    });
}

// Validate if the new password and confirm password match
function validatePasswords(newPassword, confirmNewPassword) {
    if (newPassword !== confirmNewPassword) {
        let message = "New password and confirm new password don't match.";
        console.error("ERROR: " + message);
        showStatus(message); // Display error message
        return false;
    }
    return true;
}

// Clear form values
function clearFormValues(formName, fieldName) {
    document.forms[formName][fieldName].value = '';
}

// Show status message (error/success)
function showStatus(message) {
    // Logic to display status message
    console.log(message); // Example: Log the message
}
