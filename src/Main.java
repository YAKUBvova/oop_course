public class Main {
    public static void main(String[] args) {
        StringCalculator str = new StringCalculator() ;
        String[] test = {"","-18","-15,-89","1,8\n13","//1\n1;8\n1,3","1009,8\n13","//[\n1[8\n1,3"
                ,"//[***]\n18\n1***3","//[*][%]\n1%8\n9*3","//[**][%%%]\n1%%%8\n9**3","//[2*]\n18\n92*3"};
        int result;
        for (String s : test) {
            System.out.println("For string '"+s.replace('\n','n')+"'");
            result = str.add(s);
            if (result == -1) continue;
            System.out.println("result is :"+result);
        }

    }
}