/*
思路：实现一个LRU策略的缓存，要求查找和添加的复杂度都为O(1),可以参考linkedHashmap实现，添加一个DlinkedEntry类，构造如下图
    的循环链表，越在链表尾部代表最近越访问过。并且链表中出现的元素存放在hashmap(key, DlinkedEntry)中。
    get：把get的entry放在链表尾部
    put：如果容量满了且put的是新元素，就删除链表头部节点，然后添加新元素到链表尾部。
 */
public class LRUCache {
    class DlinkedEntry{
        int key;
        int value;
        DlinkedEntry before;
        DlinkedEntry after;
        public DlinkedEntry(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    DlinkedEntry head,tail;
    int capacity,size;
    Map<Integer, DlinkedEntry> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, DlinkedEntry>();
        size = 0;
        head = new DlinkedEntry(0,0);
        tail = new DlinkedEntry(0,0);
        head.after = tail;
        tail.before = head;
    }

    public int get(int key) {
        DlinkedEntry node = map.get(key);
        if (node != null) {
            adjustToLast(node);
            return node.value;
        }
        else{
            return -1;
        }
    }

    public void adjustToLast(DlinkedEntry node){
        DlinkedEntry b = node.before;
        DlinkedEntry a = node.after;
        if (b != null) b.after = a;
        if (a != null) a.before = b;
        DlinkedEntry pre = tail.before;
        pre.after = node;
        tail.before = node;
        node.after = tail;
        node.before = pre;
    }

    public void deleteFirst(){
        DlinkedEntry first = head.after;
        head.after = first.after;
        first.after.before = head;
        int key = first.key;
        map.remove(key);
        --size;
    }

    public void put(int key, int value) {
        DlinkedEntry node = map.get(key);
        if (node == null && capacity == size) deleteFirst();

        if (node == null) {
            node = new DlinkedEntry(key,value);
            map.put(key,node);
            ++size;
        }
        else {
            node.value = value;
        }
        adjustToLast(node);
    }
    public void printList(){
        DlinkedEntry p = head;
        while (p != null){
            System.out.print("("+p.key+","+p.value+")->");
            p = p.after;
        }
        System.out.println("  "+size);
        System.out.print("         ");
        p = tail;
        while (p != null){
            System.out.print("("+p.key+","+p.value+")->");
            p = p.before;
        }
        System.out.println("  "+size);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */