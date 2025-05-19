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
