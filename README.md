# 🤖 Artificial Intelligence Projects

This repository contains two laboratory assignments developed for the **Artificial Intelligence course**, focusing on:

* Search algorithms (A*, UCS)
* Game AI (Minimax)

📄 Based on the report: 

---

# 🧠 Exercise 1 – Search Algorithms (A* vs UCS)

## 📌 Description

This exercise compares the performance of:

* **Uniform Cost Search (UCS)**
* **A* (A-Star) Algorithm**

The goal is to solve state-space problems efficiently using heuristic search.

---

## 🔍 Heuristic Function h(n)

The heuristic used combines:

* 📏 Manhattan Distance
* ❌ Number of misplaced tiles

### ✔ Properties

* Does **not overestimate** the cost
* Ensures **optimal solutions (admissible heuristic)**
* Improves search efficiency

---

## ⚡ Results

The A* algorithm significantly reduces the number of node expansions compared to UCS.

### 📊 Example Comparisons

| Case | UCS Expands | A* Expands |
| ---- | ----------- | ---------- |
| 1    | 259,464     | 9,926      |
| 2    | 212,830     | 36,822     |
| 3    | 295,135     | 32,423     |
| 4    | 202,033     | 5,234      |
| 5    | 138,186     | 8,210      |

---

## 🎯 Conclusion

* A* is **more efficient** than UCS
* Reduces search space significantly
* Maintains optimal solutions

---

# 🎮 Exercise 2 – Game AI (Minimax Algorithm)

## 📌 Description

Implementation of a **custom Tic-Tac-Toe–like game** using the **Minimax algorithm**.

---

## 🧩 Game Representation

* Board: `3x3` matrix (`char[3][3]`)
* Symbols:

  * `S` → Player
  * `C` → Computer
  * `E` → Empty

👉 The initial position includes a movable `S` element.

---

## 🏆 Winning Conditions

A player wins if they form:

* `CSE` or `ESC`

in:

* Rows
* Columns
* Diagonals

---

## 🧠 Minimax Algorithm

The computer uses **Minimax** to:

* Evaluate all possible moves
* Choose the optimal move
* Maximize its chances of winning

### Key Methods

* `findBestMove()` → Finds optimal move
* `minimax()` → Recursively evaluates moves

---

## 🏗️ Code Structure

### 📄 TicTacToe Class

* Manages game state
* Methods:

  * `initializeBoard()`
  * `printBoard()`
  * `makeMove()`
  * `checkWin()`
  * `checkDraw()`

---

### 📄 Minimax Class

* Implements AI logic
* Decision-making system

---

### 📄 Main Class

* Controls game flow
* Handles user input
* Connects all components

---

## 🎯 Features

* Player vs Computer gameplay
* AI decision making
* Turn-based system
* Win/draw detection

---

## 🛠️ Technologies

* Java
* Artificial Intelligence Algorithms
* Game Theory

---

## 🚀 Learning Outcomes

This project demonstrates:

* Heuristic search (A*)
* Algorithm comparison (A* vs UCS)
* Game AI using Minimax
* State representation & evaluation
* Decision-making in AI systems

---

## 👥 Authors

* Charalampos Kounnapis

---

## 📚 Notes

* A* provides **better performance** when a good heuristic is used
* Minimax ensures **optimal gameplay decisions**
* Demonstrates core concepts of Artificial Intelligence

---
