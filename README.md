# RPG Battle Simulator

## Overview

This is a turn-based RPG Battle Simulator developed in Java. Players can create two characters by choosing between two available classes: **Warrior** and **Wizard**. Each character is assigned random attribute values such as health points (HP), stamina or mana, and strength or intelligence depending on their class. The battle is a 1v1 fight that happens in rounds. In each round, both characters attack at the same time. This means they can both lose HP, even if one defeats the other. If both characters reach 0 HP in the same round, it's a draw.

## Technologies Used

- Java 
- Standard Java libraries: `java.util.Scanner`, `java.util.Random`

## Project Structure
```
├── BattleSimulator.java // Main entry point and game loop
├── Character.java // Abstract base class (or interface) for all characters
├── Warrior.java // Warrior subclass with stamina-based attacks
├── Wizard.java // Wizard subclass with mana-based attacks
```

## Functionalities & Class Design

This project is built using object-oriented principles with the following structure:

### Interface: `Attacker`

-Defines a common method:
 - `attack(Character target)`: All characters (Warrior and Wizard) implement this method, each with their own logic.

### Abstract Class: `Character`

Base class for all characters in the game. Includes:

- `id` – Auto-generated unique identifier (String)
- `name` – Character name (String)
- `hp` – Health points (int)
 - Random value: 100–200 for Warriors, 50–100 for Wizards
- `isAlive` – Indicates if the character is still alive (boolean)

Includes:
- Constructor with `name` and `hp`
- Getters and setters for all fields

### Class: `Warrior` (extends `Character`)

A physical melee fighter that relies on **stamina** and **strength**:
- `stamina` – Random value between 10–50 (int)
- `strength` – Random value between 1–10 (int)

**Attack logic**:
Each round, the warrior randomly performs a **Heavy Attack** or a **Weak Attack**:
- **Heavy Attack**: Full damage = strength, costs 5 stamina
- **Weak Attack**: Half damage (rounded down), recovers 1 stamina
- If not enough stamina for a weak attack, no damage is dealt and 2 stamina is recovered

### Class: `Wizard` (extends `Character`)

A spellcaster that relies on **mana** and **intelligence**:
- `mana` – Random value between 10–50 (int)
- `intelligence` – Random value between 1–50 (int)

**Attack logic**:
Each round, the wizard randomly casts a **Fireball** or uses a **Staff Hit**:
- **Fireball**: Full damage = intelligence, costs 5 mana
- **Staff Hit**: Deals 2 damage, recovers 1 mana
- If not enough mana for a staff hit, no damage is dealt and 2 mana is recovered

## BattleLogic

- Two characters are created at the beginning of the game by user input.
- The battle is a **1v1 turn-based match**, where both characters attack **simultaneously** each round.
- A round log is printed showing the HP of each character.
- If both characters reach 0 HP in the same round, the battle ends in a **draw** and **automatically restarts** with new stats.
- The combat continues until there is a clear winner.




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
- `CharacterImporter`: clase auxiliar para importar personajes desde CSV

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
