package com.amazon.dmat;



import com.amazon.dmat.dbtools.ConnectionManager;
import com.amazon.dmat.operations.UserLoginOperation;

public class App 
{
    public static void main( String[] args )
    {

		System.out.println( "************************************************" );
		System.out.println( "Welcome to D-MAT Trading Account Manager App" );
		System.out.println( "************************************************" );
		
		if(args.length>0) {
			ConnectionManager.FILEPATH=args[0];
		}
						
		UserLoginOperation userLoginOperation= new UserLoginOperation();
		userLoginOperation.initiate();
    }
}
