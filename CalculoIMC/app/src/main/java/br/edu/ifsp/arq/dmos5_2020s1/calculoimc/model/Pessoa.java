package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model;

public class Pessoa {
    private double peso;
    private double altura;

    public Pessoa(double peso, double altura) {
        setPeso(peso);
        setAltura(altura);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso < 0? peso * -1 : peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura < 0 ? altura * -1 : altura;
    }

    public double imc(){
        double imc;
        imc = peso / Math.pow(altura, 2);
        return imc;
    }
}
