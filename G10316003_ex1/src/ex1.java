//import java.util.ArrayList;

public class ex1
{

	public static void main(String[] args)
	{
		int ref[] = {0,0,0,0,0,0,0};
		int ref2[] = {0,0,0,0,0,0,0};
		int [][] node = { {9999,19,92,29,49,78,6},
						  {19,9999,21,85,45,16,26},
						  {92,21,9999,24,26,87,47},
						  {29,85,24,9999,76,17,8},
						  {49,45,26,76,9999,90,27},
						  {78,16,87,17,90,9999,55},
						  {6,26,47,8,27,55,9999}
						};
		
		int i,j;
		int mim_i=777;
		int mim_j=777;
		int temp_i;
		int temp_j;
		
		int k=0;
		//int temp_dis;
		int mim_dis=0;
		int sum_dis=0;
		int times=-1;
		
		int con=999;
		
		while(k < 6)
		{	
			int temp_dis = 100;
			
			for(i=0 ; i<7 ; i++)
				{		
							
							for(j=0 ; j<7 ; j++)
							{	
								/*System.out.printf("i:");
								System.out.println(i);								
								System.out.printf("j:");
								System.out.println(j);								
								System.out.println(node[i][j]);*/
								
								if(temp_dis > node[i][j] )
								{
									if(times==-1)
										{
											temp_dis = node[i][j];
											mim_i = i;
											mim_j = j;	
										}
									else if(times>=0){
										int match = 0; //用來比較1的個數
										int match2 = 0;//用來比較1的個數
											for(int l=0 ; l<7 ; l++){
											
													if(ref[l]==1)
														{
														 match++;
														}								
											}
											
											for(int n=0 ; n<7 ; n++){
												ref2[n]=ref[n];												
											}
																												
											temp_i = i;
											temp_j = j;
											
											ref2[i]++;
											ref2[j]++;
													
																						
											for(int m=0 ; m<7 ; m++){
												if(ref2[m]==1)
												{
												 match2++;
												}
												/*System.out.printf("ref2[%d]",m);
												System.out.println(ref2[m]);*/

												
												
											}
											/*System.out.printf("match2=");
											System.out.println(match2);
											System.out.printf("\n");*/
 
											if(match2<2)
											{
												node[i][j] = con;
												node[j][i] = con;	
											}
											
											if(match<2)
											{
												node[i][j] = con;
												node[j][i] = con;										
											}
											
											else{											
												temp_dis = node[i][j];
												mim_i = i;
												mim_j = j;	
											}
										
										
									}
									
								}														
							}
							mim_dis = 0 + temp_dis;
							
							
					}
			System.out.printf("Path %d to %d : ",mim_i,mim_j);
			System.out.println(mim_dis);
			sum_dis += mim_dis;
			
			ref[mim_i]++;
			ref[mim_j]++;

			
			/*System.out.printf("i:");
			System.out.println(mim_i+1);								
			System.out.printf("j:");
			System.out.println(mim_j+1);	
			
			System.out.println(node[mim_i][mim_j]);


			System.out.printf("ref[0]");
			System.out.println(ref[0]);
			System.out.printf("ref[1]");
			System.out.println(ref[1]);
			System.out.printf("\n");
			System.out.printf("\n");*/
			
			node[mim_i][mim_j] = con;
			node[mim_j][mim_i] = con;
			
			k++;
			times++;
		}
		System.out.printf("mimum path:");
		System.out.println(sum_dis);
		
		
	}
	
	
	
	
}


		
		
		
		
		


