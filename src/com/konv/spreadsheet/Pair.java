package com.konv.spreadsheet;

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
}