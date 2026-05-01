<?php

namespace App\Controllers;

use App\Models\GsbModel;
use App\Libraries\Gsb_lib;

/**
 * Controlleur SuiviFicheFrais — permet au comptable de valider
 * les fiches de frais clôturées par les visiteurs.
 */
class SuiviFicheFrais extends BaseController
{
    private $id_fiche;

    protected $gsb_model;
    protected $gsb_lib;

    public function __construct()
    {
        helper(['url', 'form']);
        $this->gsb_model = new GsbModel();
        $this->gsb_lib   = new Gsb_lib();
    }

    public function index()
    {
        if (!session()->get('isLoggedIn')) {
            return redirect()->to('/');
        }
        $this->id_fiche = null;
        return $this->commun();
    }

    public function selectionner()
    {
        $this->id_fiche = $this->request->getPost('lstFiche');
        return $this->commun();
    }

    public function valider($idFiche)
    {
        $this->gsb_model->maj_etat_fiche_frais($idFiche, 'VA');
        return redirect()->to('/suiviFicheFrais')
            ->with('message', 'La fiche a été validée');
    }

    public function valider_maj_fraisforfait()
    {
        $this->id_fiche = $this->request->getPost('idFiche');
        $lesFrais       = $this->request->getPost('lesFrais');

        $resultatOk = $this->gsb_model->maj_frais_forfait($this->id_fiche, $lesFrais);
        
        // ← Recalcule le montant après modification des forfaits
        $this->gsb_model->maj_montant_valide($this->id_fiche);
        
        if ($resultatOk) {
            session()->setFlashdata('message', 'Les frais forfaitaires ont bien été modifiés');
        } else {
            session()->setFlashdata('erreurs', 'Problème lors de la modification des frais forfaitaires');
        }
        return $this->commun();
    }

    public function refuser_fraishorsforfait($idFraisHF, $idFiche)
    {
        $this->id_fiche = $idFiche;
        $frais   = $this->gsb_model->get_un_frais_hors_forfait($idFraisHF);
        $libelle = $frais['libelle'];

        $libelleNet = $libelle;
        if (str_starts_with($libelle, 'REFUSE:')) {
            $libelleNet = substr($libelle, strlen('REFUSE:'));
        }
        if (str_starts_with($libelleNet, 'REPORT:')) {
            $libelleNet = substr($libelleNet, strlen('REPORT:'));
        }

        $nouveauLibelle = 'REFUSE:' . $libelleNet;
        $this->gsb_model->maj_libelle_frais_hors_forfait($idFraisHF, $nouveauLibelle);

        // ← Recalcule le montant après refus
        $this->gsb_model->maj_montant_valide($idFiche);

        return $this->commun();
    }

    public function reporter_fraishorsforfait($idFraisHF, $idFiche)
    {
        $this->id_fiche = $idFiche;
        $frais   = $this->gsb_model->get_un_frais_hors_forfait($idFraisHF);
        $libelle = $frais['libelle'];

        if (str_starts_with($libelle, 'REFUSE:')) {
            return $this->commun();
        }

        $libelleNet = $libelle;
        if (str_starts_with($libelle, 'REPORT:')) {
            $libelleNet = substr($libelle, strlen('REPORT:'));
        }

        $nouveauLibelle = 'REPORT:' . $libelleNet;
        $this->gsb_model->maj_libelle_frais_hors_forfait($idFraisHF, $nouveauLibelle);

        // ← Recalcule le montant (les reportés sont quand même comptés)
        $this->gsb_model->maj_montant_valide($idFiche);

        return $this->commun();
    }

    private function commun()
    {
        echo view('structures/page_entete');
        echo view('structures/messages');
        echo view('sommaire');

        echo view('structures/contenu_entete', [
            'titre' => 'Suivi des fiches de frais'
        ]);

        $fiches = $this->gsb_model->get_fiches_en_attente_validation();

        if (count($fiches) === 0) {
            echo view('structures/souscontenu_entete', ['sc_titre' => '']);
            echo '<p>Aucune fiche en attente de validation.</p>';
            echo view('structures/souscontenu_pied');
            echo view('structures/page_pied');
            return;
        }

        if (!isset($this->id_fiche)) {
            $this->id_fiche = $fiches[0]['idFiche'];
        }

        // Liste déroulante
        $options = [];
        foreach ($fiches as $f) {
            $libelleMois            = $this->gsb_lib->get_nom_mois((int)$f['mois']);
            $options[$f['idFiche']] = $f['nom'] . ' ' . $f['prenom']
                . ' - ' . $libelleMois . ' ' . $f['annee'];
        }

        $data = [
            'lst_contenu' => $options,
            'lst_select'  => $this->id_fiche,
            'lst_action'  => 'suiviFicheFrais/selectionner',
            'lst_id'      => 'lstFiche',
            'lst_label'   => 'Fiches',
            'sc_titre'    => 'Fiches à sélectionner :'
        ];

        echo view('structures/souscontenu_entete', $data);
        echo view('liste_deroulante', $data);
        echo view('structures/souscontenu_pied');

        // Retrouver la fiche sélectionnée dans le tableau pour avoir annee/mois
        $ficheSelectionnee = null;
        foreach ($fiches as $f) {
            if ($f['idFiche'] == $this->id_fiche) {
                $ficheSelectionnee = $f;
                break;
            }
        }

        $nomMois = $this->gsb_lib->get_nom_mois((int)$ficheSelectionnee['mois']);

        echo view('structures/souscontenu_entete', [
            'sc_titre' => 'Fiche de frais du mois de ' . $nomMois . ' ' . $ficheSelectionnee['annee']
        ]);

        $fiche                   = $this->gsb_model->get_les_infos_ficheFrais($this->id_fiche);
        $fiche['dateModifFr']    = $this->gsb_lib->date_vers_francais($fiche['dateModif']);
        $fiche['montantFormate'] = $this->gsb_lib->format_montant($fiche['montantValide']);

        echo view('etat_fiche', [
            'fiche'   => $fiche,
            'idFiche' => $this->id_fiche
        ]);

        echo view('fraisforfait_edit_suivi', [
            'fraisforfait' => $this->gsb_model->get_les_frais_forfait($this->id_fiche),
            'idFiche'      => $this->id_fiche
        ]);

        // Frais hors forfait
        $fraisHF = $this->gsb_model->get_les_frais_hors_forfait($this->id_fiche);
        foreach ($fraisHF as &$f) {
            $f['dateFraisFR']    = $this->gsb_lib->date_vers_francais($f['dateFrais']);
            $f['montantFormate'] = $this->gsb_lib->format_montant($f['montant']);
        }
        unset($f);

        echo view('fraishorsforfait_table_suivi', [
            'fraishorsforfait' => $fraisHF,
            'idFiche'          => $this->id_fiche
        ]);

        echo anchor(
            'suiviFicheFrais/valider/' . $this->id_fiche,
            'Valider la fiche',
            ['class' => 'btn-valider']
        );

        echo view('structures/souscontenu_pied');
        echo view('structures/page_pied');
    }
}