package com.blackjack.blackjack;


public class Jugador {
    private int aleatorio;

    public Jugador(int aleatorio) {
        this.aleatorio = aleatorio;
    }

    public int getAleatorio() {
        aleatorio = (int) (Math.random() * 10+1);
        return aleatorio;
    }
}
