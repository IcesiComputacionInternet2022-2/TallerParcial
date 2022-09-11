# Taller de preparación parcial

## 1. Explicar cada uno de los puntos de los principios S.O.L.I.D

- Single responsability: Dar a una clase una única responsabilidad dentro del sistema
- Open/closed: Se refiere a que se puede extender la funcionalidad de una clase sin necesidad de modificarla internamente (Clases abiertas para extenderse y cerradas para modificarse
- Liskov susbstitution: Las clases que se deriven de una clase deben de poder ser reemplazadas por esa misma clase sin ningún problema.
- Interface segregation: Especifica que las interfaces deben estar diseñadas para tener una funcionalidad concreta y es oreferible tener muuchas interfaces con pocos métodos que una sola forzada a implementar muchos.
- Dependency Inversion: hacer que el sistema no dependa de los detalles de implementación. Es decir que por medio de interfaces se planteen los métodos que una clase debe implementar pero al sistema solo le interesan los métodos como tal mas no como están implementados

## 2. Explicar por qué la necesidad y utilidad de pruebas unitarias como metodo de aseguramiento de la calidad del codigo.
- Necesario porque se cometen errores por más buen programador que sea, es posible que no se hayan tenido presente ciertas relaciones o estén mal planteadas, todo eso requiere ser validado por medio de las pruebas unitarias. Al encontrar los errores se pueden corregir con mayor facilidad garantizando que el código esté bien realizado y el programa funcione adecuadamente

## 3. Spring:
### a. Explicar que es un Bean y que utilidad tiene dentro del contexto de Spring
- Un bean es un objeto que es administrado por el "Spring IOC container". Es útil para la administración automatica de dependencias de una clase sin necesidad de cambiar la clase base como tal. Una clase definida como bean es creada automaticamente por Spring.
### b. Cúal es la diferencia entre Spring y Spring Boot
- Spring es un conjunto de proyecto entre los cuales se encuentra Spring boot. Este último se construye sobre el framework de Spring y permite el uso de anotaciones para fácilitar la codificación y reducción de "boiler plate code"
### c. Cúal es la diferencia entre @Component y @Controller, @Service, @Repository
- @Component marca una clase como bean, @Controller, @Service y @Repository tambien son components pero tienen funcionalidades más especificas.
- @Controller indica que la clase es una clase controladora que se encarga de dirigir las solicitudes al módulo correspondiente.
- @Service indica que una clase provee un servicio, donde se encontra toda la lógica del programa, la anotación permite favorcer la reutilización de código.
- @Repository indica donde se almacenan los datos
### d. Cúal es la diferencia entre @Bean y @Component
- @Component se define a nivel de una clase que indica que la clase es directamente instanciada y administrada por el container de spring. @Bean se define a nivel de método cuando no se puede definir el @Component en la clase (Ejemplo: librerías de terceros) y el método retorna una instancia de esa clase, el método es llamado por el container de Spring cuando se requiera, @Bean tiene que ir en una clase que sea del tipo @Configuration.
## 4. Explique en que consiste la arquitectura cliente servidor.
- Consiste en la separación de toda la lógica en modulo aparte llamado servidor el cuál resive solicitudes de un módulo usado por el usuario llamado cliente. El cliente unicamente se encarga de interactuar con el usuario y comunicarse con el servidor.  El servidor tiene toda la lógica y provee servicios que pueden ser usados por el cliente.
## 5. Explique porque es util la arquitectura de microservicios.
- La arquitectura de microservicios divide las funcionalidades grandes de un programa en módulos independientes uno del otro. Lo anterior es útil cuando se tienen programas de gran tamaño ya que facilita las actualizaciones y el mantenimiento del código.
## 6. Explique en que casos no es util la arquitectura de microservicios.
- Cuando se inicia el desarrollo de un programa es más fácil mantener todo unificado que separarlo en pocos servicios.
- Cuando son demasiados servicios puede ser mejor unificar algunos de ellos
- Cuanso se realizan operaciones muy complejas es mejor unificar para obtener mejor rendimiento


## 7. Corregir el proyecto
### a. arreglar los unit tests que no funcionan
### b. arreglar el codigo que hace que los unit tests fallen
### c. hacer que la aplicaciòn funcione.

https://devexperto.com/principio-responsabilidad-unica/
https://profile.es/blog/principios-solid-desarrollo-software-calidad/
https://devexperto.com/principio-de-inversion-de-dependencias/
https://medium.com/javarevisited/spring-beans-in-depth-a6d8b31db8a1#:~:text=By%20definition%2C%20a%20Spring%20bean,many%20objects%20in%20your%20application.
https://www.youtube.com/watch?v=aS9SQITRocc&t=1s
https://www.youtube.com/watch?v=948UMUCYAAw
https://www.atlassian.com/es/microservices/microservices-architecture
https://emanuelpeg.blogspot.com/2021/12/cuando-no-usar-microservicios.html
