# 📲 Inter Rapidísimo

## Descripción
Aplicación Android escrita en Kotlin, siguiendo MVVM y Clean Architecture.  
Permite:
- Chequear versión del app contra servidor.  
- Autenticar usuario.  
- Sincronizar y mostrar “Tablas” y “Localidades” desde endpoints REST.

## Características / Tecnologías
- **Networking**: Retrofit  
- **Persistencia**: Room  
- **View Binding**  
- **Dependencias**: Hilt  
- **Listas**: RecyclerView  
- **Patrones**: Singleton, Observer, Adapter


## Arquitectura
SplashActivity → LoginActivity → MainActivity/Fragments
├─ ViewModels (StateFlow + Resource)
├─ UseCases (puro negocio, maneja errores HTTP)
├─ Repositorios (Service + Database)
└─ DTOs ↔ Mappers ↔ Entities

## Endpoints
| Funcionalidad                                | Endpoint                                                                                                           |
|-----------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| Chequeo de versión                           | `GET api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl`                                        |
| Login                                        | `POST FtEntregaElectronica/MultiCanales/.../Seguridad/AuthenticaUsuarioApp`                                         |
| Obtener esquema de tablas                    | `GET apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true`                                              |
| Obtener localidades recogidas                | `GET ParametrosFramework/ObtenerLocalidadesRecogidas`                                                              |

## Cómo ejecutar
1. Clona el repositorio  
2. Abre en Android Studio (Arma proyecto Gradle)  
3. Ejecuta en un emulador o dispositivo real
