/*
 * quickSelect to find the median first
 * then put smaller than median to the last even place
 * larger median element to the first odd place
 * Time: O(n) Space: O(1)
 */
public class LeetCode324 {
	int [] nums;
    int n;
    public void wiggleSort(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int median = quickselect(0, n-1, (n+1)/2);
        int l = 0, i = 0, r = nums.length-1;
        while (l < r){
            if (nums[newIndex(l)] < median){
                swap(newIndex(l), newIndex(r--));
            } else if (nums[newIndex(l)] > median){
                swap(newIndex(l++), newIndex(i++));              
            } else{
                l++;
            }
        }
        
    }
    public int newIndex(int i){
        return (1+2*i)%(n|1);
    }    
  public void swap(int a, int b) {
    int tmp = this.nums[a];
    this.nums[a] = this.nums[b];
    this.nums[b] = tmp;
  }
  public int partition(int left, int right, int pivot_index) {
    int pivot = this.nums[pivot_index];
    swap(pivot_index, right);
    int store_index = left;
    for (int i = left; i <= right; i++) {
      if (this.nums[i] < pivot) {
        swap(store_index, i);
        store_index++;
      }
    }
    swap(store_index, right);
    return store_index;
  }
  public int quickselect(int left, int right, int k_smallest) { 
    if (left == right) 
      return this.nums[left];  
    int pivot_index = left + (right-left)/2;     
    pivot_index = partition(left, right, left+(right-left)/2);
    if (k_smallest == pivot_index)
      return this.nums[k_smallest];
    else if (k_smallest < pivot_index)
      return quickselect(left, pivot_index - 1, k_smallest);
    return quickselect(pivot_index + 1, right, k_smallest);
  }
}
