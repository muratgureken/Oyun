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

		//System.out.println("baslangic oyuncu tipleri: oyuncu1:"+oyuncu1.oyuncuTipi+ " oyuncu2:"+oyuncu2.oyuncuTipi);

		while(oyunDevam)
		{                
			if(oyuncuSirasi==1)
			{
				zorunluHamleDevam = true;
				tasSecimiDevam=true;

				//oyuncunun hamle yapabilecek tasi yoksa uyari ver ve diger oyuncuya gec.
				if(!oyuncu1.secilebilenTaslariBelirle())
				{
					System.out.println("1. Oyuncunun yapabilecegi hamle yok. 2. oyuncuya geciliyor !...");
					oyuncuSirasi = 2;
					tasSecimiDevam = false;
				}

				while(tasSecimiDevam)
				{
					System.out.println(oyuncu1.oyuncuTipi+". Oyuncu (q/Q) icin tas secimi yapin:");
					tasYeri = scan.next();
					dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);
					tasSecimiDevam = false;

					//kullanicinin sectigi tas gecerli mi
					if(oyuncu1.secilenKonumGecerliMi()==1)
					{
						System.out.println("Bu tas secilemez, tas yiyebilecek baska taslar var !...");
						tasSecimiDevam = true;
					}
					else if(oyuncu1.secilenKonumGecerliMi()==2)
					{
						System.out.println("Bu tasin yapabilecegi hamle yok !...");
						tasSecimiDevam = true;
					}
					else if(oyuncu1.secilenKonumGecerliMi()==3)
					{
						System.out.println("Secilen tas 1. Oyuncuya ait degil. 1. Oyuncunun tasini secin !...");
						tasSecimiDevam = true;
					}
				}

				dama.hamleleriSil();
				
				if(oyuncuSirasi==1)
				{	
					oyuncu1.hamleleriHesapla();

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
									System.out.println();
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
						oyuncuSirasi = 2;
						dama.hamleleriSil();
					}	
				}
			}
			else
			{
				zorunluHamleDevam = true;
				tasSecimiDevam=true;

				//oyuncunun hamle yapabilecek tasi yoksa uyari ver ve diger oyuncuya gec.
				if(!(oyuncu2.secilebilenTaslariBelirle()))
				{
					System.out.println("2. Oyuncunun yapabilecegi hamle yok. 1. oyuncuya geciliyor !...");
					oyuncuSirasi = 1;
					tasSecimiDevam = false;
				}
				
				while(tasSecimiDevam)
				{
					System.out.println(oyuncu2.oyuncuTipi+". Oyuncu (q/Q) icin tas secimi yapin:");
					tasYeri = scan.next();
					dama.secilenTasKonum = dama.tasYeri2MasaKonumu(tasYeri);
					tasSecimiDevam = false;

					//kullanicinin sectigi tas gecerli mi
					if(oyuncu2.secilenKonumGecerliMi()==1)
					{
						System.out.println("Bu tas secilemez, tas yiyebilecek baska taslar var !...");
						tasSecimiDevam = true;
					}
					else if(oyuncu2.secilenKonumGecerliMi()==2)
					{
						System.out.println("Bu tasin yapabilecegi hamle yok !...");
						tasSecimiDevam = true;
					}
					else if(oyuncu2.secilenKonumGecerliMi()==3)
					{
						System.out.println("Secilen tas 2. Oyuncuya ait degil. 2. Oyuncunun tasini secin !...");
						tasSecimiDevam = true;
					}
				}

				dama.hamleleriSil();

				if(oyuncuSirasi==2)
				{
					oyuncu2.hamleleriHesapla();

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
									System.out.println();
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
		}
		scan.close();
	}
}
