package com.example.listadepersonagens.dao;

import android.app.Person;

import com.example.listadepersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    //Numerando os personagens//
    private static int contadorDeId = 1;

    public void salvar (Personagem personagemSalvo){
        personagemSalvo.setId(contadorDeId);
        personagens.add(personagemSalvo);
        atualizaID();
    }

    private void atualizaID() {
        contadorDeId++;
    }
    //Pedaço do código voltado a edição do personagem//
    public void editar(Personagem personagem){
        Personagem personagemEscolhido = buscaPersonagemID(personagem);
        if(personagemEscolhido != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    private Personagem buscaPersonagemID(Personagem personagem) {
        for (Personagem p: personagens){
            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Personagem> todos(){
        return new ArrayList<>(personagens);
    }

    public void remove (Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemID(personagem);
        if(personagemDevolvido!=null){
            personagens.remove(personagemDevolvido);
        }
    }

}
