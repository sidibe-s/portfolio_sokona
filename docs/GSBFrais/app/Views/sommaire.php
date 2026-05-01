<?php $session = session(); ?> 
<?php // var_dump(esc($session->get('idRole'))); ?>

<div id="menuGauche">
    <div id="infosUtil">
        <div id="user">
            <img src="<?= base_url('assets/images/UserIcon.png') ?>" alt="Utilisateur" />
        </div>
        <div id="infos">
            <h2><?= esc($session->get('prenom') . ' ' . $session->get('nom')) ?></h2>
            <h3><?= esc($session->get('libelleRole')) ?></h3>  <!-- afficher libelleRole de la table Role -->
        </div>
        <ul class="menuList">
            <li>
                <?= anchor('connexion/deconnexion', 'Déconnexion', ['title' => 'Se déconnecter']) ?>
            </li>
            <li>
                <?= anchor('changerMdp', 'Changer Mdp', ['title' => 'Changer de mdp']) ?>
            </li>
        </ul> 
    </div>  

    <ul id="menuPrincipal" class="menuList">

        <?php 
            // récupérer l'url (merci chatgpt car je trouvais pas comment récupérer l'url)
            $uri = service('uri');
            $page = $uri->getSegment(1);

            $date_modif = $session->get('date_modif_mdp'); // date de la derniere modif
            $aujourdhui = new DateTime(); // date actuelle
            $dateMdp = new DateTime($date_modif);
            
            // calcul de la différence de jours
            $diff = $dateMdp->diff($aujourdhui);
            $nbJours = $diff->days;
            // echo $nbJours;
            if($nbJours < 90) :
        ?>

        <li>
            <?= anchor('accueil', 'Accueil', ['title' => 'Accueil']) ?>
        </li>
            <!-- hidden tout les rôles sauf visiteur médicaux -->
            <li <?= (esc($session->get('idRole')) != 'v') ? 'hidden' : '' ?>> 
                <?= anchor('gererfrais', 'Saisie fiche de frais', ['title' => 'Saisie fiche de frais']) ?>
            </li>
            <li <?= (esc($session->get('idRole')) != 'v') ? 'hidden' : '' ?>>
                <?= anchor('etatfrais', 'Mes fiches de frais', ['title' => 'Consultation de mes fiches de frais']) ?>
            </li>
            <!-- fin du hidden-->

            <!-- hidden tout les rôles sauf comptable -->
            <li <?= (esc($session->get('idRole')) != 'c') ? 'hidden' : '' ?>> 
                <?= anchor('rembourserfrais', 'Rembourser fiche de frais', ['title' => 'Rembourser fiche de frais']) ?>
            </li>
            <li <?= (esc($session->get('idRole')) != 'c') ? 'hidden' : '' ?>>
                <?= anchor('suiviFicheFrais', 'Suivi du paiment', ['title' => 'Suivii des fiches de frais']) ?>
            </li>
            <!-- fin du hidden-->
        <?php endif; ?>

        <?php 
            // si le mot de passe a plus d'90 jour et qu'on n'est pas sur changerMdp
            if ($nbJours >= 90 && $page != 'changerMdp') {
                header("Location: /index.php/changerMdp");
                exit();
            }
        ?>
    </ul>
</div>