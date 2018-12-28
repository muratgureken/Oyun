package KelimeBul;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\MGUREKEN\\eclipse-workspace\\Oyun\\src\\KelimeBul\\TDK_Sözlük_Kelime_Listesi.txt");
		int onlineKullanici=5, fileLineSize=57398, count=-1, rastgeleSatir;
		boolean dosyaOku=true;
		Scanner scan = new Scanner(System.in);

		Random rand = new Random();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		//run>run configuration>common altýnda Encoding UTF-8 yapýlýrsa Türkçe karakterler çýkýyor.
		String kelime;
		String [] sozluk= new String[fileLineSize+1];
		String satir;
		
		while(((satir=br.readLine())!=null))
		{
			//sozlukten uzunlugu 5 ve ustu olan tek kelimeleri sec (birden fazla kelime olmasin)
			if(!(satir.contains("	"))&&!(satir.contains(" "))&&(satir.length()>=5))
			{
				count++;
				sozluk[count]=satir;
			}
		}		
		
		br.close();
		
		/*for(int i=0;i<count;i++)
		{
			System.out.println(sozluk[i]);
		}*/
		
		for(int i=0;i<onlineKullanici;i++)
		{
			rastgeleSatir = rand.nextInt(count);
			kelime = sozluk[rastgeleSatir];
			new KelimeBul(kelime, scan);
		}
		
		scan.close();		
	}
}
