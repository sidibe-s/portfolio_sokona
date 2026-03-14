// ============================================
// SIDEBAR TOGGLE
// ============================================
const navToggle = document.querySelector(".nav-toggler");
const aside = document.querySelector(".aside");
const mainContent = document.querySelector(".main-content");

// Overlay pour fermer le menu en cliquant dehors (mobile)
const overlay = document.createElement("div");
overlay.classList.add("nav-overlay");
document.body.appendChild(overlay);

if (navToggle) {
  navToggle.addEventListener("click", () => {
    asideToggle();
  });
}

overlay.addEventListener("click", () => {
  if (aside.classList.contains("open")) {
    asideToggle();
  }
});

function asideToggle() {
  aside.classList.toggle("open");
  navToggle.classList.toggle("open");
  mainContent.classList.toggle("open");
  overlay.classList.toggle("active");
}

// ============================================
// TYPING ANIMATION
// ============================================
if (document.querySelector(".typing")) {
  var typed = new Typed(".typing", {
    strings: ["étudiante en BTS SIO", "développeuse fullstack"],
    typeSpeed: 100,
    backSpeed: 60, // Corrigé : BackSpeed → backSpeed
    loop: true,
  });
}

// ============================================
// ACTIVE MENU AU SCROLL + FERMETURE MOBILE
// ============================================
document.addEventListener("DOMContentLoaded", () => {
  const sections = document.querySelectorAll(".section");
  const navLinks = document.querySelectorAll(".nav li a");

  // Highlight du lien actif selon la section visible
  window.addEventListener("scroll", () => {
    let current = "";

    sections.forEach((section) => {
      const sectionTop = section.offsetTop;
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
      if (window.innerWidth <= 1199 && aside.classList.contains("open")) {
        asideToggle();
      }
    });
  });
});

// ============================================
// SCROLL FLUIDE SUR LES ANCRES
// ============================================
document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();

    const targetId = this.getAttribute("href");
    if (targetId === "#") return;

    const targetElement = document.querySelector(targetId);
    if (targetElement) {
      const offset = window.innerWidth <= 1199 ? 70 : 50;
      window.scrollTo({
        top: targetElement.offsetTop - offset,
        behavior: "smooth",
      });
    }
  });
});

// ============================================
// FERMER LE MENU AU REDIMENSIONNEMENT
// ============================================
window.addEventListener("resize", () => {
  if (window.innerWidth > 1199 && aside.classList.contains("open")) {
    aside.classList.remove("open");
    navToggle.classList.remove("open");
    mainContent.classList.remove("open");
    overlay.classList.remove("active");
  }
});

console.log("Portfolio chargé avec succès !");
