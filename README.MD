# 🎮 Praxi Facit Perfectum

A text-based RPG adventure game built in Java. "Praxi Facit Perfectum" (Practice Makes Perfect) is a terminal-based
role-playing game that combines classic RPG elements with simple yet engaging gameplay.

## 🚀 Features

- 🎭 Character creation and development
- ⚔️ Turn-based combat system
- 👾 Dynamic enemy generation
- 🏕️ Rest and recovery mechanics

## 🎯 Feature Backlog

1. 🎒 Inventory system
2. 💫 Character classes 
3. ✨ Special abilities and magic
4. 💰 Economy
5. 📈 Experience and leveling system
6. 💾 Save/Load functionality

## 🛠️ Installation

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Git (optional, for cloning)

### Setup

1. Clone the repository:

```bash
git clone https://github.com/mr-andrej/Praxi-Facit-Perfectum-RPG.git
cd Praxi-Facit-Perfectum-RPG
```

2. Compile the game (or run build.sh to build and run it automatically):

```bash
cd src/
javac -d build src/main/java/com/mrandrej/rpg/*.java src/main/java/com/mrandrej/rpg/entity/*.java src/main/java/com/mrandrej/rpg/map/*.java
```

3. Run the game:

```bash
java -cp build main.java.com.mrandrej.rpg.Main
```

## 🎮 How to Play

1. Start the game
2. Enter your character's name
3. Use the following commands in the main menu:
    - `1`: Show Player Status
    - `2`: View Map
    - `3`: Travel
    - `4`: Explore Current Area
    - `5`: Rest
    - `6`: Quit

During combat:

- `1`: Attack enemy
- `2`: Attempt to run

## 🏗️ Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── mrandrej/
│               └── rpg/
│                   ├── Main.java
│                   ├── Game.java        # Game logic
│                   └── entity/          # Game entities
│                   └── items/           # Currently unused
│                   └── map/             # World map logic
```

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a new branch
3. Make your changes
4. Submit a pull request

## 🎓 Learning Purpose

This project was created as a Java practice exercise, demonstrating:

- Object-Oriented Programming principles
- Inheritance and abstraction
- Game state management
- User input handling
- Basic game mechanics

## ⚠️ Development Status

This project is currently in active development. Features and mechanics may change as development progresses.
I also reserve the right to break things due to my own sheer incompetence or lack of foresight.
