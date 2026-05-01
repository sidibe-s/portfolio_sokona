<?php

namespace App\Controllers;

use App\Models\GsbModel;

/**
* Le controlleur ModificationMdp permettant de modifier son mot de passe (obligatoire apres 90 jours pour tout les visiteurs ou comptable de la base de donnée)
*/
class ModificationMdp extends BaseController
{
    /**
     * Constructeur du controlleur ModificationMdp
     */
    public function __construct()
    {
        helper(['url', 'form']); // helpers URL et form
        $this->gsb_model = new GsbModel();
    }
    /**
     * Fonction permettant de changer le mot de passe de l'utilisateur connecter
     *
     * @return void
     */
    public function changerMdp()
    {
        if (!session()->get('isLoggedIn')) {
            return redirect()->to('/');
        }

        $data['titre'] = "Changer le mot de passe";
        $data['sc_titre'] = "Entête du changement de mot de passe";

        return view('structures/page_entete')
            . view('structures/messages')
            . view('sommaire')
            . view('structures/contenu_entete', $data)
            . view('structures/souscontenu_entete', $data)
            . view('changerMotDePasse')
            . view('structures/page_pied');
    }

    /**
     * Fonction permettant de valider le changement de mot de passe , si tout est correct , le mot de passe est modifier en base de donnée
     *
     * @return void
     */
    public function validerChangerMdp()
    {
        if (!session()->get('isLoggedIn')) {
            return redirect()->to('/');
        }

        // Récupération des mots de passe
        $mdp = $this->request->getPost('pwdMdp');
        $mdpConfirm = $this->request->getPost('pwdMdpConfirm');

       // Vérification du mot de passe
       // 12 caractères au moins
        if (strlen($mdp) < 12) {
            return redirect()->to('/changerMdp')
                ->with('erreurs', 'Le mot de passe doit contenir au moins 12 caractères');
        }

        // 1 majuscule au moins
        if (!preg_match('/[A-Z]/', $mdp)) {
            return redirect()->to('/changerMdp')
                ->with('erreurs', 'Le mot de passe doit contenir au moins une majuscule');
        }

        // 1 minuscule au moins
        if (!preg_match('/[a-z]/', $mdp)) {
            return redirect()->to('/changerMdp')
                ->with('erreurs', 'Le mot de passe doit contenir au moins une minuscule');
        }

        // 1 chiffre au moins
        if (!preg_match('/[0-9]/', $mdp)) {
            return redirect()->to('/changerMdp')
                ->with('erreurs', 'Le mot de passe doit contenir au moins un chiffre');
        }

        // 1 caractère special au moins
        if (!preg_match('/[\W_]/', $mdp)) {
            return redirect()->to('/changerMdp')
                ->with('erreurs', 'Le mot de passe doit contenir au moins un caractère spécial');
        }

        $idUtilisateur = session()->get('idUtilisateur');
        $this->gsb_model->updateMdpUtilisateur($idUtilisateur, $mdp);

        // Mise à jour de la date de modification dans la session (comme sa affichage de nouveau du sommaire au complet)
        session()->set('date_modif_mdp', date('Y-m-d H:i:s'));

        // On retourne sur la meme page
        return redirect()->to('/changerMdp') ->with('infos', 'Le mot de passe a été modifié avec succès');
    }
}