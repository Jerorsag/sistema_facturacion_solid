package com.facturacion.impuesto;

import com.facturacion.model.Producto;
import java.util.Objects;

/**
 * Implementación del impuesto IVA para productos electrónicos (25%).
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es calcular el IVA del 25% para productos electrónicos.
 * 
 * <p>Cumple con <strong>Open/Closed Principle (OCP)</strong>: Se puede agregar esta
 * implementación sin modificar el código existente. Además, implementa <strong>Liskov
 * Substitution Principle (LSP)</strong> al poder sustituir a ImpuestoElectronico sin
 * alterar el comportamiento esperado.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class IVAElectronico implements ImpuestoElectronico {
    
    /**
     * Porcentaje de IVA para electrónicos: 25%
     */
    private static final double PORCENTAJE_IVA = 25.0;
    
    /**
     * Calcula el IVA del 25% sobre el precio del producto electrónico.
     * 
     * @param producto El producto electrónico. No puede ser null.
     * @return El monto del IVA calculado (precio * 0.25)
     * @throws NullPointerException si producto es null
     */
    @Override
    public double calcularImpuesto(Producto producto) {
        Objects.requireNonNull(producto, "El producto no puede ser null");
        return producto.getPrecio() * (PORCENTAJE_IVA / 100.0);
    }
    
    /**
     * Obtiene el porcentaje de IVA aplicado a electrónicos.
     * 
     * @return 25.0 (representando 25%)
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
        return String.format("IVA Electrónico (%.1f%%)", PORCENTAJE_IVA);
    }
}

