
public class Obj {
	 
	       private String url ;
	       private String type;
	       private String key;
	   // constructor
	   public Obj(String url, String type,String key) {
	      this.url = url;
	      this.type = type;
	      this.key = key;
	   }

	       // getter
	       public String getURL() { 
	    	   return url; 
	    	   }
	       public String getType() { 
	    	   return type; 
	    	   }
	       public String getKey(){
	    	   return key;
	       }
	       // setter

	       public void setURL(String url) {
	    	   this.url = url; 
	       }
	       public void setType(String type) { 
	    	   this.type = type; 
	       }
	       public void setKey(String key){
	    	   this.key = key;
	       }
	    
}
