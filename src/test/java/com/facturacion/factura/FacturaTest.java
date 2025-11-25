package com.facturacion.factura;

import com.facturacion.impuesto.Impuesto;
import com.facturacion.impuesto.IVAAlimento;
import com.facturacion.impuesto.IVAElectronico;
import com.facturacion.impuesto.IVARopa;
import com.facturacion.model.ProductoAlimento;
import com.facturacion.model.ProductoElectronico;
import com.facturacion.model.ProductoRopa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para la clase Factura.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
@DisplayName("Tests para Factura")
class FacturaTest {
    
    private Map<Class<? extends com.facturacion.model.Producto>, Impuesto> reglasImpuesto;
    
    @Mock
    private Impuesto impuestoMock;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reglasImpuesto = new HashMap<>();
        reglasImpuesto.put(ProductoAlimento.class, new IVAAlimento());
        reglasImpuesto.put(ProductoRopa.class, new IVARopa());
        reglasImpuesto.put(ProductoElectronico.class, new IVAElectronico());
    }
    
    @Test
    @DisplayName("Debería crear una factura con reglas de impuesto")
    void deberiaCrearFacturaConReglasImpuesto() {
        // Act
        Factura factura = new Factura(reglasImpuesto);
        
        // Assert
        assertNotNull(factura);
        assertEquals(0, factura.getCantidadProductos());
    }
    
    @Test
    @DisplayName("Debería lanzar NullPointerException si reglasImpuesto es null")
    void deberiaLanzarExcepcionSiReglasImpuestoEsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Factura(null);
        });
    }
    
    @Test
    @DisplayName("Debería agregar productos correctamente")
    void deberiaAgregarProductos() {
        // Arrange
        Factura factura = new Factura(reglasImpuesto);
        ProductoAlimento producto = new ProductoAlimento("Pan", 10000.0);
        
        // Act
        factura.agregarProducto(producto);
        
        // Assert
        assertEquals(1, factura.getCantidadProductos());
        assertTrue(factura.getProductos().contains(producto));
    }
    
    @Test
    @DisplayName("Debería lanzar NullPointerException si se intenta agregar producto null")
    void deberiaLanzarExcepcionSiProductoEsNull() {
        // Arrange
        Factura factura = new Factura(reglasImpuesto);
        
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            factura.agregarProducto(null);
        });
    }
    
    @Test
    @DisplayName("Debería calcular el subtotal correctamente")
    void deberiaCalcularSubtotal() {
        // Arrange
        Factura factura = new Factura(reglasImpuesto);
        factura.agregarProducto(new ProductoAlimento("Pan", 10000.0));
        factura.agregarProducto(new ProductoRopa("Camiseta", 50000.0));
        double subtotalEsperado = 60000.0;
        
        // Act
        double subtotal = factura.calcularSubtotal();
        
        // Assert
        assertEquals(subtotalEsperado, subtotal, 0.01);
    }
    
    @Test
    @DisplayName("Debería calcular el total de impuestos correctamente")
    void deberiaCalcularTotalImpuestos() {
        // Arrange
        Factura factura = new Factura(reglasImpuesto);
        factura.agregarProducto(new ProductoAlimento("Pan", 10000.0)); // 5% = 500
        factura.agregarProducto(new ProductoRopa("Camiseta", 100000.0)); // 19% = 19000
        double impuestosEsperados = 500.0 + 19000.0; // 19500
        
        // Act
        double impuestos = factura.calcularTotalImpuestos();
        
        // Assert
        assertEquals(impuestosEsperados, impuestos, 0.01);
    }
    
    @Test
    @DisplayName("Debería calcular el total correctamente")
    void deberiaCalcularTotal() {
        // Arrange
        Factura factura = new Factura(reglasImpuesto);
        factura.agregarProducto(new ProductoAlimento("Pan", 10000.0)); // precio + 5% = 10500
        factura.agregarProducto(new ProductoRopa("Camiseta", 100000.0)); // precio + 19% = 119000
        double totalEsperado = 10500.0 + 119000.0; // 129500
        
        // Act
        double total = factura.calcularTotal();
        
        // Assert
        assertEquals(totalEsperado, total, 0.01);
    }
    
    @Test
    @DisplayName("Debería lanzar IllegalStateException si falta regla de impuesto")
    void deberiaLanzarExcepcionSiFaltaReglaImpuesto() {
        // Arrange
        Map<Class<? extends com.facturacion.model.Producto>, Impuesto> reglasIncompletas = new HashMap<>();
        reglasIncompletas.put(ProductoAlimento.class, new IVAAlimento());
        // No se agrega regla para ProductoRopa
        
        Factura factura = new Factura(reglasIncompletas);
        factura.agregarProducto(new ProductoRopa("Camiseta", 100000.0));
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            factura.calcularTotalImpuestos();
        });
    }
    
    @Test
    @DisplayName("Debería usar Mockito para simular impuestos")
    void deberiaUsarMockitoParaSimularImpuestos() {
        // Arrange
        when(impuestoMock.calcularImpuesto(any())).thenReturn(1000.0);
        when(impuestoMock.getPorcentaje()).thenReturn(10.0);
        
        Map<Class<? extends com.facturacion.model.Producto>, Impuesto> reglasMock = new HashMap<>();
        reglasMock.put(ProductoAlimento.class, impuestoMock);
        
        Factura factura = new Factura(reglasMock);
        ProductoAlimento producto = new ProductoAlimento("Test", 10000.0);
        factura.agregarProducto(producto);
        
        // Act
        double impuestos = factura.calcularTotalImpuestos();
        
        // Assert
        assertEquals(1000.0, impuestos, 0.01);
        verify(impuestoMock, times(1)).calcularImpuesto(producto);
    }
}

