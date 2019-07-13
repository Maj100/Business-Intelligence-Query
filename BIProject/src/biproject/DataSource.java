
package biproject;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DataSource {
     private final ObservableList<SellsData> data = 
      FXCollections.observableArrayList();
  
  public ObservableList<SellsData> getData() {
    return data;
  }
  public void clearData(){
      data.clear();
  }
  public List<SellsData> getDataList(){
      List<SellsData> d=new ArrayList<SellsData>();
      for(SellsData t:this.data){
        d.add(t);
      }
      return d;
  }
public void setData(SellsData d){
    data.add(d);
}

public void loadData(List<SellsData> lst){
    data.clear();
    for(SellsData d:lst){
      this.data.add(d.clone());
    }
}
  public DataSource() {
  }
}
