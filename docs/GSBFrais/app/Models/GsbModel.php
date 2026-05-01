<?php

namespace App\Models;

use CodeIgniter\Model;
use App\Libraries\Gsb_lib;

class GsbModel extends Model
{
    /**
     * Retourne les informations d'un utilisateur
     *
     * @param [type] $login le login écrit par l'utilisateur
     * @param [type] $mdp le mot de passe écrit par l'utilisateur
     * @return void retourne les infos de l'utilisateur selon la vue utilisateur si le login et le mot de passe sont correcte
     */
    public function get_infos_utilisateur($login, $mdp)
    {
        // return $this->db->table('utilisateur')
        //     ->select('idUtilisateur, nom, prenom, login, utilisateur.idRole, role.libelleRole')
        //     ->join('role', 'role.idRole = utilisateur.idRole')
        //     ->where('login', $login)
        //     ->where('mdp', $mdp)
        //     ->get()
        //     ->getRowArray();

        return $this->db->table('infos_utilisateurs')
            ->where('login', $login)
            ->where('mdp', $mdp)
            ->get()
            ->getRowArray();
    }

    /**
     * Retourne les détails d'un utilisateur
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur actuellement connecter
     * @return void retourne les information de l'utilisateur selon l'id provenant de la vue info utilisateur
     */
    public function get_detail_visiteur($idUtilisateur)
    {
        return $this->db->table('utilisateur')
            ->where('idUtilisateur', $idUtilisateur)
            ->get()
            ->getRowArray();
    }

    /**
     * Mois disponibles pour un utilisateur
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur actuellement connecter
     * @return void retourne les mois disponibles pour l'utilisateur connecter
     */
    public function get_les_mois_disponibles($idUtilisateur)
    {
        return $this->db->table('fichefrais')
            ->select('CONCAT(annee,mois) AS "anneemois", annee, mois')
            ->where('idUtilisateur', $idUtilisateur)
            ->orderBy('annee', 'DESC')
            ->orderBy('mois', 'DESC')
            ->get()
            ->getResultArray();
    }

    /**
     * Id fiche de frais pour une année et un mois
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur connecter
     * @param [type] $annee l'année de la fiche choisit
     * @param [type] $mois le mois de la fiche choisit
     * @return void retourne la fiche frais selon l'utilisateur , l'année et le mois
     */
    public function get_id_ficheFrais($idUtilisateur, $annee, $mois)
    {
        return $this->db->table('fichefrais')
            ->select('fichefrais.idFiche, fichefrais.idEtat')
            ->where('fichefrais.idUtilisateur', $idUtilisateur)
            ->where('fichefrais.annee', $annee)
            ->where('fichefrais.mois', $mois)
            ->get()
            ->getRowArray();
    }
    /**
     * Infos fiche de frais pour un mois
     *
     * @param [type] $idFiche id de la fiche de frais
     * @return void retourne les informations de la fiche de frais choisit
     */
    public function get_les_infos_ficheFrais($idFiche)
    {
        return $this->db->table('fichefrais')
            ->select('fichefrais.idFiche, fichefrais.idEtat, fichefrais.dateModif, fichefrais.nbJustificatifs, fichefrais.montantValide, etatfrais.libelle')
            ->join('etatfrais', 'fichefrais.idEtat = etatfrais.idetat')
            ->where('fichefrais.idFiche', $idFiche)
            ->get()
            ->getRowArray();
    }

    /**
     * Frais forfait pour un mois
     *
     * @param [type] $idFiche id de la fiche de frais
     * @return void retourne les frais catégoriser en forfaitaire
     */
    public function get_les_frais_forfait($idFiche)
    {
        return $this->db->table('lignefraisforfait')
            ->select('fraisforfait.idfraisforfait, fraisforfait.libelle, lignefraisforfait.quantite')
            ->join('fraisforfait', 'fraisforfait.idfraisforfait = lignefraisforfait.idFraisForfait')
            ->where('lignefraisforfait.idFiche', $idFiche)
            ->orderBy('lignefraisforfait.idFraisForfait', 'ASC')
            ->get()
            ->getResultArray();
    }

    /**
     * Frais hors forfait pour un mois
     *
     * @param [type] $idFiche id de la fiche de frais
     * @return void retourne les frais catégoriser en hors forfaitaire
     */
    public function get_les_frais_hors_forfait($idFiche)
    {
        return $this->db->table('lignefraishorsforfait')
            ->where('idFiche', $idFiche)
            ->get()
            ->getResultArray();
    }

