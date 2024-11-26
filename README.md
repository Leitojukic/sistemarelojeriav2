Bienvenidos al Proyecto de Mario LUPPI & Leonardo JUKIC
El siguiente codigo esta escrito en Java mediante -IDE NETBEANS, presentacion hecha para el Prof. PABLO NAVAS
Trata de un Sistema de Gestión para Relojeria.

El proyecto "com.relojeria"es una aplicacion de esritorio desarrollada en Java, orientada a la gestion de una relojeria. Esta Aplicacion utiliza una arquitectura modular basada en paquetes para organizar el codigo y sus funcionalidades. Integra una conexion a una base de datos alojada en PythonAnywhere, permite la gestion de usuarios, prodcutos, y ventas, y ofrece caracteristicas adicionales como generacion de graficos y reportes.

El día 26-11-2024 se lanzo la versión 3.0 se agrega URL en drive con los archivos adjuntos para la explicación detalla del Sistema en donde encontraremos word, pdf y mas info: https://drive.google.com/file/d/18Bc6-35zKvILQ89nHHzoTDo5x3thXi2_/view?usp=sharing

Debajo dejo el paso a paso de como fue la produccion de este proyecto

Archivos Requeridos en el Programa

Configuración de Base de Datos:

Archivo: ConexionBD.java
Confirmado: Este archivo está correcto si se configuró con la URL, usuario y contraseña de PythonAnywhere.
Gestión de Usuarios:

Archivo: Usuario.java (Modelo)
Archivo: UsuarioDAO.java (Acceso a Datos)
Confirmado: Ambos archivos están presentes y manejan las operaciones de usuario.
Lógica de Autenticación:

Archivo: AuthService.java
Archivo: JWTUtil.java (para generación y validación de tokens)
Confirmado: Estos archivos están bien configurados y utilizan UsuarioDAO para validar credenciales.
Interfaz de Usuario:

Archivo: Login.java
Archivo: MainFrame.java (ventana principal después del login)
Confirmado: Están configurados correctamente para interactuar con el backend.
Dependencias Externas:

Archivos .jar:
java-jwt-4.4.0.jar: Maneja la creación y validación de tokens JWT.
json20240303.jar: Proporciona soporte para trabajar con JSON.
jfreechart-1.5.3.jar y jcommon-0.9.5.jar: Para gráficos (opcional si no implementas gráficos ahora).
Confirmado: Estos archivos deben estar en la carpeta libs del proyecto y agregados al classpath

Validación de JWT:

Ya se incluyó el uso de JWTUtil para generar y verificar tokens. Esto es esencial para la autenticación y autorización


