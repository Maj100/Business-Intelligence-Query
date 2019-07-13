
package biproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FXMLDocumentController implements Initializable {

    List<SellsData> originalSellsData = new ArrayList<SellsData>();
    DataSource data = new DataSource();
    @FXML // fx:id="sellstable"
    private TableView<SellsData> sellstable;

    @FXML // fx:id="yearColumn"
    private TableColumn<SellsData, Integer> yearColumn;

    @FXML // fx:id="regionColumn"
    private TableColumn<SellsData, String> regionColumn;

    @FXML // fx:id="vehicleColumn"
    private TableColumn<SellsData, String> vehicleColumn;

    @FXML // fx:id="quantityColumn"
    private TableColumn<SellsData, Integer> quantityColumn;

    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {
        originalSellsData.clear();
        Thread backgroundThread = new Thread(createTask());
        backgroundThread.setDaemon(true);
        backgroundThread.start();

    }

    /*Stacked Area Chart*/
    @FXML
    private StackedAreaChart stackedAreaChart;
    @FXML
    private StackedBarChart stackedBarChart;

    StackedAreaChart.Series[] dataSerieses;
    StackedAreaChart.Series[] dataSerieses1;

    @FXML
    private void handleStackedAreaChard(ActionEvent event) {

        stackedBarChart.getData().clear();

        stackedAreaChart.getData().clear();

        List<SellsData> dList = data.getDataList();
        Map<String, Map<Integer, Integer>> groupByRegionAndYear = dList.stream().collect(
                Collectors.
                        groupingBy(SellsData::getRegion, Collectors.
                                groupingBy(
                                        SellsData::getYear, Collectors.summingInt(SellsData::getQuantity)
                                )
                        )
        );
        dataSerieses = new XYChart.Series[groupByRegionAndYear.size()];

        int count = 0;
        for (Map.Entry<String, Map<Integer, Integer>> entry : groupByRegionAndYear.entrySet()) {
            dataSerieses[count] = new XYChart.Series();
            dataSerieses[count].setName(entry.getKey());
            for (Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
                // System.out.println(entry.getKey() + ": " + entry1.getKey() + "-" + entry1.getValue());
                dataSerieses[count].getData().add(new XYChart.Data(entry1.getKey() % 2010, entry1.getValue()));
            }
            stackedAreaChart.getData().add(dataSerieses[count]);
            count++;
        }
//group by vehicle and year
        Map<String, Map<Integer, Integer>> groupByVehicleAndYear = dList.stream().collect(
                Collectors.
                        groupingBy(SellsData::getVehicle, Collectors.
                                groupingBy(
                                        SellsData::getYear, Collectors.summingInt(SellsData::getQuantity)
                                )
                        )
        );
        dataSerieses1 = new XYChart.Series[groupByVehicleAndYear.size()];
        count = 0;
        for (Map.Entry<String, Map<Integer, Integer>> entry : groupByVehicleAndYear.entrySet()) {
            dataSerieses1[count] = new XYChart.Series();
            dataSerieses1[count].setName(entry.getKey());
            for (Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
                // System.out.println(entry.getKey() + ": " + entry1.getKey() + "-" + entry1.getValue());
                dataSerieses1[count].getData().add(new XYChart.Data(String.valueOf(entry1.getKey() % 2010), entry1.getValue()));
            }
            stackedBarChart.getData().add(dataSerieses1[count]);
            count++;
        }

    }
    /* Filter Fields*/
    @FXML
    private TextField byYear;
    @FXML
    private TextField byVehicle;
    @FXML
    private TextField byRegion;

    @FXML
    private void handleFilterButtonAction(ActionEvent event) {
        String fYear = byYear.getText();
        String fVehicle = byVehicle.getText();
        String fRegion = byRegion.getText();
        List<SellsData> sellsList = new ArrayList<SellsData>();
        for (SellsData s : originalSellsData) {
            sellsList.add(s.clone());
        }
       
            if (fYear != null && !fYear.equals("") ) {
               
             sellsList.removeIf(s -> !String.valueOf(s.getYear()).contains(fYear));
            }
        
      
          
            if (fVehicle != null && !fVehicle.equals("")) {
               sellsList.removeIf(s -> !s.getVehicle().toLowerCase().contains(fVehicle.toLowerCase()));
            }
       
          
            
            if (fRegion != null && !fRegion.equals("")) {
               sellsList.removeIf(s -> !s.getRegion().toLowerCase().contains(fRegion.toLowerCase()));
          
        }
        
        data.loadData(sellsList);
    }

    /**
     * ***********End of Filter Operaions************
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setting up the columns
        PropertyValueFactory<SellsData, Integer> yearProperty
                = new PropertyValueFactory<SellsData, Integer>("year");

        PropertyValueFactory<SellsData, String> regionProperty
                = new PropertyValueFactory<SellsData, String>("region");

        PropertyValueFactory<SellsData, String> vehicleProperty
                = new PropertyValueFactory<SellsData, String>("vehicle");

        PropertyValueFactory<SellsData, Integer> quantityProperty
                = new PropertyValueFactory<SellsData, Integer>("quantity");

        yearColumn.setCellValueFactory(yearProperty);
        regionColumn.setCellValueFactory(regionProperty);
        vehicleColumn.setCellValueFactory(vehicleProperty);
        quantityColumn.setCellValueFactory(quantityProperty);

        //setting up the table data source
        ObservableList<SellsData> tableItems = data.getData();
        sellstable.setItems(tableItems);
    }

    private Task<String> createTask() throws MalformedURLException, IOException {
        System.out.println("Data fetching from internet....");
        final URL apiEndpoint = new URL("http://....");  // ENTER SERVER LINK HERE

        // Prepare connection
        final HttpURLConnection connection = (HttpURLConnection) apiEndpoint.openConnection();
        // It's a post request
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        // With json as content
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return new Task<String>() {
            protected String call()
                    throws IOException, MalformedURLException, ParseException {
                String result = null;
                InputStreamReader reader = null;
                try {
                    reader = new InputStreamReader(connection.getInputStream());
                    int ch;
                    StringBuilder sb = new StringBuilder();
                    while ((ch = reader.read()) != -1) {
                        sb.append((char) ch);
                    }
                    result = sb.toString();
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                    connection.disconnect();
                }
                System.out.println("Data fetching completed....");
                System.out.println(result);
                System.out.println("Prepering json data....");
                JSONParser parser = new JSONParser();
                JSONArray dataArray = (JSONArray) parser.parse(result);
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject object = (JSONObject) dataArray.get(i);
                    SellsData d = new SellsData(Integer.parseInt(object.get("Year").toString()), object.get("Region").toString(), object.get("Vehicle").toString(), Integer.parseInt(object.get("Quantity").toString()));
                    originalSellsData.add(d);

                }
                data.loadData(originalSellsData);
                System.out.println("Data loading completed....");
                return null;
            }
        };
    }

    @FXML
    private void handleCloseButton(ActionEvent event) {
        System.exit(0);
    }

}
