document.addEventListener("DOMContentLoaded", () => {
  onLoaded();
  onHistory();
});

const showDivById = (id) => document.getElementById(id).style.display = 'block';
const hideDivById = (id) => document.getElementById(id).style.display = 'none';

function onLoaded() {
  console.log("Page loaded.");
  showUserName();
}

function onLogout() {
  window.location = "/logout";
}

function onExercises() {
  toggleView("Exercises");
}

function onHistory() {
  toggleView("History");
  resetCalendar();
  showHistory();
}

function onAccount() {
  toggleView("Account");
  showAccount();
}

function toggleView(view) {
  hideStatus();
  ["Exercises", "History", "Account"].forEach(id => id === view ? showDivById(id) : hideDivById(id));
}
