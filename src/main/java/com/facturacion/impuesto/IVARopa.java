package com.facturacion.impuesto;

import com.facturacion.model.Producto;
import java.util.Objects;

/**
 * Implementación del impuesto IVA para productos de ropa (19%).
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es calcular el IVA del 19% para productos de ropa.
 * 
 * <p>Cumple con <strong>Open/Closed Principle (OCP)</strong>: Se puede agregar esta
 * implementación sin modificar el código existente. Además, implementa <strong>Liskov
 * Substitution Principle (LSP)</strong> al poder sustituir a ImpuestoRopa sin
 * alterar el comportamiento esperado.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class IVARopa implements ImpuestoRopa {
    
    /**
     * Porcentaje de IVA para ropa: 19%
     */
    private static final double PORCENTAJE_IVA = 19.0;
    
    /**
     * Calcula el IVA del 19% sobre el precio del producto de ropa.
     * 
     * @param producto El producto de ropa. No puede ser null.
     * @return El monto del IVA calculado (precio * 0.19)
     * @throws NullPointerException si producto es null
     */
    @Override
    public double calcularImpuesto(Producto producto) {
        Objects.requireNonNull(producto, "El producto no puede ser null");
        return producto.getPrecio() * (PORCENTAJE_IVA / 100.0);
    }
    
    /**
     * Obtiene el porcentaje de IVA aplicado a ropa.
     * 
     * @return 19.0 (representando 19%)
     */
    @Override
    public double getPorcentaje() {
        return PORCENTAJE_IVA;
    }
    
    /**
     * Representación en cadena del impuesto.
     * 
     * @return Una cadena con el porcentaje de IVA
     */
    @Override
    public String toString() {
        return String.format("IVA Ropa (%.1f%%)", PORCENTAJE_IVA);
    }
}

