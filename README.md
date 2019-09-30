# Patrones Arquitecturales
en el siguiente tutorial se trabajaran con distintos productos ofrecidos por AWS como lo son S3, EC2 y el sistema de bases de datos ofrecido por AWS. los procedimientos a realizar en este turotial se muestran a continuación:  
1. Desplegar un sitio estático usando S3

2. Desplegar un formulario dinámico usando EC2

3. Enlazar el formulario a una base de datos relacional o no-relacional, para almacenar y traer los datos almacenados. Use servicios de base de datos de AWS.

4. Configurar un VPC para gestionar la seguridad y los permisos de acceso a sus servidores.

## 1. Desplegar un sitio estático usando S3
lo primero que se debe hacer es acceder al servicio de AWS y buscar la opción S3:  
![](Imagenes/1.1.PNG)  
posteriormente aparecera la siguiente pestaña, allí seleccione la Opción "crear bucket":  
![](Imagenes/1.2.PNG) 
se mostrará una pestaña nueva, aqui indique el nombre del bucket y de clic en siguiente:
![](Imagenes/1.3.PNG)  
en esta pestaña de clic siguiente:  
![](Imagenes/1.4.PNG)  
aqui llegará a la tercera parte de la configuración donde la opción que dice "Bloquear todo acceso público" se dejará desmarcada:  
![](Imagenes/1.5.PNG)  
finalmente revise los datos del bucket y de clic a "crear bucket":  
![](Imagenes/1.6.PNG)  
con el bucket creado notará lo siguiente:  
![](Imagenes/1.7.PNG)  
acceda al bucket y aparecerá lo siguiente,de clic en cargar:  
![](Imagenes/1.8.PNG)  
aquí, suba los archivos estaticos que funcionarán como pagina web y de clic en siguiente:  
![](Imagenes/1.9.PNG)  
en la siguiente pestaña les asigne permiso de lectura pública y de clic en siguiente:
![](Imagenes/1.10.PNG)  
siguiente:  
![](Imagenes/1.11.PNG)  
finalmente, clic en cargar:  
![](Imagenes/1.12.PNG)  
el bucket ya aparecerá con los archivos, posteriromente seleccione propiedades:
![](Imagenes/1.13.PNG)   
aqui seleccione la opción que dice Alojamiento de sitios web estáticos:  
![](Imagenes/1.14.PNG)  
en la pestaña dé clic donde dice Usar este bucket para alojar un sitio web, posteriormente escriba el nombre de los archivos estaticos (previamente subidos) en los campos de texto "documento de índice" y "Documento de error", tambien notará que dice "punto de enlace", esta es la URL desde donde se podrá acceder a los recursos.
finalmente seleccione guardar:  
![](Imagenes/1.15.PNG)  
y si accede a la pagina web observará el índice estatico que fue subido, asi como al solicitar un archivo inexistente aparece el error:  
![](Imagenes/1.16.PNG)  
![](Imagenes/1.17.PNG)  

##  2. Desplegar un formulario dinámico usando EC2
la implementación del formulario es el codigo adjunto a este readme.  
se utilizó java,maven y spring para su creación.
su despliegue en EC2 se realiza de la siguiente manera:  
en la consola de AWS busque ec2:
![](Imagenes/2.1.PNG)  
aparecerá lo siguiente, seleccione "Running Instances":  
![](Imagenes/2.2.PNG)  
aqui se pueden gestionar todas las máquinas virtuales en la nube asociadas a su cuenta, en este caso acceda a la máquina creada en el laboratorio anterior dando clic en connect:   
![](Imagenes/2.3.PNG)  
ejecute una consola local y acceda al directorio que contiene las "keys" para acceder a la máquina virtual.
  ingrese el comando que aparece como ejemplo remplazando ssh con sftp:  
