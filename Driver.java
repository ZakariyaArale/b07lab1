import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
    public static void main(String [] args) {
        int num = 1;
        //1. Zero polynomial (evaulate)
    	Polynomial p = new Polynomial();
        if(Arrays.equals(p.poly_non_zeros, new double[] {0}) && Arrays.equals(p.poly_exp, new int[] {0}) && p.evaluate(3) == 0) System.out.println("Test " + num + " good!");
        else System.out.println("Failed " + num + " :(");
        num++;
        
        //2. Create poly (evaluate)
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        if(Arrays.equals(p1.poly_non_zeros, new double[] {6,5}) && Arrays.equals(p1.poly_exp, new int[] {0,3}) && p1.evaluate(-2) == -34) System.out.println("Test " + num + " good!");
        else System.out.println("Failed " + num + " :(");
        num++;
        
        //3. Add (hasRoot)
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        if(Arrays.equals(s.poly_non_zeros, new double[] {6,-2,5,-9}) && Arrays.equals(s.poly_exp, new int[] {0,1,3,4}) && s.hasRoot(1)) System.out.println("Test " + num + " good!");
        else System.out.println("Failed " + num + " :(");
        num++;

        //4. Multiply (evaluate)
        Polynomial m = p1.multiply(p2);
        if(Arrays.equals(m.poly_non_zeros, new double[] {-12,-64,-45}) && Arrays.equals(m.poly_exp, new int[] {1,4,7}) && m.evaluate(-2) == 4760) System.out.println("Test " + num + " good!");
        else System.out.println("Failed " + num + " :(");
        num++;
        
        //5. File Reading
        try
        {
        	FileWriter f = new FileWriter("sample.txt");
            f.write("-5+7x2-9x3\n");
            f.close();
            Polynomial ms = new Polynomial(new File("sample.txt"));
        	if(Arrays.equals(ms.poly_non_zeros, new double[] {-5,7,-9}) && Arrays.equals(ms.poly_exp, new int[] {0,2,3})) System.out.println("Test " + num + ".1 good!");
            else System.out.println("Failed " + num + " :(");

            f = new FileWriter("sample.txt");
            f.write("5x1-7x2+9x3\n");
            f.close();
            ms = new Polynomial(new File("sample.txt"));
        	if(Arrays.equals(ms.poly_non_zeros, new double[] {5,-7,9}) && Arrays.equals(ms.poly_exp, new int[] {1,2,3})) System.out.println("Test " + num + ".2 good!");
            else System.out.println("Failed " + num + " :(");
            num++;
        		
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("Can't open file ;(");
        }
         catch(IOException e)
        {
        	System.out.println("Can't write file ;(");
        }
        
        //6. File Writing
        try
        {
            //Comment out the other tests to see result
            p1.saveToFile("sample.txt"); //6.0+5.0x3 good!
            //p2.saveToFile("sample.txt"); //-2.0x1-9.0x4 good!
            //Polynomial scale_poly = new Polynomial(new double[]{-1});
            //m = m.multiply(scale_poly);
            // m.saveToFile("sample.txt"); // 12.0x1+64.0x4+45.0x7 good!
            System.out.println("Test " + num + " good!");
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("Can't open file ;(");
        }
    }
}