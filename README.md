# RPG Battle Simulator

Este proyecto es un simulador de batalla RPG en Java que enfrenta a dos personajes: un **Guerrero** o un **Mago**. Cada uno tiene sus propias estadísticas y estilo de combate. El simulador ejecuta la batalla y determina un ganador, mostrando todos los eventos por consola.

---

## Estructura del Proyecto

### Clases principales:
- `Character` (abstracta): clase base para todos los personajes
- `Warrior`: personaje físico que usa fuerza y resistencia (stamina)
- `Wizard`: personaje mágico que usa inteligencia y maná
- `Attacker` (interfaz): define el método `attack()`
- `BattleSimulator`: lógica principal que simula la batalla
- `CharacterGenerator`: clase auxiliar para importar personajes desde CSV

---

##  Lógica de Combate

###  Guerrero
- Puede hacer **ataques fuertes** (consume 5 de stamina) o **débiles** (mitad de fuerza, recupera 1 de stamina)
- Si no tiene stamina suficiente, no ataca y recupera 2 puntos

###  Mago
- Puede lanzar una **Bola de Fuego** (consume 5 de maná) o dar un **Golpe de Báculo** (daño fijo de 2, recupera 1 de maná)
- Si no tiene maná suficiente, no ataca y recupera 2 puntos

###  Inteligencia de combate
- Ambos personajes atacan en cada ronda
- El daño se aplica incluso si uno muere
- En caso de empate (ambos mueren en la misma ronda), el combate se reinicia

---

##  Ejecución

Para ejecutar el programa:

```
Run BattleSimulator
```

Desde ahí podrás elegir entre:

1. Crear personajes manualmente
2. Cargar personajes desde un archivo CSV
3. Generar personajes aleatorios para una batalla automática

---

##  Requisitos Técnicos

- Java 8 o superior
- No requiere librerías externas
- Compatible con IntelliJ IDEA y otras IDEs

---

## Funcionalidades y diseño de clases
Este proyecto está construido utilizando principios de programación orientada a objetos con la siguiente estructura:

### Interfaz: Attacker
-Define un método común:

 -attack(Character target): Todos los personajes (Guerrero y Mago) implementan este método, cada uno con su propia lógica.

### Clase abstracta: Character
Clase base para todos los personajes en el juego. Incluye:

- id – Identificador único generado automáticamente (String)
- name – Nombre del personaje (String)
- hp – Puntos de salud (int)
- Valor aleatorio: 100–200 para Guerreros, 50–100 para Magos
- isAlive – Indica si el personaje sigue con vida (boolean)

Incluye:
- Constructor con name y hp
- Métodos getter y setter para todos los campos

### Clase: Warrior (extiende Character)
Un luchador físico cuerpo a cuerpo que depende de resistencia y fuerza:

- stamina – Valor aleatorio entre 10–50 (int)
- strength – Valor aleatorio entre 1–10 (int)

Lógica de ataque:
Cada ronda, el guerrero realiza aleatoriamente un Ataque Pesado o un Ataque Débil:

- Ataque Pesado (**Heavy Attack** ): Daño total = fuerza, cuesta 5 de resistencia
- Ataque Débil (**Weak Attack**): Daño a la mitad (redondeado hacia abajo), recupera 1 de resistencia

Si no hay suficiente resistencia para un ataque débil, no se realiza daño y se recuperan 2 de resistencia

### Clase: Wizard (extiende Character)
Un hechicero que depende de maná e inteligencia:

- mana – Valor aleatorio entre 10–50 (int)
- intelligence – Valor aleatorio entre 1–50 (int)

Lógica de ataque:
Cada ronda, el mago lanza aleatoriamente un Bola de Fuego o usa un Golpe de Bastón:

- Bola de Fuego: Daño total = inteligencia, cuesta 5 de maná
- Golpe de Bastón: Realiza 2 de daño, recupera 1 de maná

Si no hay suficiente maná para un golpe de bastón, no se realiza daño y se recuperan 2 de maná

##  Autores

Este proyecto fue realizado por el grupo 3 como parte del curso de desarrollo Java.

---

##  Tareas adicionales implementadas (bonus)

- [x] Importación de personajes desde archivo CSV
- [x] Reinicio automático en caso de empate
- [x] Menú interactivo con consola
- [x] Generación de personajes aleatorios

---

##  Ejemplo de salida esperada

```
Round 1
Wizard attacks Warrior with Fireball for 30 damage.
Warrior attacks Wizard with Strong Attack for 8 damage.

Round 2
Wizard attacks Warrior with Staff for 2 damage.
Warrior is out of stamina. Recovers 2 stamina.

...
Winner: Wizard 
```

---

##  Estructura de Carpetas

```
├── Character.java
├── Warrior.java
├── Wizard.java
├── Attacker.java
├── BattleSimulator.java
├── CharacterImporter.java
├── character.csv
├── .gitignore
├── README.md
```

---

Hasta la próxima!


