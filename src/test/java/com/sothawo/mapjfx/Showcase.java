/*
 Copyright 2014 Peter-Josef Meisch (pj.meisch@sothawo.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.sothawo.mapjfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Showcase application.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com).
 */
public class Showcase extends Application {
// ------------------------------ FIELDS ------------------------------

    private static final Logger logger = LoggerFactory.getLogger(Showcase.class);

    /** some coordinates from around town */
    private static final Coordinate coordKarlsruheCastle = new Coordinate(49.013517, 8.404435);
    private static final Coordinate coordKarlsruheHarbour = new Coordinate(49.015511, 8.323497);
    private static final Coordinate coordKarlsruheStation = new Coordinate(48.993284, 8.402186);

    /** the MapView */
    private MapView mapView;

    /** the label displaying the current center */
    private Label labelCenter;


// -------------------------- OTHER METHODS --------------------------

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("starting showcase...");
        BorderPane borderPane = new BorderPane();

        // MapView in the center with an initial coordinate (optional)
        // the MapView is created first as the other elements reference it
        mapView = new MapView(coordKarlsruheHarbour);
        borderPane.setCenter(mapView);

        // at the top some buttons with coordinates
        Pane topPane = createTopPane();
        borderPane.setTop(topPane);

        // at the bottom some infos
        borderPane.setBottom(createBottomPane());

        // add listener for mapView initialization state
        mapView.initializedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    topPane.setDisable(false);
                }
            }
        });

        // add an observer for the map's center property to adjust the corresponding label
        mapView.centerProperty().addListener(new ChangeListener<Coordinate>() {
            @Override
            public void changed(ObservableValue<? extends Coordinate> observable, Coordinate oldValue,
                                Coordinate newValue) {
                labelCenter.setText(newValue == null ? "" : newValue.toString());
            }
        });


        // now initialize the mapView
        mapView.initialize();

        // show the whole thing
        Scene scene = new Scene(borderPane, 800, 600);

        primaryStage.setTitle("sothawo mapjfx showcase");
        primaryStage.setScene(scene);
        primaryStage.show();

        logger.debug("application started.");
    }

    /**
     * creates the top pane with the different location buttons
     *
     * @return Pane
     */
    private Pane createTopPane() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);

        Button btn = new Button();
        btn.setText("Karlsruhe castle");
        btn.setOnAction(event -> mapView.setCenter(coordKarlsruheCastle));
        hbox.getChildren().add(btn);

        btn = new Button();
        btn.setText("Karlsruhe harbour");
        btn.setOnAction(event -> mapView.setCenter(coordKarlsruheHarbour));
        hbox.getChildren().add(btn);

        btn = new Button();
        btn.setText("Karlsruhe station");
        btn.setOnAction(event -> mapView.setCenter(coordKarlsruheStation));
        hbox.getChildren().add(btn);

        hbox.setDisable(true);
        return hbox;
    }

    /**
     * creates the top pane with the different location buttons
     *
     * @return Pane
     */
    private Pane createBottomPane() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);

        Coordinate center = mapView.getCenter();
        labelCenter = new Label(center == null ? "" : center.toString());
        hbox.getChildren().add(labelCenter);

        return hbox;
    }
// --------------------------- main() method ---------------------------

    public static void main(String[] args) {
        launch(args);
    }
}
