package dama;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
            boolean oyunDevam=true,tasSecimiDevam=true, zorunluHamleDevam=true;
            int oyuncuSirasi=1;
            Scanner scan;
            String tasYeri;
            
            scan = new Scanner(System.in);
            Oyuncu oyuncu1 = new Oyuncu();
            Oyuncu oyuncu2 = new Oyuncu();
            dama.secilenTasKonum = -1;
            dama.damaMasasiCiz();
            
            while(oyunDevam)
            {                
                if(oyuncuSirasi==1)
                {
                    zorunluHamleDevam = true;
                    tasSecimiDevam=true;
                    System.out.println("1. Oyuncu icin tas secimi yapin.");
                    tasYeri = scan.next();
                    dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);
                    oyuncu1.hamleleriHesapla();
                    dama.damaMasasiCiz();
                    //while icerisine yaz
                    while(tasSecimiDevam)
                    {
                        System.out.println("Hamle seciniz");
                        tasYeri = scan.next();
                        if(oyuncu1.hamleYap(tasYeri))
                        {
                            tasSecimiDevam = false;
                        }
                        else
                        {
                            System.out.println("Gecersiz hamle.");
                            tasSecimiDevam = true;
                        }
                    }
                    dama.damaMasasiCiz();
                    
                    //ilk hamleden sonraki hamleler icin zorunlu hamle var mi kontrolu yapilacak.
                    //artik static secilenTasKonum, hamle yapilan yerle guncellendi. Bir daha deger yazmaya gerek yok.
                    //ilk hamlede zorunlu hamle varsa asagidaki islemler yapilir. normal bir tas hareketi ise asagidaki islemler yapilmaz 
                    if(dama.zorunluHamleVar==1)
                    {
                        while(zorunluHamleDevam)
                        {
                            tasSecimiDevam = true;
                            
                            oyuncu1.hamleleriHesapla();
                            //zorunlu hamle yapilmasi gerekiyor mu?
                            if(dama.zorunluHamleVar==1)
                            {
                                zorunluHamleDevam = true;
                                dama.damaMasasiCiz();
                                //while icerisine yaz
                                while(tasSecimiDevam)
                                {
                                    System.out.println("Hamle seciniz");
                                    tasYeri = scan.next();
                                    if(oyuncu1.hamleYap(tasYeri))
                                    {
                                        tasSecimiDevam = false;
                                    }
                                    else
                                    {
                                        System.out.println("Gecersiz hamle.");
                                        tasSecimiDevam = true;
                                    }
                                }
                                dama.damaMasasiCiz();
                            }
                            else
                            {
                                zorunluHamleDevam = false;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("2. Oyuncu icin tas secimi yapin.");
                    tasYeri = scan.next();
                    dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);       
                }
            }
	}

}
