package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {

    @Spy
    private Calculatrice calculatrice = new Calculatrice();

    @Test
    public void testAdditionner() {
              int result = calculatrice.addition(2, 3);

        assert result == 5;

        verify(calculatrice).addition(2, 3);

        verifyNoMoreInteractions(calculatrice);

        // Vérification de l'état de l'objet après l'appel de la méthode "addition"
        verify(calculatrice).getState();
    }
}
