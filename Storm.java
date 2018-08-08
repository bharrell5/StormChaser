/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brett
 */
public class Storm {
    private final double KnotsToMPH =  1.15;
//   global user-defined types:
    private int beginDate = 0;
    private int duration = 0;
    private String name;
    private int category = 0;
    private int wind = 0;
    private int pressure = 0;
    
    public Storm( int bdate, int dur, String sname, int w,   int p  )
    {
        beginDate = bdate;
        setDuration(dur);
        name = sname;
        setWind(w);
        setPressure(p);
    }

    public void setDuration( int d  )
    {
        duration = d + 6;
    }
    
    public void setWind( int w )
    {
        double temp = 0.0;
        temp = KnotsToMPH * w;
        if(temp > wind)
            wind = (int)temp;
        SaffirSimpson();
    }
    
    public void setPressure( int p  )
    {
        if(pressure == 0)
            pressure = p;
        if(pressure > p && p != 0)
            pressure = p;
        SaffirSimpson();
    }
    
    public void SaffirSimpson()
    {//   Compute storm category, using the Saffir-Simpson scale
        if(pressure <=   920 &&   wind >=   156)
        {
            category =  5;
        }   // Category 5
        if(pressure >  920 &&   wind <  156)
        {
            category = 4;
        }   // Category 4}
        if(pressure >  945 &&   wind <  113)
        {
           category =  3;
        }   // Category 3
        if(pressure >  965 &&   wind <  96)
        {
            category =  2;
        }   // Category 2
        if(pressure >  980 &&   wind <  83)
        {
           category =  1;
        }   // Category 1
        if(wind <  64)
        {
            category =  -1;
        }  //   Tropical Storm
        if(wind <  34)
        {
            category =  -2;
        }  //   Tropical Depression
        if(pressure ==   0)
        {
            category =  0;
        }   // Missing pressure
    }

    public int getCategory()
    {
        return category;
    }
    
    public String toString()
    {
        return String.format("%9d %8d   %10s %4d %9d %10d\n", beginDate, duration, name, category, wind, pressure);
    }
}

