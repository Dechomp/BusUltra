package com.example.busultra;

public class Passagem {
    private String origem;
    private String destino;
    private double preco;
    private String horarioPartida;

    private String horarioChegada;
    private int tempoEstimado;

    public Passagem() {

    }

    public Passagem(String origem, String destino, double preco, String horarioPartida, String horarioChegada, int tempoEstimado) {
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
        this.tempoEstimado = tempoEstimado;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(String horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public String getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(String horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(int tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
