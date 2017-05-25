int findMin(int* nums, int numsSize) {
    while(numsSize > 2){
        if(*(nums + numsSize / 2) > *(nums + numsSize - 1)){
            nums += numsSize / 2 + 1;
            numsSize -= numsSize / 2 + 1;
        }
        else
            numsSize = numsSize / 2 + 1;
    }
    if(numsSize == 1) return *nums;
    return *nums < *(nums + 1) ? *nums : *(nums + 1);
}
