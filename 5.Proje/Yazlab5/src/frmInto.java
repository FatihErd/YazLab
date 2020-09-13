
import java.awt.Button;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.JTextField;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;

public class frmInto extends javax.swing.JFrame {

//########################################################################
//########################################################################
    int a, k = 0, p = 0;
    int baslangic, bitis;
    ArrayList<JTextField> fields;
    int[][] matris;
     int[][] matris2;
    int[] cizi;
    int[] cizj;

    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not 
        // visited 
        boolean visited[] = new boolean[a];
        for (int i = 0; i < a; ++i) {
            visited[i] = false;
        }

        // Create a queue, enqueue source vertex and mark 
        // source vertex as visited 
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop 
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < a; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then 
        // return true, else false 
        return (visited[t] == true);
    }

    private static void dfs(int[][] rGraph, int s,
            boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < rGraph.length; i++) {
            if (rGraph[s][i] > 0 && !visited[i]) {
                dfs(rGraph, i, visited);
            }
        }
    }

// Prints the minimum s-t cut 
// https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/
    private void minCut(int[][] graph, int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual  
        // graph with given capacities in the original  
        // graph as residual capacities in residual graph 
        // rGraph[i][j] indicates residual capacity of edge i-j 
        int[][] rGraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                rGraph[i][j] = graph[i][j];
            }
        }

        // This array is filled by BFS and to store path 
        int[] parent = new int[graph.length];

        // Augment the flow while tere is path from source to sink      
        while (bfs(rGraph, s, t, parent)) {

            // Find minimum residual capacity of the edhes  
            // along the path filled by BFS. Or we can say  
            // find the maximum flow through the path found. 
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // update residual capacities of the edges and  
            // reverse edges along the path 
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] = rGraph[u][v] - pathFlow;
                rGraph[v][u] = rGraph[v][u] + pathFlow;
            }
        }

        // Flow is maximum now, find vertices reachable from s      
        boolean[] isVisited = new boolean[graph.length];
        dfs(rGraph, s, isVisited);
        int artt = 0;
        // Print all edges that are from a reachable vertex to 
        // non-reachable vertex in the original graph      
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    cizi[artt] = i;
                    cizj[artt] = j;
                    artt++;
                    System.out.println(i + " - " + j);

                }
            }
        }
    }

// Returns tne maximum flow from s to t in the given graph 
// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph 
        // with given capacities in the original graph as 
        // residual capacities in residual graph 
        // Residual graph where rGraph[i][j] indicates 
        // residual capacity of edge from i to j (if there 
        // is an edge. If rGraph[i][j] is 0, then there is 
        // not) 
        int rGraph[][] = new int[a][a];

        for (u = 0; u < a; u++) {
            for (v = 0; v < a; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        // This array is filled by BFS and to store path 
        int parent[] = new int[a];

        int max_flow = 0;  // There is no flow initially 

        // Augment the flow while tere is path from source 
        // to sink 
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes 
            // along the path filled by BFS. Or we can say 
            // find the maximum flow through the path found. 
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and 
            // reverse edges along the path 
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow 
            max_flow += path_flow;
        }

        // Return the overall flow 
        return max_flow;
    }

