//�D�ؤ@ �G Greedy��TSP
//�q���e���}Ū��sample��g �o��|�s���éI�scity.java�����U����x y�b�y��


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
	 	  City[] cities = new City[29];//�Ψ��x�s�������y��
	      double city[][] = new double[29][29]; //�Ψ��x�s������t�@�ӫ��������Z��
	      ArrayList<Integer> node = new ArrayList<Integer>(); //node�|�O���������쪺�����A�åΨӧP�_�O�_����	      
	        
	      FileReader fr2=new FileReader("bayg29.txt"); 
	      BufferedReader sr2=new BufferedReader(fr2);
	        
	      	//�o��N��Ū�쪺�����y�и���x�s�_�� �ϥΤW����sample����
	       	for ( int i = 0; i < cities.length; i++){ 
	            s3 = sr2.readLine();
	            s2 = s3.split("\\s+");
	            cities[i] = new City();
	            cities[i].setX((int) (Double.parseDouble(s2[1])));
	            cities[i].setY((int) (Double.parseDouble(s2[2])));
	            //System.out.println(cities[i].getY());
  			}

	       	//�N���������Z���Q��distance�禡�D�o ���x�s�bcity�x�}���A�p�G�O������ۤv���Z���A�h�΢����������N
	       	for(int i =0;i < cities.length ; i++ ){
	       		for(int j =0;j < cities.length ; j++){
	       			if(i==j){ //�P�_�O�_�X�o�I�P��F�I�@�ˡA�@�˫h�]����������
	       				city[i][j] = 9999; 
	       			}else{ //�_�h�N�⫰�����y�Ш��X�A�å�idistance�禡���A�p�⩼���������Z���A�æs�icity�x�}��
	       			city[i][j] = distance(cities[i].getX(),cities[i].getY(),cities[j].getX(),cities[j].getY());    			
	       			}
	       		}
	       	}
	       	
	      //�Ψ���ܯx�}�����ӫ����������Z��
	       	/*for(int i =0;i<29;i++){
	       		for(int j=0;j<29;j++){
	       			System.out.printf("%.3f   ",city[i][j]);
	       		}
	       		System.out.println();
	       	}*/
	       	
	       	
	       	//�ϥΩ������e���ɪ�Greedy����k�A�Q��ArrayList�Ӱ�
	       	int count = 0; //�p�ƾ��A�ΨӪ�ܶ]�F�X������
	       	double path = 0; //�Ҧ����X�������`���|
	       	int current_city = 9; //��e�X�o�������A�]���D�ػ��n�q�ĤQ�ӫ����}�l�A�x�}�O�q���}�l��A�ҥH�ثe���]�����A����|���_�Q���N���s�������H�@���X�o�I
	       	int start = 0; //
	       	double temp = 0;//�ثe���Ϊ����|
	       	
	       	while( count < city.length) //city�����׬O�����D�]���������ӫ���
			{
				if( count == city.length-1) //�p�G�w�g����̫�@���A�n�[�^��_�I���Z���A�~��O����TSP
				{
					path = path + city[9][current_city]; //�p��̫ᨫ�X�������^��ĤQ�ӫ��������|�A�å[���`���|����
					System.out.printf("The city%d to city10 path is: %.3f\n", current_city+1, city[0][current_city]);
				}
				else
				{
					start = current_city; //�Ncurrent_city�s�istart����
					node.add(current_city); //�N�ثe���_�l�I�s�iarraylist����
					temp = 9999; //�N�ثe�̨θ��|�]�ܤj�A��K���������|�A�̤p���|���u
					
					for(int target = 0; target < 29; target++) //target�@���i���X�������A�o��ϥ�for�j��A�K�i�H���X�����ӫ����Ӥ��
					{
						for(int already_city = 0; already_city <node.size(); already_city++)//�ѩ󤣯ਫ�X�w�g���L�������A�]���έ�誺arraylist�ӧP�_
						{   //�ھ�arraylist�����h�֤��e�|�M�walready_city�ݭn�P�_������
							
							if(target == node.get(already_city) ) break; //�p�G�H���ƴN���X
							else if(city[start][target] < temp && already_city == (node.size()-1) ) //�S�����ƫh������|���j�p
							{
									temp = city[start][target];	//�N�ثe�̤p���|���N��temp
									current_city = target; //�N�ثe���̤p���|�|��F�������]������|�q�o��X�o���������A�ҥH�i�����ثe�����I�A�U�@�����_�I
							}
						}
					}
					path = path + temp; //�N�ثe�̨θ��|�[���`���|����
					System.out.printf("The city%d to city%d path is: %.3f\n", start+1,current_city+1,temp);
				}
				count++;//count+1 ��ܧ����@����������
			}
	       	
	        System.out.printf("The final sum path is %.3f",path);
	}
	
	//distance�禡 �i�H��X�⫰�������Z���A�z�L�Ĥ@�ӫ�����x1 y1�y�� �βĤG�ӫ�����x2 y2�y��
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
	   
 
