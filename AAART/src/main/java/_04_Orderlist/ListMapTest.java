package _04_Orderlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, String> cartmap = new HashMap<String, String>();
		HashSet<HashMap>  cartlist =new HashSet();
		cartmap.put("title", "11");
		cartmap.put("halfnum", "halfnum");
		cartmap.put("adultnum", "adultnum");
		cartmap.put("total1", "total1");
		cartmap.put("total2", "total2");
		System.out.println(cartmap);
		cartlist.add(cartmap);
		
		for (HashMap i : cartlist) {
        
			System.out.println(i.get("title"));	
//		System.out.println(i);
        
    }
//		
		
		
	}

}