    /**
     * Vérifie si premier frais du mois
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur connecter
     * @param [type] $annee l'année de la fiche choisit
     * @param [type] $mois le mois de la fiche choisit
     * @return bool vrai ou faux si c'est le premier frais du mois
     */
    public function est_premier_frais_mois($idUtilisateur, $annee, $mois)
    {
        $row = $this->db->table('fichefrais')
            ->select('count(*) AS nblignesfrais')
            ->where('idUtilisateur', $idUtilisateur)
            ->where('annee', $annee)
            ->where('mois', $mois)
            ->get()
            ->getRowArray();
        return $row['nblignesfrais'] === "0";
    }

    /**
     * Dernier mois saisi associer a un utilisateur précis
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur connecter
     * @return void récupère le dernier mois où cet utilisateur a saisie un frais
     */
    public function dernier_mois_saisi($idUtilisateur)
    {
        $row = $this->db->table('fichefrais')
            ->select('max(CONCAT(annee,mois)) AS dernierAnneeMois')
            ->where('idUtilisateur', $idUtilisateur)
            ->get()
            ->getRowArray();
        return $row['dernierAnneeMois'];
    }

    /**
     * Tous les id de frais forfait
     *
     * @return void récupére tout les id frais forfaitaires de la base de données
     */
    public function get_les_id_frais_forfait()
    {
        return $this->db->table('fraisforfait')
            ->select('idfraisforfait')
            ->orderBy('idfraisforfait')
            ->get()
            ->getResultArray();
    }

    /**
     * Crée nouvelles lignes de frais
     *
     * @param [type] $idUtilisateur l'id de l'utilisateur connecter
     * @param [type] $annee l'année de la fiche choisit
     * @param [type] $mois le mois de la fiche choisit
     * @return void créer des nouvelles lignes de frais pour la fiche de fraus cet utilisateur
     */
    public function cree_nouvelles_lignes_frais($idUtilisateur, $annee, $mois)
    {
        $dernierMois = $this->dernier_mois_saisi($idUtilisateur);
        $gsb_lib = new Gsb_lib();
        $num_annee = $gsb_lib->get_annee_from_anneemois($dernierMois);
        $num_mois = $gsb_lib->get_mois_from_anneemois($dernierMois);

        $laDerniereFiche = $this->get_id_ficheFrais($idUtilisateur, $num_annee, $num_mois);

        if ($laDerniereFiche != null && $laDerniereFiche['idEtat'] == 'CR') {
            $idDerniereFiche = $laDerniereFiche['idFiche'];
            $this->maj_etat_fiche_frais($idDerniereFiche, 'CL');
        }

        $resultat = $this->db->table('fichefrais')->insert([
            'idUtilisateur' => $idUtilisateur,
            'annee' => $annee,
            'mois' => $mois,
            'nbJustificatifs' => 0,
            'montantValide' => 0,
            'dateModif' => date('Y-m-d'),
            'idEtat' => 'CR'
        ]);

        $insertionsOK = true;
        if ($resultat) {
            // On récupère l'id de la fiche qui vient d'être créée
            $nouvelleFiche = $this->get_id_ficheFrais($idUtilisateur, $annee, $mois);
            $idNouvelleFiche = $nouvelleFiche['idFiche'];
            foreach ($this->get_les_id_frais_forfait() as $unIdFraisForfait) {
                $res = $this->db->table('lignefraisforfait')->insert([
                    'idFiche' => $idNouvelleFiche,
                    'idFraisForfait' => $unIdFraisForfait['idfraisforfait'],
                    'quantite' => 0
                ]);
                if (!$res) {
                    $insertionsOK = false;
                    break;
                }
            }
        } else {
            return false;
        }
        return $insertionsOK;
    }

    /**
     * Met à jour l'état d'une fiche
     *
     * @param [type] $idFiche l'id de la fiche pour la retrouver
     * @param [type] $etat nouvelle etat de la fiche
     * @return void change l'etat de la fiche
     */
    public function maj_etat_fiche_frais($idFiche, $etat)
    {
        // $this->db->table('fichefrais')->update(
        //     ['idEtat' => $etat, 'dateModif' => date('Y-m-d')],
        //     ['idFiche' => $idFiche]
        // );

        $sql = "CALL maj_etat_fiche_frais_ps(?,?)";
		
		return $this->db->query($sql, [$idFiche,$etat]);
    }

    /**
     * Met à jour les frais forfait
     *
     * @param [type] $idFiche id de la fiche frais
     * @param array $lesFrais
     * @return void le fait que la fiche a bien été changer
     */
    public function maj_frais_forfait($idFiche, array $lesFrais)
    {
        $majOK = true;
        foreach (array_keys($lesFrais) as $idFrais) {
            $res = $this->db->table('lignefraisforfait')->update(
                ['quantite' => $lesFrais[$idFrais]],
                ['idFiche' => $idFiche, 'idFraisForfait' => $idFrais]
            );
            if (!$res) {
                $majOK = false;
                break;
            }
        }
        return $majOK;
    }
    /**
     * Supprime un frais hors forfait
     *
     * @param [type] $idFrais id de la fiche frais
     * @return void le fait qu'il a bien supprimer la fiche de l'id
     */
    public function supprimer_frais_hors_forfait($idFrais)
    {
        // return $this->db->table('lignefraishorsforfait')->delete(['idLigneFHF' => $idFrais]);

        $sql = "CALL supprimer_frais_hors_forfait_ps(?)";
		
		return $this->db->query($sql, [$idFrais]);
    }

