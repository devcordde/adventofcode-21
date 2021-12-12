public class MyClass {
    public static void main(String args[]) {
        int increases = 0;

        int lastone = 0;

        for(int i = 1; i < args.length - 2; i++) {

            int sum = Integer.parseInt(args[i-1]) + Integer.parseInt(args[i]) + Integer.parseInt(args[i+1]);

            if(sum > lastone)
                increases++;

            lastone = sum;

        }

        System.out.println("Increases: " +increases);
    }
}