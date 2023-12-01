package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Model.Dao.ProductoDao;
import com.example.farmaciaflores3.Model.Producto;
import com.example.farmaciaflores3.Model.ProductoCarrito;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class VentasController implements Initializable {

    private InitController initController;
    private Stage stage;
    private ProductoDao productoDao = new ProductoDao();

    private AutoCompletionBinding<String> autoCompletionBinding;
    private Set<String> possibleSuggestions;
    private LoginController loginController;

    @FXML
    TextField NameProducto;
    @FXML
    TextField idProduct;
    @FXML
    TextField stockProduct;
    @FXML
    TextField precioProduct;
    @FXML
    TextField cantidadProduct;
    @FXML
    private TableView<ProductoCarrito> TableCarrito;
    @FXML
    private TableColumn<ProductoCarrito,String> NameTable;
    @FXML
    private TableColumn<ProductoCarrito,Integer> CodTable;
    @FXML
    private TableColumn<ProductoCarrito,Integer> CantidadTable;
    @FXML
    private TableColumn<ProductoCarrito,Integer> PrecioTable;
    @FXML
    private TableColumn<ProductoCarrito,Integer> TotalTable;
    private ObservableList<ProductoCarrito> carrito = FXCollections.observableArrayList();
    public void init(Stage stage1, InitController initController) {
        this.initController = initController;
        this.stage = stage1;
        String[] listProducts = productoDao.getListProducts();
        //TextFields.bindAutoCompletion(NameProducto,"hola","Halo","Hela","hool");
        possibleSuggestions = new HashSet<>(Arrays.asList(listProducts));
        autoCompletionBinding = TextFields.bindAutoCompletion(NameProducto,possibleSuggestions);
        NameProducto.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case ENTER :
                        Producto producto = productoDao.DatesProduct(NameProducto.getText());
                        idProduct.setText(String.valueOf(producto.getId()));
                        stockProduct.setText(String.valueOf(producto.getStock()));
                        precioProduct.setText(String.valueOf(producto.getPrecio()));
                        break;
                    default:
                        break;
                }
            }
        });

    }


    @FXML
    public void addCarrito(ActionEvent event){
        String NombreProducto = NameProducto.getText();
        int id = Integer.parseInt(idProduct.getText());
        int Cantidad = Integer.parseInt(cantidadProduct.getText());
        int Precio = Integer.parseInt(precioProduct.getText());
        int Total = Cantidad * Precio;

        ProductoCarrito nuevoProducto = new ProductoCarrito(NombreProducto, id, Cantidad, Precio, Total);
        carrito.add(nuevoProducto);

        // Actualiza la TableView
        TableCarrito.setItems(carrito);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NameTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        CodTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        CantidadTable.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        PrecioTable.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TotalTable.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public void show() {
        stage.show();
    }
    @FXML
    public void NextAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.setUserData("Administrador");
        controller.init(stage1,initController);
        stage1.show();
        this.stage.close();
    }
}
