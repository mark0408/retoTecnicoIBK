# retoTecnico

## Apis desarrolladas
| Endpoint                     | Método | Descripción                                     |
|------------------------------|--------|-------------------------------------------------|
| `/customers/{uniqueCode}`    | GET    | Recupera un cliente por uniqueCode encriptado   |
| `/customers`                 | PUT    | Actualiza un registro de cliente por uniqueCode |
| `/customers`                 | POST   | Crea un nuevo cliente                           |
| `/auth/login`                | POST   | Loguin para generar el token                    |

## Comentarios adicionales
> Se implemento Spring security y se debe generar un token para usar el resto de apis.

> Se adjunta la coleccion de Postman de prueba