//########################################################################
//########################################################################
    Graphics graphics;
    int width;
    int height;

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

    public frmInto() {
        initComponents();
        setResizable(false);
        graphics = jPanel1.getGraphics();
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    public frmInto(String name) { //Construct with label
        // this.setTitle(name);
        initComponents();
        setResizable(false);
        graphics = jPanel1.getGraphics();
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    class Node {

        int x, y;
        String name;

        public Node(String myName, int myX, int myY) {
            x = myX;
            y = myY;
            name = myName;
        }
    }

    class edge {

        int i, j;

        public edge(int ii, int jj) {
            i = ii;
            j = jj;
        }
    }

    public void addNode(String name, int x, int y) {
        //add a node at pixel (x,y)
        nodes.add(new Node(name, x, y));
        this.repaint();
    }

    public void addEdge(int i, int j) {
        //add an edge between nodes i and j
        edges.add(new edge(i, j));
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtbox = new javax.swing.JTextField();
        btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        verileriAl = new javax.swing.JButton();
        cizdir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        baslangicText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bitisText = new javax.swing.JTextField();
        maxFlowText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Heap:");

        txtbox.setToolTipText("");
        txtbox.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtbox.setName(""); // NOI18N
        txtbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtboxFocusLost(evt);
            }
        });
        txtbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtboxActionPerformed(evt);
            }
        });
        txtbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtboxKeyTyped(evt);
            }
        });

        btn.setText("kaydet");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        verileriAl.setText("Verileri Al");
        verileriAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verileriAlActionPerformed(evt);
            }
        });

        cizdir.setText("Çizdir");
        cizdir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cizdirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jLabel3.setText("Başlangıç:");

        jLabel4.setText("Bitis:");

        bitisText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitisTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(baslangicText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(bitisText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(maxFlowText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(verileriAl)
                        .addGap(40, 40, 40)
                        .addComponent(cizdir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn)
                    .addComponent(verileriAl)
                    .addComponent(cizdir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(baslangicText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(bitisText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxFlowText)
                        .addGap(39, 39, 39))))
        );

        txtbox.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
        System.out.println(txtbox.getText());
        a = Integer.parseInt(txtbox.getText());
        System.out.println(a);

        cizi = new int[a];
        cizj = new int[a];

        int art = 0;
        String indis;
        fields = new ArrayList<JTextField>();

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {

                System.out.print("0");

                txtbox = new javax.swing.JTextField(Integer.toString(0));

                txtbox.setSize(txtbox.getPreferredSize());

                txtbox.setBounds(((j * 60) + 20), ((i * 60) + 70), 40, 30);

                txtbox.setName(Integer.toString(i) + Integer.toString(j));

                fields.add(txtbox);

                System.out.println("-----" + txtbox.getName() + "/n");
                txtbox.setVisible(true);
                jLabel2.setVisible(true);

                add(txtbox);
                add(jLabel2);
                art++;
            }
            System.out.println("");

            repaint();
        }
        
               for (int i = 0; i < a; i++) {
             jLabel2 = new javax.swing.JLabel();
             jLabel2.setText(Integer.toString(i));
             jLabel2.setBounds(10, (i * 40) + 55, 10, (i * 40) + 55);
             jLabel2.setName(Integer.toString(i) );
            add(jLabel2); 
        } 
        
        for (int j = 0; j < a; j++) {
                                 jLabel2 = new javax.swing.JLabel();
                jLabel2.setText(Integer.toString(j));
                jLabel2.setBounds((j * 60) + 35, 40, (j * 60) + 35, 40);
                jLabel2.setName( Integer.toString(j));
                add(jLabel2);

            } 


    }//GEN-LAST:event_btnActionPerformed

    private void txtboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtboxActionPerformed

    private void txtboxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtboxKeyTyped
        // TODO add your handling code here           
    }//GEN-LAST:event_txtboxKeyTyped

    private void txtboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtboxFocusGained


    }//GEN-LAST:event_txtboxFocusGained

    private void txtboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtboxFocusLost


    }//GEN-LAST:event_txtboxFocusLost
