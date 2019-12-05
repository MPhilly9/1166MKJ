



package pkg1166project;

import java.util.*;

public class Main
{


    public static void main(String[] args)
    {
        System.out.println("========================= Programming Exercise 1 =========================\n");
         goldbachConjecture();
         System.out.println("\n========================= Programming Exercise 2 =========================\n");
        // Hold up
        System.out.println("\n========================= Programming Exercise 3 =========================\n");
        primeInputs();
    }
    
    
//=================[ Part I ]==============================================================================
    
    public static void goldbachConjecture()
    {
        Scanner sc = new Scanner(System.in);
        int userIn;
        int[] primeNumbers;
        System.out.println("Welcome to the Goldbach Conjecture simulation.");
        System.out.println("According to the conjecture: "
                + "\n\"Any positive, even integer > 2 is the sum of two prime numbers.\"\n");

        // Checks to see if the next input is actually an integer, if not, reiterates...
        do 
        {
            System.out.print("Be sure the number you enter follows the above credentials. Try a number: ");
            while(!sc.hasNextInt())
            {
                System.out.print("Make sure you're entering an integer! Try again: ");
                sc.next();
            }
            userIn = sc.nextInt();
        }
        while(!posEvenInt(userIn));
        System.out.println("Okay! " + userIn + " fits the bill!\n");
        
        
        primeNumbers = new int[userIn]; // Sets an array equal to the length of the user input
        for(int number = 1, index = 0; number <= primeNumbers.length; number++, index++) // Checks if prime
        {
            if(isPrimeOver2(number))
            {
                primeNumbers[index] = number;
            }
        }
        
        
        System.out.println("A possible sum of primes is: ");
        outer: for(int i = 0; i < primeNumbers.length; i++)
        {
            if(primeNumbers[i] == 0)
            {
                continue;
            }
            
            for(int j = i; j< primeNumbers.length; j++) // did this so nested index j acts independantly from i 
            {
                if(primeNumbers[j] == 0)
                {
                    continue;
                }
                
                if(primeNumbers[j] + primeNumbers[i] == userIn)
                {
                    int resultingNumber = primeNumbers[i] + primeNumbers[j];
                    System.out.println("" + primeNumbers[i] + " + " + primeNumbers[j] + " = " + resultingNumber);
                    break outer;
                }
            }
            
        }  
    }
    
    
    // Checks for the basic credentials of userIn as described by the Goldbach Conjecture...
    public static boolean posEvenInt(int userIn)
    {
        // If n>2 AND n is even ...
        return ((userIn > 2) && (userIn % 2 == 0));
    }
    
    
    // Checks to see if the current value is a prime number...
    public static boolean isPrimeOver2(int number)
    {
        if(number < 2 || number % 2 == 0)
        {
            return (number == 2);
        }
        
        for(int oddNum = 3; (oddNum * oddNum) <= number; oddNum += 2)
        {
            if (number % oddNum == 0)
            {
                return false;
            }
        }
        
        return true;
    }
    
    
// =================[ Part 2 ]===========================================================================
    
    // Good lord wtf
    
// =================[ Part 3 ]===========================================================================
    
    public static void primeInputs()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your list of prime numbers with spaces between each: ");
        String[] uIn = sc.nextLine().split(" ");
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < uIn.length; i++)
        {
            values.add(uIn[i]);
        }
        
        
        if(isValidInput(values))
        {
            System.out.println("\nYour expression in Godel's format: ");
            ArrayList<Integer> powers = new ArrayList<>();
            if(values.size() == 1)
            {
                System.out.println(values.get(0) + "^" + 1);
            }
            else
            {   
                LinkedHashSet<String> hashValues = new LinkedHashSet<>(values);
                ArrayList<String> basePrimes = new ArrayList<>(hashValues);
                int[] orderedPowers = new int[basePrimes.size()];
                int index = 0;
                
                for(String s : hashValues)
                {
                    orderedPowers[index] = Collections.frequency(values, s);
                    System.out.print(s + "^" + Collections.frequency(values, s) + " ");
                    index++; // I know I could have made an incremented forloop but I already got this far
                }
                
                // Goes through each power and prints its respective Godel symbol
                String finalOutput = "";
                for(int cPow = 0; cPow < orderedPowers.length; cPow++)
                {
                    switch(orderedPowers[cPow])
                    {
                        case 1:
                            finalOutput += "0";
                            break;
                        case 3:
                            finalOutput += "f";
                            break;
                        case 5:
                            finalOutput += "¬";
                            break;
                        case 7:
                            finalOutput += "V";
                            break;
                        case 9:
                            finalOutput += "∀";
                            break;
                        case 11:
                            finalOutput += "(";
                            break;
                        case 13:
                            finalOutput += ")";
                            break;
                        case 15:
                            finalOutput += "^";
                            break;
                        case 17:
                            finalOutput += "∃";
                            break;
                        case 19:
                            finalOutput += "=";
                            break;
                        case 21:
                            finalOutput += "x";
                            break;
                        case 23:
                            finalOutput += "y";
                            break;
                        default:
                            finalOutput += "";
                            break;
                    }
                }
                System.out.println("\nUsing Godel's numbering system, your input translates to:\n\n" + finalOutput);
                System.out.println("");
                
            }
        }
        else
        {
            System.out.println("The expression you entered is incompatable with Godel's Numbering method. ");
            System.exit(0); // Did i do that correctly???
        }
        
    }
    
    
    public static boolean isValidInput(ArrayList<String> values)
    {
        int currentVal;
        for(String s : values)
        {
            try
            {
                currentVal = Integer.parseInt(s);
            }
            catch(NumberFormatException e)
            {
                System.out.println("The value " + s + " is not an integer. Invalid input.");
                return false;
            }
            
            if(!isPrime(currentVal))
            {
                return false;
            }   
        }
        return true;
    }
    
    
    // The succession of prime numbers for Godel's numbering starts with 2... so: 2, 3, 5, 7, 11, ...
    public static boolean isPrime(int check)
    {
        if(check == 1)
        {
            return false;
        }
        for(int i = 2; i <= check/2; ++i)
        {
            if(check % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}