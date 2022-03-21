package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataPayload {
	
	public AddPlace AddPlacepayload(String name, String language, String address, String phonenumber, String website) 
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number(phonenumber);
		p.setWebsite(website);
		Location loc = new Location();
		loc.setLat(-32.5656);
		loc.setLng(56.258);
		p.setLocation(loc);
		List<String> mylist = new ArrayList<String>();
		mylist.add("pharma");
		mylist.add("medical");
		p.setTypes(mylist);
		return p;
		
	}

}
