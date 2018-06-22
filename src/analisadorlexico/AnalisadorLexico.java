
package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.atomoSemAtributo;
import static analisadorlexico.maquina.entrada;
import static analisadorlexico.maquina.initAtomoSemAtributo;
import static analisadorlexico.maquina.listaReservada;
import analisadorlexico.AnalisadorSintatico;
import static analisadorlexico.AnalisadorSintatico.operadoresADD;
import static analisadorlexico.AnalisadorSintatico.tiposADD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class AnalisadorLexico {
    public static int count = 0;
    public static int line = 1; 
    public static String consumed = ""; 
    public static LinkedList<String> tokens = new LinkedList<>();
    public static void main(String[] args) throws FileNotFoundException, IOException {      
        listaReservada();
        tiposADD();
        operadoresADD();
    initAtomoSemAtributo();
    String s;
    char current;
    
      File file = new File("ex01.txt");
    try {
      FileInputStream fis = new FileInputStream(file);

      while (fis.available() > 0) {
        current = (char) fis.read();
        s = Character.toString(current);
        entrada(s); 
      }
      System.out.println(tokens);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    //analisador sintatico
    while(!tokens.isEmpty()){
        if(!AnalisadorSintatico.consumir(tokens.pollFirst())){
            System.out.println("Programa encontrou token não esperado");
            tokens.clear();
        }
       
    }
    
    }

}
