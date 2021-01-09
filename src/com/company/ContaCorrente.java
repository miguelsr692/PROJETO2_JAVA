//package com.company;

public class ContaCorrente extends ContaBancaria { // Conta comum

    //METODO CONSTRUTOR
    public ContaCorrente (float saldo, Cliente dono) {
        super(saldo, dono);
    }

    public void rendeConta (int diasPassados) {}

    @Override
    public String toString () {
        String out = "CONTA CORRENTE \n";
        out += super.toString();
        return out;
    }
}
