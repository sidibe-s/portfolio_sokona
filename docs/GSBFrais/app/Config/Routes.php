<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'Connexion::login'); 
$routes->get('connexion', 'Connexion::login');
$routes->post('/connexion/valider', 'Connexion::valider');  
$routes->get('/connexion/deconnexion', 'Connexion::deconnexion');  

$routes->get('/accueil', 'Accueil::index');  

$routes->get('/changerMdp', 'ModificationMdp::changerMdp');
$routes->post('/ModificationMdp/validerChangerMdp', 'ModificationMdp::validerChangerMdp');

$routes->get('gererfrais', 'GererFrais::index');
$routes->post('gererfrais/maj_fraisforfait', 'Gererfrais::valider_maj_fraisforfait');
$routes->post('gererfrais/creation_fraishorsforfait', 'Gererfrais::valider_creation_fraishorsforfait');
$routes->get('gererfrais/supp_fraishorsforfait/(:num)', 'Gererfrais::supprimer_fraishorsforfait/$1');

$routes->get('etatfrais', 'EtatFrais::index');
$routes->post('etatfrais/mois', 'EtatFrais::selectionner_mois');

$routes->get('suiviFicheFrais', 'SuiviFicheFrais::index');
$routes->post('suiviFicheFrais/selectionner', 'SuiviFicheFrais::selectionner');
$routes->get('suiviFicheFrais/valider/(:num)', 'SuiviFicheFrais::valider/$1');
$routes->post('suiviFicheFrais/valider_maj_fraisforfait', 'SuiviFicheFrais::valider_maj_fraisforfait');
$routes->get('suiviFicheFrais/reporter_fraishorsforfait/(:num)/(:num)', 'SuiviFicheFrais::reporter_fraishorsforfait/$1/$2');
$routes->get('suiviFicheFrais/refuser_fraishorsforfait/(:num)/(:num)', 'SuiviFicheFrais::refuser_fraishorsforfait/$1/$2');

$routes->get('rembourserfrais', 'RembourserFrais::index');
$routes->post('rembourserfrais/selectionner', 'RembourserFrais::selectionner');
$routes->get('rembourserfrais/rembourser/(:num)' , 'RembourserFrais::rembourser/$1');