package com.konv.spreadsheet;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.controlsfx.control.spreadsheet.*;

import com.google.common.util.concurrent.Service.State;

import at.jku.isse.designspace.client.java.primitive.PrimitiveAPI;
import at.jku.isse.designspace.core.FeatureDescription;
import at.jku.isse.designspace.richapi.model.Artifact;
import at.jku.isse.designspace.richapi.model.InstanceArtifact;
import at.jku.isse.designspace.richapi.model.PackageArtifact;
import at.jku.isse.designspace.richapi.model.ProjectArtifact;
import at.jku.isse.designspace.richapi.model.TypeArtifact;
import at.jku.isse.designspace.richapi.model.Workspace;
import at.jku.isse.designspace.richapi.model.impl.ProjectArtifactImpl;
import at.jku.isse.designspace.richapi.model.impl.PropertyTypeArtifactImpl;

public class Main extends Application {
	private static Workspace clientConnectionHandler = Workspace.init(InetAddress.getLoopbackAddress().getHostAddress(), null);
	private static PrimitiveAPI clientConnection = clientConnectionHandler.getClientConnection();
	
	
	//MOUNA
    private SpreadsheetView mSpreadsheet;
    private SpreadsheetController mSpreadsheetController;
    private GridBase mGridBase;
    private TextField mTextField;
    private int mRowCount = 99;
    private int mColumnCount = 26;
    String artifact1=""; 
    String artifact2=""; 
    @Override
    public void start(Stage primaryStage) throws Exception {
        initUi(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	

        launch(args);
    }

  
    
    
    
    private void initUi(Stage stage) {
        VBox root = new VBox();
        mTextField = new TextField();
        mTextField.setOnAction(e -> {
            int focusedRow = mSpreadsheet.getSelectionModel().getFocusedCell().getRow();
            int focusedColumn = mSpreadsheet.getSelectionModel().getFocusedCell().getColumn();
            mSpreadsheet.getGrid().setCellValue(focusedRow, focusedColumn, mTextField.getText());
        });

        initSpreadsheet();
        VBox.setVgrow(mSpreadsheet, Priority.ALWAYS);

        ToolBar toolBar = new ToolBar();
        ToggleButton toggleButton = new ToggleButton("Show Formulas");
        toggleButton.setSelected(false);
        toggleButton.selectedProperty().addListener((e, oldValue, newValue) -> {

            mSpreadsheetController.setShowFormulas(newValue);
            mSpreadsheetController.display();
        });
       
        
        final ChoiceBox<String> box = new ChoiceBox<String>();

        box.getItems().add("State");
        box.getItems().add("StateMachine");
        box.getItems().add("Requirement");
        box.getItems().add("Code");

        box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
            System.out.println(box.getItems().get((Integer) number2));
          }
        });
       
        
        final ChoiceBox<String> box2 = new ChoiceBox<String>();

        box2.getItems().add("State");
        box2.getItems().add("StateMachine");
        box2.getItems().add("Requirement");
        box2.getItems().add("Code");

        box2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
            System.out.println(box2.getItems().get((Integer) number2));
          }
        });

        toolBar.getItems().addAll(toggleButton);
        toolBar.getItems().addAll(box);
        toolBar.getItems().addAll(box2);

        root.getChildren().addAll(mTextField, mSpreadsheet, toolBar);
        stage.setScene(new Scene(root, 840, 600));
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg")));
        stage.setTitle("Spreadsheet");
    }


 
	

	private void initSpreadsheet() {
    	
    	ProjectArtifact sampleProject = new ProjectArtifactImpl("SampleProject");
		PackageArtifact defaultTypePackage = sampleProject.createPackage();
		PackageArtifact defaultInstancePackage = sampleProject.createPackage();
	//	defaultTypePackage.getArtifactCollection();
		TypeArtifact RequirementType = defaultTypePackage.createType(Set.class.getName());
		TypeArtifact LinkType = defaultTypePackage.createType(Set.class.getName());
		TypeArtifact StateType = defaultTypePackage.createType(Set.class.getName());
		
		TypeArtifact CodeType = defaultTypePackage.createType(Set.class.getName());
		TypeArtifact StateMachineType = defaultTypePackage.createType(Set.class.getName());
		
		StateMachineType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("statemachinename", Artifact.STRING_TYPE.getArtifactId(), false)));
		
		InstanceArtifact statemachine1 = defaultInstancePackage.createInstance(StateMachineType);
		statemachine1.setPropertyValue("statemachinename", "statemachine1");
		
		InstanceArtifact statemachine2 = defaultInstancePackage.createInstance(StateMachineType);
		statemachine2.setPropertyValue("statemachinename", "statemachine2");
		
		InstanceArtifact statemachine3 = defaultInstancePackage.createInstance(StateMachineType);
		statemachine3.setPropertyValue("statemachinename", "statemachine3");
		
		InstanceArtifact statemachine4 = defaultInstancePackage.createInstance(StateMachineType);
		statemachine4.setPropertyValue("statemachinename", "statemachine4");
		
		CodeType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("codename", Artifact.STRING_TYPE.getArtifactId(), false)));

		InstanceArtifact code1 = defaultInstancePackage.createInstance(CodeType);
		code1.setPropertyValue("codename", "code1");
		
		InstanceArtifact code2 = defaultInstancePackage.createInstance(CodeType);
		code2.setPropertyValue("codename", "code2");
		
		InstanceArtifact code3 = defaultInstancePackage.createInstance(CodeType);
		code3.setPropertyValue("codename", "code3");
		
		InstanceArtifact code4 = defaultInstancePackage.createInstance(CodeType);
		code4.setPropertyValue("codename", "code4");
		
		RequirementType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("requirementname", Artifact.STRING_TYPE.getArtifactId(), false)));
		
		InstanceArtifact Requirement1 = defaultInstancePackage.createInstance(RequirementType);
		Requirement1.setPropertyValue("requirementname", "r1");
		
		InstanceArtifact Requirement2 = defaultInstancePackage.createInstance(RequirementType);
		Requirement2.setPropertyValue("requirementname", "r2");
		
		InstanceArtifact Requirement3 = defaultInstancePackage.createInstance(RequirementType);
		Requirement3.setPropertyValue("requirementname", "r3");
		
		InstanceArtifact Requirement4 = defaultInstancePackage.createInstance(RequirementType);
		Requirement4.setPropertyValue("requirementname", "r4");
		
		InstanceArtifact Requirement5 = defaultInstancePackage.createInstance(RequirementType);
		Requirement5.setPropertyValue("requirementname", "r5");
		
		InstanceArtifact Requirement6 = defaultInstancePackage.createInstance(RequirementType);
		Requirement6.setPropertyValue("requirementname", "r6");
		
		InstanceArtifact Requirement7 = defaultInstancePackage.createInstance(RequirementType);
		Requirement7.setPropertyValue("requirementname", "r7");
		
		StateType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("statename", Artifact.STRING_TYPE.getArtifactId(), false)));
		InstanceArtifact State1 = defaultInstancePackage.createInstance(StateType);
		State1.setPropertyValue("statename", "s1");
		
		InstanceArtifact State2 = defaultInstancePackage.createInstance(StateType);
		State2.setPropertyValue("statename", "s2");
		
		InstanceArtifact State3 = defaultInstancePackage.createInstance(StateType);
		State3.setPropertyValue("statename", "s3");
		
		InstanceArtifact State4 = defaultInstancePackage.createInstance(StateType);
		State4.setPropertyValue("statename", "s4");
		
		InstanceArtifact State5 = defaultInstancePackage.createInstance(StateType);
		State5.setPropertyValue("statename", "s5");
		
		
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linkname", Artifact.STRING_TYPE.getArtifactId(), false)));
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linktype", Artifact.STRING_TYPE.getArtifactId(), false)));
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linksource", Artifact.STRING_TYPE.getArtifactId(), false)));
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linktarget", Artifact.STRING_TYPE.getArtifactId(), false)));
		InstanceArtifact link1 = defaultInstancePackage.createInstance(LinkType);
		link1.setPropertyValue("linkname", "l1");
		
		
		
		InstanceArtifact link2 = defaultInstancePackage.createInstance(LinkType);
		link2.setPropertyValue("linkname", "l2");
		link2.setPropertyValue("linktype", "State-Requirement"); 
		
		
		InstanceArtifact link3 = defaultInstancePackage.createInstance(LinkType);
		link3.setPropertyValue("linkname", "l3");
		link3.setPropertyValue("linktype", "State-Requirement"); 

		
		InstanceArtifact link4 = defaultInstancePackage.createInstance(LinkType);
		link4.setPropertyValue("linkname", "l4");
		link4.setPropertyValue("linktype", "State-Requirement"); 

		
		InstanceArtifact link5 = defaultInstancePackage.createInstance(LinkType);
		link5.setPropertyValue("linkname", "l5");
		link5.setPropertyValue("linktype", "State-Requirement"); 

		InstanceArtifact link6 = defaultInstancePackage.createInstance(LinkType);
		link6.setPropertyValue("linkname", "l6");
		link6.setPropertyValue("linktype", "State-Requirement"); 

		InstanceArtifact link7 = defaultInstancePackage.createInstance(LinkType);
		link7.setPropertyValue("linkname", "l7");
		link7.setPropertyValue("linktype", "State-Requirement"); 

		link1.setPropertyValue("linksource", Requirement1);
		link1.setPropertyValue("linktarget", State2);
		link1.setPropertyValue("linktype", "State-Requirement"); 

		link2.setPropertyValue("linksource", Requirement2);
		link2.setPropertyValue("linktarget", State2);
		link2.setPropertyValue("linktype", "State-Requirement"); 

		link3.setPropertyValue("linksource", Requirement3);
		link3.setPropertyValue("linktarget", State3);
		link3.setPropertyValue("linktype", "State-Requirement"); 

		link4.setPropertyValue("linksource", Requirement1);
		link4.setPropertyValue("linktarget", State3);
		link4.setPropertyValue("linktype", "State-Requirement"); 

		link5.setPropertyValue("linksource", Requirement2);
		link5.setPropertyValue("linktarget", State1);
		link5.setPropertyValue("linktype", "State-Requirement"); 

		link6.setPropertyValue("linksource", Requirement5);
		link6.setPropertyValue("linktarget", State3);
		link6.setPropertyValue("linktype", "Code-StateMachine"); 

		
		link7.setPropertyValue("linksource", Requirement7);
		link7.setPropertyValue("linktarget", State5);
		link7.setPropertyValue("linktype", "Code-StateMachine"); 
		
		
		

		List<InstanceArtifact> LinkList = new ArrayList<InstanceArtifact>(); 
		
	
		List<InstanceArtifact> RequirementList= new ArrayList<InstanceArtifact>(); 
		RequirementList.add(Requirement1); 
		RequirementList.add(Requirement2); 
		RequirementList.add(Requirement3); 
		RequirementList.add(Requirement4); 
		RequirementList.add(Requirement5); 
		RequirementList.add(Requirement6); 
		RequirementList.add(Requirement7); 
		
		
		List<InstanceArtifact> StateList= new ArrayList<InstanceArtifact>(); 
		StateList.add(State1); 
		StateList.add(State2); 
		StateList.add(State3); 
		StateList.add(State4); 
		StateList.add(State5); 
		
		
		List<InstanceArtifact> ListOfLinks= new ArrayList<InstanceArtifact>(); 
		ListOfLinks.add(link1); 
		ListOfLinks.add(link2); 
		ListOfLinks.add(link3); 
		ListOfLinks.add(link4); 
		ListOfLinks.add(link5); 
		ListOfLinks.add(link6); 
		ListOfLinks.add(link7); 
		
		List<InstanceArtifact> ListofCode= new ArrayList<InstanceArtifact>(); 
		ListofCode.add(code1); 
		ListofCode.add(code2); 
		ListofCode.add(code3); 
		ListofCode.add(code4); 
		
		List<InstanceArtifact> ListofStateMachines= new ArrayList<InstanceArtifact>(); 
		ListofStateMachines.add(statemachine1); 
		ListofStateMachines.add(statemachine2); 
		ListofStateMachines.add(statemachine3); 
		ListofStateMachines.add(statemachine4); 
		
		
		List<String> RequirementLabels = new ArrayList<String>(); 
		List<String> StateLabels = new ArrayList<String>(); 
		List<String> CodeLabels = new ArrayList<String>(); 
		List<String> StateMachineLabels = new ArrayList<String>(); 

		
		
		
		
		
		int i=1; 
		
	
		for (InstanceArtifact ia: RequirementList) {
			String name= "Requirement"+new Integer(i).toString(); 
			RequirementLabels.add("Requirement"+i); 
			i++; 
		}
		
		
		i=1; 
		for (InstanceArtifact ia: StateList) {
			StateLabels.add("State"+i); 
			
			i++; 
		}
	
		i=1; 
		for (InstanceArtifact ia: ListofCode) {
			CodeLabels.add("Code"+i); 
			
			i++; 
		}
		
		i=1; 
		for (InstanceArtifact ia: ListofStateMachines) {
			StateMachineLabels.add("StateMachine"+i); 
			
			i++; 
		}
		
		
	
	System.out.println("ARTIFACT 1: "+ artifact1+ "artifact 2: "+artifact2);
		
		
			
			 String matrix[][] = new String[StateList.size()+1][RequirementList.size()+1];
			   for (int k = 0; k < StateList.size(); k++) { 
				   for (int j = 0; j < RequirementList.size(); j++) 
				   { matrix[k][j]=" "; 
				   }
			   }

				for( InstanceArtifact li: ListOfLinks) {
					int y=0; 
					for(InstanceArtifact re: RequirementList) {
						int x=0; 
						for(InstanceArtifact state: StateList) {
					
						 if(li.getPropertyValue("linksource").equals(re) && li.getPropertyValue("linktarget").equals(state)) {   
							 System.out.println("YEEEES AGAIN"+x+"     "+y);
					        	matrix[x][y]= "*"; 
					        	
					        }
						 else {
							
						 }
						 x++; 
						 
					}    
					y++; 
				}
				
				
				
		 
				}
				
	    	
	    	
				for(int p=0; p<StateList.size(); p++) {
				  	  for(int m=0; m<RequirementList.size(); m++) {
				  	  System.out.println("State: "+p+ "Requirement: "+ m+"   "+ matrix[p][m]);
				  	  }
				    }
				
				System.out.println("row count: "+mRowCount+ "column count: "+ mColumnCount);
	        mGridBase = new GridBase(mRowCount, mColumnCount);
	        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
	        mSpreadsheetController = new SpreadsheetController(mGridBase);
	    
	        int indexColumn=0; 
	      
	        int indexRow=0; 
	       
	        for (int row = 0; row < mGridBase.getRowCount(); ++row) {
	            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();    
	            for (int column = 0; column < mGridBase.getColumnCount(); ++column) {
	            	if(column<=RequirementLabels.size() && row==0 && column>=1) {
	            		 list.add(SpreadsheetCellType.STRING.createCell(0, column, 1, 1, RequirementLabels.get(indexColumn)));
	            		 indexColumn++; 
	            	}
	            	else if(column==0 && row<=StateLabels.size() && row>=1) {
	            		 list.add(SpreadsheetCellType.STRING.createCell(row, 0, 1, 1, StateLabels.get(indexRow)));
	            		 indexRow++; 
	            	}
	            	else  if(row<=StateList.size() && column<=RequirementList.size()  && row>=1 && column>=1) {
	            		System.out.println("ROW:  "+row+ "COLUMN: "+column);
	            		int new_row= row-1;  
	        			int new_column= column-1; 
	        			System.out.println("STATE=======> "+ new_row+"  REQ=======> "+new_column);
	        			System.out.println("MANTRIX ENTRY"+matrix[new_row][new_column]);
	            		if((matrix[new_row][new_column]).equals("*")) {
	            			
	            			
	                		list.add(SpreadsheetCellType.STRING.createCell(row, new_column, 1, 1, "*"));
	                		
	                	}
	            		else {
	                		list.add(SpreadsheetCellType.STRING.createCell(row, new_column, 1, 1, ""));
	                	}
	            		
	            	}
	            	
	            		
	  
	            		
	            	else {
	            		
	            			list.add(SpreadsheetCellType.STRING.createCell(row, column-1, 1, 1, " "));
	            			
	            		
	            	}	
	            	
	                
	            }
	            
	            
	          
	            
	            
	            
	            rows.add(list);
	        }
	        

	       
	            
	            
			
	        System.out.println("I AM HERE");
	        
	        mGridBase.setRows(rows);
	        System.out.println("I AM HERE 2");
	        mGridBase.addEventHandler(GridChange.GRID_CHANGE_EVENT, mSpreadsheetController);
	        System.out.println("I AM HERE 3");
	        mSpreadsheet = new SpreadsheetView(mGridBase);
	        System.out.println("I AM HERE 4");
	        mSpreadsheet.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        mSpreadsheet.addEventFilter(KeyEvent.KEY_RELEASED, e -> updateTextField());
	        mSpreadsheet.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> updateTextField());
	        for (SpreadsheetColumn c : mSpreadsheet.getColumns()) c.setPrefWidth(90);
			
			
		}
		  
    

	private void updateTextField() {
        int focusedRow = mSpreadsheet.getSelectionModel().getFocusedCell().getRow();
        int focusedColumn = mSpreadsheet.getSelectionModel().getFocusedCell().getColumn();
        mTextField.setText(mSpreadsheetController.getCell(focusedRow, focusedColumn).getFormula());
    }
    
    
   
    public List<Pair> getByIndex(LinkedHashMap<Pair, String> map , int index){
    	   return (List<Pair>) map.values().toArray()[index];
    	}

		
		
    
}

