package com.konv.spreadsheet;

import java.util.Map;
import java.util.Objects;

import at.jku.isse.designspace.richapi.model.InstanceArtifact;

public class Pair {

    public InstanceArtifact x;
    public InstanceArtifact y;

    public Pair( InstanceArtifact object,  InstanceArtifact object2) {
        this.x = object;
        this.y = object2;
    }

   

   public InstanceArtifact GetFirst(Pair p) {
	   return p.x; 
   }
   
   public InstanceArtifact GetSecond(Pair p) {
	   return p.y; 
   }
   
   public boolean contains(Map map, Pair p) {
	   if (map.keySet().contains(p)) {
		   return true; 
	   }
   return false; 
   }



@Override
public boolean equals(Object obj) {
	if(!(obj instanceof Pair))
		return false;
	
	Pair p = (Pair) obj;
	return p.hashCode() == hashCode();
}



@Override
public int hashCode() {
	return Objects.hash(x, y);
}
   
   
}