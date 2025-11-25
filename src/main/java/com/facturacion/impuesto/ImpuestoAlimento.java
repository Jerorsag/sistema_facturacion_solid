package com.facturacion.impuesto;

import com.facturacion.model.Producto;

/**
 * Interfaz específica para impuestos aplicables a productos alimenticios.
 * 
 * <p>Esta interfaz implementa el principio de <strong>Interface Segregation Principle (ISP)</strong>:
 * Define una interfaz específica para productos alimenticios, evitando que las clases
 * implementen métodos que no necesitan. Permite extender Impuesto con funcionalidades
 * específicas para alimentos si fuera necesario en el futuro.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public interface ImpuestoAlimento extends Impuesto {
    // Esta interfaz puede extenderse en el futuro con métodos específicos para alimentos
    // Por ahora, hereda todos los métodos de Impuesto
}

