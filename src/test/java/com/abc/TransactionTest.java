package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction transaction = new Transaction(5);
        assertTrue(transaction instanceof Transaction);
    }
}
