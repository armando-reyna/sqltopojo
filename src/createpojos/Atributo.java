package createpojos;

public class Atributo {
    private String nombre;
    private String tipo;

    public Atributo() {
        nombre = null;
        tipo = null;
    }
    
    public Atributo(String nombre, String tipo){
        int k;
        while(( k = nombre.indexOf('_'))>-1){
            String a = "" + nombre.charAt(k+1);
            String aux = nombre.substring(0,k);
            nombre = aux + a.toUpperCase() +nombre.substring(k+2);
            
        }
        this.nombre = nombre;
        this.tipo = tipo;
        
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
    
}
