/**
 * @author wjunchao
 * @create 2021-11-29 16:33
 */
public class ErrorLetter {
    public static void main(String[] args) {
        int a = 0, b = 1, n = 5;
        if(n < 2) System.out.println(0);
        for(int i = 3; i <= n; i++) {
            int c = (i - 1)*(a + b);
            a = b;
            b = c;
        }
        System.out.println(b);
    }
}
