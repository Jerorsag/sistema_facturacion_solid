package com.facturacion.impuesto;

import com.facturacion.model.ProductoElectronico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase IVAElectronico.
 * 
 * @author Sistema de Facturación SOLID
 * @version 1.0
 */
@DisplayName("Tests para IVAElectronico")
class IVAElectronicoTest {
    
    private IVAElectronico ivaElectronico;
    
    @BeforeEach
    void setUp() {
        ivaElectronico = new IVAElectronico();
    }
    
    @Test
    @DisplayName("Debería calcular el 25% de IVA correctamente")
    void deberiaCalcular25PorcientoIVA() {
        // Arrange
        ProductoElectronico producto = new ProductoElectronico("Laptop", 1000000.0);
        double impuestoEsperado = 250000.0; // 25% de 1000000
        
        // Act
        double impuestoCalculado = ivaElectronico.calcularImpuesto(producto);
        
        // Assert
        assertEquals(impuestoEsperado, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería retornar 0 para producto con precio 0")
    void deberiaRetornarCeroParaPrecioCero() {
        // Arrange
        ProductoElectronico producto = new ProductoElectronico("Producto Gratis", 0.0);
        
        // Act
        double impuestoCalculado = ivaElectronico.calcularImpuesto(producto);
        
        // Assert
        assertEquals(0.0, impuestoCalculado, 0.01);
    }
    
    @Test
    @DisplayName("Debería lanzar NullPointerException si el producto es null")
    void deberiaLanzarExcepcionSiProductoEsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ivaElectronico.calcularImpuesto(null);
        });
    }
    
    @Test
    @DisplayName("Debería retornar el porcentaje correcto (25%)")
    void deberiaRetornarPorcentajeCorrecto() {
        // Act
        double porcentaje = ivaElectronico.getPorcentaje();
        
        // Assert
        assertEquals(25.0, porcentaje, 0.01);
    }
}

