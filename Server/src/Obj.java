
public class Obj {
	 
	       private String url ;
	       private String type;
	       
	   // constructor
	   public Obj(String url, String type) {
	      this.url = url;
	      this.type = type;
	   }

	       // getter
	       public String getURL() { 
	    	   return url; 
	    	   }
	       public String getType() { 
	    	   return type; 
	    	   }
	       // setter

	       public void setURL(String url) {
	    	   this.url = url; 
	       }
	       public void setType(String type) { 
	    	   this.type = type; 
	       }
	    
}
