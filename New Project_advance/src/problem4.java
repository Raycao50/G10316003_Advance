//���D�| �G�Q��Branch Bound Strategy��TSP
//�q���e���}Ū��sample��g �o��|�s���éI�scity.java�����U����x y�b�y��


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
	 	  City[] cities = new City[29];//�Ψ��x�s�������y��
	      double city[][] = new double[29][29]; //�Ψ��x�s������t�@�ӫ��������Z��
	      double tempArray[][] = new double[29][29];
	      ArrayList<Integer> node = new ArrayList<Integer>(); //node�|�O���������쪺�����A�åΨӧP�_�O�_����	      
	      ArrayList<Integer> findZero = new ArrayList<Integer>(); 	//���ӦC�q����L������0�����|�O����
	      ArrayList<Integer> ZeroRow = new ArrayList<Integer>();//�Ψө��e�|�i���H����row
	      ArrayList<Integer> pass = new ArrayList<Integer>(); //�T�w�|���X�������K��i�o�̭�
	      
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
	       				city[i][j] = 99999; 
	       			}else{ //�_�h�N�⫰�����y�Ш��X�A�å�idistance�禡���A�p�⩼���������Z���A�æs�icity�x�}��
	       			city[i][j] = distance(cities[i].getX(),cities[i].getY(),cities[j].getX(),cities[j].getY());    			
	       			}
	       		}
	       	}
	       	
	       	tempArray = reduce(city); //��reduce
	       	//display(tempArray);
	       	int[] Nzero_array = new int[29];
	       	Nzero_array = check_zero(tempArray); //�ˬd��C�O�_������
	       	check_wrong(tempArray); //�]�����������ȬO99999�A�ˬd�O�_���W�L99999�������Z��
	       	double[] gapArray = new double[29];
	       	System.out.printf("The initial low bound is %.3f \n\n",bound); //�ثe��low bound
	       	  
	          	
	       	int count = 0; //�p�ƾ��A�ΨӪ�ܶ]�F�X������
	       	double path = 0; //�Ҧ����X�������`���|
	       	double temp = 0;//�ثe���Ϊ����|
	       	int sure_city = 0; //���O���H����X�Ӫ�����
	       	int RN = 0 , random_city = 0; //�Ψ��H���X�o�������P�H����
	       	int RN2 = 0, random_target = 0; //�Ψ��H���ؼЪ��{���P�H����
	       		       	
	       	while(count < city.length){
	       		double min_gap = 99999;
	       		gapArray = gap(tempArray); //�޺�ư�0�H�~���ĤG��
		       	
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
	       		
	       		if(count == city.length-1){ //�̫�@��
	       			System.out.printf("Step %d\n",count+1);
	       			System.out.printf("The final path is %.3f\n",bound);
	       			
	       		}else{  //�D�̫�@��
		       			if(ZeroRow.size()>0){ //�p�G�t���Ƽƭ�0�������ƶq�j��0�A�N�|���Q���
		       				RN = (int)(Math.random()*ZeroRow.size());
			       			random_city = ZeroRow.get(RN);
			       			
			       			for(int i=0;i<29;i++){
			       				if(tempArray[random_city][i] == 0){
			       					findZero.add(i);
			       				}
			       			}
			       			
			       			RN2 = (int)(Math.random()*findZero.size());
			       			random_target = findZero.get(RN2);
		       			}else{ //�Y�S���Ƽƭ�0�������ƶq�j��0�A�N�|���gap�̤p��
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
	    	       	System.out.printf("The current low bound is %.3f \n",bound); //�ثe��low bound
	       			System.out.printf("with city%d to city%d is %.3f\n",random_city+1,random_target+1,temp);
	       			System.out.printf("without city%d to city%d is %.3f\n",random_city+1,random_target+1,gapArray[random_city]);
	       			System.out.printf("Current bound is %.3f + %.3f = %.3f\n",bound,temp,bound+temp);
	       			System.out.println();				       			
	       			
	       		}
	       			pass.add(random_city); //�N�w�g�Q���X�����|�[�Jpass��
	       			tempArray = renew(tempArray,random_city,random_target); //���s�ܧ�}�C
	       			tempArray = reduce(tempArray);

		       		bound = bound + temp;
		       		count++;		       		
	       	}	       
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
	//�Ψ���ܤG���x�}
	static void display(double array[][]){
		for(int i=0;i<29;i++){
			for(int j =0;j<29;j++){
				System.out.printf("%10.3f   ", array[i][j]);
			}
			System.out.println();
		}
	}
	
	//�ΨӰ��x�}��reduce�A�ϱo�C�@�C �C�@�� ���u�ַ|���@�Ӣ�
	static double[][] reduce(double array[][]){
		//double[][] temp = new double[29][29];
		double minR;//���ثe�P�@�C���̤p��
		double minC;//���ثe�P�@�檺�̤p��
		int count = 1;
		//�@�U�C��reduce
		for(int i = 0;i<29;i++){
			//��X�ӦC���̤p��
			minR = 99999;
			for(int j=0;j<29;j++){
				if(array[i][j]<minR){
					minR = array[i][j];
				}
			}
			//�ӦC�����̤p��
			if(minR > 10000){
				minR = 0;
			}
			
			for(int j=0;j<29;j++){
				array[i][j] -= minR;
			}
			
			
			bound += minR; //�N�U�C����h���̤p�ȥ[�ibound�� 
			//System.out.printf("The minR is %.3f\n",minR); //�Ψ���ܥثe���n�A���low bound

		}
		
		//���U�檺reduce
		for(int i=0;i<29;i++){
			//��X�Ӧ檺�̤p��
			minC = 99999;
			for(int j=0;j<29;j++){
				if(array[j][i]<minC){
					minC = array[j][i];
				}
			}
			//�Ӧ泣���̤p��
			if(minC > 10000){
				minC = 0;
			}
			
			for(int j=0;j<29;j++){
				array[j][i] -= minC;
			}
			
			bound += minC;  //�N�U�泣��h���̤p�ȳ��[�ibound��
			//System.out.printf("The minC is %.3f\n",minC); //�Ψ���ܥثe���n���low bound

		}
		
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(i==j){
					array[i][j] = 99999;
				}
			}
		}
		
		return array; //�^��reduce�᪺�}�C
	}
	
	//�ΨӽT�{�C��C�C���s���ӼơA�æ^�ǨC�C�s�ӼƦܰ}�C
	static int[] check_zero(double array[][]){
		//int[] temp = new int[29];
		int[] check_arrayR = new int[29]; 
		int[] check_arrayC = new int[29];
		
		//�C������
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
		
		//�檺����
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
	
	//�T�{�O�_���W�L99999����
	static void check_wrong(double array[][]){
		for(int i=0;i<29;i++){
			for(int j=0;j<29;j++){
				if(array[i][j]>99999){
					System.out.printf("row%d is wrong", i);
				}
			}
		}
	}
	
	//��C�C���ĤG�p���ơA�ӽT�{���q���@��}�l��
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
		//��ܨC�ӦC��gap
		/*for(int n=0;n<29;n++){ 
			System.out.printf("%d is %.3f\n",n+1,gap_array[n]);
		}*/
		
		return gap_array; 
	}
	//��s�x�}
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
	   
 
