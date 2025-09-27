import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial
{
	
    public double[] poly_non_zeros;
    public int[] poly_exp;
   
    public Polynomial()
    {
    	poly_non_zeros = new double[0];
		poly_exp = new int[0];
    }
    
    //Helper method
    private int size_poly(double[] given_poly)
    {
		int size = 0;
		for(int i = 0; i < given_poly.length; i++)
		{
			if(given_poly[i] != 0) size++;
		}
		return size;
    }
    
    
    public Polynomial(double[] given_poly)
    {
		int size = size_poly(given_poly);
		int index = 0;
		
		poly_non_zeros = new double[size];
		poly_exp = new int[size];
		
		for(int i = 0; i < given_poly.length; i++)
		{
			if(given_poly[i] != 0)
			{
				poly_non_zeros[index] = given_poly[i];
				poly_exp[index] = i;
				index++;
			}
		}
    }
    
   public Polynomial(File given_file) throws FileNotFoundException 
   {
		Scanner sc = new Scanner(given_file);
		String input = sc.nextLine();
		sc.close();
		
		String[] segments = input.split("(?=[+-])"); 
		int size_new = Integer.parseInt(segments[segments.length - 1].split("x")[1]) + 1;
		double[] new_values = new double[size_new];
		
		for(int i = 0; i < segments.length; i++)
		{
			String[] parts = segments[i].split("x");
			if(parts.length == 1) new_values[0] += Double.parseDouble(parts[0]);
			else new_values[Integer.parseInt(parts[1])] += Double.parseDouble(parts[0]);
		}
		
		int size = size_poly(new_values);
		poly_non_zeros = new double[size];
		poly_exp = new int[size];
		int index = 0;
		for(int i = 0; i < new_values.length; i++) 
		{
			if(new_values[i] != 0) 
			{
				poly_non_zeros[index] = new_values[i];
				poly_exp[index] = i;
				index++;
			}
		}
	} 
		
	
    //Helper method
    private int index_of_val(int[] given_exp, int val)
    {
    		for(int i = 0; i < given_exp.length; i++)
    		{
    			if(given_exp[i] == val) return i;
    		}
    		return -1;
    }
    
    public Polynomial add(Polynomial given_poly)
    {
    		
    	int size = Math.max(poly_exp[poly_exp.length - 1], given_poly.poly_exp[given_poly.poly_exp.length - 1]) + 1;
     	double[] new_values = new double[size];
     	for(int i = 0; i < size; i++)
     	{
     		int index_i_poly = index_of_val(poly_exp, i);
     		int index_i_given = index_of_val(given_poly.poly_exp, i);
     		if(index_i_poly != -1)
     			new_values[i] += poly_non_zeros[index_i_poly];
     		else if(index_i_given != -1)
     			new_values[i] += given_poly.poly_non_zeros[index_i_given];
     	}
    		return new Polynomial(new_values);
    }

    public double evaluate(double val)
    {
        //Simple loop just add up all the values
        double sum_poly = 0;
        for(int i = 0; i < poly_non_zeros.length; i++)
        {
            sum_poly += (poly_non_zeros[i] * Math.pow(val, poly_exp[i]));
        }
        return sum_poly;
    }

    public boolean hasRoot(double val)
    {
        return evaluate(val) == 0;
    }
    
    
    public Polynomial multiply(Polynomial given_poly)
    {
    	int size = poly_exp[poly_exp.length - 1] + given_poly.poly_exp[given_poly.poly_exp.length - 1] + 1;
		double[] new_values = new double[size];
		for(int i = 0; i < poly_exp.length; i++)
		{
			for(int j = 0; j < given_poly.poly_exp.length; j++)
			{
				double val = poly_non_zeros[i] * given_poly.poly_non_zeros[j];
				int index = poly_exp[i] + given_poly.poly_exp[j];
				new_values[index] += val;
			}
		}
		return new Polynomial(new_values);
    		
    }
    
    public void saveToFile(String path) throws FileNotFoundException
{
		PrintStream f = new PrintStream(path);
		boolean is_first = true;
    	for(int i = 0; i < poly_non_zeros.length; i++)
		{
			if(poly_exp[i] == 0) f.print(poly_non_zeros[i]); 
			else if(poly_non_zeros[i] < 0 || is_first)
			{
				f.print(poly_non_zeros[i] + "x" + poly_exp[i]);
			}
			else f.print("+" + poly_non_zeros[i] + "x" + poly_exp[i]);
			
			if(is_first) is_first = false;
		}
		f.print("\n");
    	f.close();
    }
    
    

}