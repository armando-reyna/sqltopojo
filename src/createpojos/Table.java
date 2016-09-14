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
        if(nombre.contains("tab_") ){
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
                if (constraint.indexOf(',') > -1) {
                    j = constraint.indexOf(',');
                    aux1 = constraint.substring(0,j);
                    if(aux1.contains("constraint")){
                        fk = new ForeingKey(aux1);
                        key.add(fk);
                    }
                    constraint = constraint.substring(j+1);
                }
                else{
                    fk = new ForeingKey(constraint);
                    key.add(fk);
                    constraint = "";
                }
            }   
        }
        
        at = new ArrayList();
        while(aux.indexOf(',') > -1){
            prueba = aux.substring(0,aux.indexOf(','));
            if(!prueba.contains("primary key")){
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
                if(prueba.contains("enum")){
                    type = "String";
                }
                i = prueba.indexOf('`');
                if(i > -1){
                    name = prueba.substring(i+1, prueba.indexOf('`', i+1));
                    a = new Atributo(name, type, key);
                    aux = aux.substring(aux.indexOf(','));
                    at.add(a);
                }
                else{
                    aux = aux.substring(aux.indexOf(',')+1);
                }
            }
            else{
                prueba = ""; 
                break;
            }
        }
    }
    
    public void savePojo (String ruta, String paquete) throws IOException{
        String aux = this.nombre;
        
        if (this.nombre.contains("Cat")) {
            String ini = "" + aux.charAt(0);
            aux = ini.toLowerCase() + aux.substring(1);
        }else{
            aux = "tab" + aux;
        }
            
        
        String doc = "package " + paquete + ";\n\nimport javax.persistence.*;\nimport java.io.Serializable;\n";
        
        
        
        if (bandera){
            doc = doc + "import java.util.Date;";
        }
        
        doc = doc + "\n\n@Entity(name = \""+paquete+"."+this.nombre+"\")\n@Table(name = \""+aux+"\")\n";
        doc = doc + "public class " + this.nombre + " implements Serializable {\n";
        
        doc = doc + "\n\t@Id\n\t@GeneratedValue\n \t@Column(name = \"id\")\n";
        for(int i=0;i<at.size();i++){
            if(at.get(i).getNotacion() != null)
                doc = doc + at.get(i).getNotacion();
            doc = doc + "\tprivate " + at.get(i).getTipo() + " " + at.get(i).getNombre() + ";\n" ;
            
        }
        
        for (int y = 0;y<at.size();y++){
            doc = doc + at.get(y).getCode();
        }
        
        doc = doc + "\n}";
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
