public class Polynomial
{
    private double[] poly_val;

    public Polynomial()
    {
        double[] zero_poly = {0};
        poly_val = zero_poly;
    }

    public Polynomial(double[] given_poly)
    {
        poly_val = given_poly; 
    }

    public Polynomial add(Polynomial given_poly)
    {		
        double[] given_array = given_poly.poly_val;
        double[] new_values = new double[Math.max(poly_val.length, given_array.length)]; //Take the larger array size
        int lowest_size = Math.min(poly_val.length, given_array.length);  //This ensures that the "shared" size is within bounds
        for(int i = 0; i < new_values.length; i++) 
        {
            if(i < lowest_size) 
            {
                new_values[i] = poly_val[i] + given_array[i];
            }
            //Checks if poly_val is the bigger array, if so copy its data
            else if(poly_val.length == new_values.length)
            {
                new_values[i] = poly_val[i];
            }
            //Ditto for given_array
            else
            {
                new_values[i] = given_array[i];
            }
        }
        return new Polynomial(new_values);
    }

    public double evaluate(double val)
    {
        double sum_poly = 0;
        for(int i = 0; i < poly_val.length; i++)
        {
            sum_poly += (poly_val[i] * Math.pow(val, i));
        }
        return sum_poly;
    }

    public boolean hasRoot(double val)
    {
        return evaluate(val) == 0;
    }

}
