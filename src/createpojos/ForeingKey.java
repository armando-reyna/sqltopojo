package createpojos;

class ForeingKey {
    private String tabla;
    private String referencia;
    private String tablaNombre;

    public ForeingKey(String cadena){
        String aux = "";
        int k = 0;
        int j = 0;
        k = (cadena.indexOf('(')+2);
        j = (cadena.indexOf(')')-1);
        
        String ref = cadena.substring(k,j);
        referencia = ref; 
        String tab = cadena.substring(j+1);
        k = 0;
        j = 0;
        k = tab.indexOf("`");
        aux = tab.substring(k+1);
        j = aux.indexOf("`");
        tabla = aux.substring(0,j);
        
    }
    
    public String getTabla() {
        String nombre = this.tabla;
        int k;
        if(nombre.contains("tab_") || nombre.contains("cat_")){
            nombre = nombre.substring(4);
        }
            
        while(( k = nombre.indexOf('_'))>-1){
            String ini = "" + nombre.charAt(0);
            String a = "" + nombre.charAt(k+1);
            String aux = ini.toUpperCase() + nombre.substring(1,k);
            aux = aux + a.toUpperCase() +nombre.substring(k+2);
            nombre = aux;
        }
        return nombre;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTablaNombre() {
        String nombre = this.tabla;
        int k;
        if(nombre.contains("tab_") || nombre.contains("cat_")){
            nombre = nombre.substring(4);
            String ini = "" + nombre.charAt(0);
            nombre = ini.toUpperCase() + nombre.substring(1);
        }
        
        while(( k = nombre.indexOf('_'))>-1){
                String a = "" + nombre.charAt(k+1);
                String aux = nombre.substring(0,k);
                nombre = aux + a.toUpperCase() +nombre.substring(k+2);   
            }
        this.tablaNombre = nombre;
        return this.tablaNombre;
    }    
    
    public boolean compara(String nombre){
        if(nombre.compareTo(this.referencia)==0)
            return true;
        else
            return false;
    }
}
    