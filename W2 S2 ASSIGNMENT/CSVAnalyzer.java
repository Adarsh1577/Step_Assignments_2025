import java.util.*;

public class CSVAnalyzer {
    static String[][] parseCSV(String input){
        ArrayList<String[]> rows=new ArrayList<>();
        int i=0,n=input.length();
        ArrayList<String> row=new ArrayList<>();
        StringBuilder field=new StringBuilder();
        boolean inQuotes=false;
        while(i<n){
            char c=input.charAt(i);
            if(c=='"'){inQuotes=!inQuotes;}
            else if(c==','&&!inQuotes){row.add(field.toString());field.setLength(0);}
            else if(c=='\n'&&!inQuotes){row.add(field.toString());rows.add(row.toArray(new String[0]));row.clear();field.setLength(0);}
            else{field.append(c);}
            i++;
        }
        if(field.length()>0)row.add(field.toString());
        if(!row.isEmpty())rows.add(row.toArray(new String[0]));
        return rows.toArray(new String[0][]);
    }
    static boolean isNumeric(String s){
        if(s==null||s.isEmpty())return false;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            int a=c;
            if(!(a>=48&&a<=57||c=='.'||c=='-'))return false;
        }
        return true;
    }
    static String[][] cleanData(String[][] data){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                data[i][j]=data[i][j].trim();
                if(data[i][j].isEmpty())data[i][j]="MISSING";
            }
        }
        return data;
    }
    static void analyzeData(String[][] data){
        int cols=data[0].length;
        boolean[] numeric=new boolean[cols];
        for(int j=0;j<cols;j++){
            numeric[j]=true;
            for(int i=1;i<data.length;i++){
                if(!isNumeric(data[i][j])||data[i][j].equals("MISSING")){numeric[j]=false;break;}
            }
        }
        for(int j=0;j<cols;j++){
            if(numeric[j]){
                double min=Double.MAX_VALUE,max=-Double.MAX_VALUE,sum=0;int count=0;
                for(int i=1;i<data.length;i++){
                    if(isNumeric(data[i][j])){
                        double v=Double.parseDouble(data[i][j]);
                        min=Math.min(min,v);max=Math.max(max,v);sum+=v;count++;
                    }
                }
                double avg=sum/count;
                System.out.println("Column '"+data[0][j]+"': Min="+min+", Max="+max+", Avg="+String.format("%.2f",avg));
            }else{
                HashSet<String> set=new HashSet<>();
                for(int i=1;i<data.length;i++){
                    if(!data[i][j].equals("MISSING"))set.add(data[i][j]);
                }
                System.out.println("Column '"+data[0][j]+"': Unique Values="+set.size());
            }
        }
    }
    static void displayTable(String[][] data){
        int cols=data[0].length;
        int[] widths=new int[cols];
        for(int j=0;j<cols;j++){
            int max=0;
            for(int i=0;i<data.length;i++)max=Math.max(max,data[i][j].length());
            widths[j]=max+2;
        }
        String border="+";
        for(int w:widths){for(int k=0;k<w;k++)border+="-";border+="+";}
        System.out.println(border);
        for(int i=0;i<data.length;i++){
            System.out.print("|");
            for(int j=0;j<cols;j++){
                String val=data[i][j];
                if(val.equals("MISSING"))val="*"+val+"*";
                System.out.printf("%-"+widths[j]+"s|",val);
            }
            System.out.println();
            System.out.println(border);
        }
    }
    static void summaryReport(String[][] data){
        int total=data.length-1;
        int missing=0,fields=0;
        for(int i=1;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                fields++;
                if(data[i][j].equals("MISSING"))missing++;
            }
        }
        double completeness=100.0*(fields-missing)/fields;
        System.out.println("Total Records: "+total);
        System.out.println("Missing Values: "+missing);
        System.out.println("Data Completeness: "+String.format("%.2f",completeness)+"%");
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter CSV data (end with empty line):");
        StringBuilder input=new StringBuilder();
        while(true){
            String line=sc.nextLine();
            if(line.isEmpty())break;
            input.append(line).append("\n");
        }
        String[][] data=parseCSV(input.toString());
        data=cleanData(data);
        displayTable(data);
        System.out.println("\nAnalysis:");
        analyzeData(data);
        System.out.println("\nSummary:");
        summaryReport(data);
    }
}
