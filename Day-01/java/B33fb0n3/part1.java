public class MyClass {
    public static void main(String args[]) {
        int increases = 0;

        int lastone = 0;

        for(int i = 0; i < args.length - 2; i++) {

            if(Integer.parseInt(args[i]) > lastone)
                increases++;

            lastone = Integer.parseInt(args[i]);

        }

        System.out.println("Increases: " +increases);
    }
}