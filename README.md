# Create User Service

## Evaluación: JAVA
● Mensajes de error con formato:
```JSON
    {"mensaje": "mensaje de error"}
```

● Recibe los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono":
 ```JSON
{
    "name": "String",
    "email": "String",
    "password": "String",
    "phones": [
        {
            "number": "String",
            "citycode": "String",
            "countrycode": "String"
        }
    ]
}
```

● Responder el código de status HTTP adecuado

● En caso de éxito, retorne el usuario y los siguientes campos:

* id: UUID
* created: fecha de creación
* modified: fecha de la última actualización de usuario(la de creación)
* last_login: fecha de creación
* token: token de acceso de la API (JWT)
* isactive: true / false

```JSON
{
    "id": "4c98d0de-72e1-462d-8382-4d13d43ff8c9",
    "name": "Juan Rodriguez",
    "email": "juan@rodripuez.com",
    "password": "$2a$10$kYl9uj2QvOd.1wPn4m573uz2Y4PyehGulQtddlIWfKmQ0baEAws3G",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ],
    "created": "2022-08-18",
    "modified": "2022-08-18",
    "lastLogin": "2022-08-18",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjA4Mzk1MjB9.LKW8QVIwTyHK_t_OKKNTosZL6dtghTXu7B8yWcDoZQg",
    "isactive": true
}
```
#### Observaciones:
* password: se devuelve la contraseña ya encriptada



● Si caso el correo conste en la base de datos:
```JSON
{
    "message": "Email already registered"
}
```
● El correo debe seguir una expresión regular para validar que formato sea el correcto mediante un patron estandar.
Se utiliza la annotation `@Email` para corroborar el formato. En caso de error:

```JSON
{
    "message": "Email format is not valid"
}
```
● La clave debe seguir una expresión regular para validar que formato sea el correcto:
`regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{6,}$"`
.En caso de error:
```JSON
{
    "message": "Invalid password format. Must be 6 characters or more, 1 uppercase, 1 lowercase, 1 number, 1 special character"
}
```


● El token es persistido junto con el usuario:
tengo discrepancias con este punto, bajo mi punto de vista, no deberia ser persistido si se usa JWT pero igualmente fue persistido.




## Features

- Maven.
- Persistencia con JPA.Hibernate.
- Framework SpringBoot.
- Java 17
- JWT como token
- Pruebas unitarias
- Swagger


## API Reference

#### Crear nuevo usuario

```http
  POST /api/v1/users/register
```

| Body | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `Datos Usuario` | `JSON` | **Requerido**. Datos del usuario a crear. Se pueden enviar más de un phone por usuario |


##### Estructura pedida:
```JSON
{
    "name": "Pablo Rodriguez",
    "email": "Pablito@rodriguez.com",
    "password": "Manolito2!",
    "phones": [
        {
            "number": "1234567",
            "citycode": "123",
            "countrycode": "52"
        }
    ]
}
```

#### Deshabilitar Usuario

```http
  POST /api/v1/users/disable
```

| Parametro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `userEmail`| `string` | **Requerido**. Email del usuario a desactivar |

| Header | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `string` | **Requerido**. Token Bearer de la Api |


#### Consultar Swagger

```http
  GET /api/v1/swagger-ui/index.html
```

#### Consultar Consola - DB h2

```http
  GET /api/v1/h2-console/ 
```
## Running Tests

Para probar esta solución. He creado una colección de test en Postman para probar las funcionalidades claves requeridas, los mismos pueden ser importados a postman desde el siguente link:
[https://www.getpostman.com/collections/ed18b180799f150a1fe6](https://www.getpostman.com/collections/ed18b180799f150a1fe6)

O ser descargados desde este repositorio e importados a Postman, el archivo se nombra "User_Service.postman_collection.json"


Cada consulta tiene sus test y son importados junto con cada una de ellas, se puede hacer un "Run All Collection" para probar todos o probar de a uno.
Igualmente invito a quien este intereado en probar la solucion, a crear sus propios test, y siempre es bienvenido el feedback para mejorar.

Adicionalmente, he creado unos test unitarios con JUnit para acompañar el desarrollo de la solución.
#### Pasos para realizar el test:
- Descargar el JSON importable de Postman o importar desde el link directamente en la app
- Importar el JSON desde la App
- Setear la variable Global `URL`en Postman con el path del entorno donde se vaya a ejecutar.
  ej:
```http
http://localhost:8080
```
- Ejecutar la solución
- Realizar un run collection si se desea, o ejecutar las solicitudes individualmente

## Diagram

![Diagram](https://drive.google.com/uc?export=view&id=1FNXYdfNHhMlonaWtnpEuHpgAmd0V9EGI)
