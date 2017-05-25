/**
 * Definition for undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
class Solution {
public:
    UndirectedGraphNode *clone(UndirectedGraphNode *node, map<int, UndirectedGraphNode *> & hash) {
        
        if  (node == NULL) return NULL;
        
        int key = node->label;
        if(hash.find(key) != hash.end()) return hash[key];
        
        hash[key] = new UndirectedGraphNode(key);
        
        for(auto p : node->neighbors) hash[key]->neighbors.push_back(clone(p, hash));
        
        return hash[key];
    }
    
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        
        map<int, UndirectedGraphNode *> hash;
        
        return clone(node, hash);
    }
};
