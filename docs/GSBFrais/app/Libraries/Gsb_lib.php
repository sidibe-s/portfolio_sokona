<?php

namespace App\Libraries;

use DateTime;
use IntlDateFormatter;

class Gsb_lib
{
    /**
     * Constructeur de la librairie pour GsbFrais
     */
    public function __construct()
    {
        // session déjà disponible via service
        session(); 
        setlocale(LC_TIME, "fr_FR.utf8", "fra_fra");
    }

    /**
     * Transforme une date yyyy-mm-dd en jj/mm/yyyy
     *
     * @param string $maDate la date dans le format année - mois - jour (yyyy-mm-dd)
     * @return string la date donnée mais sous format jour/mois/année (jj/mm/yyyy)
     */
    public function date_vers_francais(string $maDate): string
    {
        [$annee, $mois, $jour] = explode('-', $maDate);
        return "$jour/$mois/$annee";
    }

    /**
     * Retourne le couple annee/mois aaaamm selon une date jj/mm/aaaa (TODO pour date passée)
     *
     * @param string|null $date la date au format jj/mm/aaaa
     * @return string l'année et le mois pour les fiches de frais
     */
    public function get_annee_mois(string $date = null): string
    {
        return date("Ym");
    }

    /**
     * Retourne l'année du couple annee/mois
     *
     * @param string $annee_mois au format aaaa/mm
     * @return string l'année au format aaaa
     */
    public function get_annee_from_anneemois(string $annee_mois): string
    {
        $num_annee = substr($annee_mois, 0, 4);
        return $num_annee;
    }
    /**
     * Retourne le mois du couple annee/mois
     *
     * @param string $annee_mois au format aaaa/mm
     * @return string le mois au format mm
     */
    public function get_mois_from_anneemois(string $annee_mois): string
    {
        $num_mois = substr($annee_mois, 4, strlen($annee_mois)-4);
        return $num_mois;
    }

    /**
     * Nom complet du mois en français
     *
     * @param integer $unNoMois le numéro du mois qu'on veut écrire en français 
     * @param string $locale reprend la langue voulu , ici français donc 'fr_FR'
     * @return string retourne en français le nom du mois en français en texte
     */
    public function get_nom_mois(int $unNoMois, string $locale = 'fr_FR'): string
    {
        $date = DateTime::createFromFormat('!m', $unNoMois);
        $formatter = new IntlDateFormatter(
            $locale,
            IntlDateFormatter::NONE,
            IntlDateFormatter::NONE,
            null,
            null,
            'MMMM'
        );
        return $formatter->format($date);
    }

    /**
     * Formate un montant avec deux décimales
     *
     * @param [type] $montant le montant dans le format chiffre
     * @return string retourne dans le format de texte le montant
     */
    public function format_montant($montant): string
    {
        return number_format($montant, 2, ',', ' ');
    }

}