![](Imagenes/2.4.PNG)  
![](Imagenes/2.5.PNG)  
ya conectado remotamente utilice el comando "put" para enviar al servidor el archivo compilado del formulario dinámico.  
ATENCIÓN: se debe subir el archivo ya compilado y empaquetado (.jar)debido a que el servidor no cuenta con el compilador de java instalado:  
![](Imagenes/2.6.PNG)  
al terminar de pasar el archivo digite exit en la consola e ingrese con ssh en lugar de sftp:  
![](Imagenes/2.7.PNG)  
revise que se ejecute correctamente y sin errores escribiendo "java -jar {nombre de su archivo}"  
![](Imagenes/2.8.PNG)  
Por el momento no podrá acceder remotamente a su proyecto ejecutado, ya que la conexión siempre será rechazada debido a que todos los puertos estan bloqueados por defecto en AWS.  
dicho problema será solucionado en la parte 4 de este turorial "Configurar un VPC para gestionar la seguridad y los permisos de acceso a sus servidores."
## 3. Enlazar el formulario a una base de datos relacional o no-relacional, para almacenar y traer los datos almacenados. Use servicios de base de datos de AWS.
para efectos de este tutorial se utilizará la base de datos relacional "mySQL".  
lo primero es generar la base de datos en AWS, para ello busque el servicio RDS:  
![](Imagenes/3.1.PNG)  
aparecerán las siguientes opciones, seleccione create database:  
![](Imagenes/3.2.PNG)  
en el método de creación seleccione Standard create:  
![](Imagenes/3.3.PNG)  
seleccione la base de datos de su preferencia, para efectos del tutorial será mySQL:  
![](Imagenes/3.4.PNG)  
seleccione Free tier en la pestaña "Templates" para no generar costos:  
![](Imagenes/3.5.PNG)  
en Settings defina el nombre general de su base de datos, así como el usuario(1) y contraseña(2) que serán utilizados más adelante:  
![](Imagenes/3.6.PNG)  
deje las pestañas "DB instance size", "Storage" y "Availability & durability" tal como están:  
![](Imagenes/3.7.PNG)  
![](Imagenes/3.8.PNG)  
![](Imagenes/3.9.PNG)  
en la pestaña Connectivity dé clic en "Additional connectivity configuration" y en "publicly accessible" Seleccione  "Yes", deje las demas opciones tal y como están.  
![](Imagenes/3.10.PNG)  
finalmente, despliegue la opción que dice "Additional configuration" y en donde dice "initial database name" indique el nombre especifico de la base de datos(3): 
![](Imagenes/3.11.PNG)   
seleccione "create database":  
![](Imagenes/3.12.PNG)   
note que en la consola de AWS ya aparece la base de datos creada:  
![](Imagenes/3.13.PNG)   
seleccionela y busque EndPoint(4) & port(5) que se encuentran en la pestaña connectivity & security 
 ![](Imagenes/3.14.PNG)   
 con dichos datos ya es posible generar el application.properties del formulario dinámico para poder enlazarlo con la base de datos, para ello acceda al archivo que se encuentra en la direccion /src/main/resources y modifiquelo de la siguiente manera:  
 ![](Imagenes/3.15.PNG)   
 en la url ingrese el siguiente formato jdbc:mysql://{EndPoint(4)}:{port(5)}/{nombre especifico base de datos (3)}?useSSL=false  
 remplazando los valores entre corchetes con los especificados con numeros en el tutorial.  
 realice lo mismo con el username(1) y el password(2) en el archivo application.properties.  
 con esto ya se encuentra enlazado el formulario con la base de datos.
 ## 4. Configurar un VPC para gestionar la seguridad y los permisos de acceso a sus servidores.
 finalmente para poder acceder desde todo lugar al formulario y que este a su vez pueda acceder a la base de datos realice los siguientes pasos:  
 lo primero que debe hacer es permitir la conexión a la base de datos por el puerto 3306, ya que, como recuerda en el paso 3 fue el que se definio para dicho fin.  
 para ello acceda a la consola de la base de datos en AWS:  
 ![](Imagenes/4.1.PNG)   
 en la pestaña Connectivity & security a la derecha hay una sección llamada Security, ahi seleccione el " VPC security group" que tiene definido:  
 ![](Imagenes/4.2.PNG)   
 le aparecerá una breve descripción del grupo de seguridad alli seleccione la pestaña que dice Inbound en la parte inferior.  
 ![](Imagenes/4.3.PNG)   
 ![](Imagenes/4.4.PNG)   
 de clic en el botón "edit" 
 ![](Imagenes/4.5.PNG)  
 ahora seleccione Add rule, busque MYSQL/Aurora en el tipo y en el source digite 0.0.0.0/0, posteriormente de clic en guardar
 ![](Imagenes/4.6.PNG)  
 con esta configuración el servidor ya sabe que debe aceptar todas las peticiones que lleguen por el puerto 3306 que es por donde se conecta a la base de datos.  
ahora se realizará algo parecido para acceder al formulario desde cualquier parte.  
ingrese a EC2 y busque la maquina donde se encuentra desplegado el formulario:  
![](Imagenes/4.7.PNG)  
en la descripción aparecera el security group, acceda a el:
![](Imagenes/4.8.PNG)    
en inbound Seleccione edit y agregue 2 nuevas reglas, el tipo debe ser "Custom TCP Rule", el puerto 8080 dado que spring inicializa en dicho puerto y el source es 0.0.0.0/0 (ipv4) para la primera regla y ::/0 (ipv6) para la segunda regla.  
seleccione guardar.
![](Imagenes/4.9.PNG)   
con estas configuraciones ya puede acceder al aplicativo y probarlo.  
![](Imagenes/4.10.PNG)   
## estructura de costos
utilizando la herramienta de AWS para calcular precios se obtiene la siguiente información para una arquitectura similar a la utlizada en el presente tutorial:
![](Imagenes/5.1.PNG)   
![](Imagenes/5.2.PNG)   