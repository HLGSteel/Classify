
public class FileNode {
	private String name;
	private String parent;
	private String root;
	
	public FileNode(String root, String parent, String name){
		this.root = root;
		this.parent = parent;
		this.name = name;
	}
	public String getFilePath(){
		return root+"\\"+parent+"\\"+name;
	}
	
	public String getRoot(){
		return root;
	}
	
	public String getParent(){
		return parent;
	}
	
	public String getFileDir(){
		return root+"\\"+parent;
	}
	public String toString(){
		return name;
	}
}
