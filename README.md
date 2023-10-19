# battleship_war
![image](https://github.com/isaacanteparac/battleship_war/assets/69361351/f946c62e-a944-4908-a14a-2cd0d01c92ea)
![image](https://github.com/isaacanteparac/battleship_war/assets/69361351/569aebed-4c5c-418f-b69f-cb340cadc88f)

       
 - [] Implementar la restricción de no permitir ataques diagonales. Puedes lograr esto limitando las opciones del usuario para seleccionar la posición final del ataque. Puedes considerar la adición de botones o proporcionar opciones múltiples (fila, columna).

 - [x] Abordar el problema con los barcos de tamaño mayor a 2, ya que pueden salirse de la matriz. Considera implementar el código proporcionado por Melanie para solucionar este problema.

 - [] Trabajar en la funcionalidad de los ataques al enemigo. Cada botón debe llamar a un método en la clase Singleton para realizar el ataque. Asegúrate de que la actualización de los componentes de la interfaz de usuario no tenga retrasos y que el ancho del registro de ataques sea apropiado.

 - [x] Cuando se realiza un ataque, envía la columna y la fila correspondiente junto con el tipo de bomba utilizada. Además, asegúrate de cambiar el color de la celda afectada para indicar el resultado del ataque.

 - [x] En cada instancia de socket, incluye una variable para confirmar que el tablero ha sido creado y que los barcos han sido colocados correctamente antes de comenzar el juego.

 - [] Cuando se gana el juego, asegúrate de eliminar todos los componentes de la interfaz de usuario y mostrar un mensaje en grande que indique la victoria.