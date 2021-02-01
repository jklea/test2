package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    
    @Test
    void testSetNumeroValido() {
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }
    
    
    void testNumeroInvalidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }
    
    @Test
    void testInstanciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }
    
    @Test
    void testSetLimiteContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetLimiteContNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    }
    
    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }
    
    @Test
    void testGetSaldoTotal(){
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testDepositoDinheiro(){
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }
    
    @Test
    void testMovimentacaoTipoCredido(){
        final double limite = 500, deposito = 500;
        final char esperado = 'C';
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final int qntMovimentacoes = instance.getMovimentacoes().size();
        final char obtido = instance.getMovimentacoes().get(qntMovimentacoes-1).getTipo();
        assertEquals(esperado, obtido);
    }

    @Test
    void testMovimentacaoConfirmada(){
        final double limite = 500, deposito = 500;
        final boolean esperado = true;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final int qntMovimentacoes = instance.getMovimentacoes().size();
        final boolean obtido = instance.getMovimentacoes().get(qntMovimentacoes-1).isConfirmada();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testMovimentacaoValorAtribuido(){
        final double limite = 500, deposito = 500, esperado = 500;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        final int qntMovimentacoes = instance.getMovimentacoes().size();
        final double obtido = instance.getMovimentacoes().get(qntMovimentacoes-1).getValor();
        assertEquals(esperado, obtido);
    }
    
    /*@Test
    void testMovimentacaoNaoincluida(){
        final double limite = 500, deposito = 500;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        assertThrows(IllegalStateException.class, () -> instance.depositoDinheiro(deposito));
    }*/
    
    @Test
    void testDepositoInvalido(){
        final double limite = 500, deposito = -500;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        assertThrows(IllegalArgumentException.class, () -> instance.depositoDinheiro(deposito));
        
    }
    
}
