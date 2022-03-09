### Caso de negocio ADN Java - Presupuesto

#### Tecnología a usar:
- Java version: 1.8
- Gradle version: 7.0.2
- Spring Boot version: 2.5.3


#### Problema de negocio (Presupuesto)
El objetivo de este desarrollo es aplicar las buenas practicas en el código y aplicar el ciclo completo en el que se inlcuye prubas unitarias CI, test entre otros en caso de negicio ADN Java

***Usuario tendrá los siguientes atributos***:  
**identificacionUsuario**: número de identificacion del usuario (campo alfanumérico de máximo 15 dígitos)  
**nombre**: string que tendrá el nombre o nombres del usuario (string de máximo 100 caracteres)  
**apellido**: string que tendrá los apellidos del usuario (string de máximo 100 caracteres)   

***Un presupuesto debe tener lo siguientes atributos:***  
**fechaPresupuesto**: fecha que tendrá el año y mes (formato YYYY/DD)  
**valorPresupuesto**: número que tendrá el presupuesto por ejecutar máximo en el mes en curso (máximo 7 dígitos)  

***Registro de gastos que tendrá los siguientes atributos:***
**fechaGasto**: fecha en la cual aplica la ejecución presupuestal (fomato YYYY/MM/DD)  
**valorGasto**: número que tiene el valor del gasto ejecutado (máximo 7 dígitos)

#### Objetivo
Crear un API tipo REST el cual le permita llevar a cabo la siguientes funcionalidades
1. Usuario
   1. crear
      1. todos los campos obligatorios
      2. validaciones y restricciones
         1. tamaños máximos
         2. formatos alfanumericos donde corresponde
   2. eliminar
   3. actualizar
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