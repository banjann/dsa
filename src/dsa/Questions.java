package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	// 7 Valid Sudoku
	public boolean isValidSudoku(char[][] board) {
		// check row per row
		for (int r = 0; r < 9; r++) {
			List<String> currentRow = new ArrayList<>();
			Set<String> currentRowAsSet = new HashSet<>();
			for (int c = 0; c < 9; c++) {
				String element = String.valueOf(board[r][c]);
				if (!".".equals(element)) {
					currentRow.add(element);
					currentRowAsSet.add(element);
				}
			}
			if (currentRow.size() != currentRowAsSet.size()) {
				return false;
			}
		}

		// check per column
		for (int c = 0; c < 9; c++) {
			List<String> currentCol = new ArrayList<>();
			Set<String> currentColAsSet = new HashSet<>();
			for (int r = 0; r < 9; r++) {
				String element = String.valueOf(board[r][c]);
				if (!".".equals(element)) {
					currentCol.add(element);
					currentColAsSet.add(element);
				}
			}
			if (currentCol.size() != currentColAsSet.size()) {
				return false;
			}
		}

		// check each sub matrix
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				ArrayList<String> submatrixAsList = new ArrayList<>();
				Set<String> submatrixAsSet = new HashSet<>();
				int subMatrix_r = 0;
				int subMatrix_c = 0;
				if (3 <= r && r < 6) {
					subMatrix_r = 3;
				}
				if (r >= 6) {
					subMatrix_r = 6;
				}
				if (3 <= c && c < 6) {
					subMatrix_c = 3;
				}
				if (c >= 6) {
					subMatrix_c = 6;
				}
				for (int i = subMatrix_r; i < subMatrix_r + 3; i++) {
					for (int j = subMatrix_c; j < subMatrix_c + 3; j++) {
						String element = String.valueOf(board[i][j]);
						if (!".".equals(element)) {
							submatrixAsList.add(element);
							submatrixAsSet.add(element);
						}
					}
				}
				if (submatrixAsList.size() != submatrixAsSet.size()) {
					return false;
				}
			}
		}
		return true;
	}

	// 8 Encode and decode Strings
	/*
	 * Design an algorithm to encode a list of strings to string. The encoded string
	 * is then sent over the network and is decoded back to the original list of
	 * strings. Please implement encode and decode.
	 */
	public String encode(List<String> strs) {
		StringBuilder encoded = new StringBuilder("");
		for (int i = 0; i < strs.size(); i++) {
			if (i != strs.size()) {
				encoded.append(String.valueOf(strs.get(i).length()));
				encoded.append("%");
			}
			encoded.append(strs.get(i));
		}
		return encoded.toString();
	}

	public List<String> decode(String str) {
		List<String> decoded = new ArrayList<>();
		int i = 0;
		int j;
		int length = str.length();
		while (i < length) {
			j = i;
			while (str.charAt(j) != '%') {
				j++;
			}
			int size = Integer.valueOf(str.substring(i, j));
			decoded.add(str.substring(j + 1, j + 1 + size));
			i = i + 1 + 1 + size;
		}
		return decoded;
	}

	// 9 Longest Consecutive Sequence
	public int longestConsecutive(int[] nums) {
		int mostCons = 0;
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		for (int num : nums) {
			int prev = num - 1;
			int next = num + 1;
			int consecutive = 1;
			while (set.contains(prev)) {
				set.remove(prev);
				prev--;
				consecutive++;
			}
			while (set.contains(next)) {
				set.remove(next);
				next++;
				consecutive++;
			}
			if (consecutive > mostCons) {
				mostCons = consecutive;
			}
		}
		return mostCons;
	}
}
