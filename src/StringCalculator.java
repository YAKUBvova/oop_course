import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    protected List<String> delimiters = new ArrayList<>();
    {
        delimiters.add(",");
        delimiters.add("\n");
    }
    protected void edit(List<String> del){
        delimiters.addAll(del);
    }
    protected void list_clean(){
        while (delimiters.size()>2){
            delimiters.remove(2);
        }
    }
    public String custom_del(String str){
        List<String> res = new ArrayList<>();
        char[] digits = {'1','2','3','4','5','6','7','8','9'};
        int start =2 ;
        int end =2;
        for (int i =2 ; i < str.length();i++){
            if (str.charAt(i)=='['&& str.charAt(i+1)!='\n')start=i+1;
            if (str.charAt(i)==']'&& str.charAt(i-1)!='/'){
                end = i;
                res.add(str.substring(start,end));
                start = i+1;
                edit(res);
            }
            if (str.charAt(i)=='\n'){
                end=i;
                if (!str.substring(start,end).equals("")){
                    res.add(str.substring(start,end));
                    edit(res);
                }
                return str.substring(i+1);
            }
            for (char dig : digits){
                if (str.charAt(i)==dig){
                    return "";
                }
            }
        }
        return "";
    }

    private int adding(String numbers){
        int result = 0 ;
        int start = 0 ;
        int end ;
        List<String> negative_dig = new ArrayList<>();
        int i = 0;
        while (i<numbers.length()){
            for (String delimiter : delimiters) {

                try{
                    if (numbers.substring(i, i + delimiter.length()).equals(delimiter)) {
                        end = i;
                        i += delimiter.length();
                        if (numbers.charAt(start) == '-') {
                            negative_dig.add(numbers.substring(start, end));
                        }
                        try {
                            int number = Integer.parseInt(numbers.substring(start, end));
                            if (number > 1000) {
                                start = end + 1;
                                continue;
                            }
                            result += number;
                        } catch (NumberFormatException e) {
                            System.out.println("Error! Invalid format");
                            list_clean();
                            return -1;
                        }
                        start = end + delimiter.length();
                        if (start == numbers.length()) {
                            System.out.println("Error! Invalid format");
                            list_clean();
                            return -1;
                        }
                    }
                }catch (StringIndexOutOfBoundsException e){
                    System.out.print("");
                }
            }
            if (i == numbers.length()-1) {
                result += Integer.parseInt(numbers.substring(start));
                list_clean();
                if (numbers.charAt(start)=='-'){
                    negative_dig.add(numbers.substring(start));
                }
                if (negative_dig.size()>0){
                    System.out.println("Error! Nonsupported negative digits: "+negative_dig);
                    return -1;
                }
                return result ;
            }
            i++;
        }
        list_clean();
        if (numbers.charAt(0)=='-'){
            System.out.println("Error! Nonsupported negative digit: "+numbers);
            return -1;
        }
        return Integer.parseInt(numbers) ;
    }
    public int add (String numbers){
        if (numbers.length() == 0) return 0 ;
        if (numbers.startsWith( "//")){
            numbers = custom_del(numbers);
            if (numbers.equals("")) {
                System.out.println("Error! Invalid custom delimiters");
                return -1;
            }
        }
        return adding(numbers) ;
    }
}
