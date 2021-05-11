/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package GUI.Class;

import GUI.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/10/2018
 * Version 1.0
 */
public class Class implements Initializable {
    Main main=new Main();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void getLive(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("mediaview"));
        System.out.println("test button");
    }

    @FXML
    private void freshCard(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("card"));
    }

    @FXML
    private void jogging(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("jogging"));
    }

}
