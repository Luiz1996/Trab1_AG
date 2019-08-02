/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vini
 */
public class Vertice {
   
    private String nome;
    private List<Vertice> listaAdjacente = new ArrayList<>();
    
    private Cor cor;
    private int descoberta; //Sera o tempo de descoberta do grafo
    private int termino; // Sera o tempo de termino do grafo
    private Vertice pred; //Guardara o predecessor do grafo
    private boolean printRepetido; //Controle para evitar prints repetidos
    private int low; //Função low que será usado em pontes e nos pontos de articulação
    private int cc; //Componente conexo.
    private boolean visitado = false;
    
    
    //CONSTRUTOR
    public Vertice(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vertice> getListaAdjacente() {
        return listaAdjacente;
    }

    public void setListaAdjacente(List<Vertice> listaAdjacente) {
        this.listaAdjacente = listaAdjacente;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getDescoberta() {
        return descoberta;
    }

    public void setDescoberta(int descoberta) {
        this.descoberta = descoberta;
    }

    public int getTermino() {
        return termino;
    }

    public void setTermino(int termino) {
        this.termino = termino;
    }

    public boolean isPrintRepetido() {
        return printRepetido;
    }

    public void setPrintRepetido(boolean printRepetido) {
        this.printRepetido = printRepetido;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    //IMPRIMI A LISTA DE ADJACENCIA 
    public void imprimirListaAdjacente(){
        for(Vertice v : listaAdjacente){
            System.out.println(v.getNome()+" ");
        }
    }
    
    //ADCIONA NA LISTA DE ADJACENCIA DO VERTICE.
    public void adcionaListaAdj(Vertice vertice){
        listaAdjacente.add(vertice);
    }

    public Vertice getPred() {
        return pred;
    }

    public void setPred(Vertice pred) {
        this.pred = pred;
    }
    

    @Override
    public String toString() {
        return this.nome;
    }

}
