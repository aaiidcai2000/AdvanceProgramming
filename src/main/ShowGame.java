package main;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The class provide all the functions of showing game detail/progress
 * It determine the showing speed of progress and showing format.
 * It can also determine the speed of the athletes when showing to audiences to control the tension in the game.
 * It also equip with the capability of dealing with accuracy calculation and scaling the competition result of each 
 * athletes to make showing smoothly.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class ShowGame {
	
	/** change ratio of each shuffling of athletes' speed */
	private static final double PROGRESS_ENTROPY=0.9;
	
	/**determine athletes' speed variance */
	private static final int SHUFFLING_PROGRESS_TIME=200;
	
	/** total speed segments for whole game */
	private static final int FRAME_SEGMENTS=40;
	
	
	/** for specifying UI layout */
	private static final Double[] ALLSPACING ={49.0, 36.0, 28.0, 22.0, 17.0};
	private static final Double[] ALLTOPMARGIN={55.0, 50.0, 47.0, 45.0, 45.0};
	private static final Double[] ALLBARGAP={13.0, 12.5, 9.0, 5.0, 3.0};
	
	/** athletes' competition result */
	private ArrayList<Double> results;
	private ArrayList<String> athletesNames;
	
	/** record athletes speed segments */
	private ArrayList<ArrayList<Double>> athletesProgress;
	private ArrayList<Double> athletePositions;
	private double scaleResultNumber;
	
	/** Count how many athletes finish */
	private int finishedCount;
	
	/** indicator of the progress of the game showing */
	private int progressIter;
	
	private String gameType;
	private String gameID;
	
	/** labels of GUI to show athletes' result */
	private ArrayList<Label> labels;
	
	/** labels of GUI to show athletes' arrival order */
	private ArrayList<Label> orderLabels;
	
	/** The BorderPane to show game  */
	private BorderPane showPane;
	
	
	/**
	 * Initialize ShowGame with athlete, get showing game ready and initialize each parameters 
	 * @param athletes
	 * @param _gameType
	 * @param _gameID
	 */
	public ShowGame(ArrayList<Athlete> athletes,String _gameType,String _gameID){
		gameType=_gameType;
		gameID=_gameID;
		results = new ArrayList<Double>();
		athletesProgress= new ArrayList<ArrayList<Double>>();
		athletePositions = new ArrayList<Double>();
		athletesNames = new ArrayList<String>();
		for(int i=0;i<athletes.size();++i){
			athletesProgress.add(new ArrayList<Double>());
			athletePositions.add(0.0);
			athletesNames.add( fixedLength(athletes.get(i).getName(), 10) );
		}
		
		scaleResultNumber=1;
		finishedCount=0;
		progressIter=0;
		
		labels= new ArrayList<Label>();
		orderLabels=new ArrayList<Label>();
		for(int i=0;i<athletes.size();++i ){
			labels.add(new Label());
			labels.get(labels.size()-1).setOpacity(0);
			orderLabels.add(new Label());
			orderLabels.get(orderLabels.size()-1).setOpacity(0);
		}
	}

	
	/**
	 * let athletes' name show on left hand site and fix output format
	 * @param string
	 * @param length
	 * @return String
	 */
	private static String fixedLength(String string, int length) {
	    return String.format("%1$-"+length+ "s", string);
	}
	
	/**
	 * to fix inaccuracy of double subtraction
	 * @param v1
	 * @param v2
	 * @return double
	 */
	private static double doubleSub(double v1,double v2) {
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * to fix inaccuracy of double addition
	 * @param v1
	 * @param v2
	 * @return double
	 */
	private static double doubleAdd(double v1,double v2) {
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.add(b2).doubleValue();
	}
	
	/**
	 * read game compete results
	 * @param result
	 */
	public void addResult(Double result) {
		results.add(result);
	}
	
	/**
	 * start showing game procedure
	 */
	public void start(){
		
		determineResultScale();
		
		generateathletesProgress();
		
		GUIstartShow();
		
	}
	
	/**
	 * calculate each athletes speed segments 
	 */
	private void generateathletesProgress(){
		
		for(int i=0;i<athletesNames.size();++i){
			double progressUnit = (1/results.get(i))*scaleResultNumber;
		
			for(int j=0;j<1/progressUnit;++j){
				athletesProgress.get(i).add(progressUnit);
			}
			
			suffleProgress(i);
			
		}
		
		
	}
	
	/**
	 * vary athletes' speed segments
	 * @param Athlete
	 */
	private void suffleProgress(int Athlete){
		
		for(int i=0;i<SHUFFLING_PROGRESS_TIME;++i){
			int indexAmount = athletesProgress.get(Athlete).size()-1;
			int addedIndex =(int) Math.floor( Math.random() * indexAmount );
			int deductedIndex=(int) Math.floor( Math.random() * indexAmount  );
			while(addedIndex==deductedIndex){
				addedIndex =(int) Math.floor( Math.random() * indexAmount );
				deductedIndex=(int) Math.floor( Math.random() * indexAmount  );
			}
			
			double deductedOriAmount = athletesProgress.get(Athlete).get(deductedIndex);
			double addedOriAmount = athletesProgress.get(Athlete).get(addedIndex);
			
			if(deductedOriAmount>addedOriAmount){
				//swap
				int temp1 = addedIndex;
				addedIndex = deductedIndex;
				deductedIndex = temp1;
				
				double temp2 = deductedOriAmount;
				deductedOriAmount = addedOriAmount ;
				addedOriAmount  = temp2;
					
			}
			
			
			double changeAmount = deductedOriAmount*Math.random()*PROGRESS_ENTROPY;
			
			double deductedNewAmount=doubleSub(deductedOriAmount,changeAmount);
			double addedNewAmount=doubleAdd(addedOriAmount,changeAmount);
					
			athletesProgress.get(Athlete).set(deductedIndex,deductedNewAmount);
			athletesProgress.get(Athlete).set(addedIndex,addedNewAmount);
		}
	}
	
	/**
	 * proceed progress
	 */
	private void nextProgress(){
		for(int i=0;i<athletePositions.size();++i){
			if(athletesProgress.get(i).size()>progressIter){
				double ori = athletePositions.get(i);
				double nextPosition = doubleAdd(ori,athletesProgress.get(i).get(progressIter));
				if(nextPosition>=1.0){
					nextPosition=1.0;
					++finishedCount;
					//showLabel
					showFinishedAthlete(i);
					showOrderLabels(i);
					
				}
				athletePositions.set(i, nextPosition);
			}
		}
		++progressIter;
	}
	
	/**
	 * Show athletes order when athletes finish the game
	 * @param AthleteIndex
	 */
	private void showOrderLabels( int AthleteIndex){
		
		if(finishedCount>3) return;
		
		Label theLabel=orderLabels.get(orderLabels.size()-1-AthleteIndex);
		
		switch(finishedCount){
			case 1:
				setLabelText(theLabel,"The First!!");
				break;
			case 2:
				setLabelText(theLabel,"The Second!!");
				break;
			case 3:
				setLabelText(theLabel,"The Third!!");
				break;
		}
		
		FadeTransition ft 
		        = new FadeTransition(Duration.millis(500), theLabel);
		ft.setFromValue(0);
		ft.setToValue(1.0);
		ft.setAutoReverse(false);
		SequentialTransition seqTransition = new SequentialTransition (
	         new PauseTransition(Duration.millis(500)), // wait a second
	         ft
	     );
	    seqTransition.play();
		
	}
	
	/**
	 * set the label's text
	 * @param l
	 * 		  the label that need to be set
	 * @param s
	 *        the string that will show on the label
	 * @return Label
	 */
	private Label setLabelText(Label l,String s){
		l.setText(s);
		return l;
	}
	
	/**
	 * When athlete finish the game, show their results on GUI
	 * @param AthleteIndex
	 */
	private void showFinishedAthlete( int AthleteIndex){
		
		labels.get(finishedCount-1).setText(finishedCount+". "+ athletesNames.get(AthleteIndex)
		+keepPlaceAfterDecimalPoint(results.get(AthleteIndex),2)+" s" );
		
		FadeTransition ft 
		        = new FadeTransition(Duration.millis(500), labels.get(finishedCount-1));
		ft.setFromValue(0);
		ft.setToValue(1.0);
		ft.setAutoReverse(false);
		SequentialTransition seqTransition = new SequentialTransition (
	         new PauseTransition(Duration.millis(500)), // wait a second
	         ft
	     );
	    seqTransition.play();
	    
	    
		
	}

	/**
	 * scale each compete result in certain range
	 */
	private void determineResultScale(){
		double progressUnit = 1.0/results.get(0);
		while(progressUnit*scaleResultNumber > 1.0/FRAME_SEGMENTS){
			scaleResultNumber/=2;
		}
		while(progressUnit*scaleResultNumber *2 < 1.0/FRAME_SEGMENTS){
			scaleResultNumber*=2;
		}
	}	
	
	/**
	 * Calculate the animation cycles
	 * @return Integer
	 *         the cycles all needed
	 */
	private int allCycles(){
		int cycles=0;
		for(int i=0;i<athletesProgress.size();++i){
			if(cycles < athletesProgress.get(i).size()){
				cycles=athletesProgress.get(i).size();
			}
		}
		return cycles;
	}
	
	/**
	 * Start showing the game details on GUI
	 * using bar chart,labels and animation
	 * and manage the layout
	 */
	private void GUIstartShow(){
		
		double spacing = ALLSPACING[athletesNames.size()-4];
		double topMargin =ALLTOPMARGIN[athletesNames.size()-4];
		double barGap = ALLBARGAP[athletesNames.size()-4];
		
		
		
		//labels title
		VBox titleBox = new VBox(); 
		titleBox.setPrefHeight(topMargin);
		
		Text labelsTitle = new Text("Results");
		labelsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		labelsTitle.setId("result");
		titleBox.getChildren().add(labelsTitle);
		titleBox.setAlignment(Pos.CENTER_LEFT);
		
		//labels
		VBox vBox = new VBox(spacing); 
		for(int i=0;i<labels.size();++i){
			vBox.getChildren().add(labels.get(i));
		}
		vBox.setAlignment(Pos.TOP_LEFT);
		
		//backBtn
		VBox backBox = new VBox(); 
		Button backBtn = new Button("Back To Menu");
		backBox.getChildren().add(backBtn);
		backBox.setPadding(new Insets(50,40,0,0));
		backBox.setAlignment(Pos.BOTTOM_RIGHT);
		backBtn.setOnAction(e->{
			Stage stage = (Stage) showPane.getScene().getWindow();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/main/views/Main.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		//rightBox
		VBox rightBox = new VBox(); 
		rightBox.getChildren().add(titleBox);
		rightBox.getChildren().add(vBox);
		rightBox.getChildren().add(backBox);
		
		//oderLabels
		VBox orderVBox = new VBox(spacing); 
		for(int i=0;i<orderLabels.size();++i){
			orderVBox.getChildren().add(orderLabels.get(i));
		}
		orderVBox.setPadding(new Insets(topMargin,0,0,0));
		orderVBox.setAlignment(Pos.TOP_LEFT);
		
		//bar chart
        final NumberAxis xAxis = new NumberAxis(0,1,0.1);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setAnimated(false);
        final BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);
        bc.setBarGap(barGap);
        bc.setTitle("Game "+gameID+" - "+gameType);
        xAxis.setLabel("Proportion Finished");  
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Athletes");        
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("finished");       
        for(int i=0;i< athletesNames.size();++i ){
        	series1.getData().add(new XYChart.Data(athletePositions.get(i) , athletesNames.get(i)));
        }
        
        bc.getData().addAll(series1);
        
        
        //Pane
        StackPane left = new StackPane(bc);
        left.setPrefWidth(450);
        showPane.setLeft(left);
        
        StackPane center = new StackPane(orderVBox);
        center.setPrefWidth(120);
        showPane.setCenter(center);
        
        StackPane right = new StackPane(rightBox);
        right.setPrefWidth(180);
        showPane.setRight(right);
        
        
        
        //animation
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(
        new KeyFrame(Duration.millis(200), 
            new EventHandler<ActionEvent>() {
                @Override 
                public void handle(ActionEvent actionEvent) {
                	nextProgress();
                    for (XYChart.Series<Number, String> series : bc.getData()) {
                    	int athletesIter =0;
                        for (XYChart.Data<Number, String> data : series.getData()) {
                            data.setXValue(athletePositions.get(athletesIter));
                            ++athletesIter;
                        }
                    }
                   
                }
             }
        ));
        tl.setCycleCount(allCycles());
        tl.play();
        
      
	}
	
	/**
	 * set the pane that the game will show at
	 * @param pane
	 */
	public void setPane(BorderPane pane){
		this.showPane = pane;
	}
	
	/**
	 * round number to which digit after decimal point
	 * @param num
	 * @param place
	 * @return double
	 *         the value after rounded
	 */
	private static double keepPlaceAfterDecimalPoint(double num,int place){
		double scaler = Math.pow(10,place);
		return Math.round(num*scaler)/scaler;
	}
	
}
