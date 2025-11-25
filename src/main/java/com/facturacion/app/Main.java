package com.facturacion.app;

import com.facturacion.factura.Factura;
import com.facturacion.factura.InvoicePrinter;
import com.facturacion.factura.SimpleInvoicePrinter;
import com.facturacion.impuesto.IVAAlimento;
import com.facturacion.impuesto.IVAElectronico;
import com.facturacion.impuesto.IVARopa;
import com.facturacion.model.ProductoAlimento;
import com.facturacion.model.ProductoElectronico;
import com.facturacion.model.ProductoRopa;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal que demuestra el funcionamiento del sistema de facturación.
 * 
 * <p>Esta clase crea productos de diferentes categorías, configura reglas de impuestos
 * y genera una factura con el cálculo correspondiente, demostrando cómo el diseño
 * SOLID permite un sistema flexible y extensible.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal que ejecuta la demostración del sistema.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== Sistema de Facturación SOLID ===\n");
        
        // Crear productos de diferentes categorías
        // Esto demuestra LSP: todas las subclases pueden usarse como Producto
        var pan = new ProductoAlimento("Pan Integral", 5000.0);
        var leche = new ProductoAlimento("Leche Entera 1L", 3500.0);
        var camiseta = new ProductoRopa("Camiseta Algodón", 25000.0);
        var pantalon = new ProductoRopa("Pantalón Jeans", 80000.0);
        var laptop = new ProductoElectronico("Laptop 15\"", 1500000.0);
        var mouse = new ProductoElectronico("Mouse Inalámbrico", 45000.0);
        
        // Configurar reglas de impuestos
        // Esto demuestra OCP: podemos agregar nuevas reglas sin modificar Factura
        // Y DIP: Factura depende de la abstracción Impuesto, no de implementaciones concretas
        Map<Class<? extends com.facturacion.model.Producto>, com.facturacion.impuesto.Impuesto> reglasImpuesto = 
            new HashMap<>();
        reglasImpuesto.put(ProductoAlimento.class, new IVAAlimento());
        reglasImpuesto.put(ProductoRopa.class, new IVARopa());
        reglasImpuesto.put(ProductoElectronico.class, new IVAElectronico());
        
        // Crear factura con inyección de dependencias por constructor (DIP)
        Factura factura = new Factura(reglasImpuesto);
        
        // Agregar productos a la factura
        factura.agregarProducto(pan);
        factura.agregarProducto(leche);
        factura.agregarProducto(camiseta);
        factura.agregarProducto(pantalon);
        factura.agregarProducto(laptop);
        factura.agregarProducto(mouse);
        
        // Calcular y mostrar resultados
        System.out.println("Productos agregados a la factura:");
        factura.getProductos().forEach(p -> System.out.println("  - " + p));
        System.out.println();
        
        // Mostrar cálculos
        System.out.println("Cálculos de la factura:");
        System.out.printf("  Subtotal: $%.2f%n", factura.calcularSubtotal());
        System.out.printf("  Total Impuestos: $%.2f%n", factura.calcularTotalImpuestos());
        System.out.printf("  TOTAL: $%.2f%n", factura.calcularTotal());
        System.out.println();
        
        // Imprimir factura detallada usando InvoicePrinter (ISP y DIP)
        InvoicePrinter printer = new SimpleInvoicePrinter();
        printer.imprimir(factura, reglasImpuesto);
        
        System.out.println("\n=== Demostración completada ===");
    }
}

