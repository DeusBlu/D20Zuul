
public class CalculateXP {

	public CalculateXP() 
	{
		
	}
	
	private int calculateNeededXP(int avgLevel)
	{
		int neededXP = 0;
		for(int i = 1; i <= avgLevel; i++){
			neededXP += (i * 1000);
		}
		return neededXP;
	}
	
	public int calculateXP(Party players, Party monsters)
	{
		double neededXP = calculateNeededXP(players.getAverageLevel());
		int battleCR = monsters.getTotalCR();
		int avgLevel = players.getAverageLevel();
		while(battleCR > avgLevel+2){
			neededXP *= 2;
			avgLevel+=2;
		}
		while(battleCR > avgLevel){
			neededXP *= 1.5;
			avgLevel++;
		}
		int gainedEXP = 0;
		gainedEXP += neededXP/23.33;
		return gainedEXP;
	}
	
	public int testCalculateXP(int testAvgLevel, int cr)
	{
		if(testAvgLevel <= 3){
			testAvgLevel = 3;
		}
		double neededXP = calculateNeededXP(testAvgLevel);
		int battleCR = cr;
		int avgLevel = testAvgLevel;
		while(battleCR > avgLevel+2){
			neededXP *= 2;
			avgLevel+=2;
		}
		while(battleCR > avgLevel){
			neededXP *= 1.5;
			avgLevel++;
		}
		int gainedEXP = 0;
		gainedEXP += neededXP/23.33;
		return gainedEXP;
	}
}
