package mainPackage.mainClasses.supportPackage;

/**
 * Jednostavan brojac koji broji sekunde i milisekunde.<br/>
 * Napravio cisto da ovo ne moramo da radimo u klasama za igru.<br/>
 * Pozivas metode calculateSec i saljes mu ono DELTA vreme sto imas u gameState.<br/>
 * DELTA vreme je vreme od poslednjeg rendera do trenutnog.<br/>
 * Ovo radi tako sto uzme taj delta, doda ga na milisekunde, i poveca full_time ako je mili >= 0, jer moze lik da posalje 2 umesto 1 za delta vreme.
 * */
public class SecondCounter {
	private int FULL_TIME_IN_SECONDS;
	private int MILLISECONDS;
	
	public SecondCounter(){
		MILLISECONDS = 0;
		FULL_TIME_IN_SECONDS = 0;
	}
	
	private void calculateMilli(int milisec){
		MILLISECONDS += milisec;
	}
	
	public void calculateSec(int milisec){
		calculateMilli(milisec);
		if(MILLISECONDS >= 1000){
			FULL_TIME_IN_SECONDS +=1;
			MILLISECONDS = 0;
		}
	}
	
	public int getSeconds(){
		return FULL_TIME_IN_SECONDS;
	}
	
	public int getMilliseconds(){
		return MILLISECONDS;
	}
}
