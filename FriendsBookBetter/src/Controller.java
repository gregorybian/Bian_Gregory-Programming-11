import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {

    public ListView friendList;
    private ArrayList <Friend> friends = new ArrayList<>();
    public TextField textFirstName;
    public TextField textLastName;
    public TextField textAge;
    public TextField textHeight;
    public TextField textFavoriteSport;
    public TextField textFavoriteGame;
    public Label lblFirstName;
    public Label lblLastName;
    public Label lblAge;
    public Label lblHeight;
    public Label lblFavoriteSport;
    public Label lblFavoriteGame;
    public Button btndeletefriend;
    public CheckBox cbxMaleFriends;
    public CheckBox cbxFemaleFriends;
    public CheckBox cbxBestFriends;
    public CheckBox cbxLoadAllFriends;
    public CheckBox cbxLoadMaleFriends;
    public CheckBox cbxLoadFemaleFriends;
    public CheckBox cbxLoadBestFriends;
    public Button btnLoad;

    public void addFriend(ActionEvent actionEvent) throws IOException {
        String type = "";
        if (cbxBestFriends.isSelected()){
            type = "Best Friends";
        }
        else if(cbxMaleFriends.isSelected()){
            type = "Male Friends";
        }
        else if(cbxFemaleFriends.isSelected()){
            type = "Female Friends";
        }
        Friend temp = new Friend(textFirstName.getText(), textLastName.getText(), Integer.parseInt(textAge.getText()), Double.parseDouble(textHeight.getText()), textFavoriteSport.getText(), textFavoriteGame.getText(), type);
        friendList.getItems().add(temp);
        textFirstName.clear();
        textLastName.clear();
        textAge.clear();
        textHeight.clear();
        textFavoriteSport.clear();
        textFavoriteGame.clear();
        cbxFemaleFriends.setSelected(false);
        cbxMaleFriends.setSelected(false);
        cbxBestFriends.setSelected(false);

        ObservableList<Friend> myList = friendList.getItems();
        for(Friend p: myList){
            p.writeToFile("FriendAll.txt");
            if (p.getType().equals("Best Friends")){
                p.writeToFile("FriendBest.txt");
            }
            else if(p.getType().equals("Male Friends")){
                p.writeToFile("FriendMale.txt");
            }
            else if(p.getType().equals("Female Friends")){
                p.writeToFile("FriendFemale.txt");
            }
        }
        friendList.getItems().clear();
    }

    public void displayFriend(MouseEvent mouseEvent) {
        Friend temp;
        temp = (Friend) friendList.getSelectionModel().getSelectedItem();
        lblFirstName.setText(temp.getFirstName());
        lblLastName.setText(temp.getLastName());
        lblAge.setText(Integer.toString(temp.getAge()) + " years old");
        lblHeight.setText(Double.toString(temp.getHeight()) + " feet tall");
        lblFavoriteSport.setText(temp.getFavoriteSport());
        lblFavoriteGame.setText(temp.getFavoriteGame());
        btndeletefriend.setDisable(false);

    }


    public void deleteFriend(ActionEvent actionEvent) {
        Friend temp;
        temp = (Friend) friendList.getSelectionModel().getSelectedItem();
        friendList.getItems().remove(temp);
        lblFirstName.setText("");
        lblLastName.setText("");
        lblAge.setText("");
        lblHeight.setText("");
        lblFavoriteSport.setText("");
        lblFavoriteGame.setText("");
        btndeletefriend.setDisable(true);

        if (friendList.getItems().isEmpty()){
            friendList.setDisable(true);
            btndeletefriend.setDisable(true);
        }

    }


    public void saveMaleFriends(ActionEvent actionEvent) {
        cbxFemaleFriends.setSelected(false);
        cbxBestFriends.setSelected(false);
    }

    public void saveFemaleFriends(ActionEvent actionEvent) {
        cbxMaleFriends.setSelected(false);
        cbxBestFriends.setSelected(false);
    }

    public void saveBestFriends(ActionEvent actionEvent) {
        cbxFemaleFriends.setSelected(false);
        cbxMaleFriends.setSelected(false);
    }

    public void setAllFriendsList(ActionEvent actionEvent) {
        cbxLoadFemaleFriends.setSelected(false);
        cbxLoadMaleFriends.setSelected(false);
        cbxLoadBestFriends.setSelected(false);
    }

    public void setMaleFriendsList(ActionEvent actionEvent) {
        cbxLoadFemaleFriends.setSelected(false);
        cbxLoadAllFriends.setSelected(false);
        cbxLoadBestFriends.setSelected(false);
    }

    public void setFemaleFriendsList(ActionEvent actionEvent) {
        cbxLoadAllFriends.setSelected(false);
        cbxLoadMaleFriends.setSelected(false);
        cbxLoadBestFriends.setSelected(false);
    }

    public void setBestFriendsList(ActionEvent actionEvent) {
        cbxLoadFemaleFriends.setSelected(false);
        cbxLoadMaleFriends.setSelected(false);
        cbxLoadAllFriends.setSelected(false);
    }

    public void load(ActionEvent actionEvent) throws IOException {
        if (!cbxLoadAllFriends.isSelected() && !cbxLoadBestFriends.isSelected() && !cbxLoadMaleFriends.isSelected() && !cbxLoadFemaleFriends.isSelected()) {
            friendList.setDisable(true);
        } else {
            lblFirstName.setText("");
            lblLastName.setText("");
            lblAge.setText("");
            lblHeight.setText("");
            lblFavoriteSport.setText("");
            lblFavoriteGame.setText("");
            friendList.getItems().clear();
            friends.clear();
            String list = getSelectedList();
            friends = CreateFriend.createAllFriends(list);
            friendList.setDisable(false);
            for (Friend f : friends) {
                friendList.getItems().add(f);
            }
            if(friends.isEmpty()){
                friendList.setDisable(true);
            }
        }
    }

    private String getSelectedList(){
        if (cbxLoadAllFriends.isSelected()) {
            return "FriendAll.txt";
        } else if (cbxLoadMaleFriends.isSelected()) {
            return "FriendMale.txt";
        } else if (cbxLoadFemaleFriends.isSelected()) {
            return "FriendFemale.txt";
        } else if (cbxLoadBestFriends.isSelected()) {
            return "FriendBest.txt";
        } else {
            return null;
        }
    }
}
