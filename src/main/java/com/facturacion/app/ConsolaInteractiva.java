package com.facturacion.app;

import com.facturacion.factura.Factura;
import com.facturacion.factura.InvoicePrinter;
import com.facturacion.factura.SimpleInvoicePrinter;
import com.facturacion.impuesto.IVAAlimento;
import com.facturacion.impuesto.IVAElectronico;
import com.facturacion.impuesto.IVARopa;
import com.facturacion.model.Producto;
import com.facturacion.model.ProductoAlimento;
import com.facturacion.model.ProductoElectronico;
import com.facturacion.model.ProductoRopa;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase que proporciona una interfaz de consola interactiva para el sistema de facturación.
 * 
 * <p>Esta clase implementa el principio de <strong>Single Responsibility Principle (SRP)</strong>:
 * Su única responsabilidad es manejar la interacción con el usuario a través de la consola.
 * 
 * <p>Cumple con <strong>Dependency Inversion Principle (DIP)</strong> al depender de las
 * abstracciones (Factura, InvoicePrinter) en lugar de implementaciones concretas.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
public class ConsolaInteractiva {
    
    /**
     * Scanner para leer entrada del usuario.
     */
    private final Scanner scanner;
    
    /**
     * Factura que se está construyendo.
     */
    private Factura factura;
    
    /**
     * Mapa de reglas de impuesto configuradas.
     */
    private final Map<Class<? extends Producto>, com.facturacion.impuesto.Impuesto> reglasImpuesto;
    
    /**
     * Constructor que inicializa la consola interactiva.
     */
    public ConsolaInteractiva() {
        this.scanner = new Scanner(System.in);
        this.reglasImpuesto = inicializarReglasImpuesto();
    }
    
    /**
     * Inicializa las reglas de impuesto del sistema.
     * 
     * @return Mapa con las reglas de impuesto configuradas
     */
    private Map<Class<? extends Producto>, com.facturacion.impuesto.Impuesto> inicializarReglasImpuesto() {
        Map<Class<? extends Producto>, com.facturacion.impuesto.Impuesto> reglas = new HashMap<>();
        reglas.put(ProductoAlimento.class, new IVAAlimento());
        reglas.put(ProductoRopa.class, new IVARopa());
        reglas.put(ProductoElectronico.class, new IVAElectronico());
        return reglas;
    }
    
    /**
     * Inicia la sesión interactiva de facturación.
     */
    public void iniciar() {
        System.out.println("========================================");
        System.out.println("   Sistema de Facturación SOLID");
        System.out.println("   Modo Interactivo");
        System.out.println("========================================");
        System.out.println();
        
        // Crear nueva factura
        factura = new Factura(reglasImpuesto);
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    mostrarResumen();
                    break;
                case 3:
                    calcularYMostrarTotal();
                    break;
                case 4:
                    imprimirFactura();
                    break;
                case 5:
                    limpiarFactura();
                    break;
                case 6:
                    mostrarAyuda();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\n¡Gracias por usar el Sistema de Facturación SOLID!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Por favor, seleccione una opción del menú.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal de opciones.
     */
    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Agregar producto a la factura");
        System.out.println("2. Ver resumen de productos");
        System.out.println("3. Calcular total");
        System.out.println("4. Imprimir factura completa");
        System.out.println("5. Limpiar factura (empezar de nuevo)");
        System.out.println("6. Ayuda");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");
    }
    