    /**
     * Crée un nouveau frais hors forfait
     *
     * @param [type] $idFiche id de la fiche
     * @param [type] $libelle le titre de frais hors forfait
     * @param [type] $date la date du jour actuelle
     * @param [type] $montant le montant du frais hors forfait
     * @return void le fait qu'il a bien créer le frais hors forfait
     */
    public function creer_nouveau_frais_hors_forfait($idFiche, $libelle, $date, $montant)
    {
        $resultat = $this->db->table('lignefraishorsforfait')->insert([
            'idFiche' => $idFiche,
            'libelle' => $libelle,
            'dateFrais' => $date,
            'montant' => $montant
        ]);
        return $resultat;
    }
    /**
     * Récupérer les fiches valider par le comptable
     *
     * @return void les fiches validée
     */
    public function get_fiches_validees_comptable()
    {
        return $this->db->table('fichefrais f')
            ->select('
                f.idFiche,
                f.annee,
                f.mois,
                u.nom,
                u.prenom
            ')
            ->join('utilisateur u', 'u.idUtilisateur = f.idUtilisateur')
            ->where('f.idEtat', 'VA')
            ->orderBy('u.nom')
            ->orderBy('f.annee', 'DESC')
            ->orderBy('f.mois', 'DESC')
            ->get()
            ->getResultArray();
    }
    /**
     * Met a jour le mot de passe de l'utilisateur
     *
     * @param [type] $idUtilisateur id de l'utilisateur connecter
     * @param [type] $mdp le nouveau mot de passe entrée par l'utilisateur connecter
     * @return void
     */
    public function updateMdpUtilisateur($idUtilisateur, $mdp)
    {
        return $this->db->table('utilisateur')->update(
                ['mdp' => $mdp, 'date_modif_mdp' => date('Y-m-d H:i:s')] , ['idUtilisateur' => $idUtilisateur]
            );
    }

    /**
     * Fiches en état 'CL' (clôturées, en attente de validation comptable)
     */
    public function get_fiches_en_attente_validation()
    {
        return $this->db->table('fichefrais f')
            ->select('f.idFiche, f.annee, f.mois, u.nom, u.prenom')
            ->join('utilisateur u', 'u.idUtilisateur = f.idUtilisateur')
            ->where('f.idEtat', 'CL')
            ->orderBy('u.nom')
            ->orderBy('f.annee', 'DESC')
            ->orderBy('f.mois', 'DESC')
            ->get()
            ->getResultArray();
    }

    /**
     * Retourne un seul frais hors forfait par son id
     */
    public function get_un_frais_hors_forfait($idFraisHF)
    {
        return $this->db->table('lignefraishorsforfait')
            ->where('idLigneFHF', $idFraisHF)
            ->get()
            ->getRowArray();
    }

    /**
     * Met à jour le libellé d'un frais hors forfait (report/refus)
     */
    public function maj_libelle_frais_hors_forfait($idFraisHF, $nouveauLibelle)
    {
        return $this->db->table('lignefraishorsforfait')
            ->where('idLigneFHF', $idFraisHF)
            ->update(['libelle' => $nouveauLibelle]);
    }

    /**
     * Calcule et met à jour le montant validé d'une fiche
     */
    public function maj_montant_valide($idFiche)
    {
        $forfaits = $this->db->table('lignefraisforfait lff')
            ->select('SUM(lff.quantite * ff.montant) AS total')
            ->join('fraisforfait ff', 'ff.idfraisforfait = lff.idFraisForfait')
            ->where('lff.idFiche', $idFiche)
            ->get()
            ->getRowArray();

        $horsForfait = $this->db->table('lignefraishorsforfait')
            ->select('SUM(montant) AS total')
            ->where('idFiche', $idFiche)
            ->where('libelle NOT LIKE', 'REFUSE:%')
            ->where('libelle NOT LIKE', 'REPORT:%') // ← ajouté
            ->get()
            ->getRowArray();

        $montantTotal = ($forfaits['total'] ?? 0) + ($horsForfait['total'] ?? 0);

        return $this->db->table('fichefrais')
            ->where('idFiche', $idFiche)
            ->update(['montantValide' => $montantTotal]);
    }
}