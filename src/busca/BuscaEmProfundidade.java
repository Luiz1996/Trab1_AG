package busca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BuscaEmProfundidade {

    private List<Vertice> listaVertice = new ArrayList<>(); //LISTA DOS VERTICES
    private Stack<Vertice> pilha = new Stack(); //USADO NA HORA DE UTILIZAR A BUSCA EM PROFUNDIDADE.

    private int tempo; // DFS
    private int numeroCC; //NUMERO DE COMPONENTES CONEXOS.
    private boolean temCiclo = false;

    public List<Vertice> getListaVertice() {
        return listaVertice;
    }

    public void setListaVertice(List<Vertice> listaVertice) {
        this.listaVertice = listaVertice;
    }

    public Stack<Vertice> getPilha() {
        return pilha;
    }

    public void setPilha(Stack<Vertice> pilha) {
        this.pilha = pilha;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(int numeroCC) {
        this.numeroCC = numeroCC;
    }

    public void inserirVertice(Vertice vertice) {
        listaVertice.add(vertice);
    }

    public boolean isTemCiclo() {
        return temCiclo;
    }

    public void setTemCiclo(boolean temCiclo) {
        this.temCiclo = temCiclo;
    }

    // ALGORITMO DE BUSCA EM PROFUNDIDADE 
    public void buscaEmProfundidade() {
        for (Vertice v : listaVertice) {
            v.setCor(Cor.BRANCO);
            v.setPred(null);
        }
        tempo = 0;
        for (Vertice u : listaVertice) {
            if (u.getCor().equals(Cor.BRANCO)) {
                visitarVertices(u);
            }
        }
    }

    private void visitarVertices(Vertice vertice) { //É COMO SE FOSSE O DFSVISIT
        tempo++;
        vertice.setCor(Cor.CINZA);
        vertice.setDescoberta(tempo);
        for (Vertice v : vertice.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {
                v.setPred(vertice);
                visitarVertices(v);
            }
        }
        vertice.setCor(Cor.PRETO);
        tempo++;
        vertice.setTermino(tempo);
    }

    public void imprimeGrafo() {
        System.out.println("\n---------------GRAFO---------------");
        System.out.println("(Vertice) --> Lista de Adjacente");
        System.out.println("-----------------------------------------------");
        for (Vertice vertice : listaVertice) {
            System.out.print("(" + vertice + ")");
            for (Vertice adj : vertice.getListaAdjacente()) {
                System.out.print(" --> " + adj.getNome());
            }
            System.out.println();
        }
    }

    public void imprimeBuscaProfundidade() {
        System.out.println("\n---------------GRAFO---------------");
        System.out.println("\n----------BuscaProfundidade--------");
        for (Vertice vertice : listaVertice) {
            System.out.print("Vertice: " + vertice + "     Descoberta: " + vertice.getDescoberta() + "     Termino: " + vertice.getTermino() + "    Predecessor: " + vertice.getPred());

            System.out.println();
        }
    }

    // ALGORITMO DE PONTO DE ARTICULAÇÃO
    public void dfsPontosArticulacao() { // BUSCA EM PROFUNDIDADE NO PONTO DE ARTICULAÇÃO.
        System.out.println("\n-------------PONTOS DE ARTICULAÇÃO-------------");
        for (Vertice u : listaVertice) {
            u.setCor(Cor.BRANCO);
            u.setPred(null);
            u.setPrintRepetido(false);
        }
        tempo = 0;
        for (Vertice u : listaVertice) {
            if (u.getCor().equals(Cor.BRANCO)) {
                pontoArticulacao(u);
            }
        }
    }

    private boolean verificaDoisFilhos(Vertice vertice) {
        int filhos = 0;
        for (Vertice v : listaVertice) {
            if ((v.getPred() != null) && (v.getPred().equals(vertice))) {
                filhos++;
                if (filhos == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void pontoArticulacao(Vertice u) { //MODIFICAÇÃO DO VISITARVERTICE
        tempo++;
        u.setCor(Cor.CINZA);
        u.setLow(tempo);
        u.setDescoberta(tempo);
        for (Vertice v : u.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {
                v.setPred(u);
                pontoArticulacao(v);
                if (u.getPred() == null) { // Se u é raiz
                    if ((verificaDoisFilhos(u)) && (!u.isPrintRepetido())) {
                        System.out.println("VERTICE (" + u + ")" + " É UM PONTO DE ARTICULAÇÃO.");
                        u.setPrintRepetido(true);
                    }
                } else {
                    u.setLow(Integer.min(u.getLow(), v.getLow()));
                    if ((v.getLow() >= u.getDescoberta()) && (!u.isPrintRepetido())) {
                        System.out.println("VERTICE (" + u + ")" + " É UM PONTO DE ARTICULAÇÃO.");
                        u.setPrintRepetido(true);
                    }
                }
            } else {
                if ((!v.equals(u.getPred())) && (v.getDescoberta() < u.getDescoberta())) {
                    u.setLow(Integer.min(u.getLow(), v.getDescoberta()));
                    System.out.println("NÃO TEM PONTOS DE ARTICULAÇÃO NO GRAFO");
                }
            }
        }
        u.setCor(Cor.PRETO);
        tempo++;
        u.setTermino(tempo);
    }

//ALGORITMO DE PONTES.
    public void dfsPontes() {
        System.out.println("\n--------------------PONTES DO GRAFO---------------------");

        for (Vertice u : listaVertice) {
            u.setCor(Cor.BRANCO);
            u.setPred(null);
        }
        tempo = 0;
        for (Vertice u : listaVertice) {
            if (u.getCor().equals(Cor.BRANCO)) {
                pontes(u);
            }
        }
    }

    private void pontes(Vertice u) { //Modificação de dfsVisit
        tempo++;
        u.setCor(Cor.CINZA);
        u.setLow(tempo);
        u.setDescoberta(tempo);
        for (Vertice v : u.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {
                v.setPred(u);
                pontes(v);
                u.setLow(Integer.min(u.getLow(), v.getLow()));

                if (v.getLow() > u.getDescoberta()) {
                    System.out.println("Aresta (" + u + " --> " + v + ")" + " é uma ponte.");;
                }
            } else {
                if ((!v.equals(u.getPred())) && (v.getDescoberta() < u.getDescoberta())) {
                    u.setLow(Integer.min(u.getLow(), v.getDescoberta()));
                    System.out.println("NÃO TEM PONTES NO GRAFO");
                }
            }
        }
        u.setCor(Cor.PRETO);
        tempo++;
        u.setTermino(tempo);
    }

    //ALGORITMO DE COMPONENTES CONEXOS.
    private void dfsCC(Vertice u) { //Modificação de dfsVisit
        u.setCor(Cor.CINZA);
        u.setCc(numeroCC);
        for (Vertice v : u.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {
                dfsCC(v);
            }
        }
        u.setCor(Cor.PRETO);
    }

    public int numComponentesConexos() {
        for (Vertice v : listaVertice) {
            v.setCor(Cor.BRANCO);
        }
        numeroCC = 0;
        for (Vertice v : listaVertice) {
            if (v.getCor().equals(Cor.BRANCO)) {
                numeroCC++;
                dfsCC(v);
            }
        }
        return numeroCC;
    }

    //ORDENAÇAO TOPOLOGICA 
    public List<Vertice> topologicalSort() {
        List<Vertice> ordernar = new ArrayList<>();

        if (this.temCiclo) {
            System.out.println("NÃO TEM ORDENAÇÃO TOPOLOGICA, TEM CICLOS");
        }
        for (Vertice v : listaVertice) {
            if (!v.isVisitado()) {
                ordenacaoTopologica(v, ordernar);
            }
        }

        Collections.reverse(ordernar);
        return ordernar;
    }

    public void ordenacaoTopologica(Vertice v, List<Vertice> guardarVertice) {

        v.setVisitado(true);

        for (Vertice adj : v.getListaAdjacente()) {
            if (!adj.isVisitado()) {
                ordenacaoTopologica(adj, guardarVertice);
            }
        }

        guardarVertice.add(v);
    }

    //COMPONENTE FORTEMENTE CONEXO
    public void buscaEmProfundidadeFC() {
        for (Vertice v : listaVertice) {
            v.setCor(Cor.BRANCO);
            v.setPred(null);
        }
        tempo = 0;
        for (Vertice u : listaVertice) {
            if (u.getCor().equals(Cor.BRANCO)) {
                visitarVerticesFC(u);
            }
        }
    }

    private void visitarVerticesFC(Vertice vertice) { //É COMO SE FOSSE O DFSVISIT
        tempo++;
        vertice.setCor(Cor.CINZA);
        vertice.setDescoberta(tempo);
        for (Vertice v : vertice.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {
                v.setPred(vertice);
                visitarVerticesFC(v);
            }
        }
        vertice.setCor(Cor.PRETO);
        tempo++;
        vertice.setTermino(tempo);
    }

    // VERIFICA SE TEM CICLO 
    public void temCiclo() {
        boolean verCiclo = false;
        for (Vertice u : listaVertice) {
            u.setCor(Cor.BRANCO);

        }
        tempo = 0;
        for (Vertice u : listaVertice) {
            if (u.getCor().equals(Cor.BRANCO)) {
                cicloVisit(u, verCiclo);
            }
        }
        
        if(verCiclo){
            System.out.println("TEM CICLO");
        }else{
            System.out.println("NAO TEM CICLO");
        }
    }

    private void cicloVisit(Vertice u, boolean verCiclo) { //Modificação de dfsVisit

        u.setCor(Cor.CINZA);

        for (Vertice v : u.getListaAdjacente()) {
            if (v.getCor().equals(Cor.BRANCO)) {

                cicloVisit(v, verCiclo);

                
                      
            } else {

                verCiclo = true;
            }
        }
    }
        
}
