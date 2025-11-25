# Sistema de Facturación SOLID

Sistema de facturación desarrollado en Java que implementa los principios SOLID para calcular impuestos por categoría de productos (Alimentos, Ropa, Electrónica).

## Resumen del Proyecto

Este proyecto demuestra la aplicación práctica de los principios SOLID en el diseño de un sistema de facturación. El sistema permite calcular impuestos diferenciados por categoría de productos, siendo extensible para agregar nuevas categorías e impuestos sin modificar el código existente.

### Características Principales

- Cálculo de impuestos por categoría (Alimentos: 5%, Ropa: 19%, Electrónica: 25%)
- Diseño extensible siguiendo principios SOLID
- Inyección de dependencias por constructor
- **Modo interactivo de consola** para probar el sistema en tiempo real
- Modo demostración predefinido
- Tests unitarios completos con JUnit 5 y Mockito
- Documentación JavaDoc exhaustiva
- Manejo de errores robusto

## Requisitos

- Java 17 o superior
- Maven 3.6+ (para compilar y ejecutar tests)

## Compilación y Ejecución

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar tests

```bash
mvn test
```

### Empaquetar el proyecto

```bash
mvn clean package
```

### Ejecutar la aplicación

Después de empaquetar, ejecuta:

**Modo Demostración** (por defecto):
```bash
java -cp target/sistema-facturacion-solid-1.0-SNAPSHOT.jar com.facturacion.app.Main
```

**Modo Interactivo** (consola):
```bash
java -cp target/sistema-facturacion-solid-1.0-SNAPSHOT.jar com.facturacion.app.Main --interactive
```

O usando la forma corta:
```bash
java -cp target/sistema-facturacion-solid-1.0-SNAPSHOT.jar com.facturacion.app.Main -i
```

#### Modo Interactivo

El modo interactivo te permite:
- Agregar productos de diferentes categorías (Alimentos, Ropa, Electrónica)
- Ver resumen de productos agregados
- Calcular subtotal, impuestos y total
- Imprimir factura completa formateada
- Limpiar factura y empezar de nuevo
- Ver ayuda del sistema

**Menú del modo interactivo:**
```
--- MENÚ PRINCIPAL ---
1. Agregar producto a la factura
2. Ver resumen de productos
3. Calcular total
4. Imprimir factura completa
5. Limpiar factura (empezar de nuevo)
6. Ayuda
0. Salir
```

## Salida Esperada

Al ejecutar `Main`, deberías ver una salida similar a:

```
=== Sistema de Facturación SOLID ===

Productos agregados a la factura:
  - [Alimento] Pan Integral - $5000.00
  - [Alimento] Leche Entera 1L - $3500.00
  - [Ropa] Camiseta Algodón - $25000.00
  - [Ropa] Pantalón Jeans - $80000.00
  - [Electrónica] Laptop 15" - $1500000.00
  - [Electrónica] Mouse Inalámbrico - $45000.00

Cálculos de la factura:
  Subtotal: $1665500.00
  Total Impuestos: $395175.00
  TOTAL: $2060675.00

========================================
          FACTURA DE VENTA
========================================

PRODUCTOS:
----------------------------------------
  [Alimento] Pan Integral - $5000.00     $   5000.00
    Impuesto (5.0%)                      $    250.00
    Subtotal                             $   5250.00

  [Alimento] Leche Entera 1L - $3500.00  $   3500.00
    Impuesto (5.0%)                      $    175.00
    Subtotal                             $   3675.00

  [Ropa] Camiseta Algodón - $25000.00    $  25000.00
    Impuesto (19.0%)                     $   4750.00
    Subtotal                             $  29750.00

  [Ropa] Pantalón Jeans - $80000.00      $  80000.00
    Impuesto (19.0%)                     $  15200.00
    Subtotal                             $  95200.00

  [Electrónica] Laptop 15" - $1500000.00  $1500000.00
    Impuesto (25.0%)                     $ 375000.00
    Subtotal                             $1875000.00

  [Electrónica] Mouse Inalámbrico - $45000.00 $  45000.00
    Impuesto (25.0%)                     $  11250.00
    Subtotal                             $  56250.00

----------------------------------------
SUBTOTAL:                             $1665500.00
TOTAL IMPUESTOS:                      $ 395175.00
----------------------------------------
TOTAL:                                $2060675.00
========================================

=== Demostración completada ===
```

