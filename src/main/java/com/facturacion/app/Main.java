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
 * Clase principal que permite ejecutar el sistema en modo demostración o modo interactivo.
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es iniciar la aplicación y delegar al modo correspondiente
 * (demostración o interactivo).
 * 
 * <p>Uso:
 * <ul>
 *   <li>Sin argumentos o con "--demo": Ejecuta una demostración predefinida</li>
 *   <li>Con "--interactive" o "-i": Inicia el modo interactivo de consola</li>
 * </ul>
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal que ejecuta el sistema según los argumentos proporcionados.
     * 
     * @param args Argumentos de línea de comandos:
     *             - Sin argumentos o "--demo": Modo demostración
     *             - "--interactive" o "-i": Modo interactivo
     */
    public static void main(String[] args) {
        // Determinar el modo de ejecución
        boolean modoInteractivo = false;
        
        if (args.length > 0) {
            String primerArg = args[0].toLowerCase();
            modoInteractivo = primerArg.equals("--interactive") || 
                            primerArg.equals("-i") ||
                            primerArg.equals("--interactivo");
        }
        
        if (modoInteractivo) {
            // Modo interactivo
            ConsolaInteractiva consola = new ConsolaInteractiva();
            consola.iniciar();
        } else {
            // Modo demostración
            ejecutarDemostracion();
        }
    }
    
    /**
     * Ejecuta una demostración predefinida del sistema.
     * 
     * <p>Esta demostración crea productos de diferentes categorías, configura reglas de impuestos
     * y genera una factura con el cálculo correspondiente, demostrando cómo el diseño
     * SOLID permite un sistema flexible y extensible.
     */
    private static void ejecutarDemostracion() {
        System.out.println("=== Sistema de Facturación SOLID ===\n");
        System.out.println("Modo: Demostración");
        System.out.println("(Use --interactive o -i para modo interactivo)\n");
        
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

