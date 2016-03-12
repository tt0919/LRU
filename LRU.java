class Node {
    int key;
    int value;
    Node pre;
    Node next;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            int res = node.value;
            remove(node);
            setHead(node);
            return res;
        }    
        return -1;
        
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            setHead(node);
        } else {
            if(this.capacity==map.size()) {
                map.remove(tail.key);
                remove(tail);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            setHead(node);
        }
    }
    
    private void remove(Node node) {
        if(node.pre!=null) {
            node.pre.next = node.next;
        } else {
            head = node.next;
        }
        if(node.next!=null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
    }
    
    private void setHead(Node node) {
        if(head!=null) {
            node.next = head;
            node.pre = null;
            head.pre = node;
            head = node;
        } else {
            head = node;
            tail = node;
        }
    }
}