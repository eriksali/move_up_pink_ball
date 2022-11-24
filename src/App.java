//package hellofx;

import java.nio.file.Path;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class App extends Application {
    int sum1;
    int sum2 = -1;
    Random rand;
    int number1;
    int number2;
    double newY;

    TranslateTransition transition;

    int counter = 1;

    @Override
    public void start(Stage primaryStage) {

        Text questiontxt = new Text("Question: ");
        Text answertxt = new Text("Answer: ");

        final Label question = new Label();
        final Label answer = new Label();

        rand = new Random();
        number1 = rand.nextInt(10);
        number2 = rand.nextInt(10);

        question.setText(number1 + " x " + number2);
        TextField result = new TextField();
        Button submit = new Button("Submit");
        Button continuebtn = new Button("Continue");
        Button btnExit = new Button("Exit");

        Circle cir = new Circle();
        cir.setFill(Color.DARKSALMON);
        cir.setRadius(30);
        cir.setLayoutX(500);
        cir.setLayoutY(550);

        Pane root = new Pane();

        root.getChildren().addAll(cir, questiontxt, answertxt, question, answer, result, submit, continuebtn, btnExit);
        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Space game!");
        primaryStage.setScene(scene);
        primaryStage.show();

        questiontxt.setLayoutX(50);
        questiontxt.setLayoutY(200);
        question.setLayoutX(110);
        question.setLayoutY(200);

        answertxt.setLayoutX(50);
        answertxt.setLayoutY(230);
        answer.setLayoutX(110);
        answer.setLayoutY(230);

        result.setLayoutX(50);
        result.setLayoutY(255);

        submit.setLayoutX(260);
        submit.setLayoutY(270);
        continuebtn.setLayoutX(260);
        continuebtn.setLayoutY(300);
        btnExit.setLayoutX(260);
        btnExit.setLayoutY(325);

        submit.setDefaultButton(true);
        submit.setOnAction(e -> {
            try {
                sum1 = number1 * number2;
                sum2 = Integer.parseInt(result.getText());
            } catch (NumberFormatException ex) {
                ;
            }

            if (counter < 10) { 

                if (sum2 == -1) {
                    Alert alert = new Alert(AlertType.ERROR, "Please enter a number");
                    alert.showAndWait();
                }
                else if (sum1 == sum2) {
                
                    transition = new TranslateTransition();
                    transition.setAutoReverse(false);
                    transition.setNode(cir);
                    
                    cir.setLayoutY(550-50*counter);
                    transition.play();

                    counter++;
                    
                    answer.setText(number1 + " x " + number2 + " = " + result.getText() + " correct");
                }
                else {
                    answer.setText(number1 + " x " + number2 + " = " + result.getText() + " incorrect");
                } 
                
            } else {
                    transition = new TranslateTransition();
                    transition.setAutoReverse(false);
                    transition.setNode(cir);
                    transition.play();
                    cir.setLayoutY(50);
            }
        });

        continuebtn.setOnAction(e -> {
            number1 = rand.nextInt(10);
            number2 = rand.nextInt(10);

            result.clear();
            question.setText(number1 + " x " + number2);
            answer.setText("");

            sum1 = 0;
            sum2 = 0;

        });

        btnExit.setOnAction(e -> {
            primaryStage.close();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
