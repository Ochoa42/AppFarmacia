package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Model.Dao.ProductoDao;
import com.example.farmaciaflores3.Model.Dao.ProveedorDao;
import com.example.farmaciaflores3.Model.Proveedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateProdController implements Initializable {


    private AdminFormController adminFormController ;
    private Stage stage;
    private ProveedorDao proveedorDao;
    private ProductoDao productoDao = new ProductoDao();

    @FXML
    private TextField UNameProducto;
    @FXML
    private TextField UPrecioProducto;
    @FXML
    private TextField UStockProducto;
    @FXML
    private TextArea UDescripcionProducto;
    @FXML
    private ComboBox<String> UProveedorProducto;
    @FXML
    private TextField IDProducto;

    public void init(Stage stage1, AdminFormController adminFormController) {
        this.adminFormController = adminFormController;
        this.stage = stage1;
    }

    public void productData(int Id,String Nombre,int Precio,int Stock,String Descipcion,String Provedor){
        IDProducto.setText(Integer.toString(Id));
        UNameProducto.setText(Nombre);
        UPrecioProducto.setText(Integer.toString(Precio));
        UStockProducto.setText(Integer.toString(Stock));
        UDescripcionProducto.setText(Descipcion);
        UProveedorProducto.setValue(Provedor);
    }
    @FXML
    private void BtnUpdate() throws IOException {

        int id = Integer.parseInt(IDProducto.getText());
        String Nombre = UNameProducto.getText();
        String Descripcion = UDescripcionProducto.getText();
        String Proveedor = UProveedorProducto.getValue();
        if (!Nombre.isEmpty() && !Descripcion.isEmpty() && !UPrecioProducto.getText().isEmpty() &&
                !UStockProducto.getText().isEmpty() && !Proveedor.isEmpty()) {

            productoDao.updateProducto(id,Nombre,Descripcion,Integer.parseInt(UPrecioProducto.getText()),
                    Integer.parseInt(UStockProducto.getText()), Proveedor);

            BtnCancelar();

        }
    }

    @FXML
    private void BtnCancelar(){
        adminFormController.show();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proveedorDao = new ProveedorDao();
        List<Proveedor> proveedores = proveedorDao.obtenerProveedores();
        for (Proveedor proveedor : proveedores) {
            UProveedorProducto.getItems().add(proveedor.getNombre());
        }
    }
}
