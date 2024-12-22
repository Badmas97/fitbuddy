document.addEventListener("DOMContentLoaded", () => {
    onLoaded();
});

function onLoaded() {
    clearRegisterForm();
    console.log("Page loaded.");
}

function onRegister() {
    const form = document.forms["registerForm"];
    if (!form) {
        console.error("Register form not found.");
        return;
    }

    // Trim and extract input values
    const name = form["name"].value.trim();
    const password = form["password"].value.trim();
    const passwordConfirm = form["passwordConfirm"].value.trim();

    // Validation: Check if password and confirmation match
    if (password !== passwordConfirm) {
        const message = "Password and confirm password don't match.";
        console.error("ERROR:", message);
        showStatus(message);
        return;
    }

    if (!name || !password || !passwordConfirm) {
        showStatus("All fields are required.");
        return;
    }

    const url = "/register";
    const data = JSON.stringify({ name, password });

    // Use Fetch API for better HTTP request handling
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: data
    })
    .then(response => {
        if (response.ok) {
            showStatus("Registered successfully.");
        } else {
            return response.text().then(errorMessage => {
                throw new Error(errorMessage);
            });
        }
    })
    .catch(error => {
        console.error("Registration failed:", error);
        showStatus(error.message || "An error occurred during registration.");
    })
    .finally(() => {
        clearRegisterForm();
    });
}

function clearRegisterForm() {
    clearFormValue("registerForm", "name");
    clearFormValue("registerForm", "password");
    clearFormValue("registerForm", "passwordConfirm");
}
