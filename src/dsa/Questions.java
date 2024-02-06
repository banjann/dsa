package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Questions {

	public Questions() {

	}

	// 1 Contains Duplicate
	public boolean containsDuplicate(int[] nums) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			if (set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	// 2 Valid Anagram
	public boolean isAnagram(String s, String t) {
		char[] char_s = s.toCharArray();
		char[] char_t = t.toCharArray();
		Arrays.sort(char_s);
		Arrays.sort(char_t);

		String sString = String.valueOf(char_s);
		String tString = String.valueOf(char_t);

		return sString.equals(tString);
	}

	// 3 Two Sum
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> complementIndex = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (complementIndex.get(nums[i]) != null) {
				return new int[] { i, complementIndex.get(nums[i]) };
			}
			complementIndex.put(target - nums[i], i);
		}

		return new int[] {};
	}

	// 4 Group Anagrams
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> returnList = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {
			char[] sortedChar = str.toCharArray();
			Arrays.sort(sortedChar);
			String sortedString = new String(sortedChar);

			if (map.containsKey(sortedString)) {
				map.get(sortedString).add(str);
			} else {
				List<String> list = new ArrayList<>();
				list.add(str);
				map.put(sortedString, list);
				returnList.add(list);
			}
		}
		return returnList;
	}

	// 5 K Frequency
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}

		List<Integer> listOfKeys = new ArrayList<>(map.keySet());
		listOfKeys.sort((a, b) -> map.get(b) - map.get(a));

		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = listOfKeys.get(i);
		}

		return result;
	}

	// 6 Product of Array Except Self
	public int[] productExceptSelf(int[] nums) {
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		int[] product = new int[nums.length];

		right[nums.length - 1] = 1;
		left[0] = 1;

		for (int i = 1; i < left.length; i++) {
			left[i] = nums[i - 1] * left[i - 1];
		}

		for (int i = right.length - 2; i >= 0; i--) {
			right[i] = nums[i + 1] * right[i + 1];
		}

		for (int i = 0; i < product.length; i++) {
			product[i] = left[i] * right[i];
		}

		return product;
	}
}
