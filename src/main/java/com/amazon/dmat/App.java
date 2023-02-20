package com.amazon.dmat;

import com.amazon.classifieds.dbtools.ConnectionManager;
import com.amazon.classifieds.operations.AppDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

		System.out.println( "************************************************" );
		System.out.println( "Welcome to Amazon Internal  Classified App" );
		System.out.println( "************************************************" );
		
		if(args.length>0) {
			ConnectionManager.FILEPATH=args[0];
		}
		
				
		AppDriver appDriver= new AppDriver();
		appDriver.initiate();
    }
}
