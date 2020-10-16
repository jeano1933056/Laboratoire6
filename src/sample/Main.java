package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) {
        window.setFullScreen(true);
        window.setMaximized(true);

        MenuItem image1 = new MenuItem("Image #1");
        MenuItem image2 = new MenuItem("Image #2");
        MenuItem image3 = new MenuItem("Image #3");
        MenuItem reinitialiser = new MenuItem("Réinitialiser");

        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        Menu chargerUneImage = new Menu("Charger une image");

        actions.getItems().addAll(reinitialiser);
        fichiers.getItems().addAll(chargerUneImage);
        chargerUneImage.getItems().addAll(image1, image2, image3);

        MenuBar menuBar = new MenuBar(fichiers, actions);

        Image chat = new Image("Image1.JPG");
        Image rainbow = new Image("Image2.png");
        Image fruits = new Image("Image3.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(chat);

        Label barreInfo = new Label("Programme chargé!");

        ColorAdjust imageModifs = new ColorAdjust();
        Label labelLuminosite = new Label("Luminosité");
        Slider luminosite = new Slider();
        Label labelContraste = new Label("Contraste");
        Slider contraste = new Slider();
        Label labelTeinte = new Label("Teinte");
        Slider teinte = new Slider();
        Label labelSaturation = new Label("Saturation");
        Slider saturation = new Slider();
        Tooltip toolLuminosite = new Tooltip("Rend l'image plus claire ou plus sombre");
        Tooltip toolContraste = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip toolTeinte = new Tooltip("Change la couleur de l'image");
        Tooltip toolSaturation = new Tooltip("Diminue ou augmente l'intensité des couleurs");

        image1.setOnAction(event -> {
            imageView.setImage(chat);
            barreInfo.setText("Image #1 chargée");
        });

        image2.setOnAction(event -> {
            imageView.setImage(rainbow);
            barreInfo.setText("Image #2 chargée");
        });

        image3.setOnAction(event -> {
            imageView.setImage(fruits);
            barreInfo.setText("Image #3 chargée");
        });

        luminosite.setMin(0);
        luminosite.setMax(1);
        luminosite.setTooltip(toolLuminosite);
        luminosite.valueProperty().addListener((observable, oldValue, newValue) ->
                imageModifs.setBrightness(luminosite.getValue()));

        contraste.setMin(0);
        contraste.setMax(1);
        contraste.setTooltip(toolContraste);
        contraste.valueProperty().addListener((observable, oldValue, newValue) ->
                imageModifs.setContrast(contraste.getValue()));

        teinte.setMin(0);
        teinte.setMax(1);
        teinte.setTooltip(toolTeinte);
        teinte.valueProperty().addListener(((observable, oldValue, newValue) ->
                imageModifs.setHue(teinte.getValue())));

        saturation.setMin(0);
        saturation.setMax(1);
        saturation.setTooltip(toolSaturation);
        saturation.valueProperty().addListener(((observable, oldValue, newValue) ->
                imageModifs.setSaturation(saturation.getValue())));

        imageView.setEffect(imageModifs);

        reinitialiser.setOnAction(event -> {
            imageView.setImage(chat);
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            barreInfo.setText("Réinitialisation effectuée.");
        });

        VBox slidersEtLabels = new VBox(labelLuminosite, luminosite, labelContraste,
                contraste, labelTeinte, teinte, labelSaturation, saturation);
        HBox hBox = new HBox(imageView, slidersEtLabels);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(hBox);
        borderPane.setBottom(barreInfo);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        slidersEtLabels.setSpacing(10);
        slidersEtLabels.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(borderPane);

        window.setResizable(true);
        window.setScene(scene1);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}