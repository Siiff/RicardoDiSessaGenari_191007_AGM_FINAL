package com.example.listadepersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    //pegando as variaveis//
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String rg;
    private String cep;
    private String genero;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento, String telefone, String rg, String cep, String genero) {
        //setando variaveis
        this.nome=nome;
        this.altura=altura;
        this.nascimento=nascimento;
        this.nascimento=telefone;
        this.nascimento=rg;
        this.nascimento=cep;
        this.nascimento=genero;
    }
    public Personagem (){

    } //esse construtor vazio serve para caso o usuario insira um campo vazio

    //covertendo o nome para um string para exibição//
    @NonNull
    @Override
    public String toString(){ return nome;
    }
    public String getNome() { return nome;
    }
    public void setNome(String nome) { this.nome = nome;
    }
    public String getAltura() { return altura;
    }
    public void setAltura(String altura) { this.altura = altura;
    }
    public String getNascimento() { return nascimento;
    }
    public void setNascimento(String nascimento) { this.nascimento = nascimento;
    }
    public String getTelefone() { return telefone;
    }
    public void setTelefone(String telefone) { this.telefone = telefone;
    }
    public String getRg() { return rg;
    }
    public void setRg(String rg) { this.rg = rg;
    }
    public String getCep() { return cep;
    }
    public void setCep(String cep) { this.cep = cep;
    }
    public String getGenero() { return genero;
    }
    public void setGenero(String genero) { this.genero = genero;
    }

    //Get Setter do Id//
    public int getId(){ return id;
    }
    public void setId(int id){ this.id = id;
    }
    public boolean IDValido (){return id > 0;}
}
