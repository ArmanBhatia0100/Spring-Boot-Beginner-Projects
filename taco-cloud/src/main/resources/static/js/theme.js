function toggleTheme() {
    const body = document.body;

    if (body.classList.contains("dark-mode")) {
        body.classList.replace("dark-mode", "light-mode");
        localStorage.setItem("theme", "light");
    } else {
        body.classList.replace("light-mode", "dark-mode");
        localStorage.setItem("theme", "dark");
    }
}

function loadTheme() {
    const savedTheme = localStorage.getItem("theme") || "light";
    document.body.classList.add(savedTheme + "-mode");
}
document.addEventListener("DOMContentLoaded", loadTheme);