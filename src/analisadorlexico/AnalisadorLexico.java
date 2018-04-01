
package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.atomoSemAtributo;
import static analisadorlexico.maquina.entrada;
import static analisadorlexico.maquina.initAtomoSemAtributo;
import static analisadorlexico.maquina.listaReservada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class AnalisadorLexico {
    public static int count = 0;
    public static int line = 1;
    public static void main(String[] args) throws FileNotFoundException, IOException {
    listaReservada();
    initAtomoSemAtributo();
    String s;
    char current;
    
      File file = new File("teste.txt");
    try {
      FileInputStream fis = new FileInputStream(file);

      while (fis.available() > 0) {
        current = (char) fis.read();
        s = Character.toString(current);
        entrada(s); 
      }
      entrada(" ");
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    }

}
