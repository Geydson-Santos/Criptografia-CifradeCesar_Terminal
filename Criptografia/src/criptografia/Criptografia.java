/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package criptografia;
import java.util.Scanner;

/**
 *
 * @author ALUNO
 */
public class Criptografia {

    // Créditos ao thingol que postou o código no guj, link: https://www.guj.com.br/t/acentos-no-java/41869/2
    static String acentuado = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
    static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
    static char[] tabela;
    static {
        tabela = new char[256];
        for (int i = 0; i < tabela.length; ++i) {
	    tabela [i] = (char) i;
        }
        for (int i = 0; i < acentuado.length(); ++i) {
            tabela [acentuado.charAt(i)] = semAcento.charAt(i);
        }
    }
    public static String remover (final String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt (i);
            if (ch < 256) { 
                sb.append (tabela [ch]);
            } else {
                sb.append (ch);
            }
        }
        return sb.toString();
    }
    
    // Créditos ao dyeimys que postou o código no guj, link: https://www.guj.com.br/t/busca-em-texto-com-ignorecase-resolvido/86326/5
    public static boolean verifica(String texto, String padrao) {
        //Tira acentos e coloca tudo minusculo
        texto = prepara(texto);
        padrao = prepara(padrao);

        //Verifica se tem a palavra
        if (texto.contains(padrao)) {
            return true;
        } else {
            return false;
        }
    }
    
    private static String prepara(String str) {
        //coloca tudo em minusculo
        str = str.toLowerCase();

        //remover acentos
        str = remover(str);
        return str;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String e; // String que vai receber a entrada
        String codificar; // String que vai receber a saída codificada
        String decodificar; // String que vai receber a saída decodificada

        String sair;
        boolean saindo = false;

        String codificar_decodificar;
        boolean codificando;
        boolean decodificando;

        Scanner leia = new Scanner(System.in);

        System.out.println("Programa Cifra de César");
        
        while(saindo == false){
            codificando = false;
            codificar = "";
            decodificar = "";
            System.out.println("Você deseja codificar ou decodificar?");
            codificar_decodificar = leia.next();
            decodificando = verifica(codificar_decodificar, "decodificar"); // Se for verdadeiro -> é pra decodificar
            if(decodificando == false){ // se for falso -> é pra codificar
                codificando = verifica(codificar_decodificar, "codificar");
            }
            if(codificando){
                System.out.println("Digite uma palavra para codificar: ");
                e = leia.next();

                int n, nI;

                System.out.println("Digite o número de deslocamento: ");
                n = leia.nextInt();
                n = n%26;

                String alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

                // cria um vetor de array com todos os caracteres
                char[] arrayAlfabeto = alfabeto.toCharArray();

                // cria um vetor de array com todos os caracteres
                char[] array = e.toCharArray();

                // percorre o array , em cada vez ele percore o vetor albabeto até
                // achar, quando acha sai
                // e o indice mais um equivale ao proximo digito
                for (int i = 0, j; i < array.length; i++) { // Para cada letra

                    for (j = 0; j < arrayAlfabeto.length; j++) // Para o alfabeto
                        if (array[i] == arrayAlfabeto[j])
                            break;

                    
                    if(j < 26){
                        if((j+n) >= 26){ // Para Letras minúsculas
                            nI = (j + n) - 26;
                            codificar += alfabeto.charAt(nI); // A letra vai subtrair o número
                        }else
                            if((j+n) < 26)
                                codificar += alfabeto.charAt(j + n); // A letra vai somar o número
                        
                    }else/*if(j >= 26)*/{
                        
                        if(j < 52){ // Para Letras Maiúsculas
                            if((j+n) >= 52){ // A letra vai subtrair o número
                                nI = (j + n) - 26;
                                codificar += alfabeto.charAt(nI);
                            }else  // A letra vai somar o número
                                codificar += alfabeto.charAt(j + n);
                        }

                        if(j >= 52){ // Para Números
                            codificar += arrayAlfabeto[j];
                        }
                    }

                }

                System.out.println("Palavra codificada: " + codificar);
            }
            
            if(decodificando){
                System.out.println("Digite uma palavra para decodificar: ");
                e = leia.next();

                int n, nI;

                System.out.println("Digite o número de conversão: ");
                n = leia.nextInt();
                n = n%26;

                String alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

                // cria um vetor de arrayAlfabeto com todos os caracteres do alfabeto
                char[] arrayAlfabeto = alfabeto.toCharArray();

                // cria um vetor de array com todos os caracteres da palavra
                char[] array = e.toCharArray();

                // percorre o array , em cada vez ele percore o vetor albabeto ate
                // achar, quando acha sai
                // e o indice mais um equivale ao proximo digito
                for (int i = 0, j; i < array.length; i++) { // Para cada letra

                    for (j = 0; j < arrayAlfabeto.length; j++) // Para o alfabeto
                        if (array[i] == arrayAlfabeto[j])
                            break;

                    
                    if(j < 26){
                        if((j-n) < 0){ // Para Letras minúsculas
                            nI = (j - n) + 26;
                            decodificar += alfabeto.charAt(nI); // A letra vai subtrair o número
                        }else
                            if((j-n) >= 0)
                                decodificar += alfabeto.charAt(j - n); // A letra vai somar o número
                    }
                    
                    if(j >= 26){ // Para Letras Maiúsculas e Números
                        if(j < 52){ // Para Letras Maiúsculas
                            if((j-n) < 26){ // A letra vai subtrair o número
                                nI = (j - n) + 26;
                                decodificar += alfabeto.charAt(nI);
                            }else  // A letra vai somar o número
                                decodificar += alfabeto.charAt(j - n);
                        }else{ // Para Números
                            decodificar += arrayAlfabeto[j];
                        }
                    }

                }

                System.out.println("Palavra decodificada: " + decodificar);
            }

            System.out.println("Deseja sair? Digite \"sair\".");
            sair = leia.next();
            saindo = verifica(sair, "sair");
        }

        /*System.out.println("Codificar ou Decodificar? ");
        cod_dec = leia.nextLine();

        switch(cod_dec){
            case "Codificar":
                System.out.println("Deu certo");
        }*/
                
                
    }
    
}
