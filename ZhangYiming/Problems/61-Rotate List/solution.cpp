/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if(head == NULL) return head;
        int length = 1;
        ListNode *rear = head;
        while(rear->next != NULL){
            rear = rear->next;
            length ++;
        }
        int find = length - k % length;
        if(find == length) return head;
        
        ListNode *p = head;
        while(find > 1){
            p = p->next;
            find --;
        }
        
        rear->next = head;
        head = p->next;
        p->next = NULL;
        
        return head;
    }
};
