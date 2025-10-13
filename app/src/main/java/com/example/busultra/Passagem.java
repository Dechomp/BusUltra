package com.example.busultra;

public class Passagem {
    private String origem;
    private String destino;
    private double preco;
    private String horarioPartida;

    private String horarioChegada;

    public Passagem(String origem, String destino, double preco, String horarioPartida, String horarioChegada) {
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
    }
}
