<?php

namespace App\Controllers;

/**
* Le controlleur accueil permettant d'avoir le flux rss 
*/
class Accueil extends BaseController
{
    /**
     * Constructeur du controlleur Accueil
     */
    public function __construct()
    {
        // On charge le helper URL et HTML
        helper(['url', 'html']);
    }

    /**
     * Méthode par défaut de la page d'accueil , affiche les views nécessaires pour que la page fonctionne correctement
     *
     * @return void affiche la page
     */
    public function index()
    {
        // Vérifie si l’utilisateur est connecté
        if (!session()->get('isLoggedIn')) {
            return redirect()->to('/');
        }

        $data['titre'] = "Bienvenue sur l'intranet GSB";
        $data['actualites'] = $this->getRssFlux(); // récup le flux

        return view('structures/page_entete')
            . view('structures/messages')
            . view('sommaire')
            . view('structures/contenu_entete', $data)
            . view('actualites', $data)
            . view('structures/page_pied');
    }
    /**
     * Récupérer le flux RSS pour afficher sur la page d'accueil
     *
     * @return void
     */
    private function getRssFlux()
    {
        $url = 'https://www.santemagazine.fr/feeds/rss/sante';

        // Code Mme Piton pour débloquer le codage SSL
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        $data = curl_exec($ch);
        curl_close($ch);

        // on parse le XML
        $rss = simplexml_load_string($data);

        // préparation du tableau d'articles
        $articles = [];
        foreach ($rss->channel->item as $item) {
            $articles[] = [
                'titre' => (string) $item->title,
                'description' => strip_tags((string) $item->description),
                'lien' => (string) $item->link,
                'date' => date("d/m/Y H:i", strtotime((string) $item->pubDate)),
                'image' => isset($item->enclosure['url']) ? (string) $item->enclosure['url'] : null
            ];
        }

        return $articles;
    }
}