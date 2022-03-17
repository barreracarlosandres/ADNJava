### Caso de negocio ADN Java - Presupuesto

#### Tecnología a usar:
- Java version: 1.8
- Gradle version: 7.0.2
- Spring Boot version: 2.5.3


#### Problema de negocio (Presupuesto)
El objetivo de este desarrollo es aplicar las buenas practicas en el código y ciclo completo en el que se inlcuye prubas unitarias,  CI, test entre otros en caso de negicio ADN Java

***Usuario tendrá los siguientes atributos***:  
**identificacionUsuario**: número de identificacion del usuario (campo alfanumérico de máximo 15 dígitos)  
**nombre**: string que tendrá el nombre o nombres del usuario (string de máximo 100 caracteres)  
**apellido**: string que tendrá los apellidos del usuario (string de máximo 100 caracteres)   

***Presupuesto debe tener lo siguientes atributos:***  
**identificacionUsuario**: número de identificacion del usuario (campo alfanumérico de máximo 15 dígitos) 
**fechaPresupuesto**: fecha que tendrá el año y mes en que aplica el presupuesto (formato YYYY/DD)  
**valorPresupuesto**: compo númericoo que tendrá el presupuesto por ejecutar máximo en el mes en curso (máximo 7 dígitos)  

***Gastos que tendrá los siguientes atributos:***  
**identificacionUsuario**: número de identificación del usuario (campo alfanumérico de máximo 15 dígitos)  
**fechaGasto**: fecha en la cual aplica la ejecución presupuestal (fomato YYYY/MM/DD)  
**valorGasto**: número que tiene el valor del gasto ejecutado (máximo 7 dígitos)

#### Objetivo
Crear un API tipo REST el cual le permita llevar a cabo la siguientes funcionalidades
1. Usuario
   1. crear
      1. todos los campos obligatorios
      2. validaciones y restricciones
         1. identificacionUsuario:
            1. Máximo 15 caractetes
            2. Alfanumérico
            3. Campo obligatorio
            4. identificador único
         2. nombre:
            1. Máximo 100 caracteres
            2. Sólo alfabeto, con acentos, con espacio (Ejemplo: "Carlos Andrés")
            3. Campo obligatorio
         3. apellido:
            1. Máximo 100 caracteres
            2. Sólo alfabeto, con acentos, con espacio
            3. Campo obligatorio
   2. eliminar
      1. Se alimina por id
   3. actualizar
      1. Se actualizará por Id sólo nombre y/o apellido.
      2. 
   4. buscar por nombre y apellido
   5. buscar por identificacionUsuario
   6. listar
2. 
3. 
4. El path es `/presupuesto/gasto` y el método **POST** permitirá adicionar gastos  
       1. Se registra un gasto con el siguiente json de ejemplo:  
```json{"identificacionUsuario" : "94303068","fechaGasto : "2022-03", "valorGasto" : 500000 }```  
Al ser exitoso retornará el siguiente json:  
   ```json{"mensaje" : "Se registró el gasto X y el saldo restante es de XX"}```   
   Donde **X** es el el valor en formato #.###.###
   Donde ***XX* es el valor gasto pendiente por ejecutar en el mes y tendrá el formato #.###.###  
         2. El gasto ingresado no puede superar el presupuesto del mes, de ser así se retornará error HTTP 400 con el siguiente json.   
       ```json{"mensaje" : "Se superó el presupuesto del XX. El gastos disponible para este mes es de XX"}```  
       Donde **X** corresponde a la fecha en formato MM-YYYY.  
       Donde **XX** corresponade a valor en pesos en formato #.###.###  
       2. El gasto del mes es sólo numérico y no puede superar los 7 dígitos. De ser así debe retonar un error HTTP 400 con siguiente json  
       ```json{"mensaje" : "Sólo se permiten número y 7 dígitos"}```  
       3. Debe existir un presupuesto para el año y mes correspondiente. De no ser así retornará error HTTP 400 y el siguiente json  
   ```json{"mensaje" : "No se tiene presupuesto para la fecha X"}```   
   Donde **X** es fecha en formato MM-YYYY  
       
5. Crear un usuario
6. Ingresar un presupuesto para un usurio