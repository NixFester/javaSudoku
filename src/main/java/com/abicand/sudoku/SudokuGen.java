package com.abicand.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SudokuGen {
    private static final int LIMIT_ATAS = 9; //sebagai limit dari angka
                                             //yang mungkin diproduksi
    
    private static final int LIMIT_SUBGRID = 3; //untuk pengecekan tiap kotak
    
    //papan permainan dan duplikatnya
    private final int[][] papan = new int[LIMIT_ATAS][LIMIT_ATAS];
    private final int papanDuplikat[][] = new int[LIMIT_ATAS][LIMIT_ATAS];

    // Isi semua sel sudoku dengan angka acak
    private boolean isiSel() {
        for (int baris = 0; baris < LIMIT_ATAS; baris++) {
            for (int kolom = 0; kolom < LIMIT_ATAS; kolom++) {
                
                //pengecek sel kosong dan gerbang pertama
                //ketika tidak ada lagi sel yang kosong maka program
                // akan terus berjalan tanpa memanggil dirinya sendiri lagi
                if (papan[baris][kolom] == 0) {
                    
                    //array angka acak dari 1-9
                    //array ini akan terus dibuat dengan yang baru di tiap 
                    //pengulangan dan yang lama tidak dihapus
                    Integer[] numbers = buatAngkaAcak();
                    
                    //mengecek semua kemungkinan angka yang telah diacak tadi
                    for (int number : numbers) {
                        
                        //pengecekan kevalidan dan gerbang kedua
                        if (isValid(baris, kolom, number)) {
                            
                            //jika valid maka ganti sel yang kosong dengan
                            //angka acak di variabel number
                            papan[baris][kolom] = number;
                            
                            //memulai ulang fungsi, gerbang ketiga
                            //bayangkan sebuah portal di samping gerbang
                            //kata sandi portal itu adalah angka yang tadi
                            if (isiSel()) {
                                //kunci yang berada di akhir perjalanan
                                return true;
                            } else {
                                //hapus kata sandi sebelumnya karena 
                                //kata sandi itu membuat portal selanjutnya
                                //mengalami kegagalan
                                papan[baris][kolom] = 0; // backtrack
                            }
                        }
                        //jika tidak valid maka angka selanjutnya
                        //di array angka acak
                    }
                    //jika program sampai sini maka artinya program melakukan
                    //backtrack
                    //bayangkan semua kata sandi yang kita coba untuk portal
                    //salah semua dan kita harus kembali ke portal sebelumnya
                    //lalu mencoba sandi baru
                    return false;
                }
                
            }
        }
        //program akan mencapai titik ini ketika tidak ada lagi sel yang kosong
        //artinya gerbang pertama ditutup dan ada seorang malaikat yang 
        //memberikan kita kunci dari gerbang sebelum portal
        return true; 
    }
    
    public void mulaiDiIsi(){
        isiSel();
        for (int i=0;i<9;i++){
            System.arraycopy(papan[i], 0, papanDuplikat[i], 0, 9);
        }
    }
    
    //membuat array angka dari 1-9 lalu mengacaknya
    private Integer[] buatAngkaAcak() {
        Integer[] angka = new Integer[LIMIT_ATAS];
        for (int i = 0; i < LIMIT_ATAS; i++) {
            angka[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(angka));
        return angka;
    }

    // mengecek kevalidan. Jika semua valid maka akan mengembalikan true
    private boolean isValid(int baris, int kolom, int angka) {
        return !dalamBaris(baris, angka) && !dalamKolom(kolom, angka) 
                && !dalamKotak(baris, kolom, angka);
    }
    
    //cek semua baris apakah ada angka yang sama?
    private boolean dalamBaris(int baris, int angka) {
        for (int kolom = 0; kolom < LIMIT_ATAS; kolom++) {
            if (papan[baris][kolom] == angka) {
                return true;
            }
        }
        return false;
    }
    //cek semua kolom apakah ada angka yang sama?
    private boolean dalamKolom(int kolom, int number) {
        for (int baris = 0; baris < LIMIT_ATAS; baris++) {
            if (papan[baris][kolom] == number) {
                return true;
            }
        }
        return false;
    }
    
    //cek sel dalam wilayak kotaknya apakah ada yang sama?
    private boolean dalamKotak(int baris, int kolom, int number) {
        int boxbarisStart = baris - baris % LIMIT_SUBGRID;
        int boxkolomStart = kolom - kolom % LIMIT_SUBGRID;

        for (int i = 0; i < LIMIT_SUBGRID; i++) {
            for (int j = 0; j < LIMIT_SUBGRID; j++) {
                if (papan[boxbarisStart + i][boxkolomStart + j] == number) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //ambil sel berdasarkan lokasi array
    public int getSel(int baris,int kolom){
        return papan[baris][kolom];
    }
    
    //sembunyikan beberapa nomor
    //tahap ini masih level kesulitan mudah
    // yaitu 32 - 41 sel kosomg
    public void mulaiPuzzle(){
        Random rand = new Random();
        int kosongMaks = rand.nextInt(10) + 32;
        int lokasiSelKosong[] = new int[kosongMaks];
        int nomorRand;
        for (int i = 0; i< lokasiSelKosong.length; i++) {
            nomorRand = rand.nextInt(80) + 1;
            if (checkArray(lokasiSelKosong,nomorRand)){
                do {
                    nomorRand = rand.nextInt(80) + 1;
                } while (checkArray(lokasiSelKosong,nomorRand));
                lokasiSelKosong[i] = nomorRand;
            } else {
                lokasiSelKosong[i] = nomorRand;
            }
        }
        
        for (int isi:lokasiSelKosong){
            int baris =  Math.floorDiv(isi,9);
            int kolom = isi % 9;
            papan[baris][kolom] = 0;
            
        }
        
    }
    
    //cek array apakah ada yang sama v1
    private boolean checkArray(int arr[],int nomor){
        for (int isi: arr){
            if (isi == nomor){
                return true;
            }
        }
        return false;
    }
    
    public void resetPapan(){
        for (int i=0; i<9;i++){
            for (int k =0;k<9;k++){
                papan[i][k] = 0;
                papanDuplikat[i][k]= 0;
            }
        }
    }
    
    //bandingkan
    public boolean bandingkan(int baris,int kolom, int nomor){
        return papanDuplikat[baris][kolom] == nomor;
    }
    
    // print semua sel Sudoku 
    public void printSel() {
        for (int baris = 0; baris < LIMIT_ATAS; baris++) {
            for (int kolom = 0; kolom < LIMIT_ATAS; kolom++) {
                System.out.print(papan[baris][kolom] + " ");
            }
            System.out.println();
        }
    }
}
