package com.facturacion.model;

/**
 * Representa un producto de la categoría Alimentos.
 * 
 * <p>Esta clase implementa el principio de <strong>Liskov Substitution Principle (LSP)</strong>:
 * Puede ser utilizada en cualquier lugar donde se espere un Producto, manteniendo
 * el comportamiento esperado. Cumple con <strong>Single Responsibility Principle (SRP)</strong>
 * al tener la única responsabilidad de representar productos alimenticios.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class ProductoAlimento extends Producto {
    
    /**
     * Constructor que crea un producto de alimento.
     * 
     * @param nombre El nombre del alimento. No puede ser null.
     * @param precio El precio del alimento. Debe ser >= 0.
     * @throws NullPointerException si nombre es null
     * @throws IllegalArgumentException si precio es negativo
     */
    public ProductoAlimento(String nombre, double precio) {
        super(nombre, precio);
    }
    
    /**
     * Representación en cadena del producto de alimento.
     * 
     * @return Una cadena con la información del alimento
     */
    @Override
    public String toString() {
        return String.format("[Alimento] %s", super.toString());
    }
}

