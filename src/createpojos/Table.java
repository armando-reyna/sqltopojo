package createpojos;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Table {
    private String nombre;
    private String atributos;
    private ArrayList<Atributo> at;
    private boolean bandera;

    public Table(){
        nombre = null;
        atributos = null;
        bandera = false ;
    }
    
    
    public Table(String nombre, String atributos){
        int k;
        bandera = false;
        if(nombre.contains("tab_") || nombre.contains("cat_")){
            nombre = nombre.substring(4);
            String ini = "" + nombre.charAt(0);
            nombre = ini.toUpperCase() + nombre.substring(1);
        }
            
        while(( k = nombre.indexOf('_'))>-1){
            String ini = "" + nombre.charAt(0);
            String a = "" + nombre.charAt(k+1);
            String aux = ini.toUpperCase() + nombre.substring(1,k);
            aux = aux + a.toUpperCase() +nombre.substring(k+2);
            nombre = aux;
        }
         
        this.nombre = nombre;
        this.atributos = atributos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }
    
    public ArrayList<Atributo> getAt() {
        return at;
    }

    public void setAt(ArrayList<Atributo> at) {
        this.at = at;
    }
 
    public void viewAtributos(){
        int i = 0, j=0, m;
        String prueba="";
        String aux = atributos ;
        String name = "";
        String type = "";
        Atributo a;
        ArrayList<ForeingKey> key = new ArrayList();
        if(aux.contains("constraint")){
            m = aux.indexOf("constraint");
            String constraint = aux.substring(m);
            aux = aux.substring(0, m-1);
            ForeingKey fk;
            String aux1 = "";
            while(constraint.contains("constraint")){
                //System.out.println(constraint);
                if (constraint.indexOf(',') > -1) {
                    j = constraint.indexOf('\n');
                    //System.out.println(j);
                    aux1 = constraint.substring(0,j);
                    fk = new ForeingKey(aux1);
                    key.add(fk);
                    //System.out.println(constraint);
                    constraint = constraint.substring(j);
                }
                else{
                    fk = new ForeingKey(constraint);
                    key.add(fk);
                    //System.out.println(constraint);
                    constraint = "";
                }

            }   
        }
        
        at = new ArrayList();
        while(aux.indexOf('\n') > -1){
            prueba = aux.substring(0,aux.indexOf('\n'));
            
            if(prueba.contains("int")){
                type = "int";
            }
            if(prueba.contains("varchar")){
                type = "String";
            }
            if(prueba.contains("text")){
                type = "String";
            }
            if(prueba.contains("double") || prueba.contains("numeric")){
                type = "double";
            }
            if(prueba.contains("timestamp") || prueba.contains("date")){
                bandera = true ;
                type = "Date";
            } 
            i = prueba.indexOf('`');
            name = prueba.substring(i+1, prueba.indexOf('`', i+1));
            a = new Atributo(name, type, key);
            
            aux = aux.substring(aux.indexOf('\n')+1);
            
            at.add(a);
        }
    }
    
    public void savePojo (String ruta) throws IOException{
        String doc = "";
        if (bandera){
            doc = "import java.util.Date;\n\n public class " + nombre + "\n{\n";
        }
        else
            doc = "public class " + nombre + "\n{\n";
        
        for(int i=0;i<at.size();i++){
            doc = doc + "\tprivate " + at.get(i).getTipo() + " " + at.get(i).getNombre() + ";\n" ;
        }
        doc = doc + "}";
        File archivo = new File(ruta+"\\"+nombre+".java");
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(doc);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(doc);
        }
        bw.close();
    }
}
