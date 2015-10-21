package practicePackage;

public class RecursiveBinarySearch {

	public int binarySearch(int[] arr, int low, int high, int x){
		int middleIndex = (low + high) / 2;
		System.out.println("low: " + low + ",high: " + high + ",middleindex: " + middleIndex);
		if(arr[middleIndex] == x){
			return middleIndex;
		}else if(arr[middleIndex] < x){
			return binarySearch(arr,middleIndex + 1,high,x);
		}else{
			return binarySearch(arr,low,middleIndex - 1,x);
		}

	}
	
	
	public static void main(String[] args) {
		RecursiveBinarySearch rec = new RecursiveBinarySearch();
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
		
		
		System.out.println(rec.binarySearch(arr, 0, arr.length-1, 2));

	}

}
