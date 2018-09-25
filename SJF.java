package labAssignment321;


import java.util.Scanner;

public class SJF {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please neter the number of processes");
		int pros = sc.nextInt();
		int[] burst_time = new int[pros];
		int [] pros_id = new int[pros];
		int [] arrival_time = new int[pros];
		System.out.println("Please enter the burst time of the processes");
		for(int i=0; i<pros; i++) {
			burst_time[i]= sc.nextInt();
			pros_id[i] = i+1;
			
		}
		System.out.println("Please enter the arival time of the processes");
		for(int i=0; i<pros; i++) {
			arrival_time[i]= sc.nextInt();
			
		}
		
		
		
		int[] waitT = new int[pros];
		int[] endT = new int[pros];
		
		// for wait time 
		
		for(int i =0; i< pros; i++) {
			if(i==0) {
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
		
		System.out.println("ID\tTime\tWaitT\tStartT\tEndT");
		for(int i=0; i<pros; i++) {
			System.out.println(pros_id[i] + "\t" + burst_time[i] + "\t" + waitT[i] + "\t" + waitT[i] + "\t" + endT[i]);                 
		}
		sc.close();
		
	}

}




