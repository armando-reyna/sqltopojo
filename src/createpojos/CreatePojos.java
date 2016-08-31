
package createpojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreatePojos {
    
    public ArrayList<String> pojos;
    private int cont;
    Date fecha;
    
    public CreatePojos(){
    }
    
    public String fileArchivo(File fichero) throws FileNotFoundException, IOException{
        
        FileReader fr=new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);
        String cadena="";
        String linea;
        while((linea = br.readLine()) != null)
        {
            cadena=cadena+linea+"\n";
        }
        return cadena;
    }
    
    public String viewPojos(String cadena){
        String aux = cadena.toLowerCase();
        String find = "create table";
        char find1 = ';';
        String aux1 = "";
        int i=0 , j=0;
        
        pojos = new ArrayList<>(); 
        
        while (aux.indexOf(find) > -1) {
            aux1 = null;
            i = aux.indexOf(find);
            j = aux.indexOf(find1,i);
            aux1 = aux.substring(i,j+1);
            pojos.add(aux1);
            aux = aux.substring((j+1),aux.length());
        } 
        int x, tam = pojos.size();
        String cad = "";
        for(x=0;x<tam;x++){
            cad =cad + ((x+1)+ ". " + pojos.get(x) + "\n\n");
        }
        return cad;
    } 
    
    public String namePojo(String linea){
        cont++;
        String name = "";
        int i=0,j=0;
        
        if (linea != null){
            i = linea.indexOf('`');
            j= linea.indexOf('`', i+1);
            name = linea.substring(i+1, j);
            return name;
            
        }
        else
            return null;
        
    }
    
    public String obtenAtributos(String select, int i){
        String atributos = "";
        String cadena = "";
        String aux = "";
        int k = 0, x=0, y=0, h=0,j=0;
        if(select != null){
            cadena = pojos.get(i);
            if (cadena.indexOf(select) > -1) {
                x = cadena.indexOf('(');
                y = cadena.indexOf(';',x);
                aux = cadena.substring(x,y);
                while (aux.indexOf("null") > -1){
                    h = aux.indexOf('`');
                    j = aux.indexOf("null");
                    atributos = atributos + aux.substring(h, j+4) + "\n";
                    aux = aux.substring(j+4);
                }
                if((k=aux.indexOf("timestamp")) > -1){
                    k = k + 9;
                    atributos = atributos + aux.substring(aux.indexOf('`'),k) + "\n";
                }
            } 
        }
        else
            atributos = null;
        return atributos;
    }
    
    
}
