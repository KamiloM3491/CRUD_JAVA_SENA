# CRUD Java SENA

Aplicación de escritorio desarrollada en Java para la gestión de usuarios, que permite registrar cuentas, iniciar sesión, consultar y actualizar información del perfil y eliminar usuarios. La aplicación utiliza una base de datos MySQL para almacenar la información.

## Descripción general

Este proyecto consiste en una aplicación de escritorio que permite gestionar usuarios mediante una interfaz gráfica desarrollada con Java Swing. Su funcionamiento se conecta a una base de datos MySQL, en la que se almacenan los datos personales, las credenciales y los roles de los usuarios.

La aplicación cuenta con módulos para el registro de usuarios, autenticación, consulta y actualización del perfil, cierre de sesión y eliminación de cuentas.

Para la conexión y persistencia de datos se utilizan JDBC e Hibernate. JDBC permite ejecutar consultas y operaciones directamente sobre la base de datos, mientras que Hibernate facilita el mapeo de las clases Java con las tablas de MySQL. Adicionalmente, las contraseñas se procesan mediante BCrypt antes de almacenarse, evitando guardar las claves en texto plano.

## Tecnologías utilizadas

- Java (JDK 25)
- Maven
- JDBC
- Hibernate
- MySQL
- XAMPP
- phpMyAdmin
- BCrypt
- NetBeans IDE
- Git y GitHub

## Estructura del proyecto

```text
CRUD_JAVA_SENA/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── mycompany/
│       │           └── crud_java_sena/
│       │               ├── config/
│       │               │   ├── ConexionJDBC.java
│       │               │   └── HibernateUtil.java
│       │               ├── model/
│       │               │   ├── Usuario.java
│       │               │   └── Rol.java
│       │               ├── dao/
│       │               │   ├── UsuarioJDBC.java
│       │               │   └── UsuarioHibernate.java
│       │               ├── service/
│       │               │   └── AutenticacionService.java
│       │               ├── view/
│       │               │   ├── RegistroUsuario.java
│       │               │   ├── Login.java
│       │               │   └── PerfilUsuario.java
│       │               └── CRUD_JAVA_SENA.java
│       └── resources/
│           └── hibernate.cfg.xml
├── pom.xml
└── README.md
```

## Requisitos previos

- JDK 25 instalado.
- Apache Maven.
- NetBeans IDE o un entorno compatible con proyectos Maven.
- XAMPP con el servicio MySQL en ejecución.
- phpMyAdmin para administrar la base de datos.

## Base de datos

La aplicación utiliza una base de datos MySQL llamada `evidencia_java`, que contiene las tablas `Usuario` y `Rol`.

La tabla `Usuario` almacena los datos personales y de acceso de cada cuenta, mientras que la tabla `Rol` contiene los roles disponibles. Ambas tablas se relacionan mediante el campo `idRol`.

Para ejecutar el proyecto, es necesario contar con la base de datos creada y configurar los parámetros de conexión de acuerdo con la instalación local de MySQL.

La conexión JDBC se encuentra en `ConexionJDBC.java` y la configuración de Hibernate se establece en `hibernate.cfg.xml`.

## Instalación y ejecución

1. Clona el repositorio y accede a la carpeta del proyecto:

```bash
git clone https://github.com/KamiloM3491/CRUD_JAVA_SENA.git
cd CRUD_JAVA_SENA
```

2. Abre el proyecto desde NetBeans mediante la opción **Open Project** y selecciona la carpeta `CRUD_JAVA_SENA`.

3. Verifica que el servicio MySQL de XAMPP esté activo y que la base de datos `evidencia_java` se encuentre creada.

4. Revisa la configuración de conexión en `ConexionJDBC.java` y `hibernate.cfg.xml`, verificando que los datos correspondan a tu instalación local.

5. Permite que Maven descargue las dependencias del proyecto.

6. Ejecuta la clase principal `CRUD_JAVA_SENA.java` desde NetBeans.

La aplicación iniciará la interfaz de inicio de sesión.

## Módulos y funcionalidades

### Registro de usuarios

Permite crear nuevas cuentas mediante un formulario que solicita los datos personales y las credenciales del usuario. Se validan los campos obligatorios y se verifica que el correo electrónico no esté registrado previamente.

Las contraseñas se procesan con BCrypt antes de almacenarse en MySQL. El registro utiliza Hibernate para realizar la persistencia de la información.

### Inicio de sesión

Permite autenticar a los usuarios mediante su correo electrónico y contraseña. El sistema verifica las credenciales almacenadas y comprueba que la cuenta se encuentre activa antes de permitir el acceso.

### Consulta del perfil

Después de iniciar sesión, el usuario puede consultar su información personal desde la ventana de perfil.

### Actualización del perfil

Permite modificar el nombre, apellido, teléfono y dirección del usuario que inició sesión. Los cambios se actualizan directamente en la base de datos mediante JDBC.

### Cierre de sesión

Permite finalizar la sesión actual y regresar a la ventana de inicio de sesión, solicitando confirmación antes de realizar la operación.

### Eliminación de cuenta

Permite eliminar la cuenta del usuario autenticado. Antes de ejecutar la operación, se solicita confirmación y posteriormente se elimina el registro correspondiente de la base de datos.

## Persistencia de datos

El proyecto utiliza dos mecanismos para interactuar con MySQL:

- **JDBC:** permite realizar consultas y operaciones SQL directamente sobre la base de datos, como la consulta de usuarios, actualización de perfiles y eliminación de cuentas.
- **Hibernate:** permite relacionar las clases del modelo con las tablas de la base de datos y gestionar la persistencia de los registros, como ocurre durante el registro de usuarios.

Esta combinación permite trabajar con la información almacenada y mantener organizadas las operaciones de acceso a datos.

## Pruebas

Se realizaron pruebas funcionales manuales para verificar el comportamiento de los módulos implementados y su interacción con la base de datos.

Entre las operaciones verificadas se encuentran:

- Registro de usuarios con información válida.
- Validación de correos electrónicos duplicados.
- Inicio de sesión con credenciales correctas e incorrectas.
- Consulta de la información del perfil.
- Actualización de los datos personales.
- Cierre de sesión.
- Eliminación de la cuenta del usuario.

Las pruebas permitieron comprobar el funcionamiento de las operaciones y la persistencia de los cambios en MySQL.

## Control de versiones

El proyecto utiliza Git para el seguimiento de los cambios realizados durante su desarrollo y GitHub como plataforma para alojar el repositorio remoto.

El historial de commits permite consultar las modificaciones registradas y los avances realizados en la implementación de la aplicación.

## Estado del proyecto

La aplicación de escritorio cuenta con los módulos de registro, autenticación, consulta y actualización del perfil, cierre de sesión y eliminación de cuentas. Las funcionalidades implementadas se conectan con una base de datos MySQL y fueron verificadas mediante pruebas funcionales manuales.

Este desarrollo corresponde a una aplicación de escritorio (stand-alone) elaborada como parte de la evidencia de formación del programa SENA.