package createpojos;

import java.util.ArrayList;

public class Atributo {
    private String nombre;
    private String tipo;
    private String notacion;

    public Atributo() {
        nombre = null;
        tipo = null;
    }
    
    public Atributo(String nombre, String tipo, ArrayList<ForeingKey> fk){
        int k,i=0;
        boolean bandera = false;
        while(i<fk.size()){
            if(fk.get(i).compara(nombre)){
                this.nombre = fk.get(i).getTabla();
                this.tipo = fk.get(i).getTablaNombre();
                //--------------Codigo ne Notacion---------------------
                while(( k = nombre.indexOf('_'))>-1){
                    String a = "" + nombre.charAt(k+1);
                    String aux = nombre.substring(0,k);
                    nombre = aux + a.toUpperCase() +nombre.substring(k+2);   
                }   
                this.notacion = "\n\t@ManyToOne\n \t@JoinColumn(name = \""+nombre+"\")\n";
                //------------------------------------------------------
                bandera = true;
            }
            i++;
        }
        if(!bandera){
            while(( k = nombre.indexOf('_'))>-1){
                String a = "" + nombre.charAt(k+1);
                String aux = nombre.substring(0,k);
                nombre = aux + a.toUpperCase() +nombre.substring(k+2);   
            }
            this.nombre = nombre;
            this.tipo = tipo;
            this.notacion = null;
        }                    
    }
    
    
    public String getCode(){
        String funcion = this.nombre;
        String ini = "" + funcion.charAt(0);
        funcion = ini.toUpperCase() + funcion.substring(1);
        String code = "\npublic "+this.tipo+" get"+funcion+"() {\n" +
                    "\treturn "+this.nombre+";\n" +
                    "}\n" +
                    "\n" +
                    "public void set"+funcion+"("+this.tipo+" "+this.nombre+") {\n" +
                    "\tthis."+this.nombre+" = "+this.nombre+";" +
                    "\n}" +
                    "\n\n";
        
        return code;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
     public String getNotacion() {
        return notacion;
    }

    public void setNotacion(String notacion) {
        this.notacion = notacion;
    }
    
}
