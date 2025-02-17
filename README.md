# Cosas a tener en cuenta
- Esta API funciona usando Spring, Lombok y JPA, conectandose este último a una BBDD MySQL
- En el application.properties se encuentran los datos de mi conexion a la BBDD en MySQL Workbench, si quieres usar este repositorio tienes que cambiar esos ajustes para poner los tuyos
- La base de datos se crea si no existe, solo tienes que preocuparte porque los datos relativos a la conexión con el server sean correctos
- El paquete de modelo cuenta con una clase **Populaters** que automaticamente inserta varios libros, autores, etc al crear las tablas de la BBDD, por lo que desde un inicio se tienen datos para probar la API
- En Angular tendrás que rehacer todo el modelo para que cuadren los nombres y tipos de los campos con los DTO en Java
- Las propiedades Autor, Formato, Tema y Edición son objetos, por lo que a la hora de querer mostrarlos en el HTML hay que llamar a "libro.autor.nombre"

### El servicio **LibrosService** en Angular quedaría algo así:
```typescript
export class LibrosService {

  private baseURL = "http://localhost:8080"
  private options = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private httpClient: HttpClient) {
  }
  public getLibros(){
    return this.httpClient.get<Libro[]>(`${this.baseURL}/api/libros`, {headers: this.options});
  }
}
```
*esto es un ejemplo del servicio de **Libros**, por lo que si vas a copiarlo y pegarlo para hacer el resto de servicios tienes que cambiar el nombre de la clase, el nombre del método y cambiar tanto el tipo de array dentro de los diamantes de **this.httpClient.get<>** como la url a la que se accede*

## Funcionamiento de los endpoints
En caso de que no lo sepas, un endpoint es, de forma brusca, *"los métodos de la API que tienen lo del mapping"*, y se acceden utilizando el httpClient que le entra al servicio por el constructor (en el Angular)

### Metodo POST
Al post le entra un DTO entero, asi que le pasamos como segundo parámetro el libro (de Angular) que queramos guardar
```typescript
 public postLibro(libro: Libro) {
    return this.httpClient.post<Libro>(`${this.baseURL}/api/libros`, libro, {headers: this.options});
  }
```

### Metodo UPTATE
Aqui pasa una cosita, y es que en el DTO de libro he omitido el ID al tener ya el ISBN como un campo único. En la anterior versión de la API habia que añadirle el ID al libro antes de mandarselo al servicio, pero esta versión es capaz de actualizar sin necesidad de hacer eso
```typescript
  putLibro(libro: Libro) {
    return this.httpClient.put<Libro>(`${this.baseURL}/api/libros`, libro, {headers: this.options});
  }
```
**¡LÓGICAMENTE NO DEJES QUE SE MODIFIQUE EL CAMPO DEL ISBN, SI LO CAMBIAS LA API NO TIENE FORMA DE SABER CUÁL QUIERES MODIFICAR!**

### Metodo DELETE
El DELETE, al igual que el UPDATE, en la versión anterior se hacía por ID, ahora se hace por ISBN
```typescript
  deleteLibro(libro: Libro) {
    return this.httpClient.delete<Libro>(`${this.baseURL}/api/libros?ISBN=${libro.ISBN}`, {headers: this.options});

  }
```

# Anotaciones
En caso de que sigas haciendo algo mal, antes de decirme "tu API no va" asegurate de que:
- El modelo en Angular lo tienes con los mismos campos que su respectivo DTO en Java. Con mismos campos me refiero a mismo número, tipo y nombre. Metele el DTO al chatGPT y pidele que te lo pase a TypeScript si te da pereza
- ¡TIENES QUE PONER BIEN LA CONEXIÓN CON LA BASE DE DATOS! No tienes que abrir el xampp para nada, borratelo de tu pc, echale gasolina y tirale fuego... lo que prefieras, no necesitas usar eso para nada. Tan solo asegurate de poner bien el usuario, la contraseña y el puerto, el resto se hace todo solo
- Escribe bien los mappings en Angular. Metete al controller al que vayas a usar (Si vas a coger los Autores te vas al de Autores, si vas a coger Libros te vas al de Libros, parecerá obvio pero algo me dice que no lo es) y **COPIA Y PEGA** el texto que hay en la anotación (lo que empieza por @) que ponga *"nosequé mapping"*
- Al post y al put le tienen que entrar un objeto. Suena obvio, si, pero si el Libro tiene atributos de tipo Autor, Edicion, etc, es porque la API **MANDA Y RECIBE** objetos cuyos atributos son de ese tipo. Asegurate que estás metiendolo todo bien
