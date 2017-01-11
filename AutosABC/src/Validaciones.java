/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */

//Clase validaciones
public class Validaciones {
    AccesoDatos datos;
     public Validaciones() {
         datos = new AccesoDatos();
     }
     
     //Validacion de si un String es un numero
    public boolean validarNumero(String cadena){
        try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}   
    }
    
    // Validacion de que un campo no sea vacio 
    public boolean noVacio(String cadena){
        if(cadena.length() == 0 || cadena.isEmpty()){
            return false;
        }else return true;
    }
    
    
 

   
}
