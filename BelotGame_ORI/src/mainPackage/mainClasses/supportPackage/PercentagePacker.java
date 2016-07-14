package mainPackage.mainClasses.supportPackage;

import java.util.ArrayList;

public class PercentagePacker {
	
	private ArrayList<Percent> percentages = new ArrayList<Percent>();
	
	public ArrayList<Percent> getPercentages(){
		return percentages;
	}
	
	public int getIndex_MaxPtsTolr(int tolerance){
		int x = -1;
		ArrayList<Percent> tolerancy = new ArrayList<Percent>();
		for(Percent p : percentages){
			if(p.getPercentage() >= tolerance){
				tolerancy.add(p);
			}
		}
		if(!tolerancy.isEmpty())
			return getMaximumPercentageIndexPts(tolerancy);
		return x;
	}
	
	public int getIndex_MinPtsTolr(int tolerance){
		int x = -1;
		ArrayList<Percent> tolerancy = new ArrayList<Percent>();
		for(Percent p : percentages){
			if(p.getPercentage() >= tolerance){
				tolerancy.add(p);
			}
		}
		if(!tolerancy.isEmpty())
			return getMinimumPercentageIndexPts(tolerancy);
		return x;
	}
	
	public Percent getMaximumPercent(){
		Percent x = percentages.get(0);
		for(int i=0;	i<percentages.size();	i++){
			if(x.getPercentage() < percentages.get(i).getPercentage()){
				x = percentages.get(i);
			}
		}
		return x;
	}
	
	public Percent getMinimumPercent(){
		Percent x = percentages.get(0);
		for(int i=0;	i<percentages.size();	i++){
			if(x.getPercentage() > percentages.get(i).getPercentage()){
				x = percentages.get(i);
			}else if(x.getPercentage() == percentages.get(i).getPercentage()){
				if(x.getNumberOfPoints() < percentages.get(i).getNumberOfPoints()){
					x = percentages.get(i);
				}
			}
		}
		return x;
	}
	
	public int getMinimumPercentageIndex(){
		int i = percentages.get(0).getCardIndex();
		for(Percent p : percentages){
			if(p.getPercentage() < percentages.get(i).getPercentage()){
				i = p.getCardIndex();
			}
		}
		return i;
	}
	
	public int getMaximumPercentageIndex(){
		int i = percentages.get(0).getCardIndex();
		for(Percent p : percentages){
			if(p.getPercentage() > percentages.get(i).getPercentage()){
				i = p.getCardIndex();
			}
		}
		return i;
	}
	
	private int getMinimumPercentageIndexPts(ArrayList<Percent>tolerancy){
		int i = 0;
		for(int x = 1;	x < tolerancy.size();	x++){
			if(tolerancy.get(x).getNumberOfPoints() < tolerancy.get(i).getNumberOfPoints()){
				i = x;
			}
		}
		return tolerancy.get(i).getCardIndex();
	}
	
	private int getMaximumPercentageIndexPts(ArrayList<Percent>tolerancy){
		int i = 0;
		for(int x = 1;	x < tolerancy.size();	x++){
			if(tolerancy.get(x).getNumberOfPoints() > tolerancy.get(i).getNumberOfPoints()){
				i = x;
			}
		}
		return tolerancy.get(i).getCardIndex();
	}
}
