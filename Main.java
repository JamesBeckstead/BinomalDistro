
public class Main {

	public static void main(String[] args) {
		System.out.println("What is the probability that we'll make money given the following variables.");
		System.out.println("400 students, 220 are women, 180 are men, study groups of 10 students, probability of getting exactly 4 men in a group");
		System.out.println("Probability is " + Prob(10, .45, 4) + "%.");

		System.out.println("What is the probability that we'll make money given the following variables.");
		System.out.println("5% of products fail QC. If you test 20 new products what is the probability that none will fail QC?");
		System.out.println("Probability is " + Prob(20,0.05,0) + "%.");
		
		System.out.println("What is the probability that we'll make money given the following variables.");
		System.out.println("I flipped a coin 10 times. what is the probabilty of getting at least 5 heads?");
		System.out.println("Probability is " + Prob(10, .5, 5, 10) + "%.");
		
		System.out.println("What is the probability that we'll make money given the following variables.");
		System.out.println("Basketbal player makes 60% of free throws. At end of game she shoots 6 free throws. What is the probability she make less than 5?");
		System.out.println("Probability is " + Prob(6,.6,0,4) + "%.");
	}
	
	public static double Prob(int trials, double probability, int successValue) {
		double numSuccesses = 0;
		
		numSuccesses = ((factorial(trials) / (factorial(successValue) * factorial(trials - successValue))) * Math.pow(probability, successValue)* 
						Math.pow((1 - probability), trials - successValue)) * 100;
		numSuccesses = (double) Math.round(numSuccesses * 100 ) / 100;
		return numSuccesses;
	}
	
	public static double Prob(int trials, double probability, int successValue, int max) {
		double numSuccesses = 0;
		int start = successValue;
		while(start<= max) {
			numSuccesses = numSuccesses + ((factorial(trials) / (factorial(start) * factorial(trials - start))) * Math.pow(probability, start)* 
							Math.pow((1 - probability), trials - start)) * 100;
			start++;
		}
		numSuccesses = (double) Math.round(numSuccesses * 100 ) / 100;
		return numSuccesses;
	}
	

	
	public static double factorial(int trials) {
		
		if (trials == 0) {
			return 1;
		}else {
			return trials * factorial(trials - 1);
		}
	}

}
