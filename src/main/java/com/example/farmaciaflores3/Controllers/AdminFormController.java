package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Model.Dao.ProductoDao;
import com.example.farmaciaflores3.Model.Dao.ProveedorDao;
import com.example.farmaciaflores3.Model.Producto;
import com.example.farmaciaflores3.Model.Proveedor;
import com.example.farmaciaflores3.Utils.Alerts;
import com.example.farmaciaflores3.Utils.InputDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {

    private FileInputStream fis;
    private int longitudBytes;
    private LoginController loginController;
    private Stage stage;
    ProductoDao productoDao = new ProductoDao();
    private ProveedorDao proveedorDao;
    private VentasController ventasController;
    private InitController initController;

    @FXML
    private Label labelImg;
    @FXML
    private ComboBox<String> NombreProveedores;
    @FXML
    private TextField TxtNameProd;
    @FXML
    private TextField TxtPrecioProd;
    @FXML
    private TextField TxtStockProd;
    @FXML
    private TextArea TxtDescripcionProd;
    @FXML
    private TableView<Producto> PTableProducto;
    @FXML
    private TableColumn<Producto, Integer> IDProdColumn;
    @FXML
    private TableColumn<Producto, String> NombreProdColumn;
    @FXML
    private TableColumn<Producto, String> DescriptionProdColumn;
    @FXML
    private TableColumn<Producto, Integer> PrecioProdColumn;
    @FXML
    private TableColumn<Producto, Integer> StockProdColum;
    @FXML
    private TableColumn<Producto, String> ProveedorProdColum;


    public void init(Stage stage1, LoginController loginController) {
        this.loginController = loginController;
        this.stage = stage1;
    }
    private void refreshTable() {
        List<Producto> productos = productoDao.obtenerProductosConProveedor();
        PTableProducto.setItems(FXCollections.observableArrayList(productos));
    }
    private void Limpiar(){
        TxtNameProd.clear();
        TxtDescripcionProd.clear();
        TxtPrecioProd.clear();
        TxtStockProd.clear();
    }
    public void show(){
        stage.show();
    }
    @FXML
    public void BtnMostrarAdminForm(ActionEvent event) throws IOException{
        refreshTable();
    }
    @FXML
    public void BtnAddAdminForm(ActionEvent event) throws IOException{
        String NombreProducto =TxtNameProd.getText() ;
        String DescripcionProducto=TxtDescripcionProd.getText();
        String Proveedor = NombreProveedores.getValue();
        if (!NombreProducto.isEmpty() && !DescripcionProducto.isEmpty() && !TxtPrecioProd.getText().isEmpty() &&
        !TxtStockProd.getText().isEmpty() && !Proveedor.isEmpty()){
            productoDao.AddProducto(NombreProducto,DescripcionProducto,Integer.parseInt(TxtPrecioProd.getText()),Integer.parseInt(TxtStockProd.getText()),Proveedor);
            refreshTable();
            Limpiar();
        }else {
            Alerts.showErrorMessage("Por Favor Complete todos los Datos");
        }


    }
    @FXML
    public void BtnDeleteAdminForm(ActionEvent event) throws IOException{
        Producto selectTableStatus = PTableProducto.getSelectionModel().getSelectedItem();
        if (selectTableStatus != null){
            if (Alerts.showConfirmationAlert("Delete","Esta Seguro de Eliminar esta Tarea")){
                PTableProducto.getItems().remove(selectTableStatus);
                productoDao.deleteProducto(selectTableStatus.getId());
                refreshTable();
            }
        }
    }
    @FXML
    public void BtnUpdateAdminForm(ActionEvent event) throws IOException{
        //InputDialogs.showTextInputDialog("Actualizar Datos","Ingrese el Id del Producto","ID: ");
        Producto producto = PTableProducto.getSelectionModel().getSelectedItem();
        if (producto != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/UpdateProd.fxml"));
            Parent root = loader.load();
            UpdateProdController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            controller.productData(producto.getId(),producto.getNombre(),producto.getPrecio(),producto.getStock(),producto.getDescripcion(),producto.getNombreProveedor());
            controller.init(stage1,this);
            stage1.show();
            this.stage.close();
        }else {
            Alerts.showErrorMessage("Por favor Seleccione el Producto dentro de la tabla");
        }
    }
    @FXML
    public void BtnVender() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/Ventas.fxml"));
        Parent root = loader.load();
        VentasController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.init(stage1,initController);
        stage1.show();
        this.stage.close();
    }
    @FXML
    public void BtnCargarImg(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                fis = new FileInputStream(selectedFile);
                this.longitudBytes = (int) selectedFile.length();

                Image image = new Image(selectedFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(labelImg.getWidth());
                imageView.setFitHeight(labelImg.getHeight());

                labelImg.setGraphic(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void goNext(){
        loginController.show();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        proveedorDao = new ProveedorDao();
        List<Proveedor> proveedores = proveedorDao.obtenerProveedores();
        for (Proveedor proveedor : proveedores) {
            NombreProveedores.getItems().add(proveedor.getNombre());
        }
        NombreProveedores.setValue("Selecciona un proveedor");


       IDProdColumn.setCellValueFactory(data -> data.getValue().getIdProperty().asObject());
       NombreProdColumn.setCellValueFactory(data -> data.getValue().getNombreProperty());
       DescriptionProdColumn.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
       PrecioProdColumn.setCellValueFactory(data -> data.getValue().getPrecioProperty().asObject());
       StockProdColum.setCellValueFactory(data -> data.getValue().getStockProperty().asObject());
       ProveedorProdColum.setCellValueFactory(data -> data.getValue().getIdProveedorProperty());

       refreshTable();
    }
}
