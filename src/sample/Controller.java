package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.*;

public class Controller {

    private String currentBackGround;
    public HashMap<String, String> termsMap;
    public List<String> wordList;
    public List<String> definitionList;
    public Random random;
    public String randomWord;
    public String randomDefinition;
    public String input;
    public Iterator iterator;
    public Boolean takeInput = false;
    public Boolean control = false;
    public Boolean reset = false;
    public int score = 0;
    public int rounds = 10;
    public int i;

    @FXML
    GridPane mainGridPane;
    @FXML
    AnchorPane basePane;
    @FXML
    TextField roundsCounter;
    @FXML
    TextField inputField;
    @FXML
    TextArea outputDisplay;
    @FXML
    Label authorLabel;


    //Backgrounds
    @FXML
    Button tropical;
    @FXML
    Button mountain;
    @FXML
    Button city;
    @FXML
    Button arctic;

    //Quiz buttons
    @FXML
    Button quiz1;
    @FXML
    Button quiz2;
    @FXML
    Button quiz3;
    @FXML
    Button quiz4;
    @FXML
    Button quiz5;
    @FXML
    Button submit;
    @FXML
    public void initialize(){
        roundsCounter.setText(String.valueOf(rounds));
        submit.setDefaultButton(true);
        outputDisplay.setWrapText(true);
        authorLabel.setFont(Font.font(9));
        authorLabel.getStyleClass().add("authorLabel");
    }

    @FXML
    private void setTropical(ActionEvent event){
        basePane.getStyleClass().remove(getCurrentBackGround());
        setCurrentBackGround("tropical");
        basePane.getStyleClass().add("tropical");
    }
    @FXML
    private void setMountain(ActionEvent event){
        basePane.getStyleClass().remove(getCurrentBackGround());
        setCurrentBackGround("mountain");
        basePane.getStyleClass().add("mountain");
    }
    @FXML
    private void setCity(ActionEvent event){
        basePane.getStyleClass().remove(getCurrentBackGround());
        setCurrentBackGround("city");
        basePane.getStyleClass().add("city");
    }
    @FXML
    private void setArctic(ActionEvent event){
        basePane.getStyleClass().remove(getCurrentBackGround());
        setCurrentBackGround("arctic");
        basePane.getStyleClass().add("arctic");
    }
    @FXML
    private void setDefaultBG(){
        setCurrentBackGround("tropical");
        basePane.getStyleClass().add("tropical");
    }

    public String getCurrentBackGround() {
        return currentBackGround;
    }

    public void setCurrentBackGround(String currentBackGround) {
        this.currentBackGround = currentBackGround;
    }


    @FXML
    private void setQuiz1(ActionEvent event) throws IOException, InterruptedException {
        TermReader.setTermlist("quiz1");
        control = false;
        playQuiz();
    }
    @FXML
    private void setQuiz2(ActionEvent event) throws IOException, InterruptedException {
        TermReader.setTermlist("quiz2");
        control = false;
        playQuiz();
    }
    @FXML
    private void setQuiz3(ActionEvent event) throws IOException, InterruptedException {
        TermReader.setTermlist("quiz3");
        control = false;
        playQuiz();
    }
    @FXML
    private void setQuiz4(ActionEvent event) throws IOException, InterruptedException {
        TermReader.setTermlist("quiz4");
        control = false;
        playQuiz();
    }
    @FXML
    private void setQuiz5(ActionEvent event) throws IOException, InterruptedException {
        TermReader.setTermlist("quiz5");
        control = false;
        playQuiz();
    }
    @FXML
    private void submitPressed(ActionEvent event){
        outputDisplay.setScrollTop(outputDisplay.getScrollTop());
        recieveInput();
        if(takeInput){
            checkInput();
            }
        if(input.length() < 10000 && control.equals(false)){
            gameLogic();
        }
    }
    @FXML
    private void setRounds(){
        if(!roundsCounter.getText().isEmpty()) {
            rounds = Integer.parseInt(roundsCounter.getText());
            if(rounds == 0 || !(Character.isLetterOrDigit(Integer.parseInt(roundsCounter.getText()))) || rounds < 0) rounds = 10;
        }
    }

    public void loadList() throws IOException {
         termsMap = TermReader.readTerms();
         wordList = new ArrayList<>(termsMap.keySet());
         definitionList = new ArrayList<>(termsMap.values());
    }
    public void pickRandomElement() {
        random = new Random();
        int rand = random.nextInt(termsMap.size());
        randomWord = wordList.get(rand);
        randomDefinition = definitionList.get(rand);
    }
    public void queueQuestion(){
        outputDisplay.appendText("What is the matching word to this Definition: " + "\n");
        outputDisplay.appendText(randomDefinition + "\n");
    }
    public void welcomeText(){
        outputDisplay.appendText("Welcome to: " + TermReader.getTermlist() + "\n");
    }
    public void clearText(){
        outputDisplay.clear();
    }

    public void recieveInput(){
        input = inputField.getText().toLowerCase();
        input = input.replaceAll("\\s+","");
        input = input.trim();
        inputField.clear();
    }
    public void checkInput(){
        if(input.equals(randomWord)){
            outputDisplay.appendText("You answered: " + input + "\n");
            outputDisplay.appendText("Correct!" + "\n");
            score++;
        } else {
            outputDisplay.appendText("Incorrect"+ "\n");
            outputDisplay.appendText("You answered: " + input + "\n");
            outputDisplay.appendText("The correct answer was: " + randomWord + "\n");
        }
            takeInput = true;
    }
    public void printScore(){
        outputDisplay.appendText("\n");
        outputDisplay.appendText("Your Score: " + score +"/" + rounds);
        score = 0;
    }
    public void setupDisplay(){
        outputDisplay.setEditable(false);
        outputDisplay.setWrapText(true);
        outputDisplay.setMouseTransparent(true);
        outputDisplay.setPromptText("Display Initialized");
    }
    public void gameLogic(){
        takeInput = true;
        pickRandomElement();
        if(!(i == rounds)){
            outputDisplay.appendText("\n");
            outputDisplay.appendText("Question: " + String.valueOf(i + 1) + "\n");
        }
        i++;
        if(!(i == rounds +1))queueQuestion();
        if(i > rounds - 1){
            if(reset.equals(false)) {
                if(i > rounds){
                    if(i > rounds + 1)checkInput();
                    control = true;
                    takeInput = false;
                    printScore();
                    i = 0;
                    reset = true;
                }
            }
            reset = false;
        }
    }
    public void playQuiz() throws IOException, InterruptedException {
            i = 0;
            clearText();
            welcomeText();
            loadList();
            setRounds();
            gameLogic();
    }
}
