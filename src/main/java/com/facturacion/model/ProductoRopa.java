package com.facturacion.model;

/**
 * Representa un producto de la categoría Ropa.
 * 
 * <p>Esta clase implementa el principio de <strong>Liskov Substitution Principle (LSP)</strong>:
 * Puede ser utilizada en cualquier lugar donde se espere un Producto, manteniendo
 * el comportamiento esperado. Cumple con <strong>Single Responsibility Principle (SRP)</strong>
 * al tener la única responsabilidad de representar productos de ropa.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class ProductoRopa extends Producto {
    
    /**
     * Constructor que crea un producto de ropa.
     * 
     * @param nombre El nombre del producto de ropa. No puede ser null.
     * @param precio El precio del producto de ropa. Debe ser >= 0.
     * @throws NullPointerException si nombre es null
     * @throws IllegalArgumentException si precio es negativo
     */
    public ProductoRopa(String nombre, double precio) {
        super(nombre, precio);
    }
    
    /**
     * Representación en cadena del producto de ropa.
     * 
     * @return Una cadena con la información del producto de ropa
     */
    @Override
    public String toString() {
        return String.format("[Ropa] %s", super.toString());
    }
}

