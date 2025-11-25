package com.facturacion.impuesto;

/**
 * Interfaz específica para impuestos aplicables a productos de ropa.
 * 
 * <p>Esta interfaz implementa el principio de <strong>Interface Segregation Principle (ISP)</strong>:
 * Define una interfaz específica para productos de ropa, evitando que las clases
 * implementen métodos que no necesitan. Permite extender Impuesto con funcionalidades
 * específicas para ropa si fuera necesario en el futuro.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public interface ImpuestoRopa extends Impuesto {
    // Esta interfaz puede extenderse en el futuro con métodos específicos para ropa
    // Por ahora, hereda todos los métodos de Impuesto
}

