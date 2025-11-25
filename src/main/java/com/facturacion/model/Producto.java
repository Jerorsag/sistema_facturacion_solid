package com.facturacion.model;

import java.util.Objects;

/**
 * Clase abstracta que representa un producto genérico en el sistema de facturación.
 * 
 * <p>Esta clase implementa el principio de <strong>Liskov Substitution Principle (LSP)</strong>:
 * Todas las subclases (ProductoAlimento, ProductoRopa, ProductoElectronico) pueden
 * sustituir a Producto sin alterar el comportamiento esperado del sistema. Además,
 * cumple con <strong>Single Responsibility Principle (SRP)</strong> al tener una única
 * responsabilidad: representar la información básica de un producto (nombre y precio).
 * 
 * <p>La clase es inmutable después de la construcción, lo que garantiza la integridad
 * de los datos del producto.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public abstract class Producto {
    
    /**
     * Nombre del producto. No puede ser null.
     */
    private final String nombre;
    
    /**
     * Precio del producto. Debe ser mayor o igual a cero.
     */
    private final double precio;
    
    /**
     * Constructor que inicializa un producto con nombre y precio.
     * 
     * <p>Valida que el nombre no sea null y que el precio sea no negativo.
     * 
     * @param nombre El nombre del producto. No puede ser null.
     * @param precio El precio del producto. Debe ser >= 0.
     * @throws NullPointerException si nombre es null
     * @throws IllegalArgumentException si precio es negativo
     */
    public Producto(String nombre, double precio) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre del producto no puede ser null");
        
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
        }
        
        this.precio = precio;
    }
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Representación en cadena del producto.
     * 
     * @return Una cadena con el nombre y precio del producto
     */
    @Override
    public String toString() {
        return String.format("%s - $%.2f", nombre, precio);
    }
    
    /**
     * Compara este producto con otro objeto para determinar igualdad.
     * 
     * @param obj El objeto a comparar
     * @return true si los productos tienen el mismo nombre y precio
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return Double.compare(producto.precio, precio) == 0 &&
               Objects.equals(nombre, producto.nombre);
    }
    
    /**
     * Calcula el código hash del producto.
     * 
     * @return El código hash basado en nombre y precio
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio);
    }
}

