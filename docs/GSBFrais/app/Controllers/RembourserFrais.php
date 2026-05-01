<?php

namespace App\Controllers;

use App\Models\GsbModel;
use App\Libraries\Gsb_lib;

/**
* Le controlleur RembourserFrais permettant rembourser les fiches de frais des visiteurs de la base de donnée pour modifier les frais de chacun des visiteurs (comptable)
*/
class RembourserFrais extends BaseController
{
    private $id_fiche;

    protected $gsb_model;
    protected $gsb_lib;
    /**
     * Constructeur du controlleur RembourserFrais
     */
    public function __construct()
    {
        helper(['url', 'form']);

        $this->gsb_model = new GsbModel();
        $this->gsb_lib   = new Gsb_lib();
    }
    /**
     * Verifie si l'utilisateur est connecter
     *
     * @return void
     */
    public function index()
    {
        if (!session()->get('isLoggedIn')) {
            return redirect()->to('/');
        }

        $this->id_fiche = null;
        return $this->commun();
    }
    /**
     * Permet de choisir la fiche de frais 
     *
     * @return void
     */
    public function selectionner()
    {
        $this->id_fiche = $this->request->getPost('lstFiche');
        return $this->commun();
    }

    /**
     * Permet de rembourser la fiche de frais choisir par l'utilisateur
     *
     * @param [type] $idFiche id de la fiche choisit par l'utilisateur
     * @return void
     */
    public function rembourser($idFiche)
    {
        $this->gsb_model->maj_etat_fiche_frais($idFiche, 'RB');

        return redirect()->to('/rembourserfrais')
            ->with('message', 'La fiche a été remboursée');
    }
    /**
     * affiche la page avec ses views correspondant afin de la faire fonctionner correctement 
     *
     * @return void
     */
    private function commun()
    {
        echo view('structures/page_entete');
        echo view('structures/messages');
        echo view('sommaire');

        echo view('structures/contenu_entete', [
            'titre' => 'Suivi du remboursement des frais'
        ]);

        $fiches = $this->gsb_model->get_fiches_validees_comptable();

        if (count($fiches) === 0) {
            return redirect()->back()->with('erreurs', 'Aucune fiche validée à rembourser');
        }

        if (!isset($this->id_fiche)) {
            $this->id_fiche = $fiches[0]['idFiche'];
        }

        $options = [];
        foreach ($fiches as $f) {
            $libelleMois = $this->gsb_lib->get_nom_mois($f['mois']);
            $options[$f['idFiche']] = $f['nom'] . ' ' . $f['prenom'] . ' - ' . $libelleMois . ' ' . $f['annee'];
        }

        $data = [
            'lst_contenu' => $options,
            'lst_select'  => $this->id_fiche,
            'lst_action'  => 'rembourserfrais/selectionner',
            'lst_id'      => 'lstFiche',
            'lst_label'   => 'Fiches',
            'sc_titre'    => 'Fiches à sélectionner :'
        ];

        echo view('structures/souscontenu_entete', $data);
        echo view('liste_deroulante', $data);
        echo view('structures/souscontenu_pied');

        $fiche = $this->gsb_model->get_les_infos_ficheFrais($this->id_fiche);
        $fiche['dateModifFr']    = $this->gsb_lib->date_vers_francais($fiche['dateModif']);
        $fiche['montantFormate'] = $this->gsb_lib->format_montant($fiche['montantValide']);

        echo view('structures/souscontenu_entete', [
            'sc_titre' => 'Fiche de frais'
        ]);

        echo view('etat_fiche', [
            'fiche'   => $fiche,
            'idFiche' => $this->id_fiche
        ]);

        echo view('fraisforfait_table', [
            'fraisforfait' => $this->gsb_model->get_les_frais_forfait($this->id_fiche)
        ]);

        $fraisHF = $this->gsb_model->get_les_frais_hors_forfait($this->id_fiche);
        foreach ($fraisHF as &$f) {
            $f['dateFraisFR']    = $this->gsb_lib->date_vers_francais($f['dateFrais']);
            $f['montantFormate'] = $this->gsb_lib->format_montant($f['montant']);
        }

        echo view('fraishorsforfait_table', [
            'fraishorsforfait' => $fraisHF
        ]);

        echo anchor(
            'rembourserfrais/rembourser/' . $this->id_fiche,
            'Rembourser la fiche',
            ['class' => 'btn-rembourser']
        );

        echo view('structures/souscontenu_pied');
        echo view('structures/page_pied');
    }
}
