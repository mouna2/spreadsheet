package com.konv.spreadsheet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
import java.util.List;
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
	
	
	
    private SpreadsheetView mSpreadsheet;
    private SpreadsheetController mSpreadsheetController;
    private GridBase mGridBase;
    private TextField mTextField;
    private int mRowCount = 99;
    private int mColumnCount = 26;

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
       
        toolBar.getItems().addAll(toggleButton);
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
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linksource", Artifact.STRING_TYPE.getArtifactId(), false)));
		LinkType.addPropertyType(new PropertyTypeArtifactImpl(new FeatureDescription("linktarget", Artifact.STRING_TYPE.getArtifactId(), false)));
		InstanceArtifact link1 = defaultInstancePackage.createInstance(LinkType);
		link1.setPropertyValue("linkname", "l1");
		
		InstanceArtifact link2 = defaultInstancePackage.createInstance(LinkType);
		link2.setPropertyValue("linkname", "l2");
		
		InstanceArtifact link3 = defaultInstancePackage.createInstance(LinkType);
		link3.setPropertyValue("linkname", "l3");
		
		InstanceArtifact link4 = defaultInstancePackage.createInstance(LinkType);
		link4.setPropertyValue("linkname", "l4");
		
		InstanceArtifact link5 = defaultInstancePackage.createInstance(LinkType);
		link5.setPropertyValue("linkname", "l5");
		
		InstanceArtifact link6 = defaultInstancePackage.createInstance(LinkType);
		link6.setPropertyValue("linkname", "l6");
		
		InstanceArtifact link7 = defaultInstancePackage.createInstance(LinkType);
		link7.setPropertyValue("linkname", "l7");
		
		link1.setPropertyValue("linksource", Requirement1);
		link1.setPropertyValue("linktarget", State2);
		
		link2.setPropertyValue("linksource", Requirement2);
		link2.setPropertyValue("linktarget", State2);
		
		link3.setPropertyValue("linksource", Requirement3);
		link3.setPropertyValue("linktarget", State3);
		
		link4.setPropertyValue("linksource", Requirement1);
		link4.setPropertyValue("linktarget", State3);
		
		link5.setPropertyValue("linksource", Requirement2);
		link5.setPropertyValue("linktarget", State1);
		
		link6.setPropertyValue("linksource", Requirement5);
		link6.setPropertyValue("linktarget", State3);
		
		
		link7.setPropertyValue("linksource", Requirement7);
		link7.setPropertyValue("linktarget", State5);
		
		List<InstanceArtifact> LinkList = new ArrayList<InstanceArtifact>(); 
		
		System.out.println("THIS IS LINK 1hhh=========>"+link1);
		
		System.out.println("THIS IS LINK 2=========>"+link2);
		
		System.out.println("THIS IS LINK 3=========>"+link3);
		
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
		List<String> RequirementLabels = new ArrayList<String>(); 
		List<String> StateLabels = new ArrayList<String>(); 
		 
		
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
	
		   boolean matrix[][] = new boolean[StateList.size()+1][RequirementList.size()+1];
		     
			for( InstanceArtifact li: ListOfLinks) {
				int y=0; 
				for(InstanceArtifact re: RequirementList) {
					int x=0; 
					for(InstanceArtifact state: StateList) {
					if(li.toString().equals(ListOfLinks.get(6).toString())) {
						System.out.println("YEEEES"+x+"     "+y);
					} 
					 if(li.getPropertyValue("linksource").equals(re) && li.getPropertyValue("linktarget").equals(state)) {   
						 System.out.println("YEEEES AGAIN"+x+"     "+y);
				        	matrix[x][y]= true; 
				        	
				        }
					 else {
						
					 }
					 x++; 
					 
				}    
				y++; 
			}
			
			
			
	 
			}
			
    	
    	
			for(int p=0; p<StateList.size()+1; p++) {
			  	  for(int m=0; m<RequirementList.size()+1; m++) {
			  	  System.out.println("State: "+p+ "Requirement: "+ m+"   "+ matrix[p][m]);
			  	  }
			    }
			
			System.out.println("row count: "+mRowCount+ "column count: "+ mColumnCount);
        mGridBase = new GridBase(mRowCount, mColumnCount);
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        mSpreadsheetController = new SpreadsheetController(mGridBase);
        int indexColumn=0; 
        int counter=0; 
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
            		if(matrix[new_row][new_column]==true) {
            			
            			
                		list.add(SpreadsheetCellType.STRING.createCell(row, new_column, 1, 1, "*"));
                		
                	}
            		else {
                		list.add(SpreadsheetCellType.STRING.createCell(row, new_column, 1, 1, ""));
                	}
            	}
            	else {
            		list.add(SpreadsheetCellType.STRING.createCell(row-1, column-1, 1, 1, ""));
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
    
    
   
    	

		
		
    
}

