// Fermer et ouvrir la barre latérale
const navToggle = document.querySelector(".nav-toggler");
const aside = document.querySelector(".aside");
const mainContent = document.querySelector(".main-content");

if (navToggle) {
  navToggle.addEventListener("click", () => {
    asideToggle();
  });
}

function asideToggle() {
  aside.classList.toggle("open");
  navToggle.classList.toggle("open");
  mainContent.classList.toggle("open");
}

// Typing animation
if (document.querySelector(".typing")) {
  var typed = new Typed(".typing", {
    strings: ["étudiante en BTS SIO", "à la recherche d'un stage"],
    typeSpeed: 100,
    BackSpeed: 60,
    loop: true,
  });
}

// Active menu lors du scroll et fermer menu au clic sur mobile
document.addEventListener("DOMContentLoaded", () => {
  const sections = document.querySelectorAll(".section");
  const navLinks = document.querySelectorAll(".nav li a");

  // Navigation au scroll
  window.addEventListener("scroll", () => {
    let current = "";

    sections.forEach((section) => {
      const sectionTop = section.offsetTop;
      const sectionHeight = section.clientHeight;

      if (window.scrollY >= sectionTop - 300) {
        current = section.getAttribute("id");
      }
    });

    navLinks.forEach((link) => {
      link.classList.remove("active");
      if (link.getAttribute("href") === `#${current}`) {
        link.classList.add("active");
      }
    });
  });

  // Fermer le menu au clic sur un lien (mobile)
  navLinks.forEach((link) => {
    link.addEventListener("click", () => {
      // Si on est sur mobile (menu ouvert)
      if (window.innerWidth <= 1199) {
        asideToggle();
      }
    });
  });
});

// Empêcher le comportement par défaut des liens ancres
document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();

    const targetId = this.getAttribute("href");
    if (targetId === "#") return;

    const targetElement = document.querySelector(targetId);
    if (targetElement) {
      window.scrollTo({
        top: targetElement.offsetTop - 50,
        behavior: "smooth",
      });
    }
  });
});

// Gérer le redimensionnement de la fenêtre
window.addEventListener("resize", () => {
  // Si on repasse en desktop et que le menu est ouvert, le fermer
  if (window.innerWidth > 1199 && aside.classList.contains("open")) {
    aside.classList.remove("open");
    navToggle.classList.remove("open");
    mainContent.classList.remove("open");
  }
});

// Chargement initial
document.addEventListener("DOMContentLoaded", function () {
  console.log("Portfolio chargé avec succès!");
});
