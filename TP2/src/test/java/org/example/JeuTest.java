package org.example;
import org.junit.Test;
import static org.mockito.Mockito.*;

    public class JeuTest {

        @Test(expected = JeuFermeException.class)
        public void testJeuFerme() throws JeuFermeException, DebitImpossibleException {

            Banque banque = mock(Banque.class);

                Jeu jeu = new Jeu(banque);
            jeu.fermer(); // Fermeture du jeu

             Joueur joueur = mock(Joueur.class);
            when(joueur.mise()).thenReturn(10); // La mise n'a pas d'importance ici

             jeu.jouer(joueur, null, null);
        }
        @Test(expected = DebitImpossibleException.class)
        public void testJoueurInsolvable() throws JeuFermeException, DebitImpossibleException {
            // Création d'un mock pour la banque
            Banque banque = mock(Banque.class);
            when(banque.est_solvable()).thenReturn(true); // La banque est solvable

              Jeu jeu = new Jeu(banque);

               Joueur joueur = mock(Joueur.class);
            when(joueur.mise()).thenReturn(100); // Le joueur mise plus qu'il ne possède

            jeu.jouer(joueur, null, null);
        }
        @Test
        public void testSommeDesDesPasEgaleA7() throws JeuFermeException, DebitImpossibleException {
            // Création d'un mock pour la banque
            Banque banque = mock(Banque.class);
            when(banque.est_solvable()).thenReturn(true); // La banque est solvable

            Jeu jeu = new Jeu(banque);

            Joueur joueur = mock(Joueur.class);
            when(joueur.mise()).thenReturn(50); // Mise du joueur

            De de1 = mock(De.class);
            De de2 = mock(De.class);

            when(de1.lancer()).thenReturn(3);
            when(de2.lancer()).thenReturn(4);

            // Appel de la méthode jouer
            jeu.jouer(joueur, de1, de2);

            // Vérification que la banque n'est pas créditée
            verify(banque, never()).crediter(anyInt());
        }
        @Test
        public void testSommeDesDesEgaleA7_BanqueSolvable() throws JeuFermeException, DebitImpossibleException {
             Banque banque = mock(Banque.class);
            when(banque.est_solvable()).thenReturn(true); // La banque est solvable

              Jeu jeu = new Jeu(banque);
   Joueur joueur = mock(Joueur.class);
            when(joueur.mise()).thenReturn(50); // Mise du joueur

              De de1 = mock(De.class);
            De de2 = mock(De.class);

                when(de1.lancer()).thenReturn(3);
            when(de2.lancer()).thenReturn(4);

              jeu.jouer(joueur, de1, de2);
              verify(banque).crediter(100);
        }
    @Test(expected = JeuFermeException.class)
        public void testSommeDesDesEgaleA7_BanqueNonSolvable() throws JeuFermeException, DebitImpossibleException {
                     Banque banque = mock(Banque.class);
            when(banque.est_solvable()).thenReturn(false); // La banque n'est pas solvable

        Jeu jeu = new Jeu(banque);

            Joueur joueur = mock(Joueur.class);
            when(joueur.mise()).thenReturn(50); // Mise du joueur

            De de1 = mock(De.class);
            De de2 = mock(De.class);

            when(de1.lancer()).thenReturn(3);
            when(de2.lancer()).thenReturn(4);


            jeu.jouer(joueur, de1, de2);
        }
    }


