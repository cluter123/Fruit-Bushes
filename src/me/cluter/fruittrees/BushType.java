package me.cluter.fruittrees;

import java.util.UUID;

public enum BushType {
	CHERRY("Cherry", "http://textures.minecraft.net/texture/d525707696bcd15a173056fa39296e80ff41168bb0add552f4523e2558a3119", "06ebcfbe-0ea9-4d1f-a0f2-04ac2a98dc5a", 45),
	APPLE("Apple","http://textures.minecraft.net/texture/cbb311f3ba1c07c3d1147cd210d81fe11fd8ae9e3db212a0fa748946c3633", "ad04b162-ae44-4e40-8c5b-9271cb3a40d6", 1), 
	STRAWBERRY("Strawberry", "http://textures.minecraft.net/texture/cbc826aaafb8dbf67881e68944414f13985064a3f8f044d8edfb4443e76ba", "1acb2610-4fc4-46b0-8dae-679ccb31f057", 20), 
	COCONUT("Coconut", "http://textures.minecraft.net/texture/e9b0e969cf3fcced36b712350ffb46d8ed761fe5efb10e3b6a9795e6656da97", "4d09dc2c-ae45-4542-b667-c7770b8d787b", 63), 
	TACO("Taco", "http://textures.minecraft.net/texture/98ced74a22021a535f6bce21c8c632b273dc2d9552b71a38d57269b3538cf", "36784eab-0695-455b-9b45-94943728579d", 1),
	LEMON("Lemon", "http://textures.minecraft.net/texture/957fd56ca15978779324df519354b6639a8d9bc1192c7c3de925a329baef6c", "09d10b7a-f525-412c-bedf-b2fe7c8bfc70", 27), 
	PLUM("Plum", "http://textures.minecraft.net/texture/5cc016f568d1433860d82fa3379d784cbbd52e56b55f78be7291f8618da38c8", "f4f1a6e2-363a-4f7b-83e7-07f861c604a9", 51), 
	DONUT1("Donut", "http://textures.minecraft.net/texture/d07b8c51acec2a508bb2fa652fb6e4a08b19485159a099f5982ccb88df1fe27e", "6d38a755-bcf4-4c26-ab82-19a0e54dabab", 1), 
//	DONUT2("Donut", "http://textures.minecraft.net/texture/837c9b82b186656e9f6363a2a1c6a4b5b93cfa9ef4dad6f16b94ebb5e362678", "b48503a4-6dec-438c-a3bc-6b5da7fb1fde", 1), 
//	DONUT3("Donut", "http://textures.minecraft.net/texture/59da54ff366e738e31de92919986abb4d50ca944fa9926af63758b7448f18", "1422239d-a249-453c-91ba-343ec9b46f92", 1), 
	TOMATO("Tomato", "http://textures.minecraft.net/texture/99172226d276070dc21b75ba25cc2aa5649da5cac745ba977695b59aebd", "6d00d20b-a23f-43f8-bb26-b6ac346a107a", 60), 
	ORANGE("Orange", "http://textures.minecraft.net/texture/87b3d291d3b99bcd4c37a1839dc160d885ecd4e237b3aea1baf0adbb1775cd64", "997bdc8f-1269-43c7-9fe2-eeac02df6232", 4), 
	GRAPE("Grape", "http://textures.minecraft.net/texture/ee5935863c53a996f5334e90f55de538e83ffc5f6b0b8e83a4dc4f6e6b1208", "7815481b-f563-4ece-af98-64e941b82239", 69), 
	RASPBERRY("Raspberry", "http://textures.minecraft.net/texture/b12ef1b486e97e4cb124aa7629aceb91edc51d63338c91a012885493c5d9c", "f4e64e08-a19c-48db-aabb-521f571f89ed", 1), //add vip perms
	BLACKBERRY("Blackberry", "http://textures.minecraft.net/texture/2769f8b78c42e272a669d6e6d19ba8651b710ab76f6b46d909d6a3d482754", "73ebeb97-286c-479f-91ff-7b178da675e8", 1);
	
	String type;
	String url;
	UUID uuid;
	int level;
	
	private BushType(String name, String URL, String uuidd, int lvl) {
		type = name;
		url = URL;
		uuid = UUID.fromString(uuidd);
		level = lvl;
	}
	
	public String getType() {
		return type;
		}
	public String getURL() {
		return url;
	}
	public UUID getUUID() {
		return uuid;
	}
	public int getLevel() {
		return level;
	}
	
	public static BushType fromString(String s) {
		for(BushType bt : BushType.values()) {
			if(bt.toString().equals(s)) {
				return bt;
			}
		}
		return null;
	}

}
