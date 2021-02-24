package ui;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.io.IOException;

public class Main
{
  public static void main(String args[]) throws IOException 
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int tries= Integer.parseInt(br.readLine());
    String[] numbersStringArray;
    double[] numbersArray;
    String line = "";
    String answer = "";

    for(int i=0;i<tries;i++){
      line = br.readLine();
      numbersStringArray = line.split(" ");
      int size = numbersStringArray.length;
      numbersArray = new double[size];
      for(int j=0;j<size;j++){
        numbersArray[j]=Double.parseDouble(numbersStringArray[j]);
      }
      numbersArray=bubbleSort(numbersArray);
      answer += writeTheAnswer(numbersArray)+"\n";
    }
      
    bw.write(answer);

    br.close();
    bw.close();
  }

  public static double[] bubbleSort(double[] numbersArray){
    double totalValue = 0;
    int changes = 1;
    double[] arrayInOrder = new double[numbersArray.length+1];
    for(int i = 1;i<numbersArray.length;i++){ //&& changes>0
        changes = 0;
      for(int j=0;j<numbersArray.length-i;j++){
        if(numbersArray[j]>numbersArray[j+1]){
          double temp = numbersArray[j];
          numbersArray[j]=numbersArray[j+1];
          numbersArray[j+1]=temp;
          changes++;
        }
        //
      }
      totalValue+=changes;
    }
    totalValue /= (numbersArray.length-1);
    arrayInOrder[0]=totalValue;
    for(int i=0;i<numbersArray.length;i++){
        arrayInOrder[i+1]=numbersArray[i];
    }
    return arrayInOrder;
  }

  public static String writeTheAnswer(double[] numbersArray){
	DecimalFormat df = new DecimalFormat("#.00");
	String answer = "";
	double aux = (numbersArray[0]-(int)numbersArray[0])+0.001;
	String aux2 = aux+"";
	if(numbersArray[0] % 1 == 0) {
		answer = ((numbersArray[0] * 1e2) / 1e2)+"-";
	}
	else if(aux2.charAt(3)=='0') {
		answer = df.format(numbersArray[0]);
		answer = answer.substring(0,answer.length() - 1)+"-";; 	
	}
	else {
		df.setRoundingMode(RoundingMode.DOWN);
		answer = df.format(numbersArray[0])+"-";
	}
	
    for(int i=1;i<numbersArray.length;i++){
    	answer+= numbersArray[i]+" ";
    }
    answer=answer.replaceAll(",",".");
    answer = answer.substring(0,answer.length() - 1); 
    return answer;
  }
}