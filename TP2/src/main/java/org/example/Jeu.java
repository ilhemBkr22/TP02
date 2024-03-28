package org.example;

public class Jeu {
    private Banque banque;
    private boolean ouvert;

    public Jeu(Banque labanque) {
        this.banque = labanque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        if (!banque.est_solvable()) {
            fermer();
            throw new JeuFermeException("La banque est insolvable.");
        }

        int mise = joueur.mise();
        try {
            joueur.debiter(mise);
            int sommeDes = de1.lancer() + de2.lancer();
            if (sommeDes == 7) {
                joueur.crediter(mise * 2);
                banque.crediter(mise * 2);
                if (!banque.est_solvable()) {
                    fermer();
                    throw new JeuFermeException("La banque est insolvable après paiement du gain.");
                }
            } else {
                fermer();
            }
        } catch (DebitImpossibleException e) {
            fermer();
            throw new JeuFermeException("Le joueur est insolvable.");
        }
    }

    public void fermer() {
        this.ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}

