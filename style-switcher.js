/**
 * Gestion du changeur de style et du mode jour/nuit
 */

// Ouvre/ferme le panneau de configuration des styles
const styleSwitcherToggle = document.querySelector(".style-switcher-toggler");
if (styleSwitcherToggle) {
  styleSwitcherToggle.addEventListener("click", () => {
    document.querySelector(".style-switcher").classList.toggle("open");
  });
}

// Masque le sélecteur de style lors du défilement
window.addEventListener("scroll", () => {
  if (document.querySelector(".style-switcher").classList.contains("open")) {
    document.querySelector(".style-switcher").classList.remove("open");
  }
});

// Gestion des couleurs du thème
const alternateStyles = document.querySelectorAll(".alternate-style");

function setActiveStyle(color) {
  alternateStyles.forEach((style) => {
    if (color === style.getAttribute("title")) {
      style.removeAttribute("disabled");
    } else {
      style.setAttribute("disabled", "true");
    }
  });
  // Enregistrer la préférence de couleur
  localStorage.setItem("color", color);
}

// Vérifie s'il y a une couleur déjà enregistrée
const currentColor = localStorage.getItem("color");
if (currentColor) {
  setActiveStyle(currentColor);
}

// Gestion du mode jour/nuit
const dayNight = document.querySelector(".day-night");
if (dayNight) {
  dayNight.addEventListener("click", () => {
    dayNight.querySelector("i").classList.toggle("fa-sun");
    dayNight.querySelector("i").classList.toggle("fa-moon");
    document.body.classList.toggle("dark");
    
    // Enregistrer la préférence de thème
    if (document.body.classList.contains("dark")) {
      localStorage.setItem("theme", "dark");
    } else {
      localStorage.setItem("theme", "light");
    }
  });
}

// Vérifie le thème au chargement
window.addEventListener("load", () => {
  if (dayNight) {
    // Vérifie s'il y a un thème déjà enregistré
    const theme = localStorage.getItem("theme");
    if (theme === "dark") {
      document.body.classList.add("dark");
      dayNight.querySelector("i").classList.add("fa-sun");
      dayNight.querySelector("i").classList.remove("fa-moon");
    } else if (window.matchMedia && window.matchMedia("(prefers-color-scheme: dark)").matches && !theme) {
      // Si le thème du système est sombre et qu'aucune préférence n'est enregistrée
      document.body.classList.add("dark");
      dayNight.querySelector("i").classList.add("fa-sun");
      dayNight.querySelector("i").classList.remove("fa-moon");
      localStorage.setItem("theme", "dark");
    } else {
      dayNight.querySelector("i").classList.add("fa-moon");
      dayNight.querySelector("i").classList.remove("fa-sun");
    }
  }
});

