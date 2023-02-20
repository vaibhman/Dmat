package com.amazon.dmat.assets;

public class AssetFactory {
	private static AssetFactory assetFactory;

	public static AssetFactory getInstance() {
		if (assetFactory == null) {
			assetFactory = new AssetFactory();
		}
		return assetFactory;
	}

	public User getUserInstance(int accountNo, String userName, float accountBalance, String password) {
		return new User(accountNo, userName, accountBalance, password);
	}
	
	public Share getShareInstance(int shareId, String shareName, float sharePrice) {
		return new Share(shareId, shareName, sharePrice);
	}
}
