

import com.mycompany.models.cajeroModel;
import com.mycompany.models.Views.CajeroView;



   
public class CajeroAutomatico {
  public static void main(String[]args){
      cajeroModel model=new cajeroModel();
      CajeroView<String> view=new CajeroView<>();
      CajeroControllers controller=new CajeroControllers(model,view);
      controller.iniciarSistema();
  }

}
