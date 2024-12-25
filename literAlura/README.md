# Catálogo de Libros - Interacción Textual 👀📖📚

Este proyecto es un catálogo de libros que permite la interacción con el usuario a través de una interfaz de línea de comandos. Los usuarios pueden buscar libros, listar autores, y consultar diversas estadísticas relacionadas con los libros almacenados en una base de datos PostgreSQL

## Cómo Empezar 🚀
Prerrequisitos:
 - Java 8 o superior.
 - Spring Boot 3
 - Conexión a Internet.
 - API de Gutendex.

## Instalación
1. Clona este repositorio:

```ts
git clone https://github.com/jissykakin/LiterAluraApp.git
```
2. Configurar la base de datos:
 - Crear una base de datos PostgreSQL.
 - Configurar las credenciales de la base de datos en las variables de entornos puedes visualizarlas en el archivo application.properties.

3. Navega a la carpeta del proyecto:
```
cd LiterALura/src/main/com.challenger.literAlura
```
3. Compila el código: ``` javac LiterAluraAplication.java.```


4. Ejecuta el programa con ```java LiterAluraAplication```.



## Menú Principal 🍽️

Cuando ejecutes el programa, verás el siguiente menú:

```
***** Menú Principal *****

 1 - Buscar Libro por Titulo
 2 - Listar Libros Registrados
 3 - Listar Autores Registrados
 4 - Listar Autores Vivos en un determinado año
 5 - Listar Libros por Idioma
 6 - Listar Libros por Nombre de Autor
 7 - Listar Top 10 de los Libros más descargados
 8 - Listar Autor por Nombre
 9 - Listar Estadisticas de Libros

 0 - Salir
 
***********************************
```


## Funcionalidades 🛠️

- **Opción 1 - Buscar libro por título:** Realiza búsquedas en la API Gutendex y almacena los resultados en la base de datos para futuras consultas.

![image](https://github.com/user-attachments/assets/115d09aa-2f10-4617-89ff-f84b517053cc)


- **Opción 2 - Listar libros registrados:** Muestra todos los libros almacenados en la base de datos.

![image](https://github.com/user-attachments/assets/f4c57e1a-66c6-49d7-9301-5182921f60e0)

- **Opción 3 - Listar autores registrados:** Muestra una lista de todos los autores presentes en la base de datos.

![image](https://github.com/user-attachments/assets/41d4feef-4f45-4f1b-aa84-3606db4378d2)

- **Opción 4 - Listar autores vivos en un determinado año:** Consulta la base de datos para obtener los autores que estaban vivos en un año específico.

![image](https://github.com/user-attachments/assets/eb200004-6029-44a1-a285-bd586f1b559c)

- **Opción 5 - Listar libros por idioma:** Muestra los libros disponibles en un idioma determinado.

![image](https://github.com/user-attachments/assets/ccc6cbff-edfa-431f-a8e2-2283f865dd95)

- **Opción 6 - Listar libros por nombre de autor:** Muestra los libros escritos por un autor específico.

![image](https://github.com/user-attachments/assets/e6e9b226-4303-484f-9231-11b1495c2f26)

- **Opción 7 - Listar Top 10 de los libros más descargados:** Presenta una lista de los 10 libros más populares según las descargas.

![image](https://github.com/user-attachments/assets/7034f7cd-899c-4ec5-8f76-d3b97764d5cd)

- **Opción 8 - Listar autor por nombre:** Busca un autor específico por su nombre.

![image](https://github.com/user-attachments/assets/b96e0732-1f84-4f68-bc45-8b244455d2b2)

- **Opción 9 - Listar estadísticas de libros:** Proporciona información estadística sobre los libros, como el número total de libros, autores, idiomas, etc.

![image](https://github.com/user-attachments/assets/e1e76a92-868b-4748-8fe4-72f02c4c532b)



## Tecnologías Utilizadas: ⚙️

- Java: Lenguaje de programación principal.
- Spring Boot 3: Framework Java para desarrollo de aplicaciones web.
- PostgreSQL: Base de datos relacional para almacenar la información de los libros.
- API Gutendex: Fuente externa de datos para obtener información sobre libros.