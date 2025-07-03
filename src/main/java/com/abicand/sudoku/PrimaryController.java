package com.abicand.sudoku;

import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

public class PrimaryController {
    
//    sebagai skor dari permainan ini
    private int skor =0;
    
    //papan sudoku
    SudokuGen sudoku = new SudokuGen();
    
    //target grid yang jadi papan sudoku dengan fx:id=papanSudoku
    @FXML
    private GridPane papanSudoku;
    
    //menargetkan semua textfield yang ada di papan sudoku
    @FXML
    private final TextField sel[][] = new TextField[9][9];
    
    //menargetkan tombol
    @FXML
    private Button ulangBtn,cekBtn;
    
    //menargetkan label skor dan progress bar
    @FXML
    private Label labelSkor;
    @FXML
    private ProgressBar skorProgress;

    
    @FXML
    public void initialize() {
        
        //fungsi lamda
        ulangBtn.setOnAction((eh)->{
            //hanya ganti teks tombol menjadi ulangi
            if(ulangBtn.getText().equals("Mulai!")){
                ulangBtn.setText("Ulangi");
            //reset papan jika teks tombol bukan mulai
            } else {
                sudoku.resetPapan();
                skor = 0;
            }
            //fungsi memulai permainan
            mulaiGame();
        });
        
        //fungsi lamda yang akan mengecek kebenaran papan sudoku
        cekBtn.setOnAction(eh->{
            cek();
        });
        
        
        //fungsi yang menargetkan semua sel di papan ke variabel sel
        buatSel();
        
    }
    
    
    
    private void buatSel(){
        //di tiap sel yang ada di grid "papanSudoku"
        //ambil posisi tiap sel
        //lalu masukan ke array sel
        //pasang sebuah filter
        //ganti semua teks menjadi teks kosong
        //jangan perbolehkan pengguna untuk mengedit
        for (Node child : papanSudoku.getChildren()) {
            
            //mengambil posisi dari sel berdasarkan baris dan kolom
            Integer b = GridPane.getRowIndex(child);
            Integer k = GridPane.getColumnIndex(child);
            int baris = b == null? 0: b;
            int kolom = k == null? 0: k;
            
            //masukan ke array sel
            TextField selSudoku = (TextField) child;
            sel[baris][kolom] = selSudoku;
            
            //maaf, aku juga tidak tahu mengapa harus berbentuk seperti ini
            //yang penting bentuk ini bekerja
            UnaryOperator<Change> integerFilter = change -> filterNo(change);
            
            //pasang filter yang sudah ditentukan
            selSudoku.setTextFormatter(new TextFormatter<>(new 
                                        IntegerStringConverter(),
                                        0, integerFilter));
            //ganti semua teks menjadi teks kosong
            //jangan perbolehkan pengguna untuk mengedit
            selSudoku.setText(" ");
            selSudoku.setEditable(false);
        }
    }
    
    //sebuah format yang memaksa pengguna untuk hanya memasukan nomor 
    //1-9 dan hanya 1 nomor
    private Change filterNo(Change change) {
        //jika menekan backspace maka langsung perbolehkan
                if (change.isDeleted()){
                    return change;
                }
                
                //hanya memperbolehkan perubahan jika text lolos filter
                String textBaru = change.getControlNewText();
                if (textBaru.matches("[ 1-9]")) {
                    return change;
                }
                
                //tidak melakukan apapun ketika tidak lolos filter
                return null;
    }
    
    
    //fungsi yang akan memulai permainan sudoku ini
    private void mulaiGame(){
        //isi semua sel
        sudoku.mulaiDiIsi();
        
        //hilangkan beberapa sel
        sudoku.mulaiPuzzle();
        
        //isi tiap sel dengan data dari papan sudoku
        //untuk sel yang berisi nilai 0, kosongkan dan buat agar bisa
        //diubah oleh pengguna
        for (int baris=0;baris<9;baris++){
            for (int kolom=0;kolom<9;kolom++){
                String text = "" + sudoku.getSel(baris, kolom);
                sel[baris][kolom].setText(text);
                if (sudoku.getSel(baris, kolom) == 0) {
                    sel[baris][kolom].setText("");
                    sel[baris][kolom].setEditable(true);
                }
            }
        }
        
    }
    
    //bandingkan tiap sel dengan papan yang sudah terisi penuh
    //lalu tambahkan skor dan buat agar tidak bisa diubah
    //kemudian kalkulasi jumlah skor 
    private void cek(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                String text = sel[i][j].getText();
                
                if (text.matches("[1-9]")){
                    if (sudoku.bandingkan(i, j, Integer.parseInt(text))){
                        skor++;
                        sel[i][j].setEditable(false);
                    }
                }
            }
        }
        
        skor = Math.floorDiv((skor *100), 81);
        double skorSekarang = (double) skor/100;
        skorProgress.setProgress(skorSekarang);
        labelSkor.setText("Skor: " + skor);
        skor = 0;
    }
    
}
