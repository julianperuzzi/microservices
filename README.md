
# Microservicios de Productos y Órdenes  
**Proyecto de Julian Peruzzi – DNI 39.955.389**

Este proyecto consiste en una arquitectura basada en microservicios desarrollada con **Spring Boot**, simulando el backend de una tienda virtual similar al sistema de compras de Apple. Se compone de dos microservicios principales: `Product` y `Orders`.

El sistema permite gestionar productos y órdenes de compra, asegurando que las órdenes solo se creen si hay stock disponible. Los servicios se comunican entre sí utilizando HTTP interno a través de `RestTemplate`.

---

## Requisitos del Entorno

- Java 17 o superior  
- PostgreSQL (instalación local o mediante Docker)  
- Docker + Docker Compose (opcional pero recomendado)  
- Postman (para pruebas de endpoints)

---

## Clonación y Configuración del Proyecto

1. Clonar el repositorio:

git clone https://github.com/julianperuzzi/microservices.git



2. Navegar a la raíz del proyecto y levantar las bases de datos:

docker-compose up --build



> Asegurarse de que los puertos y nombres de las bases de datos no estén siendo utilizados por otros proyectos para evitar conflictos.

3. Compilar ambos microservicios utilizando Maven:

mvn clean install -DskipTests


4. Ejecutar los archivos `.jar` generados:

- Para el microservicio Product:

  ```
  java -jar target/Product-0.0.1-SNAPSHOT.jar
  ```

- Para el microservicio Orders:

  ```
  java -jar target/orders-0.0.1-SNAPSHOT.jar
  ```

---

## Microservicio: Product

- **Puerto por defecto:** `8080`
- **Documentación Swagger:**  
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)  
- **Autenticación básica:**  
Usuario: `admin`  
Contraseña: `1234`
- **Funcionalidad:**  
- Crear productos  
- Consultar productos  
- Modificar productos  
- Eliminar productos  
- Controlar stock disponible

---

## Microservicio: Orders

- **Puerto por defecto:** `8084`
- **Documentación Swagger:**  
[http://localhost:8084/swagger-ui/index.html#/](http://localhost:8084/swagger-ui/index.html#/)  
- **Autenticación básica:**  
Usuario: `admin`  
Contraseña: `1234`
- **Funcionalidad:**  
- Crear órdenes únicamente si hay stock suficiente  
- Consultar todas las órdenes creadas

---

## Comunicación entre Microservicios

Para interactuar entre sí, los microservicios utilizan `RestTemplate`. Por ejemplo, cuando se intenta crear una orden, el microservicio `Orders` consulta al `Product` con la siguiente URL:

GET http://localhost:8080/api/products/{id}

Esto permite:

- Verificar que el producto exista.
- Comprobar si el stock es suficiente para crear la orden.
- Impedir que se creen órdenes inválidas.

---

## Flujo de Creación de una Orden

Para que la creación de una orden sea exitosa, es necesario seguir este flujo:

### Paso 1: Crear un producto

`POST http://localhost:8080/api/products`

Ejemplo de cuerpo:

```json
{
  "name": "iPhone 15 Pro",
  "price": 1599.99,
  "stock": 10
}
Paso 2: Crear una orden
POST http://localhost:8084/api/orders

Ejemplo de cuerpo:

[
  {
    "name": "Pedido de Julian",
    "productId": 1,
    "stock": 3
  }
]
productId: ID del producto previamente creado.

stock: cantidad deseada. Este valor será comparado con el stock actual del producto.

Resultado:
Si el producto existe y hay suficiente stock, la orden se crea correctamente.

Si el producto no existe o no hay stock suficiente, se devuelve un mensaje de error y la orden no se crea.

Ambos microservicios deben estar corriendo para que este flujo funcione correctamente.

Archivos para Postman
En la raíz del proyecto encontrarás dos archivos listos para importar en Postman y facilitar las pruebas:

product-postman.json: contiene las colecciones para el microservicio Product.

orders-postman.json: contiene las colecciones para el microservicio Orders.

Estos archivos permiten realizar pruebas de manera rápida para crear, consultar, actualizar y eliminar recursos.

Características Técnicas
Arquitectura basada en Microservicios

Separación en capas siguiendo el patrón MVC

Validaciones con DTOs y Bean Validation

Comunicación entre microservicios usando RestTemplate

Documentación generada con Swagger UI

Autenticación HTTP básica para todos los endpoints

Proyecto preparado para extensión modular futura (servicio de usuarios, pagos, etc.)

Autor
Julian Peruzzi
DNI: 39.955.389
GitHub: https://github.com/julianperuzzi