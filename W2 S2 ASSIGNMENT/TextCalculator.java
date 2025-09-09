import java.util.*;

public class TextCalculator {
    static boolean isDigit(char c){int a=c;return a>=48&&a<=57;}
    static boolean isOp(char c){return c=='+'||c=='-'||c=='*'||c=='/';}
    static boolean isParen(char c){return c=='('||c==')';}
    static boolean isSpace(char c){return c==' ';}
    static boolean isValidExpression(String expr){
        int bal=0; boolean lastWasOp=true; boolean lastWasOpen=false; boolean seenDigit=false;
        for(int i=0;i<expr.length();i++){
            char c=expr.charAt(i);
            int a=c;
            if(!(isSpace(c)||isDigit(c)||isOp(c)||isParen(c))) return false;
            if(c=='('){bal++; lastWasOp=true; lastWasOpen=true;}
            else if(c==')'){bal--; if(bal<0) return false; if(lastWasOp) return false; lastWasOp=false; lastWasOpen=false;}
            else if(isOp(c)){ if(lastWasOp||lastWasOpen) return false; lastWasOp=true; lastWasOpen=false;}
            else if(isDigit(c)){ lastWasOp=false; lastWasOpen=false; seenDigit=true;}
        }
        if(bal!=0) return false;
        expr=expr.trim();
        if(expr.isEmpty()) return false;
        char first=expr.charAt(0), last=expr.charAt(expr.length()-1);
        if(isOp(first)||isOp(last)) return false;
        return seenDigit;
    }
    static void parseNoParen(String expr, ArrayList<Integer> nums, ArrayList<Character> ops){
        int i=0, n=expr.length();
        while(i<n){
            while(i<n&&isSpace(expr.charAt(i))) i++;
            int j=i;
            while(j<n&&isDigit(expr.charAt(j))) j++;
            if(i<j){nums.add(Integer.parseInt(expr.substring(i,j))); i=j;}
            else if(i<n&&isOp(expr.charAt(i))){ops.add(expr.charAt(i)); i++;}
            else i++;
        }
    }
    static String buildExpr(ArrayList<Integer> nums, ArrayList<Character> ops){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<nums.size();i++){
            if(i>0) sb.append(' ').append(ops.get(i-1)).append(' ');
            sb.append(nums.get(i));
        }
        return sb.toString();
    }
    static int apply(int a, int b, char op){
        if(op=='+') return a+b;
        if(op=='-') return a-b;
        if(op=='*') return a*b;
        if(op=='/'){ if(b==0) throw new ArithmeticException("Division by zero"); return a/b; }
        return 0;
    }
    static int evaluateNoParen(String expr, StringBuilder steps){
        ArrayList<Integer> nums=new ArrayList<>();
        ArrayList<Character> ops=new ArrayList<>();
        parseNoParen(expr, nums, ops);
        for(int i=0;i<ops.size();){
            char op=ops.get(i);
            if(op=='*'||op=='/'){
                int a=nums.get(i), b=nums.get(i+1), r=apply(a,b,op);
                nums.set(i,r); nums.remove(i+1); ops.remove(i);
                steps.append(a).append(' ').append(op).append(' ').append(b).append(" = ").append(r)
                        .append(" => ").append(buildExpr(nums,ops)).append('\n');
            }else i++;
        }
        for(int i=0;i<ops.size();){
            char op=ops.get(i);
            int a=nums.get(i), b=nums.get(i+1), r=apply(a,b,op);
            nums.set(i,r); nums.remove(i+1); ops.remove(i);
            steps.append(a).append(' ').append(op).append(' ').append(b).append(" = ").append(r)
                    .append(" => ").append(buildExpr(nums,ops)).append('\n');
        }
        return nums.get(0);
    }
    static int evaluate(String expr, StringBuilder steps){
        String e=expr;
        while(e.indexOf('(')!=-1){
            int open=e.lastIndexOf('(');
            int close=e.indexOf(')',open);
            if(close==-1) throw new IllegalArgumentException("Unmatched parentheses");
            String inside=e.substring(open+1,close);
            StringBuilder inner=new StringBuilder();
            int val=evaluateNoParen(inside, inner);
            steps.append("Inner (").append(inside).append(") = ").append(val).append('\n');
            String before=e;
            e=e.substring(0,open)+val+e.substring(close+1);
            steps.append(before).append(" -> ").append(e).append('\n');
        }
        return evaluateNoParen(e, steps);
    }
    static void showSteps(String expr, boolean valid){
        System.out.println("Original: "+expr);
        if(!valid){ System.out.println("Validation: Invalid"); return; }
        StringBuilder steps=new StringBuilder();
        int result=evaluate(expr, steps);
        System.out.println("Validation: Valid");
        System.out.print(steps.toString());
        System.out.println("Result: "+result);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of expressions:");
        int n=Integer.parseInt(sc.nextLine());
        for(int i=1;i<=n;i++){
            System.out.println("Enter expression "+i+":");
            String expr=sc.nextLine();
            boolean valid=isValidExpression(expr);
            showSteps(expr, valid);
            if(i<n) System.out.println();
        }
    }
}
