package br.edu.ifsp.arq.dmos5_2020s1.calculoimc.model;

import android.os.Parcelable;

import java.io.Serializable;

import br.edu.ifsp.arq.dmos5_2020s1.calculoimc.R;

public class Pessoa implements Serializable {
    public static final int ABAIXO = R.string.abaixo_do_peso;
    public static final int NORMAL = R.string.peso_normal;
    public static final int SOBREPESO = R.string.sobrepeso;
    public static final int GRAU_1 = R.string.obesidade_grau_1;
    public static final int GRAU_2 = R.string.obesidade_grau_2;
    public static final int GRAU_3 = R.string.obesidade_grau_3;

    public static final double IMC_REFERENCIAL_MINIMO = 18.5;
    public static final double IMC_REFERENCIAL_MAXIMO = 25;

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

    public int resultado(){
        double imc = imc();
        if(imc < 18.5){
            return ABAIXO;
        }else{
            if (imc < 24.9){
                return NORMAL;
            }else{
                if(imc < 29.9){
                    return SOBREPESO;
                }else{
                    if(imc < 34.9){
                        return GRAU_1;
                    }else{
                        if(imc < 39.9){
                            return GRAU_2;
                        }else{
                            return GRAU_3;
                        }
                    }
                }
            }
        }
    }

    public int explicativo(){
        double imc = imc();
        if(imc < 18.5){
            return R.string.resultado_abaixo_do_peso;
        }else{
            if (imc < 24.9){
                return R.string.resultado_peso_normal;
            }else{
                if(imc < 29.9){
                    return R.string.resultado_sobrepeso;
                }else{
                    if(imc < 34.9){
                        return R.string.resultado_obesidade_grau_1;
                    }else{
                        if(imc < 39.9){
                            return R.string.resultado_obesidade_grau_2;
                        }else{
                            return R.string.resultado_obesidade_grau_3;
                        }
                    }
                }
            }
        }
    }

    public double pesoMinimo(){
        return IMC_REFERENCIAL_MINIMO * Math.pow(altura, 2);
    }

    public double pesoMaximo(){
        return IMC_REFERENCIAL_MAXIMO * Math.pow(altura, 2);
    }
}
