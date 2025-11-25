package com.facturacion.impuesto;

import com.facturacion.model.Producto;
import java.util.Objects;

/**
 * Implementación del impuesto IVA para productos alimenticios (5%).
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es calcular el IVA del 5% para productos alimenticios.
 * 
 * <p>Cumple con <strong>Open/Closed Principle (OCP)</strong>: Se puede agregar esta
 * implementación sin modificar el código existente. Además, implementa <strong>Liskov
 * Substitution Principle (LSP)</strong> al poder sustituir a ImpuestoAlimento sin
 * alterar el comportamiento esperado.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class IVAAlimento implements ImpuestoAlimento {
    
    /**
     * Porcentaje de IVA para alimentos: 5%
     */
    private static final double PORCENTAJE_IVA = 5.0;
    
    /**
     * Calcula el IVA del 5% sobre el precio del producto alimenticio.
     * 
     * @param producto El producto alimenticio. No puede ser null.
     * @return El monto del IVA calculado (precio * 0.05)
     * @throws NullPointerException si producto es null
     */
    @Override
    public double calcularImpuesto(Producto producto) {
        Objects.requireNonNull(producto, "El producto no puede ser null");
        return producto.getPrecio() * (PORCENTAJE_IVA / 100.0);
    }
    
    /**
     * Obtiene el porcentaje de IVA aplicado a alimentos.
     * 
     * @return 5.0 (representando 5%)
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
        return String.format("IVA Alimento (%.1f%%)", PORCENTAJE_IVA);
    }
}

