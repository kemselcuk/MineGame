import java.sql.PseudoColumnUsage;
import java.util.*;
public class MineSweeper {
    static String[][] gamePlay(String gameMap[][],String mineMap[][],int x,int y,int satir,int sutun){
        // Bu fonksiyon kullanıcının sectigi konumun etrafındaki mayın sayısını hesaplıyor.
        int a =0;
        if(mineMap[satir][sutun].equals("*")){
            gameMap[satir][sutun]="*";
        }else{
            if (((satir-1)>=0)&&((sutun-1)>=0)&&(mineMap[satir-1][sutun-1].equals("*"))){
                a++;
            }
            if(((satir-1)>=0)&&(mineMap[satir-1][sutun].equals("*"))){
                a++;
            }
            if(((satir-1)>=0)&&((sutun+1)<y)&&(mineMap[satir-1][sutun+1].equals("*"))){
                a++;
            }
            if(((sutun-1)>=0)&&(mineMap[satir][sutun-1].equals("*"))){
                a++;
            }
            if(((sutun+1)<y)&&(mineMap[satir][sutun+1].equals("*"))){
                a++;
            }
            if(((satir+1)<x)&&((sutun-1)>=0)&&(mineMap[satir+1][sutun-1].equals("*"))){
                a++;
            }
            if(((satir+1)<x)&&(mineMap[satir+1][sutun].equals("*"))){
                a++;
            }
            if(((satir+1)<x)&&((sutun+1)<y)&&(mineMap[satir+1][sutun+1].equals("*"))){
                a++;
            }
            gameMap[satir][sutun]= String.format("%d",a);
        }
    return gameMap;
    }
    static String game(String gameMap[][],String mineMap[][],int x,int y,int n){
        // Bu fonksiyon oyun,kazanma ve kaybetmeleri belirliyor.
        Scanner sc = new Scanner(System.in);
        String s="";
        String[][] playMap = new String[x][y];
        int i = 0,d=0,satir,sutun;
        while (i == 0) {
            d++;
            if (d == ((x * y) - n)+1) {
                i = 1;
                System.out.print("---------------------------\n");
                printMap(playMap, x, y);
                System.out.print("---------------------------\n");
                s = "Kazandınız.Tebrikler!!!";
                System.out.print("\nMayınların konumu\n");
                System.out.print("---------------------------\n");
                printMap(mineMap, x, y);
                System.out.print("---------------------------\n");
            } else{
                System.out.print("Satır girin: ");
                satir = sc.nextInt();
                while ((satir < 1) || (satir > x)) {
                    System.out.print("Hatalı giriş! Tekrar Satır girin: ");
                    satir = sc.nextInt();
                }
                satir--;
                System.out.print("Sutun girin: ");
                sutun = sc.nextInt();
                while ((sutun < 1) || (sutun > y)) {
                    System.out.print("Hatalı giriş! Tekrar sütun girin: ");
                    sutun = sc.nextInt();
                }
                sutun--;
                playMap = gamePlay(gameMap, mineMap, x, y, satir, sutun);
                if ((playMap[satir][sutun].equals("1")) || (playMap[satir][sutun].equals("2")) || (playMap[satir][sutun].equals("3"))) {
                    i = 0;
                    System.out.print("---------------------------\n");
                    printMap(playMap, x, y);
                    System.out.print("---------------------------\n");
                } else if ((playMap[satir][sutun].equals("4")) || (playMap[satir][sutun].equals("5")) || (playMap[satir][sutun].equals("6"))) {
                    i = 0;
                    System.out.print("---------------------------\n");
                    printMap(playMap, x, y);
                    System.out.print("---------------------------\n");
                } else if ((playMap[satir][sutun].equals("7")) || (playMap[satir][sutun].equals("8")) || (playMap[satir][sutun].equals("0"))) {
                    i = 0;
                    System.out.print("---------------------------\n");
                    printMap(playMap, x, y);
                    System.out.print("---------------------------\n");
                } else if ((playMap[satir][sutun].equals("k"))) {
                    i = 1;
                    System.out.print("---------------------------\n");
                    printMap(playMap, x, y);
                    System.out.print("---------------------------\n");
                    s = "Mayın patladı!Kaybettiniz!!";
                    System.out.print("\nMayınların konumu\n");
                    System.out.print("---------------------------\n");
                    printMap(mineMap, x, y);
                    System.out.print("---------------------------\n");
                }
            }
        }
        return s;
    }
    static String[][] mineLoc(String map[][],int n,int x,int y){
        //Bu fonksiyon mayınların konumlarını belirliyor.
        Random rand = new Random();
        int a,b;
        for (int i = 0; i < n; i++) {
            a = rand.nextInt(x);
            b = rand.nextInt(y);
            for (int j = 0; j < x; j++) {
                for (int k = 0; k < y; k++) {
                    if((a==j)&&(b==k)) {
                        map[j][k] = "*";
                    }
                }
            }
        }
        return map;
    }
    static String[][] mineMap(int x,int y,int n){
        //Bu fonksiyon oyun mapi ile karşılaştırma için mayın mapi oluşturuyor.
        Scanner sc = new Scanner(System.in);
        String[][] map = new String[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                    map[i][j] = "-";
            }
        }
        map = mineLoc(map,n,x,y);
        return map;
    }
    static String[][] emptyMap(int x,int y){
        //Bu fonksiyon boş harita oluşturuyor.
        String[][] map = new String[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = "-";
            }
        }
        return map;
    }
    static void printMap(String[][] map,int x,int y){
        // Bu fonksiyon maplari ekrana yazdırıyor.
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print("   "+map[i][j]+"   ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sec,x,y,n;
        do{
            String s = "devam";
            System.out.println("Oyun Başlıyor...");
            System.out.println("--Oyun haritası boyutlarını belirlemek için istenen değerleri giriniz--");
            System.out.print("Satır Sayısı : ");
            x = sc.nextInt();
            System.out.print("Sütun Sayısı : ");
            y = sc.nextInt();
            String[][] mineMap;
            String[][] gameMap;
            n = ((x*y)/4);
            mineMap = mineMap(x,y,n);
            gameMap = emptyMap(x,y);
            System.out.print("---------------------------\n");
            printMap(gameMap,x,y);
            System.out.print("---------------------------\n");
            while(s.equals("devam")){
                s = game(gameMap, mineMap, x, y,n);
                System.out.print(s);
            }
            System.out.print("\nYeni oyuna başla : [1]\n" +
                             "Çıkış yap : [0]\n" +
                             "Seçim : ");
            sec = sc.nextInt();
        }while (sec==1);
    }
}
