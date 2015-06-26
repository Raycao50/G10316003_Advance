//題目一 ： Greedy做TSP
//從之前的開讀檔sample改寫 這邊會存取並呼叫city.java內的各城市x y軸座標


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class problem1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	 	  String[] s2;
	 	  String s3;  
	 	  City[] cities = new City[29];//用來儲存城市的座標
	      double city[][] = new double[29][29]; //用來儲存城市到另一個城市間的距離
	      ArrayList<Integer> node = new ArrayList<Integer>(); //node會記錄之後選取到的城市，並用來判斷是否重複	      
	        
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
	       				city[i][j] = 9999; 
	       			}else{ //否則將兩城市的座標取出，並丟進distance函式中，計算彼此之間的距離，並存進city矩陣當中
	       			city[i][j] = distance(cities[i].getX(),cities[i].getY(),cities[j].getX(),cities[j].getY());    			
	       			}
	       		}
	       	}
	       	
	      //用來顯示矩陣間的個城市之間的距離
	       	/*for(int i =0;i<29;i++){
	       		for(int j=0;j<29;j++){
	       			System.out.printf("%.3f   ",city[i][j]);
	       		}
	       		System.out.println();
	       	}*/
	       	
	       	
	       	//使用明宏之前分享的Greedy的方法，利用ArrayList來做
	       	int count = 0; //計數器，用來表示跑了幾次城市
	       	double path = 0; //所有走訪城市的總路徑
	       	int current_city = 9; //當前出發的城市，因為題目說要從第十個城市開始，矩陣是從０開始算，所以目前先設為９，之後會不斷被取代為新的城市以作為出發點
	       	int start = 0; //
	       	double temp = 0;//目前較佳的路徑
	       	
	       	while( count < city.length) //city的長度是２９．因為有２９個城市
			{
				if( count == city.length-1) //如果已經做到最後一次，要加回到起點的距離，才算是完成TSP
				{
					path = path + city[9][current_city]; //計算最後走訪的城市回到第十個城市的路徑，並加到總路徑之中
					System.out.printf("The city%d to city10 path is: %.3f\n", current_city+1, city[0][current_city]);
				}
				else
				{
					start = current_city; //將current_city存進start之中
					node.add(current_city); //將目前的起始點存進arraylist之中
					temp = 9999; //將目前最佳路徑設很大，方便之後比較路徑，最小路徑最優
					
					for(int target = 0; target < 29; target++) //target作為可能到訪的城市，這邊使用for迴圈，便可以走訪２９個城市來比較
					{
						for(int already_city = 0; already_city <node.size(); already_city++)//由於不能走訪已經走過的城市，因此用剛剛的arraylist來判斷
						{   //根據arraylist內有多少內容會決定already_city需要判斷的次數
							
							if(target == node.get(already_city) ) break; //如果以重複就跳出
							else if(city[start][target] < temp && already_city == (node.size()-1) ) //沒有重複則比較路徑的大小
							{
									temp = city[start][target];	//將目前最小路徑取代為temp
									current_city = target; //將目前走最小路徑會到達的城市設為之後會從這邊出發的的城市，所以可視為目前的終點，下一次的起點
							}
						}
					}
					path = path + temp; //將目前最佳路徑加到總路徑之中
					System.out.printf("The city%d to city%d path is: %.3f\n", start+1,current_city+1,temp);
				}
				count++;//count+1 表示完成一次城市移動
			}
	       	
	        System.out.printf("The final sum path is %.3f",path);
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
	
	static void display(double array[][]){
		for(int i=0;i<29;i++){
			for(int j =0;j<29;j++){
				System.out.printf("%.3f", array[i][j]);
			}			
		}
	}
	
}
	   
 
