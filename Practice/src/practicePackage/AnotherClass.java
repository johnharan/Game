package practicePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnotherClass extends Abstract{

	public static void main(String[] args){
		Abstract another = new AnotherClass();
		
		int add = another.y + another.z;
		System.out.println(add);
		
		another.draw();
		
		////
		HashMap<String,String> hash = new HashMap<String,String>();
		
		hash.put("Alice", "in wonderland");
		hash.put("George", "of the jungle");
		////
		
		HashMap<String,Integer> test = new HashMap<String,Integer>();
		test.put("First", 1);
		test.put("second", 2);
		System.out.println(test.get("second"));
		
		////
		
		Map<String,Integer> tst = new HashMap<String,Integer>();
		
		tst.put("1st", 1);
		tst.put("2nd", 2);
		
		for(String key: tst.keySet()){
			Integer val = tst.get(key);
			System.out.println(val);
		}
		
		///
		
		Map testing = new HashMap();
		
		testing.put(2.65, 100);
		testing.put("how", true);
		
		System.out.println(testing.get(2.65));		
		
		
		///
		
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("hi");
		System.out.println(lst.size());
		System.out.println(lst.get(0));
		
		for(String s: lst){
			System.out.println(s);
		}
		
		for(int i=0;i<lst.size();i++){
			System.out.println(lst.get(i));
		}
		
		////
		
		
		int[] intarr = new int[10];
		intarr[0] = 1;
		intarr[1] = 2;
		for(int i: intarr){
			if(i != 0){
				System.out.println(i);
			}
		}
		System.out.println(Arrays.toString(intarr));
		
		////

		List lstarr = new ArrayList<Integer>();
		lstarr.add(1);
		
		
		////
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Hi");
		arr.add("Hello");
		
		for(String s: arr){
			System.out.println(s);
		}
		
		///
		
		Map<String,Integer> maparr = new HashMap();
		
		maparr.put("hi",12);
		
		for(Map.Entry<String,Integer> e: maparr.entrySet()){
			System.out.println("Key: "+ e.getKey() + ",Value: "+e.getValue());
		}
		
		maparr.get("hi");
		
		for(String key: maparr.keySet()){
			System.out.println(maparr.get(key));
		}
		
		///
		List<String> arrarr = new ArrayList<String>();
		arrarr.add("lot");
		arrarr.add("lore");
		
		Iterator<String> iter = arrarr.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		////
		
		
		String[][] matrix = {{"hi","hello"},{"h","l"}};
		System.out.println(matrix[0][1]);
		
		for(String[] s: matrix){
			System.out.println(Arrays.toString(s));
		}
		
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				System.out.println(matrix[i][j]);
			}
		}
		
		////
		
	}
}
