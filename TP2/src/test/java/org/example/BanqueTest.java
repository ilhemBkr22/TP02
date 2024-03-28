package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.*;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BanqueTest {

    @Test
    public void testBanqueNonSolvable() {
        Banque banque = new BanqueImpl(1000, 500);


        assertTrue(banque.est_solvable());

        banque.debiter(600);

        assertFalse(banque.est_solvable());
    }
}
