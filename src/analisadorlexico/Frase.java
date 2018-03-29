package analisadorlexico;

import static analisadorlexico.maquina.atm;
import static analisadorlexico.maquina.defMaq;
import static analisadorlexico.maquina.state;

public class Frase {

    public static atomo Frase(String y) {
            // System.out.println("Maquina: " + defMaq + " Estado: " + state +" atomo: " + atm); 
            //  System.out.println(y);
        switch (state) {
            case 0:
                defMaq = "Frase";
                atm = y;
                state = 1;
                return new atomo(atm, "Frase");
            case 1:              
                atm = atm+y;
                if(!"\"".equals(y) ){
                   System.out.print("ERRO2");
                   System.exit(1);
                    return new atomo(atm, "Frase");
                }
                else{
                    state = 2;                   
                }
                return new atomo(atm, "Frase");
                
            case 2:
                atm = atm + y;
                 if(atm.toLowerCase().contains("\\n".toLowerCase())){
                   System.out.print("ERRO3 frase contem caracteres proibidos");
                   System.exit(1);
                    return new atomo(atm, "Frase");
                }
                 else if("\"".equals(y)){
                    state = 3;
                    return new atomo(atm, "Frase");
                }
                 return new atomo(atm, "Frase");
            case 3:
                 return new atomo(atm, "Frase");
        }
        return null;
    }
}
