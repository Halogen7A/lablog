
if (!localStorage.recordedTheme){
	localStorage.setItem('recordedTheme', 'Light');
}

if (localStorage.getItem('recordedTheme') === 'Light') {
	document.documentElement.style.setProperty("--main-color-1", "#000000");
    document.documentElement.style.setProperty("--main-color-2", "#ffffff");
}

if (localStorage.getItem('recordedTheme') === 'Dark') {
	document.documentElement.style.setProperty("--main-color-1", "#ffffff");
    document.documentElement.style.setProperty("--main-color-2", "#000000");
}

function setTheme(theme) {
    if(theme === 'Dark') {
		document.documentElement.style.setProperty("--main-color-1", "#ffffff");
    	document.documentElement.style.setProperty("--main-color-2", "#000000");
        localStorage.setItem('recordedTheme', 'Dark');
    }
    else if(theme === 'Light') {
		document.documentElement.style.setProperty("--main-color-1", "#000000");
    	document.documentElement.style.setProperty("--main-color-2", "#ffffff");
        localStorage.setItem('recordedTheme', 'Light');
    }
    else {
        alert("Enter a valid theme!");
    }
}