package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class searchController implements Initializable {


    public TableView<Profile> tableView;
    public TableColumn<Profile, String> username_column;
    public TableColumn<Profile, String> name_column;
    private final ObservableList<Profile> allProfile = FXCollections.observableArrayList();
    public TextField filtered_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allProfile.removeAll();
        allProfile.addAll(ClientHandlerCommands.getProfiles());
        username_column.setCellValueFactory(new PropertyValueFactory<>("username"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        FilteredList<Profile> filteredList = new FilteredList<>(allProfile, b -> true);
        filtered_field.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredList.setPredicate(profile -> {
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newvalue.toLowerCase();
                if (profile.getName().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (profile.getUserName().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else
                    return false;

            });

        });
        SortedList<Profile> sorteddata = new SortedList<>(filteredList);
        sorteddata.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sorteddata);
    }

    @FXML
    public void getProfile(MouseEvent mouseEvent) {

        try {
            if (!tableView.getSelectionModel().getSelectedItem().getUsername().equals(Main.currentusername)) {
                ProfilePagecontroller.profile = tableView.getSelectionModel().getSelectedItem();
                new PageLoader().load("ProfilePage");
            }
            else {
                 CurrentProfilePagecontroller.thisProfile=ClientHandlerCommands.get_profile_by_Username(Main.currentusername);
                new PageLoader().load("CurrentProfilePage");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
