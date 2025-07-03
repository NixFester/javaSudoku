# 🧩 Sudoku in Java | 🇮🇩 Sudoku dalam Java

A fully playable Sudoku game built with **Java** and **JavaFX**.  
Sebuah game Sudoku yang sepenuhnya dapat dimainkan, dibuat menggunakan **Java** dan **JavaFX**.

---

## ✨ Features | Fitur

**EN:**
- ✅ Dynamic score and progress bar based on the number of correct digits
- 🔎 "Check" feature to verify correctly placed numbers
- 🎲 "Restart" button to reset and randomize the Sudoku board
- 🎨 Clean UI styled with CSS and FXML views

**ID:**
- ✅ Skor dan progress bar yang mengisi berdasarkan jumlah angka yang benar
- 🔎 Tombol "Check" untuk memeriksa angka yang ditempatkan dengan benar
- 🎲 Tombol "Restart" untuk mengacak dan mengulang papan Sudoku
- 🎨 Antarmuka rapi dengan styling dari CSS dan FXML

---

## 📸 Screenshots

- **Main Interface / Tampilan Utama**  
  ![Main UI](Sudoku.png)

- **Before Starting / Sebelum Memulai**  
  ![Before Start](Sudoku1.png)

- **After Starting / Setelah Memulai**  
  ![After Start](Sudoku2.png)

- **After Checking / Setelah Diperiksa**  
  ![After Check](Sudokucek.png)

---

## 🛠 Built With | Dibuat Dengan

- Java 11+
- JavaFX 13
- Maven (`javafx-maven-plugin`)
- FXML untuk layout
- CSS untuk styling

---

## 📦 How to Build & Run | Cara Build dan Menjalankan

**EN:**
```bash
# Clone the repo
git clone https://github.com/yourusername/sudoku-javafx.git
cd sudoku-javafx

# Build the project
mvn clean package

# Run the app
mvn javafx:run
```

**ID:**
```bash
# Clone repositori
git clone https://github.com/yourusername/sudoku-javafx.git
cd sudoku-javafx

# Build proyek
mvn clean package

# Jalankan aplikasi
mvn javafx:run
```

💡 *If you want to run the JAR manually, make sure to include JavaFX on the module path.*  
💡 *Jika ingin menjalankan file JAR secara manual, pastikan sudah menyertakan JavaFX di module path.*

---

## 📁 Project Structure | Struktur Proyek

```
src/
├── main/
│   ├── java/
│   │   └── com.abicand.sudoku/
│   │       ├── App.java
│   │       ├── SudokuGen.java
│   │       ├── PrimaryController.java
│   │       └── SecondaryController.java
│   └── resources/
│       └── com.abicand.sudoku/
│           ├── sudoku.fxml
│           ├── primary.fxml
│           ├── secondary.fxml
│           └── sudoku.css
└── module-info.java
```
