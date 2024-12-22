document.addEventListener("DOMContentLoaded", () => {
    onLoaded();
});

function onLoaded() {
    clearLoginForm();
    console.log("Page loaded.");
}

function onLogin() {
    const form = document.forms["loginForm"];
    if (!form) {
        console.error("Login form not found.");
        return;
    }

    // Trim and extract input values
    const name = form["name"].value.trim();
    const password = form["password"].value.trim();

    if (!name || !password) {
        showStatus("Username and password are required.");
        clearLoginForm();
        return;
    }

    const url = "/login/perform_login";
    const data = JSON.stringify({ name, password });

    // Use Fetch API for better modern HTTP request handling
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: data
    })
    .then(response => {
        if (response.ok) {
            showStatus("Logged in successfully.");
            window.location.href = "/"; // Redirect to home page
        } else {
            return response.text().then(errorMessage => {
                throw new Error(errorMessage);
            });
        }
    })
    .catch(error => {
        console.error("Login failed:", error);
        showStatus(error.message || "An error occurred during login.");
        clearLoginForm();
    });
}

function clearLoginForm() {
    clearFormValue("loginForm", "name");
    clearFormValue("loginForm", "password");
}
