/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.ListAdapter;

/**
 *
 * @author Vini
 */
public class Teste {

    public static void main(String[] args) {
        BuscaEmProfundidade busca = new BuscaEmProfundidade();

        Scanner leitor = new Scanner(System.in);
        int opcao = 1;

        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");
        Vertice E = new Vertice("E");

        busca.inserirVertice(A);
        busca.inserirVertice(B);
        busca.inserirVertice(C);
        //busca.inserirVertice(D);
       // busca.inserirVertice(E);

        A.adcionaListaAdj(B);
        B.adcionaListaAdj(C);
        //C.adcionaListaAdj(A);
        //C.adcionaListaAdj(D);
        //D.adcionaListaAdj(E);
        //E.adcionaListaAdj(A);

        while (opcao != 0) {
            System.out.println("\n--------------------OPÇÕES---------------------");
            System.out.println("(1) Exibir o grafo formado"
                    + "\n(2) Listar o numero de Componentes Conexos"
                    + "\n(3) Listar Pontes do grafo"
                    + "\n(4) Listar Pontos de Articulação do grafo"
                    + "\n(5) Busca em Profundidade COMPLETA."
                    + "\n(6) Ordenação Topologica"
                    + "\n(7) Verificar Ciclos"
                    + "\n(0) Sair do programa");
            System.out.println("-----------------------------------------------");
            System.out.print("Digite uma opção valida: ");
            opcao = leitor.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("\nFim do programa.");
                    break;
                case 1:
                    busca.imprimeGrafo();
                    break;
                case 2:
                    System.out.println("\nNumero de Componentes conexos dentro do grafo: " + busca.numComponentesConexos());
                    break;
                case 3:
                    busca.dfsPontes(); // COLOCAR DUAS MSG DE RETORNO QUANDO NAO TIVER PONTE E NEM PONTO DE ARTICULAÇÃO
                    break;
                case 4:
                    busca.dfsPontosArticulacao();
                    break;
                case 5:
                    busca.buscaEmProfundidade();
                    busca.imprimeBuscaProfundidade();
                    break;
                case 6:
                    List<Vertice> verticeTopologico = busca.topologicalSort();
                    int count = 1;
                    for (Vertice vertice : verticeTopologico) {
                        System.out.println("VISITADO = " + vertice.isVisitado());
                        System.out.println(count + " " + vertice.getNome());
                        count++;
                    }
                    break;

                case 7:
                    busca.temCiclo();
                    
                    
                    break;

                default:
                    System.out.println("ERRO: Opção inválida.");
                    break;
            }
        }
    }

}
