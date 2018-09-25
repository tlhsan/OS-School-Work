package labAssignment321;

import java.util.Scanner;

public class Priority {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of processes");
		int pros = sc.nextInt();
		int[] burst_time = new int[pros];
		int [] pros_id = new int[pros];
		int [] priority = new int[pros];
		System.out.println("Please enter the burst time of the processes");
		for(int i=0; i<pros; i++) {
			burst_time[i]= sc.nextInt();
			pros_id[i] = i+1;
			
		}
		System.out.println("Please enter the priority of the processes");
	    priority[0]  = sc.nextInt();
	    for(int i=1; i<pros; i++) {
			priority[i]= sc.nextInt();	
		}
		
		// sorting priority-wise
		
		for(int i=0; i<pros; i++) 
		{
			
			for(int c=i+1; c<pros; c++) 
			{
				
				if(priority[c] < priority[i]) 
				{
					
				    int prior_temp = priority[i];
				    int burst_temp = burst_time[i];
					priority[i] = priority[c];
					burst_time[i] = burst_time[c];
					priority[c] = prior_temp;
					burst_time[c] = burst_temp;
					
					
				}
				
			}
			
		}
		
	
		
		int[] waitT = new int[pros];
		//int[] startT = new int[pros];
		int[] endT = new int[pros];
		
		// for wait time 
		
		for(int i =0; i< pros; i++) {
			if(priority[i]==1) {
				waitT[i]=0;
			}
			else {
				waitT[i] = burst_time[i-1] + waitT[i-1] ;
				
			}
		}
		
		// for end time
		
		for(int i =0; i<pros; i++) {
			if(i==0) {
				endT[i] = burst_time[i];
				
			}
			else {
				endT[i] = endT[i-1] + burst_time[i];
			}
		}
		
		System.out.println("ID\tTime\tWaitT\tStartT\tEndT\tPriority");
		for(int i=0; i<pros; i++) {
			System.out.println(pros_id[i] + "\t" + burst_time[i] + "\t" + waitT[i] + "\t" + waitT[i] + "\t" + endT[i] + "\t" + priority[i]);                 
		}
		sc.close();
		
	}

}







