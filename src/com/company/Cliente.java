package com.company;

import java.text.DecimalFormat;

public class Cliente {
    // ATRIBUTOS
    private static int numClientes = 0; // Variavel estatica, pois o numero de clientes criados e o mesmo 
    private final int id;
    private String senha;
    private boolean status;
    private String nome;
    private int idade;
    private Sexo sexo;
    private String profissao;
    private ContaBancaria conta;
    private float dinheiroTotal;
    
    
    // METODOS CONSTRUTORES
    public Cliente(String nome, String senha, int idade, Sexo sexo, String profissao, float dinheiroTotal) {
        // Construtor sobrecarregado para criar novo cliente em Admin
        this.id = numClientes + 1; // Comeca em 1 porque Admin e 0
        this.senha = senha;
        this.status = true;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.profissao = profissao;
        this.conta = null;
        this.dinheiroTotal = dinheiroTotal;

        numClientes++; // Aumenta o numero de clientes criados

        //Adiciona o cliente na lista de clientes do Admin
        Admin.adicionaCliente(this);
    }

    public Cliente() {
        // Construtor sobrecarregado, definir atributos com sets
        this.id = numClientes + 1; // Comeca em 1 porque Admin e 0

        numClientes++;

        setStatus(true);

        //Adiciona o cliente na lista de clientes do Admin
        Admin.adicionaCliente(this);
    }



    // METODOS
    public boolean abrirConta(int tipoDaConta){
        // Tipo da conta: 1 - Conta Corrente
        //                2 - Conta Poupanca
        //                3 - Conta investidor

        if (status) {
            if(tipoDaConta == 1){
                this.conta = new ContaCorrente(dinheiroTotal, this);
                return true;
            } else if(tipoDaConta == 2){
                this.conta = new ContaPoupanca(dinheiroTotal, this);
                return true;
            } else if(tipoDaConta == 3){
                this.conta = new ContaInvestidor(dinheiroTotal, this);
                return true;
            }
        }
        return false;
    }

    //Fechar conta, deixa a conta como null
    public void fecharConta(){
        conta = null;
    }

    public String verConta(){
        return conta.toString();
    }

    public String toString() {
        DecimalFormat d1 = new DecimalFormat("#. 00"); // Formatacao para deixar no formato "00,00"

        String out = "Dados do Cliente:\n";
        out += "Nome: " + getNome() + "\n";
        out += "Idade: " + getIdade() + "\n";
        out += "Sexo: " + getSexo().getAtributos() + "\n";
        out += "Profissao: " + getProfissao() + "\n";
        out += "ID do cliente: " + getId() + "\n";
        out += "Senha: " + getSenha() + "\n";
        out += "Status: " + converteStatus(getStatus()) + "\n";
        out += "Dinheiro total: R$" + d1.format(getDinheiroTotal()) + "\n";
        if(conta != null){ // Verifica se tem conta
            out += "ID da conta: " + getConta().getId() + "\n";
            out += "Tipo da conta: " + tipoDaConta() + "\n";
            out += "Saldo da conta: R$" + d1.format(getConta().getSaldo()) + "\n";
            if(this.getConta() instanceof ContaInvestidor){
            	out += "Total Investido: R$";            
            	if (((ContaInvestidor) getConta()).getMontanteTotal() != 0) {
            		out += d1.format(((ContaInvestidor) getConta()).getMontanteTotal()) + "\n";
            	}
            	else {
            		out += "0,00\n";
            	}
            }
        } else {
            out += "Sem conta!\n";
        }

        return out;
    }

    // Metodo auxilia o toString
    private String converteStatus (boolean status) {
        if(status) return "Cliente ativo";
        else return "Cliente inativo";
    }
    
    // Metodo auxilia o toString
    private String tipoDaConta () {
        if(this.getConta() instanceof ContaInvestidor) return "Conta Investidor";
        else if (this.getConta() instanceof ContaPoupanca) return "Conta Poupanca";
        else return "Conta Corrente";
    }


    // GET E SETTERS
    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public static int getNumClientes() {
        return numClientes;
    }
    public static void setNumClientes(int numClientes) {
        Cliente.numClientes = numClientes;
    }

    public int getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public ContaBancaria getConta() {
        return conta;
    }
    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public float getDinheiroTotal() {
        return dinheiroTotal;
    }
    public void setDinheiroTotal(float dinheiroTotal) {
        this.dinheiroTotal = dinheiroTotal;
    }

}