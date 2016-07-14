package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.Flags;

public class Declaration {

	private ArrayList<Integer> value = new ArrayList<>();
	private ArrayList<Integer> to = new ArrayList<>();
	private ArrayList<Integer> suit = new ArrayList<>();

	private int maxValue = 0;
	private int maxTo = 0;

	public Declaration() {
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getMaxTo() {
		return maxTo;
	}

	public void addDeclaration(int value, int to, int suit) {
		this.value.add(value);
		this.to.add(to);
		this.suit.add(suit);

		if (value > maxValue) {
			maxValue = value;
			maxTo = to;
		}
	}

	public void clearDeclaration() {
		value.clear();
		to.clear();
		suit.clear();
		maxTo = 0;
		maxValue = 0;
	}

	public int getDeclarationCount() {
		return value.size();
	}

	public int getValueAt(int index) {
		return value.get(index);
	}

	public int getToAt(int index) {
		return to.get(index);
	}

	public int getSuitAt(int index) {
		return suit.get(index);
		/*if(suitT == Flags.ZIR){
			return suit;
		}else if(suitT == Flags.SRCE){
			return "HEARTS";
		}else if(suitT == Flags.TIKVA){
			return "PUMPKINS";
		}else if(suitT == Flags.LIST){
			return "SPADES";
		}else{
			return "";
		}*/
	}
}
