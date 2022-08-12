import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    public ListView friendList;
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
    public Button btnaddFriend;

    public void addFriend(ActionEvent actionEvent) {
        Friend temp = new Friend(textFirstName.getText(), textLastName.getText(), Integer.parseInt(textAge.getText()), Double.parseDouble(textHeight.getText()), textFavoriteSport.getText(), textFavoriteGame.getText());
        friendList.getItems().add(temp);
        textFirstName.clear();
        textLastName.clear();
        textAge.clear();
        textHeight.clear();
        textFavoriteSport.clear();
        textFavoriteGame.clear();
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
    }
}