// ########################   çizim  ####################################
// http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
    private void cizdirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cizdirActionPerformed

        FontMetrics f = graphics.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        for (edge e : edges) {
            for (int q=0; q < cizi.length; q++) {
                if(cizi[q]==(e.i) && cizj[q]==(e.j)){
                    graphics.setColor(Color.red);
                    graphics.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,nodes.get(e.j).x, nodes.get(e.j).y);
                    graphics.drawString(Integer.toString(matris2[e.i][e.j]),((nodes.get(e.i).x)+nodes.get(e.j).x)/2,((nodes.get(e.i).y)+nodes.get(e.j).y)/2); 
                     
                    break; 
                    
                    
                }
                else
                {
                    graphics.setColor(Color.black);
                    graphics.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
                    nodes.get(e.j).x, nodes.get(e.j).y);  
                     graphics.drawString(Integer.toString(matris2[e.i][e.j]),((nodes.get(e.i).x)+nodes.get(e.j).x)/2,((nodes.get(e.i).y)+nodes.get(e.j).y)/2); 
}
            }
        }
            /*graphics.setColor(Color.black);
       for (edge e : edges) {
           System.out.println(e.i);
           System.out.println(e.j);
	    graphics.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
	     nodes.get(e.j).x, nodes.get(e.j).y);  
	}*/

            for (Node n : nodes) {
                int nodeWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
                graphics.setColor(Color.CYAN);
                graphics.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2, nodeWidth, nodeHeight);
                graphics.setColor(Color.BLUE);
                graphics.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2, nodeWidth, nodeHeight);
                graphics.drawString(n.name, n.x - f.stringWidth(n.name) / 2, n.y + f.getHeight() / 2);

            }

            /*  for (edge e : edges) {
for(int a;a<cizi.length;a++){
	   İf(cizi[a]==nodes.get(e.i)&&cizj[a]==nodes.get(e.j)){
graphics.setColor(Color.red);
graphics.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
	     nodes.get(e.j).x, nodes.get(e.j).y);  
}else{ 
graphics.setColor(Color.black)
graphics.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
	     nodes.get(e.j).x, nodes.get(e.j).y);  
}
	}}*/

    }//GEN-LAST:event_cizdirActionPerformed

    private void verileriAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verileriAlActionPerformed
        baslangic = Integer.parseInt(baslangicText.getText());
        bitis = Integer.parseInt(bitisText.getText());
        matris = new int[a][a];
        matris2 = new int[a][a];
        for (int i = 0; i < (a * a); i++) {

            //indis = Integer.toString(i) + Integer.toString(j);
            // System.out.println(fields.get(i).getText());
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                matris[i][j] = Integer.parseInt(fields.get(k).getText());
                k++;

            }
        }


        /*
        addNode("a", 50,50);
	addNode("b", 100,50);
        addNode("c", 50,100);
        addNode("d", 100,100);
        addNode("e", 50,150);
        addNode("f", 100,150);
        addNode("g", 50,200);
         */
        for (int i = 0; i < a; i++) {
            if (i == 0) {
                addNode(Integer.toString(i), 300, 30 * (i) + 30);
                continue;
            }
            if (i == (a - 1)) {
                addNode(Integer.toString(i), 300, 30 * (i) + 30);
                continue;
            }
            if (i % 2 == 1) {
                addNode(Integer.toString(i), 200, 30 * (i) + 30);
            } else {
                addNode(Integer.toString(i), 400, 30 * (i - 1) + 30);
            }

        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if (matris[i][j] != 0) {
                    System.out.println(i + "-" + j + " " + matris[i][j]);
                    matris2[i][j]=matris[i][j];
                    addEdge(i, j);
                    //addEdge(0,2);
                }

            }
            //System.out.println("\n");
        }
        int maxFlow = fordFulkerson(matris, baslangic, bitis);  // 0-5
        maxFlowText.setText("Max Flow: " + Integer.toString(maxFlow));

        //System.out.println("Min Cut");
        minCut(matris, baslangic, bitis);

        for (int i = 0; i < cizi.length; i++) {
          //  System.out.println(cizi[i]);
        }

    }//GEN-LAST:event_verileriAlActionPerformed

    private void bitisTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitisTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bitisTextActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField baslangicText;
    private javax.swing.JTextField bitisText;
    private javax.swing.JButton btn;
    private javax.swing.JButton cizdir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel maxFlowText;
    private javax.swing.JTextField txtbox;
    private javax.swing.JButton verileriAl;
    // End of variables declaration//GEN-END:variables
}
