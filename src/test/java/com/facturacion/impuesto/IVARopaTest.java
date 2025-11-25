package com.facturacion.impuesto;

import com.facturacion.model.ProductoRopa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase IVARopa.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
@DisplayName("Tests para IVARopa")
class IVARopaTest {
    
    private IVARopa ivaRopa;
    
    @BeforeEach
    void setUp() {
        ivaRopa = new IVARopa();
    }
    
    @Test
    @DisplayName("Debería calcular el 19% de IVA correctamente")
    void deberiaCalcular19PorcientoIVA() {
        // Arrange
        ProductoRopa producto = new ProductoRopa("Camiseta", 100000.0);
        double impuestoEsperado = 19000.0; // 19% de 100000
        
        // Act
        double impuestoCalculado = ivaRopa.calcularImpuesto(producto);
        
        // Assert
        assertEquals(impuestoEsperado, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería retornar 0 para producto con precio 0")
    void deberiaRetornarCeroParaPrecioCero() {
        // Arrange
        ProductoRopa producto = new ProductoRopa("Producto Gratis", 0.0);
        
        // Act
        double impuestoCalculado = ivaRopa.calcularImpuesto(producto);
        
        // Assert
        assertEquals(0.0, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería lanzar NullPointerException si el producto es null")
    void deberiaLanzarExcepcionSiProductoEsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ivaRopa.calcularImpuesto(null);
        });
    }
    
    @Test
    @DisplayName("Debería retornar el porcentaje correcto (19%)")
    void deberiaRetornarPorcentajeCorrecto() {
        // Act
        double porcentaje = ivaRopa.getPorcentaje();
        
        // Assert
        assertEquals(19.0, porcentaje, 0.01);
    }
}

