package com.facturacion.impuesto;

import com.facturacion.model.Producto;

/**
 * Interfaz base para el cálculo de impuestos sobre productos.
 * 
 * <p>Esta interfaz implementa el principio de <strong>Dependency Inversion Principle (DIP)</strong>:
 * Las clases de alto nivel (como Factura) dependen de esta abstracción en lugar de
 * depender de implementaciones concretas. También cumple con <strong>Interface Segregation
 * Principle (ISP)</strong> al definir una interfaz específica y cohesiva para el cálculo
 * de impuestos, sin métodos innecesarios.
 * 
 * <p>El diseño permite agregar nuevos tipos de impuestos sin modificar el código existente,
 * cumpliendo con el <strong>Open/Closed Principle (OCP)</strong>.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public interface Impuesto {
    
    /**
     * Calcula el impuesto a aplicar sobre un producto.
     * 
     * @param producto El producto sobre el cual calcular el impuesto. No puede ser null.
     * @return El monto del impuesto calculado. Debe ser >= 0.
     * @throws NullPointerException si producto es null
     */
    double calcularImpuesto(Producto producto);
    
    /**
     * Obtiene el porcentaje de impuesto aplicado.
     * 
     * @return El porcentaje de impuesto (ej: 19.0 para 19%)
     */
    double getPorcentaje();
}

