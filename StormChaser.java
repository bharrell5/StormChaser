/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brett
 */

import java.io.*;
import java.util.Scanner;

public class StormChaser {
    
    public static void main(String[] args)
    {
        //   Constants
        final int MAX_STORMS =  200;
        
        Storm[] List = new Storm[MAX_STORMS];  //   array of   Storms
        Storm CurrentStorm;      // storm returned by GetStorm
        int NStorms =  0;         // number in array List
        int Total =  0;           // total number of storms in the input file
        Scanner fileInput;

        //   Openning hurricane data file
        try{
            System.out.println("Openning hurricane data file...");
            fileInput =  new Scanner(new File("hurricane.data"));
        }
        
        catch(FileNotFoundException e){
            System.err.println("FileNotFoundException: "  + e.getMessage());
            return;
        }
        System.out.println( "File opened successfully...");
        System.out.println( "Reading file..." );
        
        //   Read Storm data from file until EOF
        
        while( fileInput.hasNextLine())
        {
            CurrentStorm = GetStorm(fileInput);
            ++Total;
            if( CurrentStorm.getCategory() >= 3  )
            {
                List[NStorms++] = CurrentStorm;
            }
        }
        System.out.println( "Number of   storms: " + Total);
        System.out.println( "Hurricanes with category 3  and above: "  + NStorms );
        DisplayStorms( "First Ten Storms", List, 10 );
        Sort( List, NStorms );
        DisplayStorms( "Top Ten Storms", List, 10   );
        fileInput.close();
}
    public static Storm GetStorm( Scanner in )
    {
        //   Build a  Storm object and return it
            int year = 0,   month = 0, day = 0, hour = 0, sequence = 0, wind = 0, pressure = 0;
            String name;
            double junk = 0.0;
            int current = 0, beginDate = 0, duration =  0;
            Storm NewStorm;
            
            //   Check for end of file
            //if( !in.hasNextLine() )
            //{
                //NewStorm =  new Storm(beginDate, duration, name, wind, pressure);
                //return NewStorm;
            //}

            //   Read next record.
            year = in.nextInt();
            month = in.nextInt();
            day = in.nextInt();
            hour = in.nextInt();
            sequence = in.nextInt();
            name = in.next();
            junk = in.nextDouble();
            junk = in.nextDouble();
            wind = in.nextInt();
            pressure = in.nextInt();


                //   Make a  storm object and initialize it   with info from the currentrecord
                beginDate =  year *  10000 + month *  100 +  day;
                
                NewStorm = new Storm(beginDate, duration, name, wind, pressure);
                current =  sequence;
                
                while( in.hasNextLine() &&  current == sequence)
                {
                    //update storm info
                    duration += 6;
                    NewStorm.setDuration(duration);
                    NewStorm.setWind(wind);
                    NewStorm.setPressure(pressure);


                    //get next record
                    year = in.nextInt();
                    month = in.nextInt();
                    day = in.nextInt();
                    hour = in.nextInt();
                    sequence = in.nextInt();
                    name = in.next();
                    junk = in.nextDouble();
                    junk = in.nextDouble();
                    wind = in.nextInt();
                    pressure = in.nextInt();
                    
                }


                //   and return the new storm object
                return NewStorm;
    }
    
    
    public static void DisplayStorms( String title, Storm[] List, int NStorms )
    {
        //   display NStorms storms
        //   print some title and column headings
        System.out.println(title +  "\n");
        System.out.println("Begin Date   Duration   Name   Category   Maximum    Minimum");
        System.out.println("             (hours)                     Winds (mph) Press. (mb)");
        System.out.println("----------------------------------------------------------------");
        
        for( int k =  0; k  <  NStorms; k++ )
            System.out.println(List[k].toString());
        System.out.println ("\n");
    }
    
    public static void Sort( Storm[] StormList, int N  )
    {
        //   bubble sort the list of   Storms
        int pass = 0,   k,   switches;
        Storm temp;
        switches = 1;
        
        while( switches != 0 )
        {
            switches =  0;
            pass++;
            for( k =  0; k  < N  -  pass; k++ )
            {
                if( StormList[k].getCategory() < StormList[k+1].getCategory() )
                {
                    temp =  StormList[k];
                    StormList[k] =  StormList[k+1];
                    StormList[k+1] = temp;
                    switches = 1;
                }
            }
        }
    }
}