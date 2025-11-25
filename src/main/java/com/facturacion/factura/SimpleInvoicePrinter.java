package com.facturacion.factura;

import com.facturacion.impuesto.Impuesto;
import com.facturacion.model.Producto;
import java.io.PrintStream;
import java.util.Map;
import java.util.Objects;

/**
 * Implementación simple de InvoicePrinter que imprime facturas en formato texto plano.
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es formatear e imprimir el detalle de una factura.
 * 
 * <p>Cumple con <strong>Liskov Substitution Principle (LSP)</strong> al poder sustituir
 * a InvoicePrinter sin alterar el comportamiento esperado. También implementa
 * <strong>Open/Closed Principle (OCP)</strong> al permitir agregar nuevas implementaciones
 * de InvoicePrinter sin modificar esta clase.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class SimpleInvoicePrinter implements InvoicePrinter {
    
    /**
     * Stream de salida donde se imprimirá la factura.
     */
    private final PrintStream output;
    
    /**
     * Constructor que inicializa el printer con salida estándar.
     */
    public SimpleInvoicePrinter() {
        this(System.out);
    }
    
    /**
     * Constructor que inicializa el printer con un stream de salida personalizado.
     * 
     * <p>La inyección de dependencias por constructor permite testear fácilmente
     * redirigiendo la salida, cumpliendo con DIP.
     * 
     * @param output El stream de salida. No puede ser null.
     * @throws NullPointerException si output es null
     */
    public SimpleInvoicePrinter(PrintStream output) {
        this.output = Objects.requireNonNull(output, "El stream de salida no puede ser null");
    }
    
    /**
     * Imprime el detalle completo de la factura en formato texto.
     * 
     * @param factura La factura a imprimir. No puede ser null.
     * @param reglasImpuesto Mapa de reglas de impuesto para mostrar detalles. No puede ser null.
     * @throws NullPointerException si factura o reglasImpuesto son null
     */
    @Override
    public void imprimir(Factura factura, Map<Class<? extends Producto>, Impuesto> reglasImpuesto) {
        Objects.requireNonNull(factura, "La factura no puede ser null");
        Objects.requireNonNull(reglasImpuesto, "Las reglas de impuesto no pueden ser null");
        
        output.println("========================================");
        output.println("          FACTURA DE VENTA");
        output.println("========================================");
        output.println();
        
        // Imprimir productos
        output.println("PRODUCTOS:");
        output.println("----------------------------------------");
        
        for (Producto producto : factura.getProductos()) {
            Class<? extends Producto> claseProducto = producto.getClass();
            Impuesto impuesto = reglasImpuesto.get(claseProducto);
            
            double precioBase = producto.getPrecio();
            double montoImpuesto = impuesto != null ? impuesto.calcularImpuesto(producto) : 0.0;
            double precioTotal = precioBase + montoImpuesto;
            
            output.printf("  %-30s $%10.2f\n", producto.toString(), precioBase);
            if (impuesto != null) {
                output.printf("    Impuesto (%.1f%%)              $%10.2f\n", 
                    impuesto.getPorcentaje(), montoImpuesto);
            }
            output.printf("    Subtotal                         $%10.2f\n", precioTotal);
            output.println();
        }
        
        // Imprimir resumen
        output.println("----------------------------------------");
        output.printf("SUBTOTAL:                             $%10.2f\n", factura.calcularSubtotal());
        output.printf("TOTAL IMPUESTOS:                      $%10.2f\n", factura.calcularTotalImpuestos());
        output.println("----------------------------------------");
        output.printf("TOTAL:                                $%10.2f\n", factura.calcularTotal());
        output.println("========================================");
    }
}

