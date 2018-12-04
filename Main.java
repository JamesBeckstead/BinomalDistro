import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		//these were the use cases
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
		
		System.out.println("What is the probability that we'll make money given the following variables.");
		System.out.println("An MBA program tries to have an incoming class of 350 students. 70% of those admitted enroll. How many applicants should the program accept?");
		System.out.println("It should accept " + accept(350, 0.70) + " students.");
		System.out.println("What is the probability the program will enroll between 330 and 370 student? " + Prob(500, .70, 330, 370) + "%");
		

	}
	
	public static double Prob(int trials, double probability, int successValue) {
		//simple binomial probability, where factorial of a number is used
		//factorial of 6 is 1*2*3*4*5*6, recursion is useful for lower numbers
		double numSuccesses = 0;
		
		numSuccesses = ((factorial(trials) / (factorial(successValue) * factorial(trials - successValue))) * Math.pow(probability, successValue)* 
						Math.pow((1 - probability), trials - successValue)) * 100;
		numSuccesses = (double) Math.round(numSuccesses * 100 ) / 100;
		return numSuccesses;
	}
	
	public static double Prob(int trials, double probability, int successValue, int max) {
		//if the success value is < 50 recursive call should be able to handle it, otherwise big decimal will handle it
		//factored on the success value because in use case the trial was 500, but result needed to be less
		double numSuccesses = 0;
		int start = successValue;
		if (start <= 50) {
			while(start <= max) {
				numSuccesses = numSuccesses + ((factorial(trials) / (factorial(start) * factorial(trials - start))) * Math.pow(probability, start) * 
								Math.pow((1 - probability), trials - start)) * 100;
				start++;
			}
		} else {
			//any time the success value is greater than 50 big integer or big decimal should be used, or the number becomes lost
			while(start <= max) {
				//needed to use big decimal because a factor of 500 is a VERY big number
				//big decimal and big integer don't allow for normal math equations, found it easier to do each part individually
				BigDecimal facTrls = bigFactorial(trials);
				BigDecimal facStrt = bigFactorial(start);

				BigDecimal facTrls_strt = bigFactorial(trials - start);
				
				BigDecimal denom = facStrt.multiply(facTrls_strt);
				
				BigDecimal fctrl = facTrls.divide(denom);
				//This is the point where the big numbers come back into manageable ranges
				//switched to doubles for memory savings and easy of understanding
				double fctl = fctrl.doubleValue();
				numSuccesses = numSuccesses + fctl * Math.pow(probability, start) * Math.pow((1 - probability), (trials - start));
				//need to increment the starting number so we don't end up in an eternal loop
				start++;
			}
			//makes the final number look like a percent not a decimal, easier to read
			numSuccesses = numSuccesses * 100;
		}
		//just wanted to round to two decimal places
		numSuccesses = (double) Math.round(numSuccesses * 100 ) / 100;
		return numSuccesses;
	}
	
	public static int accept(int goal, double enroll) {
		//quick way to get how many to start with if given how many finish
		return (int) (goal / enroll);
	}

	
	public static double factorial(int value) {
		//recursive call, the worry of stack over flow is here, so limited this to 50
		//recursion makes the code easy to read, but could be done as while loop or for loop
		if (value == 0) {
			return 1;
		}else {
			return value * factorial(value - 1);
		} 
	}
	
	public static BigDecimal bigFactorial(int value) {
		//use of big decimal is necessary because when dividing two big integers it didn't give the remainder
		BigDecimal fact = BigDecimal.valueOf(1);
		//for loop here made sense because of fear that recursion wouldn't be able to hold the values in the stack
	    for (int i = 1; i <= value; i++) {
	        fact = fact.multiply(BigDecimal.valueOf(i));
	    }
	    return fact;
	}
	
}
