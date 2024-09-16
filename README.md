# Prueba "Tienda de Productos"
## Consigna:
Se debe implementar la funcionalidad básica de una tienda de productos:
* Se pueden almacenar productos y vendedores
* Al registrar una venta se asocia el producto con el vendedor
* Se debe calcular la comisión de ventas de un vendedor, que se obtiene de:
  * Un 5% de las ventas en caso de vender hasta 2 productos
  * Un 10% de las ventas en caso de vender más de 2 productos  
* Deben implementarse distintos tipos de buscadores de productos, por ejemplo por categoría
* La aplicación tendrá que implementar el manejo de excepciones correctamente
* Deberá diseñarse un Diagrama Entidad Relación
* Deberá ejecutarse por consola y se almacenarán los datos en memoria

## Diagrama Entidad Relación
![image](https://github.com/user-attachments/assets/e12eeb45-6dd9-415e-b142-af943c953e84)

## Tecnologías utilizadas
* Java 17
* Spring Boot 3.3.3
* H2 --> Utilice H2 para el guardado en memoria de los datos

## Uso del proyecto
Como se ejecuta por consola, en el main se encuentra un menú principal con las posibles acciones a realizar. Luego, en cada controlador se encuentran los métodos según la funcionalidad ingresada. Dichos controladores no tienen la estructura típica debido a que los datos se ingresan y muestran por consola.

## Descripción de funcionalidades
Las acciones posibles a realizar son: 
1. Crear producto
2. Crear vendedor
3. Crear venta
4. Buscar productos
5. Calcular comisión vendedor
6. Salir

#### Crear producto
Para probar esta funcionalidad, se le solicitará al usuario que ingrese el nombre y el precio del producto. Además, se le mostrarán las posibles categorías que puede seleccionar para que ingrese la que desee.
Luego se crea el producto y se muestra el resultado por consola.

#### Crear vendedor
Al igual que con el producto, se deben ingresar los datos correspondientes (nombre de vendedor y salario de vendedor).
Se creará el vendedor y se podrá visualizar el resultado por consola.

#### Crear venta
Al momento de crear una venta, primero se mostrará un listado de los productos disponibles. El usuario ingresará el id de todos los productos que se vendieron, previamente habiendo ingresado la cantidad de productos vendidos.
Una vez ingresados los productos, se mostrará un listado de los vendedores, para que el usuario ingrese el id del vendedor que realizó la venta.
Finalmente se creará la venta con los respectivos productos y su vendedor. Además, la venta contendrá otros datos como el monto total y la comisión que el vendedor obtuvo de esa venta.

#### Buscar productos
Para la búsqueda de productos, se tienen 3 posibles opciones:
##### a. Búsqueda por categoría
Se muestra al usuario un listado de las categorías disponibles, el usuario ingresa el nombre de la misma y después se mostrará el listado de todos los productos de esa categoría.
##### b. Búqueda por nombre
El usuario ingresa un nombre y se mostrará por consola un listado de los productos filtrados por nombre.
##### c. Búsqueda por rango de precios
El usuario ingresa un rango de precios (precio desde y precio hasta) y se le mostrará el listado de productos que tengan su precio contenido en dicho rango.

#### Calcular comisión vendedor
La comisión que se lleva un vendedor por cada venta, se calcula al momento de crear una venta y se almacena. Pero en caso de querer obtener el valor total de las comisiones obtenidas dentro de un determinado rango de fechas, el usuario puede ingresar dicho rango junto con el id del vendedor (previamente se mostró un listado de vendedores) y se le mostrará el resultado de la suma de todas las comisiones que obtuvo en ese rango de fechas.
