class Myqueue{
private:
    int * queue;
    int max_size;
    int begin;
    int end;
    
    void extend(){
        int * new_queue = new int[max_size * 2];
        int j = 0;
        for(int i = begin; i != end; i = (i + 1) % max_size, ++j) new_queue[j] = queue[i];
        begin = 0;
        end  = j;
        delete [] queue;
        queue = new_queue;
        max_size *= 2;
    }
    
public:
    Myqueue(){
        max_size = 2;
        begin = 0;
        end = 0;
        queue = new int[max_size];
    }
    
    void push_back(int x){
        queue[end] =  x;
        end = (end + 1) % max_size;
        if((end + 1) % max_size == begin) extend();
    }
    
    int pop(){
        if (isEmpty()) return -1;
        else return queue[begin ++];
    }
    
    int front(){
        if (isEmpty()) return -1;
        else return queue[begin];
    }
    
    bool isEmpty(){
        return begin == end;
    }
    
    int size(){
        return begin <= end ? end - begin : max_size + end - begin;
    }
    
    void print(){
	    for(int i = begin; i != end; i = (i + 1) % max_size) std::cout << queue[i]<<" ";
	    std::cout << std::endl;
	    std::cout << "max_size "<<max_size<<" begin "<<begin<<" end "<<end<<std::endl;
    }
};


class MyStack {
private:
    Myqueue *q;
    int f;
public:
    /** Initialize your data structure here. */
    MyStack() {
        q = new Myqueue[2];
        f = 0;
    }
    
    /** Push element x onto stack. */
    void push(int x) {
        q[1 - f].push_back(x);
        while(! q[f].isEmpty()) q[1 - f].push_back(q[f].pop());
        f = 1 - f;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    int pop() {
        return q[f].pop();
    }
    
    /** Get the top element. */
    int top() {
        return q[f].front();
    }
    
    /** Returns whether the stack is empty. */
    bool empty() {
        return q[f].isEmpty();
    }
};

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * bool param_4 = obj.empty();
 */
