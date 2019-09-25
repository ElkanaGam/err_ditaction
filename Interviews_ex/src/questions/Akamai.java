package questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Akamai {

	static int [] find_pair1 (int sum , int [] array) {
		int [] ret_arr  = new int[2];
		for (int i = 0 ; i< array.length ;  i++)
		{
			for (int j = i+1 ; j < array.length; j++) 
			{
				if (array[i] +array[j] == sum) 
				{
					ret_arr[0] = array[i];
					ret_arr[1] = array[j];
					return ret_arr;
					
				}
			}
		}
		return null;
	}
	
	static int [] find_pair2 (int sum , int [] array) {
		int [] ret_arr  = new int[2];
		Arrays.sort(array);
		int start = 0;
		int end  = array.length-1;
		while (start < end)
		{
			int current_sum = array[start]+array[end];
			if (current_sum== sum)
			{
				ret_arr[0] = array[start];
				ret_arr[1] = array[end];
				return ret_arr;
			}
			else if(current_sum < sum)
			{
				start++;
			}
			else
			{
				end--;
			}
			
		}
		return null;
	}
	
	
	static int [] find_pair3 (int sum, int [] array  ) {
		int [] ret_arr  = new int[2];
		HashMap <Integer, Integer> set = new HashMap <Integer, Integer> ();
		for (int i = 0; i< array.length ; i++)
		{
			set.put(array[i], array[i]);
			
		}
		for (int i = 0; i< array.length; i++)
		{
			if (set.get(sum - array[i] )!= null)
			{
				ret_arr[0] = array[i];
				ret_arr[1] = set.get(sum-array[i]);
				return ret_arr;
			}
		}
		return null;
	}
	
	public static void main (String [] args) {	
		Random random = new Random();
		int []arr = new int[100000];
		for (int i = 0; i< 100000; i++)
		{
			arr[i] = random.nextInt(300000);
		}
		
		int sum = 300;
		int [] ret;


		long startTime = System.currentTimeMillis();
		ret = find_pair1(sum, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		System.out.printf("%d %d\n", ret[0], ret[1]);
		startTime = System.currentTimeMillis();
		
		ret = find_pair2(sum, arr);
		endTime = System.currentTimeMillis();
		System.out.printf("%d %d\n", ret[0], ret[1]);
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		startTime = System.currentTimeMillis();
		ret = find_pair3(sum, arr);
		endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		System.out.printf("%d %d", ret[0], ret[1]);
		
		
		
		

	}
	
}
