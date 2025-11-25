package com.facturacion.integration;

import com.facturacion.factura.Factura;
import com.facturacion.impuesto.IVAAlimento;
import com.facturacion.impuesto.IVAElectronico;
import com.facturacion.impuesto.IVARopa;
import com.facturacion.model.ProductoAlimento;
import com.facturacion.model.ProductoElectronico;
import com.facturacion.model.ProductoRopa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de integración que verifica el funcionamiento completo del sistema
 * con implementaciones reales de todas las clases.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
@DisplayName("Test de Integración para Factura")
class FacturaIntegrationTest {
    
    private Map<Class<? extends com.facturacion.model.Producto>, com.facturacion.impuesto.Impuesto> reglasImpuesto;
    private Factura factura;
    
    @BeforeEach
    void setUp() {
        // Configurar reglas de impuesto reales (no mocks)
        reglasImpuesto = new HashMap<>();
        reglasImpuesto.put(ProductoAlimento.class, new IVAAlimento());
        reglasImpuesto.put(ProductoRopa.class, new IVARopa());
        reglasImpuesto.put(ProductoElectronico.class, new IVAElectronico());
        
        factura = new Factura(reglasImpuesto);
    }
    
    @Test
    @DisplayName("Debería calcular correctamente una factura completa con múltiples productos")
    void deberiaCalcularFacturaCompleta() {
        // Arrange: Crear productos reales de diferentes categorías
        ProductoAlimento pan = new ProductoAlimento("Pan Integral", 5000.0);
        ProductoAlimento leche = new ProductoAlimento("Leche 1L", 3500.0);
        ProductoRopa camiseta = new ProductoRopa("Camiseta", 25000.0);
        ProductoElectronico laptop = new ProductoElectronico("Laptop", 1500000.0);
        
        // Agregar productos a la factura
        factura.agregarProducto(pan);
        factura.agregarProducto(leche);
        factura.agregarProducto(camiseta);
        factura.agregarProducto(laptop);
        
        // Calcular valores esperados
        double subtotalEsperado = 5000.0 + 3500.0 + 25000.0 + 1500000.0; // 1545500.0
        double impuestoPan = 5000.0 * 0.05; // 250.0
        double impuestoLeche = 3500.0 * 0.05; // 175.0
        double impuestoCamiseta = 25000.0 * 0.19; // 4750.0
        double impuestoLaptop = 1500000.0 * 0.25; // 375000.0
        double totalImpuestosEsperado = impuestoPan + impuestoLeche + impuestoCamiseta + impuestoLaptop; // 380175.0
        double totalEsperado = subtotalEsperado + totalImpuestosEsperado; // 1925675.0
        
        // Act
        double subtotal = factura.calcularSubtotal();
        double totalImpuestos = factura.calcularTotalImpuestos();
        double total = factura.calcularTotal();
        
        // Assert
        assertEquals(subtotalEsperado, subtotal, 0.01, "El subtotal debe ser correcto");
        assertEquals(totalImpuestosEsperado, totalImpuestos, 0.01, "El total de impuestos debe ser correcto");
        assertEquals(totalEsperado, total, 0.01, "El total debe ser correcto");
        assertEquals(4, factura.getCantidadProductos(), "Debe haber 4 productos");
    }
    
    @Test
    @DisplayName("Debería manejar correctamente una factura vacía")
    void deberiaManejarFacturaVacia() {
        // Act
        double subtotal = factura.calcularSubtotal();
        double totalImpuestos = factura.calcularTotalImpuestos();
        double total = factura.calcularTotal();
        
        // Assert
        assertEquals(0.0, subtotal, 0.01);
        assertEquals(0.0, totalImpuestos, 0.01);
        assertEquals(0.0, total, 0.01);
        assertEquals(0, factura.getCantidadProductos());
    }
}

