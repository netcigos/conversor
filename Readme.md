<h1 align="center">Aplicación de Conversión de Divisas</h1>

Esta aplicación, basada en **Java 17**, permite realizar conversiones de divisas en tiempo real para diversas monedas internacionales. Desarrollada utilizando la biblioteca Gson para el manejo de JSON, soporta conversiones entre múltiples pares de divisas y ofrece funcionalidades adicionales como historial de tipos de cambio y soporte para pares personalizados.
Hace uso de las api https://www.exchangerate-api.com/ para la conversion en tiempo real y https://bluelytics.com.ar/ para el dolar blue en Argentina.

## Características

- **Conversiones de Divisas:**
  1. Convertir **USD** a **ARS** (tipo de cambio oficial del BCRA).
  2. Convertir **ARS** (tipo de cambio oficial del BCRA) a **USD**.
  3. Convertir **USD** a **BRL**.
  4. Convertir **BRL** a **USD**.
  5. Convertir **USD** a **PYG**.
  6. Convertir **PYG** a **USD**.
  7. Convertir **USD** a **EUR**.
  8. Convertir **EUR** a **USD**.
  
- **Otras Funcionalidades:**
  9. Ingresar un par de divisas personalizado para conversión.
  10. Ver el historial reciente de conversiones.
  11. Ingresar una divisa de origen y ver tasas de conversión para 161 países disponibles.
  12. Ver la última cotización del **Dólar Blue** en Argentina.
  13. Ver el valor histórico del **Dólar Blue** para un día específico.
  14. Salir de la aplicación.

## Requisitos

- **Java 17** 
- Biblioteca **Gson** para procesamiento de JSON.
- Apis https://www.exchangerate-api.com/ Conversion tiempo real divisas
- Api https://api.bluelytics.com.ar/v2/latest Dolar Blue Argentina 

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone [https://github.com/netcigos/conversor.git]

2. Descargar el archivo Jar de la siguiente direccion:
   www.cigos.com.ar/java/conversormonedas.jar

## Pantallas 
![menu](https://github.com/user-attachments/assets/9132e2c5-f41b-4c4d-b46f-5f7ae56c6e59)
##Ejemplo de Dolar USD a Pesos Argentino
![usdars](https://github.com/user-attachments/assets/9d9ff95c-4758-4784-9ae7-8dbe555ed1cf)
##Historial
![historial](https://github.com/user-attachments/assets/1a7b798c-9b97-4209-9055-86be08b94106)

