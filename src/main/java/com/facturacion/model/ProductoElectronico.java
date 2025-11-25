package com.facturacion.model;

/**
 * Representa un producto de la categoría Electrónica.
 * 
 * <p>Esta clase implementa el principio de <strong>Liskov Substitution Principle (LSP)</strong>:
 * Puede ser utilizada en cualquier lugar donde se espere un Producto, manteniendo
 * el comportamiento esperado. Cumple con <strong>Single Responsibility Principle (SRP)</strong>
 * al tener la única responsabilidad de representar productos electrónicos.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class ProductoElectronico extends Producto {
    
    /**
     * Constructor que crea un producto electrónico.
     * 
     * @param nombre El nombre del producto electrónico. No puede ser null.
     * @param precio El precio del producto electrónico. Debe ser >= 0.
     * @throws NullPointerException si nombre es null
     * @throws IllegalArgumentException si precio es negativo
     */
    public ProductoElectronico(String nombre, double precio) {
        super(nombre, precio);
    }
    
    /**
     * Representación en cadena del producto electrónico.
     * 
     * @return Una cadena con la información del producto electrónico
     */
    @Override
    public String toString() {
        return String.format("[Electrónica] %s", super.toString());
    }
}

