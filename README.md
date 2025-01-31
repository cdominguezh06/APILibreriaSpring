# Cosas a tener en cuenta
- Esta API funciona usando Spring, Lombok y JPA, conectandose este último a una BBDD MySQL
- En el application.properties se encuentran los datos de mi conexion a la BBDD en MySQL Workbench, si quieres usar este repositorio tienes que cambiar esos ajustes para poner los tuyos
- La base de datos se crea si no existe, solo tienes que preocuparte porque los datos relativos a la conexión con el server sean correctos
- El paquete de modelo cuenta con una clase **Populaters** que automaticamente inserta varios libros, autores, etc al crear las tablas de la BBDD, por lo que desde un inicio se tienen datos para probar la API
- En Angular tendrás que rehacer todo el modelo para que cuadren los nombres y tipos de los campos con sus contrapartes de Java
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
