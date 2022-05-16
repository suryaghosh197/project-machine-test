package javatest;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {
	
	//1st Approch
	public void findRepeatingElementList_Approch1(int[] arr) {
		
		List<Integer> result = new ArrayList<>();
		
		
		for(int i=0;i<arr.length;i++) {
			int elementToSearch = arr[i];
			int count=0;
			
			if(result.indexOf(elementToSearch)>=0) {
				continue;
			}
			for(int j=0; j<arr.length;j++) {
				if(arr[j]==elementToSearch) {
					count++;
				}
				if(count>=2) {
					result.add(elementToSearch);
					break;
				}
			}
		}
		
		System.out.println(result);
	}
	
	//2nd Approch
	public void findRepeatingElementList_Approch2(int[] arr) {
		
		List<Integer> result = new ArrayList<>();
		List<Integer> trmp = new ArrayList<>();
		
		for(int i=0;i<arr.length;i++) {
			if(trmp.indexOf(arr[i])<0) {
				trmp.add(arr[i]);
			}
			else {
				result.add(arr[i]);
			}
		}
		
		System.out.println(result);
	}
	

	

	public static void main(String[] args) {
		
		int[] inputArray = new int[] {10,8,3,1,3,8,16,23};
		
		JavaTest obj = new JavaTest();
//		obj.findRepeatingElementList_Approch1(inputArray);
		obj.findRepeatingElementList_Approch2(inputArray);

	}

}
