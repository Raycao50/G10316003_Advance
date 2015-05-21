//Using the Best First Algorithm to slove 8-puzzle problem

import java.util.*;

public class BestFirst {
	public static void main(String args[]){
		int[][] A = {{2,8,3},{1,6,4},{7,0,5}};
		int[][] B = {{1,2,3},{8,0,4},{7,6,5}};
		int Step = 1;
		Scanner input = new Scanner(System.in);
		
		int count = 0 , finish = 0;
		
		System.out.print("Origin:\n");
		Display(A);
		
		System.out.print("Target:\n");
		Display(B);
		System.out.print("Here comes moving....\n");

		while(input.hasNext()){
			int k = input.nextInt();
			System.out.printf("Step :%d\n",Step);
			int result = Compare(A,B);
			System.out.printf("Current Wrong :%d\n",result);
			if(result==0){
				System.out.printf("We got it");
				break;
			}else{
				A=Moving(A,B);
			    //result = Compare(A,B);
				Step++;
			}
		}
	}
	
	static void Display(int[][] a){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.printf("%d ",a[i][j]);
			}
				System.out.println("");
		}					
	}
	
	static int Compare(int[][] a,int[][] b){
		int WP=0; //WP means the wrong position
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(a[i][j]!=b[i][j])
					WP++;
			}
		}
		return WP;
	}
	
	static int[][] Moving(int[][] a,int[][] b){
		int index_i = 0 ,index_j = 0;
		int nup,ndown,nleft,nright;
		int up = 0 , down = 1 , left = 2, right = 3;
		int count = 0;
		int choose[] = new int[4]; 
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(a[i][j]==0){
					index_i = i;
					index_j = j;
				}
			}
		}
		
		nup = Compare(up(a,index_i,index_j),b);
		ndown = Compare(down(a,index_i,index_j),b);
		nleft = Compare(left(a,index_i,index_j),b);
		nright = Compare(right(a,index_i,index_j),b);
		
		System.out.printf("Up :%d  ",nup);
		System.out.printf("Down :%d  ",ndown);
		System.out.printf("Left :%d  ",nleft);
		System.out.printf("Right :%d\n",nright);
		
		int min = Min(Compare(up(a,index_i,index_j),b),Compare(down(a,index_i,index_j),b),
				Compare(left(a,index_i,index_j),b),Compare(right(a,index_i,index_j),b));
		System.out.printf("min is :%d\n",min);
		
		if(min == nup){choose[up]++; count++;}
		if(min == ndown){choose[down]++; count++;}
		if(min == nleft){choose[left]++; count++;}
		if(min == nright){choose[right]++; count++;}

		
		if(count>1){
			int num = (int)(Math.random()*count) + 1;
			System.out.printf("count is :%d\n",count);
			System.out.printf("random is :%d\n",num);

			for(int m=0;m<4;m++){
				if(choose[m] == 1){
					num--;
					if(num == 0){
						switch(m){
						case 0:
							System.out.print("Choose Up\n");
							//Display(up(a,index_i,index_j));
							a[index_i][index_j] = a[index_i - 1][index_j];
							a[index_i - 1][index_j] = 0;
							
							break;
							
						case 1:
							System.out.print("Choose Down\n");
							//Display(down(a,index_i,index_j));
							a[index_i][index_j] = a[index_i +1][index_j];
							a[index_i + 1][index_j] = 0;
							
							break;
							
						case 2:
							System.out.print("Choose Left\n");
							//Display(left(a,index_i,index_j));
							a[index_i][index_j] = a[index_i][index_j - 1];
							a[index_i][index_j - 1] = 0;
							
							break;
							
						case 3:
							System.out.print("Choose Right\n");
							//Display(right(a,index_i,index_j));
							a[index_i][index_j] = a[index_i][index_j + 1];
							a[index_i][index_j + 1] = 0;
							
							break;
						}
						Display(a);
					}
				}
			}
		}else if(count == 1){
			for(int i = 0;i<4;i++){
				if(choose[i]==1){
					switch(i){
					case 0:
						System.out.print("Choose Up\n");
						//Display(up(a,index_i,index_j));
						a[index_i][index_j] = a[index_i - 1][index_j];
						a[index_i - 1][index_j] = 0;
						
						break;
						
					case 1:
						System.out.print("Choose Down\n");
						//Display(down(a,index_i,index_j));
						a[index_i][index_j] = a[index_i +1][index_j];
						a[index_i + 1][index_j] = 0;
						
						break;
						
					case 2:
						System.out.print("Choose Left\n");
						//Display(left(a,index_i,index_j));
						a[index_i][index_j - 1] = a[index_i][index_j - 1];
						a[index_i][index_j - 1] = 0;
						
						break;
						
					case 3:
						System.out.print("Choose Right\n");
						//Display(right(a,index_i,index_j));
						a[index_i][index_j] = a[index_i][index_j + 1];
						a[index_i][index_j + 1] = 0;
						
						break;
					}
					Display(a);
				} 
			}
		}
		
		return a;
		
	}
	
	static int[][] up(int[][] a,int index_i ,int index_j){
		if(index_i == 0){
			return a;
		}else{
		int[][] temp = new int[3][3];
				
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				temp[i][j] = a[i][j];
			}
		}
				
		temp[index_i - 1][index_j] = 0;
		temp[index_i][index_j] = a[index_i - 1][index_j];
		
		return temp;}
	}
	
	static int[][] down(int[][] a,int index_i ,int index_j){
		if(index_i == 2){
			return a;
		}else{
		int[][] temp = new int[3][3];
				
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				temp[i][j] = a[i][j];
			}
		}
				
		temp[index_i + 1][index_j] = 0;
		temp[index_i][index_j] = a[index_i + 1][index_j];
		
		return temp;}
	}
	
	static int[][] left(int[][] a,int index_i ,int index_j){
		if(index_j == 0){
			return a;
		}else{
		int[][] temp = new int[3][3];
				
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				temp[i][j] = a[i][j];
			}
		}
				
		temp[index_i][index_j - 1] = 0;
		temp[index_i][index_j] = a[index_i][index_j - 1];
		
		return temp;}
	}
	
	static int[][] right(int[][] a,int index_i ,int index_j){
		if( index_j == 2){
			return a;
		}else{
		int[][] temp = new int[3][3];
				
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				temp[i][j] = a[i][j];
			}
		}
				
		temp[index_i][index_j + 1] = 0;
		temp[index_i][index_j] = a[index_i][index_j + 1];
		
		return temp;}
	}
	
	static int Min(int a,int b,int c,int d){
		int temp0,temp1,min;
		if(a>b){
			temp0 = b;
		}else{ temp0 = a;}
		
		if(c>d){
			temp1 = d;
		}else{temp1 = c;}
		
		if(temp0>temp1){
			min = temp1;
		}else{min = temp0;}
		
		return min;
	}
	
	static int[][] newpuzzle(int[][] a){
		return a;
	}
	
	static int[][] before(int[][] a){
		int[][] temp = a;
		return temp;
	}
	
	
}
