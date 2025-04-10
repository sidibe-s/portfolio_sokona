// Fermer et ouvrir la barre latérale
const navToggle = document.querySelector('.nav-toggler');
const aside = document.querySelector('.aside');
const mainContent = document.querySelector('.main-content');

if (navToggle) {
  navToggle.addEventListener('click', () => {
    asideToggle();
  });
}

function asideToggle() {
  aside.classList.toggle('open');
  navToggle.classList.toggle('open');
  mainContent.classList.toggle('open');
}

// Typing animation
if (document.querySelector('.typing')) {
  var typed = new Typed('.typing', {
    strings: [
      'étudiante en BTS SIO',
      'à la recherche d\'un stage',
      'passionnée de développement web'
    ],
    typeSpeed: 100,
    BackSpeed: 60,
    loop: true
  });
}

// Active menu lors du scroll
document.addEventListener('DOMContentLoaded', () => {
  const sections = document.querySelectorAll('.section');
  const navLinks = document.querySelectorAll('.nav li a');
  
  window.addEventListener('scroll', () => {
    let current = '';
    
    sections.forEach(section => {
      const sectionTop = section.offsetTop;
      const sectionHeight = section.clientHeight;
      
      if (window.scrollY >= sectionTop - 300) {
        current = section.getAttribute('id');
      }
    });
    
    navLinks.forEach(link => {
      link.classList.remove('active');
      if (link.getAttribute('href') === `#${current}`) {
        link.classList.add('active');
      }
    });
  });
});
