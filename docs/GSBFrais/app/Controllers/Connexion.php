<?php
namespace App\Controllers;

use App\Models\GsbModel;

/**
* Le controlleur Connexion permettant de se connecter au site web
*/
class Connexion extends BaseController
{
    protected $gsb_model;

    /**
     * Constructeur du controlleur Connexion
     */
    public function __construct()
    {
        helper(['url', 'form']); // helpers URL et form

        $this->gsb_model = new GsbModel();
    }

    /**
     * Affiche l’écran de connexion
     *
     * @return void 
     */
    public function login()
    {
        return view('structures/page_entete')
            . view('structures/messages')
            . view('connexion')
            . view('structures/page_pied');
    }

    /**
     * Valide la saisie du formulaire de connexion
     *
     * @return void si l'utilisateur est valide , sa va le connecter a la page 
     */
    public function valider()
    {
        $reglesSaisie = [
            'txtLogin' => [
                'rules' => 'required|min_length[3]',
                'label' => 'Login'
            ],
            'pwdMdp' => [
                'rules' => 'required|min_length[3]',
                'label' => 'Mot de passe'
            ]
        ];

        if (!$this->validate($reglesSaisie)) {
            // Redirection avec input et validation
            return redirect()->back()->withInput()->with('validation', $this->validator);
        }

        $login = $this->request->getPost('txtLogin');
        $mdp = $this->request->getPost('pwdMdp');

        $utilisateur = $this->gsb_model->get_infos_utilisateur($login, $mdp);

        if ($utilisateur) {
            session()->set([
                'idUtilisateur' => $utilisateur['idUtilisateur'],
                'nom' => $utilisateur['nom'],
                'prenom' => $utilisateur['prenom'],
                'idRole' => $utilisateur['idRole'],
                'libelleRole' => $utilisateur['libelleRole'],
                'date_modif_mdp' => $utilisateur['date_modif_mdp'],
                'isLoggedIn' => true
            ]);
            return redirect()->to('/accueil');
        }

        return redirect()->back()->withInput()->with('erreurs', 'Login ou mot de passe incorrect');
    }

    /**
     * Déconnecte l’utilisateur
     *
     * @return void
     */
    public function deconnexion()
    {
        session()->remove(['idUtilisateur', 'nom', 'prenom', 'isLoggedIn']);
        return redirect()->to('/')->with('infos', 'Vous avez bien été déconnecté.');
    }

}
