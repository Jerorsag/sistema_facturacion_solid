package com.facturacion.impuesto;

import com.facturacion.model.ProductoAlimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase IVAAlimento.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
@DisplayName("Tests para IVAAlimento")
class IVAAlimentoTest {
    
    private IVAAlimento ivaAlimento;
    
    @BeforeEach
    void setUp() {
        ivaAlimento = new IVAAlimento();
    }
    
    @Test
    @DisplayName("Debería calcular el 5% de IVA correctamente")
    void deberiaCalcular5PorcientoIVA() {
        // Arrange
        ProductoAlimento producto = new ProductoAlimento("Pan", 10000.0);
        double impuestoEsperado = 500.0; // 5% de 10000
        
        // Act
        double impuestoCalculado = ivaAlimento.calcularImpuesto(producto);
        
        // Assert
        assertEquals(impuestoEsperado, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería retornar 0 para producto con precio 0")
    void deberiaRetornarCeroParaPrecioCero() {
        // Arrange
        ProductoAlimento producto = new ProductoAlimento("Producto Gratis", 0.0);
        
        // Act
        double impuestoCalculado = ivaAlimento.calcularImpuesto(producto);
        
        // Assert
        assertEquals(0.0, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería lanzar NullPointerException si el producto es null")
    void deberiaLanzarExcepcionSiProductoEsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ivaAlimento.calcularImpuesto(null);
        });
    }
    
    @Test
    @DisplayName("Debería retornar el porcentaje correcto (5%)")
    void deberiaRetornarPorcentajeCorrecto() {
        // Act
        double porcentaje = ivaAlimento.getPorcentaje();
        
        // Assert
        assertEquals(5.0, porcentaje, 0.01);
    }
}

