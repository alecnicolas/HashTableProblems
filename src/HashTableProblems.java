//Alec Marcum
//Comp 282

import java.lang.reflect.Array;
import java.util.*;

public class HashTableProblems
{
    public static void main(String args[])
    {
        //Problem 1 test
        int myArray[] = randomIntArray(15,30,true);
        System.out.println("Problem 1\nNo Dupes:" + Arrays.toString(myArray));

        //Problem 2 test
        int list1[] = {3, 91, 83, 13, 35, 99, 59, 81, 28, 96, 56, 11, 55, 38, 26, 29, 80, 43, 62, 30};
        int list2[] = {19, 83, 25, 47, 9, 30, 91, 57, 39, 70, 43, 90, 23, 73, 5};
        System.out.println("\nProblem 2\nList 1:" + Arrays.toString(list1) + "\nList 2:" + Arrays.toString(list2));

        System.out.println("Integers in Common" + numbersInCommon(list1,list2));

        //Problem 3 test
        int list3[] = {11, 28, 67, 16, 47, 19, 6, 35, 74, 72, 36, 59, 70, 32, 60, 89, 95, 52, 45, 22, 8, 88, 50, 39, 9};
        int sum = 75;
        System.out.println("\nProblem 3\n" + "Sum:" + 75 + "\nList:" + Arrays.toString(list3) + "\nMatching Pairs:" + pairSum(list3, sum));
    }

    //Problem 1 method
    public static int[] randomIntArray(int n, int limit, boolean nodups)
    {
        Hashtable<Integer, Integer> htable = new Hashtable<Integer, Integer>();
        int rArray[] = new int[n];
        Random r = new Random();

        //dups allowed
        if (nodups == false) //just creates a random array of set limit and size like normal
        {
            for (int i = 0; i < rArray.length; i++)
            {
                rArray[i] = r.nextInt(limit-1) + 1;
            }
        }

        //no dups allowed
        else
        {
            for (int i = 0; i < rArray.length; i++)
            {
                rArray[i] = r.nextInt(limit-1) + 1;
                while(htable.containsKey(rArray[i])) //generates new values until a number that isn't already a key is generated
                {
                    rArray[i]=r.nextInt(limit-1) + 1;
                }
                htable.put(rArray[i], 1); //once new number is generated, it's added to hash table as a new key
            }
        }

        return rArray;
    }

    //Problem 2 method
    public static ArrayList<Integer> numbersInCommon(int[] list1, int[] list2)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Hashtable<Integer, Integer> htable = new Hashtable<Integer, Integer>();

        //Insert all list1 values into hash table
        for(int i=0; i<list1.length;i++)
        {
            htable.put(list1[i], 0);
        }

        //Check all list2 values for key match to list1 values
        for(int i=0; i<list2.length;i++)
        {
            if(htable.containsKey(list2[i]))
            {
                result.add(list2[i]);
            }
        }

        return result;
    }

    //Problem 3 method
    public static ArrayList<Tuple> pairSum(int[] nums, int d)
    {
        ArrayList<Tuple> result = new ArrayList<Tuple>();
        Hashtable<Integer, Integer> htable = new Hashtable<Integer, Integer>();
        int compliment;

        for(int i=0; i<nums.length;i++)
        {
            htable.put(nums[i],0);
            compliment = Math.abs(nums[i]-d);
            if(!htable.containsKey(compliment)) //if key doesn't exist, add it
            htable.put(compliment,0); //if key does exist, then we've already seen compliment -- add both to result
            else //
            {
                Tuple newPair = new Tuple(nums[i], compliment);
                result.add(newPair);
            }

        }

        return result;
    }
}

//Tuple Class
class Tuple
{
    int x;
    int y;

    public Tuple(int a, int b)
    {
        x = a;
        y = b;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}

