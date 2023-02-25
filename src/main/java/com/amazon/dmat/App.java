package com.amazon.dmat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		SharePriceUpdater priceUpdater = new SharePriceUpdater();
		Thread priceThread = new Thread(priceUpdater); 
		priceThread.start();
		
						
		UserLoginOperation userLoginOperation= new UserLoginOperation();
		userLoginOperation.initiate();
		
		priceUpdater.stop();
    }
}

class SharePriceUpdater implements Runnable{

	private volatile boolean running = true;

	public void run() {

		Connection con;
		try {
			con = ConnectionManager.getConnection2();
			Statement statement = con.createStatement();
			while (running) {
				String sqlquery1 = "SELECT sharePrice from shares;";
				ResultSet resultSet = statement.executeQuery(sqlquery1);
				while(resultSet.next()) {
					float sharePrice = resultSet.getFloat(1);
					float newSharePrice = newSharePrice(sharePrice);
					
					String sqlquery2 = "UPDATE shares SET sharePrice="+newSharePrice+" WHERE sharePrice="+sharePrice+";";
					Statement statement2 = con.createStatement();
					
					statement2.executeUpdate(sqlquery2);
				}
				Thread.sleep(10000);
			}
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		running = false;
	}

	public float getRandomNumber(float min, float max) {
		return (float) ((Math.random() * (max - min)) + min);
	}

	public float newSharePrice(float sharePrice) {
		float newPrice= sharePrice+(sharePrice * (getRandomNumber(-0.5f,0.5f) / 100));
		
		if(newPrice<1) {
			return sharePrice;
		}
		
		return newPrice;
	}

}