## Estructura del Proyecto

```
sistema_facturacion_solid/
├── pom.xml
├── README.md
├── LICENSE
├── CONTRIBUTING.md
├── .gitignore
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── facturacion/
│   │               ├── app/
│   │               │   ├── Main.java
│   │               │   └── ConsolaInteractiva.java
│   │               ├── model/
│   │               │   ├── Producto.java
│   │               │   ├── ProductoAlimento.java
│   │               │   ├── ProductoRopa.java
│   │               │   └── ProductoElectronico.java
│   │               ├── impuesto/
│   │               │   ├── Impuesto.java
│   │               │   ├── ImpuestoAlimento.java
│   │               │   ├── ImpuestoRopa.java
│   │               │   ├── ImpuestoElectronico.java
│   │               │   ├── IVAAlimento.java
│   │               │   ├── IVARopa.java
│   │               │   └── IVAElectronico.java
│   │               └── factura/
│   │                   ├── Factura.java
│   │                   ├── InvoicePrinter.java
│   │                   └── SimpleInvoicePrinter.java
│   └── test/
│       └── java/
│           └── com/
│               └── facturacion/
│                   ├── impuesto/
│                   │   ├── IVAAlimentoTest.java
│                   │   ├── IVARopaTest.java
│                   │   └── IVAElectronicoTest.java
│                   ├── factura/
│                   │   └── FacturaTest.java
│                   └── integration/
│                       └── FacturaIntegrationTest.java
└── diagramas/
    └── diagrama-clases.puml
```

## Principios SOLID Implementados

### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad:
- `Producto`: Representar información básica de un producto
- `IVAAlimento`, `IVARopa`, `IVAElectronico`: Calcular impuestos específicos
- `Factura`: Gestionar productos y calcular totales
- `SimpleInvoicePrinter`: Formatear e imprimir facturas

### Open/Closed Principle (OCP)
El sistema está abierto para extensión pero cerrado para modificación:
- Se pueden agregar nuevas categorías de productos (ej: `ProductoLibro`) sin modificar `Factura`
- Se pueden agregar nuevos tipos de impuestos (ej: `ImpuestoEspecial`) sin modificar código existente
- Las reglas de impuesto se inyectan mediante un `Map`, permitiendo configuración flexible

### Liskov Substitution Principle (LSP)
Las subclases pueden sustituir a sus clases base sin alterar el comportamiento:
- `ProductoAlimento`, `ProductoRopa`, `ProductoElectronico` pueden usarse donde se espera `Producto`
- Cualquier implementación de `Impuesto` puede sustituir a la interfaz base
- `SimpleInvoicePrinter` puede sustituir a `InvoicePrinter`

### Interface Segregation Principle (ISP)
Las interfaces son específicas y cohesivas:
- `Impuesto` define solo métodos necesarios para calcular impuestos
- `ImpuestoAlimento`, `ImpuestoRopa`, `ImpuestoElectronico` son interfaces específicas
- `InvoicePrinter` tiene solo métodos relacionados con la impresión

### Dependency Inversion Principle (DIP)
Las clases de alto nivel dependen de abstracciones:
- `Factura` depende de la interfaz `Impuesto`, no de implementaciones concretas
- La inyección de dependencias se realiza por constructor
- `SimpleInvoicePrinter` puede recibir cualquier `PrintStream` (abstracción)

## Tests

El proyecto incluye tests unitarios completos:

- **Tests de Impuestos**: Verifican el cálculo correcto de cada tipo de IVA
- **Tests de Factura**: Verifican agregado de productos, cálculos y manejo de errores
- **Test de Integración**: Verifica el funcionamiento completo del sistema con implementaciones reales
- **Uso de Mockito**: Se utiliza para simular impuestos en pruebas de `Factura`

Ejecutar todos los tests:

```bash
mvn test
```

## Referencias

- [Principios SOLID](https://en.wikipedia.org/wiki/SOLID)
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)