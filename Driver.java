import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        Polynomial m = p1.multiply(p2);
        
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        for(int i = 0; i < m.poly_non_zeros.length; i++)
        {
        		System.out.println(m.poly_non_zeros[i]);
        }
        System.out.println("m(2) = " + m.evaluate(2));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        try
        {
        		Polynomial ms = new Polynomial(new File("sample.txt"));
        		for(double x:ms.poly_non_zeros) System.out.println(x);
        		
        }
        catch(FileNotFoundException e)
        {
        		System.out.println("Can't open file ;(");
        }
        
        
        
    }
}