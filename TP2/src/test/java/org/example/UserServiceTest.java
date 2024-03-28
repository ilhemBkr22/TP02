package org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur(12,"ilhem", "Bekri", "ilhembkr@email.com");
        // Création du service avec le mock
        UserService userService = new UserService((UtilisateurApi) utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }

    @Test(expected = ServiceException.class)
    public void testCreerUtilisateur_Exception() throws ServiceException {
        // Création d'un utilisateur fictif pour le test
        Utilisateur utilisateur = new Utilisateur(12,"ilhem", "Bekri", "ilhembkr@email.com");

        // Configuration du comportement du mock pour lever une exception
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        // Création du service UserService avec le mock utilisateurApiMock
        UserService userService = new UserService((UtilisateurApi) utilisateurApiMock);

        // Appel de la méthode à tester creerUtilisateur du service UserService
        userService.creerUtilisateur(utilisateur);
    }

    @Test
    public void testCreerUtilisateur_Validation() throws ServiceException {
        // Création d'un utilisateur fictif pour le test
        Utilisateur utilisateur = new Utilisateur(12,"ilhem", "Bekri", "ilhembkr@email.com");

        // Configuration du comportement du mock pour retourner false (erreur de validation)
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(false);

        // Création du service UserService avec le mock utilisateurApiMock
        UserService userService = new UserService((UtilisateurApi) utilisateurApiMock);

        // Appel de la méthode à tester creerUtilisateur du service UserService
        userService.creerUtilisateur(utilisateur);

        // Vérification que la méthode creerUtilisateur a été appelée exactement une fois
        verify(utilisateurApiMock, times(1)).creerUtilisateur(any(Utilisateur.class));
        // Vérification qu'aucune autre méthode n'a été appelée sur le mock utilisateurApiMock
        verifyNoMoreInteractions(utilisateurApiMock);
    }



    @Test
    public void testCreerUtilisateur_VerificationID() throws ServiceException {
        // Définition d'un ID fictif
        int idUtilisateur = 123;

        // Création d'un utilisateur fictif pour le test
        Utilisateur utilisateur = new Utilisateur(12,"ilhem", "Bekri", "ilhembkr@email.com");

        // Configuration du mock pour renvoyer true
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);
        // Configuration du mock pour renvoyer l'ID fictif lors de l'appel à getId()
        when(utilisateur.getId()).thenReturn(idUtilisateur);

        // Création du service UserService avec le mock utilisateurApiMock
        UserService userService = new UserService((UtilisateurApi) utilisateurApiMock);

        // Appel de la méthode à tester creerUtilisateur du service UserService
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'ID de l'utilisateur
        assertEquals(idUtilisateur, utilisateur.getId());
    }

}