package pw.childcontrol.tools.reverseGeocoding;

import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class TreeTool {
	private DefaultMutableTreeNode rootNode;
	private String[] parents;
	private boolean[] bparents;
	private String[][] brothers;
	private boolean[] bbrothers;
	private String wynik;
	Result rezultat;
	HashMap<String, Result> calkowityWynik;
	
	public TreeTool(DefaultMutableTreeNode rootNode){
		this.rootNode = rootNode;
	}
	public void printTree(){
		System.out.println("Root: "+rootNode.getRoot());
		printTree(rootNode.getRoot(),0);
		
	}
	private void printTree(TreeNode tree, int level){
		
		for(Enumeration<TreeNode> e = tree.children(); e.hasMoreElements();){
			TreeNode tmp = e.nextElement();
			
			for(int i = 0; i<level;i++)
				System.out.print("  ");
			if(tmp.isLeaf()){				
				System.out.println(tmp);
			}else{
				System.out.println("<"+tmp+">");
			}
			printTree(tmp,level+1);
		}	
	}
	
	public void findNode(String node, String[] parents, String[][] brothers){
		this.parents = parents;
		bparents = new boolean[parents.length];
		for(boolean v:bparents)
			v=false;	
		wynik = null;
		findNode(rootNode.getRoot(),node);
		//System.out.println(wynik);
		
	}
	
	private void findNode(TreeNode tree, String node){
		for(Enumeration<TreeNode> e = tree.children(); e.hasMoreElements();){
			TreeNode tmp = e.nextElement();
			//isIn(tmp);
			
			if(tmp.toString().equals(node)){
				for(boolean v:bparents) 
					v=false;
				hasExactParents(tmp);
				System.out.println(tmp);
				this.wynik = tmp.toString();
			}
			findNode(tmp,node);
		}
		
	}
	private void isIn(TreeNode tree){
		int i=0;
		for(String tmp : parents){
			if (tree.toString().equals(tmp))
				bparents[i] = true;
			i++;
		}
	}
	private boolean isAllTrue(){
		for(boolean v:bparents){
			if(v==false)
				return false;
		}
		return true;
	}
	private boolean isThatBrothers(TreeNode tree){
		if(tree.getChildCount()==0) return false;
		for(int i=0;i<this.brothers.length;i++){
			if(tree.equals(brothers[i][0]) && tree.getChildAt(0).equals(brothers[i][1]))
				return true;
		}
		return false;
	}
	private void hasExactParents(TreeNode tree){
		isIn(tree);
		if(tree.getParent()!=null)
			hasExactParents(tree.getParent());
	}
	private void hasExactBrothers(TreeNode tree){
		for(Enumeration<TreeNode> v=tree.getParent().children();v.hasMoreElements();){
			//if(v.eg)
		}
	}
	
	public HashMap getAdress(){
		calkowityWynik = new HashMap<String, Result>();
		getAdress(rootNode.getRoot());
		return calkowityWynik;
	}
	
	private void getAdress(TreeNode tree){
		for(Enumeration<TreeNode> e = tree.children(); e.hasMoreElements();){
			TreeNode tmp = e.nextElement();
			//isIn(tmp);
			
			if(tmp.toString().equals("formatted_address") && tmp.getChildCount()!=0 && tmp.getParent()!=null){
				Result tmp2 = new Result();
				tmp2.formatedAdress = tmp.getChildAt(0).toString();
				for(Enumeration<TreeNode> f = tmp.getParent().children(); f.hasMoreElements();){
					TreeNode ftemp = f.nextElement();
					if(ftemp.toString().equals("type")){
						calkowityWynik.put(ftemp.getChildAt(0).toString(),tmp2);
						break;
					}
				}
			}
			 getAdress(tmp);
		}
	}
		
}
