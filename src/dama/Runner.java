package dama;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		boolean oyunDevam=true,tasSecimiDevam=true, zorunluHamleDevam=true;
		int oyuncuSirasi=1, digerOyuncuTasSayisi=0;
		Scanner scan;
		String tasYeri;

		scan = new Scanner(System.in);
		Oyuncu oyuncu1 = new Oyuncu();
		Oyuncu oyuncu2 = new Oyuncu();
		dama.secilenTasKonum = -1;
		dama.damaMasasiCiz();

		System.out.println("baslangic oyuncu tipleri: oyuncu1:"+oyuncu1.oyuncuTipi+ " oyuncu2:"+oyuncu2.oyuncuTipi);
		
		while(oyunDevam)
		{                
			if(oyuncuSirasi==1)
			{
				zorunluHamleDevam = true;
				tasSecimiDevam=true;
				System.out.println("1. Oyuncu (q/Q) icin tas secimi yapin:"+oyuncu1.oyuncuTipi);
				while(tasSecimiDevam)
				{
					tasYeri = scan.next();
					dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);
					System.out.println("secilen konum:"+dama.secilenTasKonum);
					tasSecimiDevam = false;

					if(!(oyuncu1.hamleleriHesapla()))
					{
						System.out.println("Yanlis yer sectiniz. Kendi tasinizi secin !");	
						tasSecimiDevam = true;
					}
				}
				tasSecimiDevam = true;
				
				dama.damaMasasiCiz();
				//while icerisine yaz
				while(tasSecimiDevam)
				{
					System.out.println("Hamle seciniz");
					tasYeri = scan.next();
					if(oyuncu1.hamleYap(tasYeri))
					{
						tasSecimiDevam = false;
						digerOyuncuTasSayisi = oyuncu1.DigerOyuncuSonTasDamaYap();
					}
					else
					{
						System.out.println("Gecersiz hamle.");
						tasSecimiDevam = true;
					}
				}
				dama.damaMasasiCiz();
				if(digerOyuncuTasSayisi==0)
				{
					System.out.println("Oyun bitti, 1. Oyuncu Kazandi, TEBRIKLER :)");
					oyunDevam = false;
				}
				else
				{
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
								System.out.println("1");
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
								System.out.println("2");
								dama.damaMasasiCiz();

							}
							else
							{
								zorunluHamleDevam = false;
							}
						}
					}
					oyuncuSirasi = 2;
					dama.hamleleriSil();
				}		
			}
			else
			{
				zorunluHamleDevam = true;
				tasSecimiDevam=true;
				System.out.println("2. Oyuncu (t/T) icin tas secimi yapin:"+oyuncu1.oyuncuTipi);
				while(tasSecimiDevam)
				{
					tasYeri = scan.next();
					dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);
					tasSecimiDevam = false;
					if(!(oyuncu2.hamleleriHesapla()))
					{
						System.out.println("Yanlis yer sectiniz. Kendi tasinizi secin !");	
						tasSecimiDevam = true;
					}
				}
				tasSecimiDevam = true;
				dama.damaMasasiCiz();
				//while icerisine yaz
				while(tasSecimiDevam)
				{
					System.out.println("Hamle seciniz");
					tasYeri = scan.next();
					if(oyuncu2.hamleYap(tasYeri))
					{
						tasSecimiDevam = false;
						digerOyuncuTasSayisi = oyuncu2.DigerOyuncuSonTasDamaYap();
					}
					else
					{
						System.out.println("Gecersiz hamle.");
						tasSecimiDevam = true;
					}
				}
				dama.damaMasasiCiz();
				if(digerOyuncuTasSayisi==0)
				{
					System.out.println("Oyun bitti, 2. Oyuncu Kazandi, TEBRIKLER :)");
					oyunDevam = false;
				}
				else
				{
					//ilk hamleden sonraki hamleler icin zorunlu hamle var mi kontrolu yapilacak.
					//artik static secilenTasKonum, hamle yapilan yerle guncellendi. Bir daha deger yazmaya gerek yok.
					//ilk hamlede zorunlu hamle varsa asagidaki islemler yapilir. normal bir tas hareketi ise asagidaki islemler yapilmaz 
					if(dama.zorunluHamleVar==1)
					{
						while(zorunluHamleDevam)
						{
							tasSecimiDevam = true;
							oyuncu2.hamleleriHesapla();
							
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
									if(oyuncu2.hamleYap(tasYeri))
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
					oyuncuSirasi = 1;
					dama.hamleleriSil();
				}       
			}
		}
		scan.close();
	}

}
