package com.facturacion.factura;

import com.facturacion.impuesto.Impuesto;
import com.facturacion.model.Producto;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Interfaz para imprimir facturas en diferentes formatos.
 * 
 * <p>Esta interfaz implementa el principio de <strong>Interface Segregation Principle (ISP)</strong>:
 * Define una interfaz específica y cohesiva para la impresión de facturas, sin métodos
 * innecesarios. También cumple con <strong>Dependency Inversion Principle (DIP)</strong>
 * al ser una abstracción que puede tener múltiples implementaciones.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public interface InvoicePrinter {
    
    /**
     * Imprime el detalle completo de una factura.
     * 
     * @param factura La factura a imprimir. No puede ser null.
     * @param reglasImpuesto Mapa de reglas de impuesto para mostrar detalles. No puede ser null.
     * @throws NullPointerException si factura o reglasImpuesto son null
     */
    void imprimir(Factura factura, java.util.Map<Class<? extends Producto>, Impuesto> reglasImpuesto);
}