    /**
     * Lee la opción seleccionada por el usuario.
     * 
     * @return La opción seleccionada, o -1 si hay error
     */
    private int leerOpcion() {
        try {
            String entrada = scanner.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Permite al usuario agregar un producto a la factura.
     */
    private void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.println("Seleccione la categoría del producto:");
        System.out.println("1. Alimento (IVA: 5%)");
        System.out.println("2. Ropa (IVA: 19%)");
        System.out.println("3. Electrónica (IVA: 25%)");
        System.out.println("0. Cancelar");
        System.out.print("\nOpción: ");
        
        int categoria = leerOpcion();
        
        if (categoria == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        if (categoria < 1 || categoria > 3) {
            System.out.println("❌ Categoría no válida.");
            return;
        }
        
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }
        
        System.out.print("Ingrese el precio del producto: ");
        String precioStr = scanner.nextLine().trim();
        
        try {
            double precio = Double.parseDouble(precioStr);
            
            if (precio < 0) {
                System.out.println("❌ El precio no puede ser negativo.");
                return;
            }
            
            Producto producto = crearProducto(categoria, nombre, precio);
            
            if (producto != null) {
                factura.agregarProducto(producto);
                System.out.println("\n✅ Producto agregado exitosamente:");
                System.out.println("   " + producto);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El precio debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Crea un producto según la categoría seleccionada.
     * 
     * @param categoria La categoría del producto (1: Alimento, 2: Ropa, 3: Electrónica)
     * @param nombre El nombre del producto
     * @param precio El precio del producto
     * @return El producto creado, o null si hay error
     */
    private Producto crearProducto(int categoria, String nombre, double precio) {
        return switch (categoria) {
            case 1 -> new ProductoAlimento(nombre, precio);
            case 2 -> new ProductoRopa(nombre, precio);
            case 3 -> new ProductoElectronico(nombre, precio);
            default -> null;
        };
    }
    
    /**
     * Muestra un resumen de los productos agregados a la factura.
     */
    private void mostrarResumen() {
        System.out.println("\n--- RESUMEN DE PRODUCTOS ---");
        
        if (factura.getCantidadProductos() == 0) {
            System.out.println("No hay productos en la factura.");
            return;
        }
        
        System.out.println("Productos en la factura (" + factura.getCantidadProductos() + "):");
        System.out.println();
        
        int contador = 1;
        for (Producto producto : factura.getProductos()) {
            System.out.printf("%d. %s%n", contador++, producto);
        }
        
        System.out.println();
        System.out.printf("Subtotal: $%.2f%n", factura.calcularSubtotal());
    }
    
    /**
     * Calcula y muestra el total de la factura.
     */
    private void calcularYMostrarTotal() {
        System.out.println("\n--- CÁLCULO DE TOTAL ---");
        
        if (factura.getCantidadProductos() == 0) {
            System.out.println("No hay productos en la factura para calcular.");
            return;
        }
        
        try {
            double subtotal = factura.calcularSubtotal();
            double impuestos = factura.calcularTotalImpuestos();
            double total = factura.calcularTotal();
            
            System.out.println();
            System.out.printf("Subtotal:              $%15.2f%n", subtotal);
            System.out.printf("Total Impuestos:       $%15.2f%n", impuestos);
            System.out.println("----------------------------------------");
            System.out.printf("TOTAL:                 $%15.2f%n", total);
            
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
            System.out.println("Asegúrese de que todos los productos tengan una regla de impuesto configurada.");
        }
    }
    
    /**
     * Imprime la factura completa usando InvoicePrinter.
     */
    private void imprimirFactura() {
        System.out.println("\n--- IMPRIMIR FACTURA ---");
        
        if (factura.getCantidadProductos() == 0) {
            System.out.println("No hay productos en la factura para imprimir.");
            return;
        }
        
        try {
            InvoicePrinter printer = new SimpleInvoicePrinter();
            printer.imprimir(factura, reglasImpuesto);
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
            System.out.println("Asegúrese de que todos los productos tengan una regla de impuesto configurada.");
        }
    }
    
    /**
     * Limpia la factura actual y crea una nueva.
     */
    private void limpiarFactura() {
        System.out.println("\n--- LIMPIAR FACTURA ---");
        System.out.print("¿Está seguro de que desea limpiar la factura? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("y") || confirmacion.equals("yes")) {
            factura = new Factura(reglasImpuesto);
            System.out.println("✅ Factura limpiada. Puede comenzar a agregar productos nuevamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    /**
     * Muestra información de ayuda sobre el sistema.
     */
    private void mostrarAyuda() {
        System.out.println("\n--- AYUDA ---");
        System.out.println("Sistema de Facturación SOLID - Modo Interactivo");
        System.out.println();
        System.out.println("Este sistema permite crear facturas agregando productos de diferentes categorías:");
        System.out.println("  • Alimentos: IVA del 5%");
        System.out.println("  • Ropa: IVA del 19%");
        System.out.println("  • Electrónica: IVA del 25%");
        System.out.println();
        System.out.println("Funcionalidades:");
        System.out.println("  1. Agregar producto: Permite agregar productos a la factura");
        System.out.println("  2. Ver resumen: Muestra todos los productos agregados");
        System.out.println("  3. Calcular total: Calcula subtotal, impuestos y total");
        System.out.println("  4. Imprimir factura: Muestra la factura completa formateada");
        System.out.println("  5. Limpiar factura: Reinicia la factura actual");
        System.out.println("  6. Ayuda: Muestra esta información");
        System.out.println("  0. Salir: Termina la sesión");
        System.out.println();
        System.out.println("Nota: Los precios deben ser números positivos válidos.");
    }
}

