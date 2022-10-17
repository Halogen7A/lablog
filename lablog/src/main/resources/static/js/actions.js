function setTheme(theme) {
    if(theme === 'Dark') {
        document.documentElement.style.setProperty("--main-color-1", "#ffffff");
        document.documentElement.style.setProperty("--main-color-2", "#000000");
    }
    else if(theme === 'Light') {
        document.documentElement.style.setProperty("--main-color-1", "#000000");
        document.documentElement.style.setProperty("--main-color-2", "#ffffff");
    }
    else {
        alert("Enter a valid theme!");
    }
}