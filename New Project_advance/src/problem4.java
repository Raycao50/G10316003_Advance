//問題四 ：利用Branch Bound Strategy做TSP
//從之前的開讀檔sample改寫 這邊會存取並呼叫city.java內的各城市x y軸座標


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class problem4 {
	
	static double bound=0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	 	  String[] s2;
	 	  String s3;  
	 	  City[] cities = new City[29];//用來儲存城市的座標
	      double city[][] = new double[29][29]; //用來儲存城市到另一個城市間的距離
	      double tempArray[][] = new double[29][29];
	      ArrayList<Integer> node = new ArrayList<Integer>(); //node會記錄之後選取到的城市，並用來判斷是否重複	      
	      ArrayList<Integer> findZero = new ArrayList<Integer>(); 	//找到該列通往其他城市為0的路徑是哪條
	      ArrayList<Integer> ZeroRow = new ArrayList<Integer>();//用來放當前會進行隨機的row
	      ArrayList<Integer> pass = new ArrayList<Integer>(); //確定會走訪的城市便放進這裡面
	      
	      FileReader fr2=new FileReader("bayg29.txt"); 
	      BufferedReader sr2=new BufferedReader(fr2);
	        
	      	//這邊將所讀到的城市座標資料儲存起來 使用上次的sample部分
	       	for ( int i = 0; i < cities.length; i++){ 
	            s3 = sr2.readLine();
	            s2 = s3.split("\\s+");
	            cities[i] = new City();
	            cities[i].setX((int) (Double.parseDouble(s2[1])));
	            cities[i].setY((int) (Double.parseDouble(s2[2])));
	            //System.out.println(cities[i].getY());
  			}

	       	//將城市間的距離利用distance函式求得 並儲存在city矩陣當中，如果是城市到自己的距離，則用９９９９取代
	       	for(int i =0;i < cities.length ; i++ ){
	       		for(int j =0;j < cities.length ; j++){
	       			if(i==j){ //判斷是否出發點與到達點一樣，一樣則設為９９９９
	       				city[i][j] = 99999; 
	       			}else{ //否則將兩城市的座標取出，並丟進distance函式中，計算彼此之間的距離，並存進city矩陣當中
	       			city[i][j] = distance(cities[i].getX(),cities[i].getY(),cities[j].getX(),cities[j].getY());    			
	       			}
	       		}
	       	}
	       	
	       	tempArray = reduce(city); //做reduce
	       	//display(tempArray);
	       	int[] Nzero_array = new int[29];
	       	Nzero_array = check_zero(tempArray); //檢查行列是否都有０
	       	check_wrong(tempArray); //因為給的極限值是99999，檢查是否有超過99999的城市距離
	       	double[] gapArray = new double[29];
	       	System.out.printf("The initial low bound is %.3f \n\n",bound); //目前的low bound
	       	  
	          	
	       	int count = 0; //計數器，用來表示跑了幾次城市
	       	double path = 0; //所有走訪城市的總路徑
	       	double temp = 0;//目前較佳的路徑
	       	int sure_city = 0; //不是用隨機選出來的城市
	       	int RN = 0 , random_city = 0; //用來隨機出發的城市與隨機數
	       	int RN2 = 0, random_target = 0; //用來隨機目標的程式與隨機數
	       		       	
	       	while(count < city.length){
	       		double min_gap = 99999;
	       		gapArray = gap(tempArray); //技算排除0以外的第二數
		       	
	       		for(int aa=0;aa<29;aa++){  //
		       		if(gapArray[aa]==0){
		       			ZeroRow.add(aa);
		       		}
		       	}
	       			       		
	       		for(int k=0;k<29;k++){
	       			if(gapArray[k]<min_gap){
	       				min_gap = gapArray[k];
	       				sure_city = k;
	       			}
	       		}
	       		
	       		if(count == city.length-1){ //最後一次
	       			System.out.printf("Step %d\n",count+1);
	       			System.out.printf("The final path is %.3f\n",bound);
	       			
	       		}else{  //非最後一次
		       			if(ZeroRow.size()>0){ //如果含有複數個0的城市數量大於0，將會先被選擇
		       				RN = (int)(Math.random()*ZeroRow.size());
			       			random_city = ZeroRow.get(RN);
			       			
			       			for(int i=0;i<29;i++){
			       				if(tempArray[random_city][i] == 0){
			       					findZero.add(i);
			       				}
			       			}
			       			
			       			RN2 = (int)(Math.random()*findZero.size());
			       			random_target = findZero.get(RN2);
		       			}else{ //若沒有複數個0的城市數量大於0，將會選擇gap最小的
		       				double min = 99999;
		       				int sure_target = 0;
		       				for(int i=0;i<29;i++){
		       					if(tempArray[sure_city][i]<min){
		       						min = tempArray[sure_city][i];
		       						sure_target = i;
		       					}
		       				}
		       				
		       				random_city = sure_city;
		       				random_target = sure_target;
		       			}		       		
	       			ZeroRow.clear();
	       			findZero.clear();
	       			
	       			temp = tempArray[random_city][random_target];	       			
	       			System.out.printf("Step %d\n",count+1);
	    	       	System.out.printf("The current low bound is %.3f \n",bound); //目前的low bound
	       			System.out.printf("with city%d to city%d is %.3f\n",random_city+1,random_target+1,temp);
	       			System.out.printf("without city%d to city%d is %.3f\n",random_city+1,random_target+1,gapArray[random_city]);
	       			System.out.printf("Current bound is %.3f + %.3f = %.3f\n",bound,temp,bound+temp);
	       			System.out.println();				       			
	       			
	       		}
	       			pass.add(random_city); //將已經被走訪的路徑加入pass內
	       			tempArray = renew(tempArray,random_city,random_target); //重新變更陣列
	       			tempArray = reduce(tempArray);

		       		bound = bound + temp;
		       		count++;		       		
	       	}	       
	}
	
	//distance函式 可以算出兩城市間的距離，透過第一個城市的x1 y1座標 及第二個城市的x2 y2座標
	static double distance(int x1,int y1,int x2,int y2) 
	{	double dis = 0;
		double x,y;
		x = Math.pow(x1 - x2, 2);
		y = Math.pow(y1 - y2, 2);
		dis = Math.sqrt(x+y);
		return dis; 
	}
	//用來顯示二維矩陣
	static void display(double array[][]){
		for(int i=0;i<29;i++){
			for(int j =0;j<29;j++){
				System.out.printf("%10.3f   ", array[i][j]);
			}
			System.out.println();
		}
	}
	
	//用來做矩陣的reduce，使得每一列 每一行 都只少會有一個０
	static double[][] reduce(double array[][]){
		//double[][] temp = new double[29][29];
		double minR;//找到目前同一列的最小值
		double minC;//找到目前同一行的最小值
		int count = 1;
		//作各列的reduce
		for(int i = 0;i<29;i++){
			//找出該列的最小值
			minR = 99999;
			for(int j=0;j<29;j++){
				if(array[i][j]<minR){
					minR = array[i][j];
				}
			}
			//該列都減掉其最小值
			if(minR > 10000){
				minR = 0;
			}
			
			for(int j=0;j<29;j++){
				array[i][j] -= minR;
			}
			
			
			bound += minR; //將各列都減去的最小值加進bound中 
			//System.out.printf("The minR is %.3f\n",minR); //用來顯示目前的要再減掉的low bound

		}
		
		//做各行的reduce
		for(int i=0;i<29;i++){
			//找出該行的最小值
			minC = 99999;
			for(int j=0;j<29;j++){
				if(array[j][i]<minC){
					minC = array[j][i];
				}
			}
			//該行都減掉其最小值
			if(minC > 10000){
				minC = 0;
			}
			
			for(int j=0;j<29;j++){
				array[j][i] -= minC;
			}
			
			bound += minC;  //將各行都減去的最小值都加進bound中
			//System.out.printf("The minC is %.3f\n",minC); //用來顯示目前的要減掉的low bound

		}
		
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(i==j){
					array[i][j] = 99999;
				}
			}
		}
		
		return array; //回傳reduce後的陣列
	}
	
	//用來確認每行每列的零的個數，並回傳每列零個數至陣列
	static int[] check_zero(double array[][]){
		//int[] temp = new int[29];
		int[] check_arrayR = new int[29]; 
		int[] check_arrayC = new int[29];
		
		//列的部分
		for(int i=0;i<29;i++){
			int check = 0;
			for(int j=0;j<29;j++){
				if(array[i][j] == 0){
					check++;
				}
			}
			check_arrayR[i] = check;
		}				
		for(int i=0;i<29;i++){
			if(check_arrayR[i]==0){
			System.out.printf("The row of city%d is %d\n",i+1,check_arrayR[i]);
			}
		}
		
		//行的部分
		for(int i=0;i<29;i++){
			int check = 0;
			for(int j=0;j<29;j++){
				if(array[j][i] == 0){
					check++;
				}
			}
			check_arrayC[i] = check;
		}		
		for(int i=0;i<29;i++){
			if(check_arrayC[i]==0){
			System.out.printf("The column of city%d is %d\n",i+1,check_arrayC[i]);
			}
		}
		
		return check_arrayR;
	}
	
	//確認是否有超過99999的數
	static void check_wrong(double array[][]){
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(array[i][j]>99999){
					System.out.printf("row%d is wrong", i);
				}
			}
		}
	}
	
	//找每列中第二小的數，來確認先從哪一行開始做
	static double[] gap(double array[][]){
		double[] gap_array = new double[29];
		
		for(int i=0;i<29;i++){
			int zero_number=0;
			double small = 99999;
			for(int j=0;j<29;j++){
				if(array[i][j]==0){
					zero_number++;
				}
			}
			
			//System.out.printf("the %d row got %d zero\n",i,zero_number);
			if(zero_number > 1){
				gap_array[i] = 0;
			}else{
				for(int j=0;j<29;j++){
					if(array[i][j]<small && array[i][j]!=0){
						small = array[i][j];
					}
				}
				gap_array[i] = small;
			}			
		}
		//顯示每個列的gap
		/*for(int n=0;n<29;n++){ 
			System.out.printf("%d is %.3f\n",n+1,gap_array[n]);
		}*/
		
		return gap_array; 
	}
	//更新矩陣
	static double[][] renew(double array[][],double start,double des){
		for(int i=0;i<29;i++){
			array[(int)start][i] = 99999;
		}
		
		for(int j=0;j<29;j++){
			array[j][(int)des] = 99999;
		}
		array[(int)des][(int)start] = 99999;
		
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(i==j){
					array[i][j] = 99999;
				}
			}
		}
		
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(array[i][j]>10000)
					array[i][j] = 99999;
			}
		}
		
		return array;
		
	}
}
	   
 
