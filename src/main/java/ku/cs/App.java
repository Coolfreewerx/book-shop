package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.github.saacsos.FXRouter;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application
{
    private static Scene scene;

//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "JAVAPAI",1024.0,768.0);
        configRoute();
        FXRouter.goTo("aboutUs");
//        FXRouter.goTo("sellerStock");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("bookDetail", packageStr + "bookDetail.fxml");
        FXRouter.when("home",packageStr + "home.fxml");
        FXRouter.when("pageBookCartoon",packageStr + "pageBookCartoon.fxml");
        FXRouter.when("pageMagazine",packageStr + "pageMagazine.fxml");
        FXRouter.when("basket", packageStr + "basket.fxml");
        FXRouter.when("login",packageStr + "login.fxml");
        FXRouter.when("register" , packageStr + "register.fxml");
        FXRouter.when("seller", packageStr + "seller.fxml");
        FXRouter.when("sellerStock", packageStr + "sellerStock.fxml");
        FXRouter.when("applyToBeASeller", packageStr + "applyToBeASeller.fxml");
        FXRouter.when("item",packageStr + "item.fxml");
        FXRouter.when("headWhenLogin",packageStr + "headWhenLogin.fxml");
        FXRouter.when("headNoLogin",packageStr + "headNoLogin.fxml");
        FXRouter.when("detailUser",packageStr + "editPasswordDetail.fxml");
        FXRouter.when("aboutUs",packageStr + "aboutUs.fxml");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}