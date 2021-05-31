package com.example.listadepersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listadepersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;


public class ListaPersonagensActivity extends AppCompatActivity{
    //Constantes
    public static final String TITULO_APPBAR = "Cadastro Das Bruzundangas";
    //referenciando o index de dao para poder utiliza-lo//
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    //criando um override para a lista de personagens
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR); //Setando o titulo
        configuraFabNovoPersonagem(); //Met Novo personagem
        configuraLista(); //Metodo para configurar a lista
    }

    private void configuraFabNovoPersonagem() {
        //pegando o floatingActionbutton//
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.iconeAdd);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormulario();
            }
        });
    }
    private void abreFormulario() {
        startActivity(new Intent(ListaPersonagensActivity.this, FormularioPersonagemActivity.class) );
    }

    //Fazendo uma proteção para os dados, assim eles não são apagados ao dar Back//
    @Override
    protected void onResume(){
        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        adapter.clear(); //Limpando a Lista e atualizando
        adapter.addAll(dao.todos());
    }

    private void remove (Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override //Criando menu de contexto para remoção do personagem
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
    }
    @Override //Código para remoção do personagem escolhido
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configuraMenu(item);
    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover){
        //Dialog perguntando se quer mesmo excluir o personagem
        new AlertDialog.Builder(this)
                .setTitle("Removendo Cadastro")
                .setMessage("Deseja Mesmo Remover?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //utilizando o adapter para pegar o personagem na lista
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                        remove(personagemEscolhido);
                    }
            })
                .setNegativeButton("Não",null)
                .show();
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.lista_personagens);
        listaDePersonagens(listaDePersonagens); //Metodo Para Setar o personagem na lista
        configuraItemPorClick(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }
    private void configuraItemPorClick(ListView listaDePersonagens) {
        //setando os personagens na lista(in app)
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //Metodo para seleção do personagem//
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int posicao, long id){
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioEditar(personagemEscolhido);
            }
        });
    }
    private void abreFormularioEditar(Personagem personagemEscolhido) {
        Intent vaiParaOFormulario = new Intent (ListaPersonagensActivity.this,  FormularioPersonagemActivity.class);
        vaiParaOFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaOFormulario);
    }
    private void listaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}
