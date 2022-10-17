package com.example.gamebee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController {
    ArrayList<String> dictionary = new ArrayList<>();
    ArrayList<String> wordsarraylist = new ArrayList<>();
    ArrayList<String> lettersarraylist = new ArrayList<>();
    ArrayList<String> pangram = new ArrayList<>();
    ArrayList<Character> pangramcorrector = new ArrayList<>();
    ArrayList<String> lettercombinationchooser = new ArrayList();
    Random rand = new Random();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private TextField output;
    @FXML
    private TextArea wordsText;
    @FXML
    private Label point;

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;

    @FXML
    private AnchorPane output1;


    @FXML
    private void btn1(MouseEvent event) {
        output.setText(output.getText().substring(0, output.getText().length() - 1));
    }
// DELETE

    @FXML
    void onCharButtonClick(MouseEvent event) {
        String letter = ((Button) event.getSource()).getText();
        output.setText(output.getText() + letter);
    }

    @FXML
    void initialize() throws IOException {
        starter();
        pangramChooserFromDictionary();
        randomGameMaker();
        lettersarraylist.add(btn3.getText());
        shuffle();
        btn3.setText(lettersarraylist.get(6));
        lettersarraylist.clear();
    }

    @FXML
    void enterClick(ActionEvent event) {
        if (!(output.getLength() == 0))
            if (output.getText().length() > 3)
                if (output.getText().contains(btn3.getText()))
                    if (dictionary.stream().anyMatch(a -> output.getText().toUpperCase().equals(a.toUpperCase())))
                        if (!wordsarraylist.stream().anyMatch(b -> output.getText().equals(b.toUpperCase()))) {
                            wordsarraylist.add(output.getText());
                            wordsText.appendText(output.getText() + System.lineSeparator());
                            point.setText(String.valueOf(Integer.valueOf(point.getText()) + output.getText().length() - 3));
                        } else
                            System.out.println("daha önce girilen kelime!!");
                    else
                        System.out.println("kelime sözlükte bulunmuyor!!");
                else
                    System.out.println("ortadaki harf kullanılmadı!!");
            else
                System.out.println("kelime çok kısa!!");
        else
            System.out.println("harf yazınız!!");
        output.clear();
    }

    @FXML
    public void shuffleClick() {
        shuffle();
        lettersarraylist.clear();
    }

    private void starter() throws IOException {
        BufferedReader sozluktara = new BufferedReader(new FileReader("./sozluk.txt"));
        while (sozluktara.ready())
            dictionary.add(sozluktara.readLine());
        output.clear();
    }

    private void pangramChooserFromDictionary() {
        for (String a : dictionary) {
            for (int i = 0; i < a.length(); i++) {
                pangramcorrector.add(a.charAt(i));
            }
            for (int i = 0; i < pangramcorrector.size(); i++) {
                for (int j = i + 1; j < pangramcorrector.size(); j++) {
                    if (pangramcorrector.get(i).equals(pangramcorrector.get(j))) {
                        pangramcorrector.remove(j);
                    }
                }
            }
            if (pangramcorrector.size() == 7) {
                pangram.add(a);
                lettercombinationchooser.add(pangramcorrector.toString());
            }
            pangramcorrector.clear();
        }
    }

    private void randomGameMaker() {
        int randomword = rand.nextInt(lettercombinationchooser.size());
        System.out.println(lettercombinationchooser.get(randomword));
        btn1.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(1)));
        btn2.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(4)));
        btn3.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(7)));
        btn4.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(10)));
        btn5.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(13)));
        btn6.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(16)));
        btn7.setText(String.valueOf(lettercombinationchooser.get(randomword).charAt(19)));

    }

    private void shuffle() {
        lettersarraylist.add(btn1.getText());
        lettersarraylist.add(btn2.getText());
        lettersarraylist.add(btn4.getText());
        lettersarraylist.add(btn5.getText());
        lettersarraylist.add(btn6.getText());
        lettersarraylist.add(btn7.getText());
        Collections.shuffle(lettersarraylist);
        btn1.setText(lettersarraylist.get(0));
        btn2.setText(lettersarraylist.get(1));
        btn4.setText(lettersarraylist.get(2));
        btn5.setText(lettersarraylist.get(3));
        btn6.setText(lettersarraylist.get(4));
        btn7.setText(lettersarraylist.get(5));
    }

}




