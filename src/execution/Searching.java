package execution;

public class Searching {
	
	public static Comparable[] linearSearch(Comparable[] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);
		
		//array to place search hits into
		Comparable[] searched = new Comparable[n];
		int counter = 0;
		
		//seach linearly through entire inputted array
		for(int i = 0; i < n; i++) {
			//note that compareTo needs to parse through entire entry in specified category
			//and return true if the intArgument is in it at any point
			//could pose an error with the rank
			//not sure how to implement a "getcategory()" bc the category could be so many different things
			if(x[i].category.CompareTo(intArgument))
			{
				searched[counter] = x[i];
				counter += 1;
			}
		}
		return searched;
	}
	
	public static Comparable[] binaryLinearSearch(Comparable[] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);
		
		//array to place search hits into
		Comparable[] searched = new Comparable[n];
		int counter = 0;
		
		//binary search to find one matching WineADT
		int oneIndex = indexOf(x, intArgument, category);
		
		//find lower boundary of matching WineADTs
		while(oneIndex != 0) {
			if(WineADT.equal(x[oneIndex].category, intArgument))
				oneIndex -= 1;
			else break;
		}
		int lowerBound = oneIndex;
		
		//find upper boundary of matching WineADTs
		while(oneIndex != n-1)
		{
			if(WineADT.equal(x[oneIndex].category, intArgument)) {
				oneIndex += 1;
			}
			else break;
		}
		int upperBound = oneIndex;
		
		//place found WineADTs into an array
		for(int i = lowerBound; i <= upperBound; i++) {
			searched[i-lowerBound] = x[i];
		}
		
		return searched;
	}
	
	
	//binary search for combined binary linear search
	public static int indexOf(Comparable[] x, Integer key, String category) {
		int lo = 0;
		int hi = x.length - 1;
		while(lo <- hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(WineADT.less(key, x[mid].category)) 
			{
				hi = mid - 1;
			}
			else if(WineADT.less(key,x[mid].category))
			{
				lo = mid + 1;
			}
			else return mid;
		}
		return -1;
	}
	
	//straight up binary search
	public static WineADT binarySearch(Comparable[] x, String argument, String category) {
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);		
				
		int lo = 0;
		int hi = x.length - 1;
		while(lo <- hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(WineADT.less(intArgument, x[mid].category)) 
			{
				hi = mid - 1;
			}
			else if(WineADT.less(x[mid].category, intArgument))
			{
				lo = mid + 1;
			}
		}
		return x[-1];
	}
	
	
}
