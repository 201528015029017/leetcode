/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int val(char c){
    //返回一个字母对应在row数组中的位置
    if(c - 97 >= 0)
        return c - 97;
        //如果是小写字母，a的ASCII码为97，*c减去它为所在row数组中的对应位置
    else
        return c - 65;
        //大写字母同上
}
char** findWords(char** words, int wordsSize, int* returnSize) {
    int row[26] = {2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
    // 每一个字母对应的行号
    int i;
    int j = 0;
    char **result = (char *)malloc(wordsSize * sizeof(char *));
    // 结果数组最多和原数组一样长，所以结果不会超过wordsSize的空间，就开这么大。
    
    for(i = 0; i < wordsSize; ++i){
        
        char* c = words[i];
        if (*c == 0) continue;
        
        int count;
        // 记录当前单词第一个字符的行号
        switch(row[val(*c)]){
            case 1: count = 1; break;
            case 2: count = 2; break;
            case 3: count = 3; break;
        }
        
        while(*c != 0){
            if(row[val(*c)] != count){
                count = 0;
                break;
            }
            c ++;
            //判断这个单词的接下来的字符的行号，如果与第一个不同，说明不符合要求，count置为0
        }
        
        if (count != 0){
            // count 不为0 说明符合要求
            result[j++] = words[i];
        }
    }
    
    *returnSize = j;
    //输出数组的有效长度为j    
    return result;
}
