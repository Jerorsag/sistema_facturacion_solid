package com.facturacion.factura;

import com.facturacion.impuesto.Impuesto;
import com.facturacion.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Clase que representa una factura con capacidad de calcular impuestos por categoría de productos.
 * 
 * <p>Esta clase implementa múltiples principios SOLID:
 * 
 * <ul>
 *   <li><strong>Single Responsibility Principle (SRP)</strong>: Su única responsabilidad es
 *       gestionar productos y calcular el total de la factura con impuestos.</li>
 *   <li><strong>Open/Closed Principle (OCP)</strong>: Permite agregar nuevas categorías de productos
 *       e impuestos sin modificar esta clase, mediante el Map de reglas de impuestos.</li>
 *   <li><strong>Dependency Inversion Principle (DIP)</strong>: Depende de la abstracción Impuesto
 *       en lugar de implementaciones concretas. La inyección de dependencias se realiza por
 *       constructor.</li>
 *   <li><strong>Liskov Substitution Principle (LSP)</strong>: Acepta cualquier subclase de Producto
 *       y cualquier implementación de Impuesto sin alterar su comportamiento.</li>
 * </ul>
 * 
 * <p>La factura utiliza inyección de dependencias por constructor para recibir las reglas de
 * impuestos, lo que permite una alta flexibilidad y testabilidad.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class Factura {
    
    /**
     * Lista de productos en la factura.
     */
    private final List<Producto> productos;
    
    /**
     * Mapa que relaciona clases de productos con sus respectivas reglas de impuesto.
     * La clave es la clase del producto, el valor es la implementación de Impuesto a aplicar.
     */
    private final Map<Class<? extends Producto>, Impuesto> reglasImpuesto;
    
    /**
     * Constructor que inicializa una factura con reglas de impuestos.
     * 
     * <p>La inyección de dependencias por constructor permite que la factura sea flexible
     * y testeable, cumpliendo con DIP.
     * 
     * @param reglasImpuesto Mapa que relaciona clases de productos con sus impuestos.
     *                       No puede ser null, pero puede estar vacío.
     * @throws NullPointerException si reglasImpuesto es null
     */
    public Factura(Map<Class<? extends Producto>, Impuesto> reglasImpuesto) {
        this.reglasImpuesto = Objects.requireNonNull(reglasImpuesto, 
            "Las reglas de impuesto no pueden ser null");
        this.productos = new ArrayList<>();
    }
    
    /**
     * Agrega un producto a la factura.
     * 
     * @param producto El producto a agregar. No puede ser null.
     * @throws NullPointerException si producto es null
     */
    public void agregarProducto(Producto producto) {
        productos.add(Objects.requireNonNull(producto, "El producto no puede ser null"));
    }
    
    /**
     * Calcula el subtotal de la factura (suma de precios sin impuestos).
     * 
     * @return El subtotal de la factura
     */
    public double calcularSubtotal() {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
    
    /**
     * Calcula el total de impuestos aplicando las reglas correspondientes a cada producto.
     * 
     * <p>Si un producto no tiene una regla de impuesto registrada, se lanza una excepción.
     * 
     * @return El total de impuestos calculados
     * @throws IllegalStateException si algún producto no tiene una regla de impuesto registrada
     */
    public double calcularTotalImpuestos() {
        double totalImpuestos = 0.0;
        
        for (Producto producto : productos) {
            Class<? extends Producto> claseProducto = producto.getClass();
            Impuesto impuesto = reglasImpuesto.get(claseProducto);
            
            if (impuesto == null) {
                throw new IllegalStateException(
                    String.format("No existe una regla de impuesto para la clase de producto: %s", 
                        claseProducto.getSimpleName()));
            }
            
            totalImpuestos += impuesto.calcularImpuesto(producto);
        }
        
        return totalImpuestos;
    }
    
    /**
     * Calcula el total de la factura (subtotal + impuestos).
     * 
     * @return El total de la factura
     * @throws IllegalStateException si algún producto no tiene una regla de impuesto registrada
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularTotalImpuestos();
    }
    
    /**
     * Obtiene la lista de productos en la factura.
     * 
     * @return Una copia de la lista de productos (para mantener la inmutabilidad)
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
    
    /**
     * Obtiene el número de productos en la factura.
     * 
     * @return La cantidad de productos
     */
    public int getCantidadProductos() {
        return productos.size();
    }
    
    /**
     * Representación en cadena de la factura.
     * 
     * @return Una cadena con la información de la factura
     */
    @Override
    public String toString() {
        return String.format("Factura con %d producto(s)", productos.size());
    }
}

