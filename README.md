# Business-Intelligence-Query
#Developed with Java 
#Created by Maj Alshibly

# Introduction

Business Intelligence tools have their place in applications, technology and practices for the purpose of integration, analysis of a business raw data. Business intelligence is used for disciplines such as data mining, queries and reporting on data by using charts and graphs. These business intelligence tools can be created using different methods and become incorporated in many software applications. Examples would be using business tools from a website or an application. These tools are extremely useful for data visualization, reporting on the tools and data analytics. Google analytics is a good example as they use different charts, graphs and data to measure a website&#39;s traffic. Google analytics&#39; users can determine which methods to improve a website&#39;s traffic through these data analysis. It is the same attribute with business owners that want to keep track of their business revenue and identifying market trends.

One of the biggest advantages of business intelligence is that an owner can keep track of their stocks and revenue. A business intelligence control panel can record the products and their information to output data such as their value, amount of other details which can become useful for referencing. As a team, we were tasked to create a business intelligence control panel which would take data from a web service and output it on a table for examination.

# Overview

The task that was created was for businesses to project their data through a business intelligence control panel application which reads api data asynchronously and binding the data with the table model to represent data on a table. The created project has the ability to filter out the data based on the inputted filter information. The control panel that is created is able to filter out a table using three filter fields: Region, Vehicle and Year. Using three filtration fields allow for a user to receive the exact data they require. When developing the table, we kept in mind some inaccuracies some users might face when filtering. So it was fixed by allowing any combination of filter fields, case insensitive and allowing partial word filter in the process.

# Networking &amp; Concurrency

When creating the control panel, the web service may not always process the requests in time or it may even crash. Handling this part of the web service was made possible by the use of api synchronously so the application wouldn&#39;t freeze.

![alt text](https://i.ibb.co/NWyP908/1.png)

Figure 1 – Load button that loads table

The process of loading the data to the table is done by clicking the &quot;Load Data&quot; button which displays the full data without any filtration. When clicking the &quot;Load Data&quot; button, the api synchronously initiates. For the asynchronous reading – the Task and Thread is used so the application wouldn&#39;t freeze or lag. The &quot;Load data&quot; button is programmed by giving it a **handleTableButtonAction** event which is programmed from this:

![alt text]( https://i.ibb.co/44D9Rzw/2.png)

Figure 2 – Code of the load button

When initiating the &quot;Load Button&quot; action, the table displays the data fully:

![alt text]( https://i.ibb.co/sV2hvcC/3.png)

Figure 3 – Image of the table being loaded

# Data Querying Filters

The control panel allows for three filtrations simultaneously to output the data that is exactly needed. These filters allow any combination of filter fields, case insensitive and allowing partial word filter in the process. The filter result changes the binding table&#39;s data source. This allows the data table to display the result automatically and was developed here which show each filter option:

![alt text]( https://i.ibb.co/SvLsySP/4.png)

Figure 4 – Code of the filter options

The filter boxes all have their own purposes to categorize the table. When specifying a region, such as &#39;Europe&#39; or &#39;europe&#39;, then pressing the filter button. It would only display the table that contains Europe as its region:

![alt text]( https://i.ibb.co/T4nqbDW/5.png)

Figure 5 – table that shows Europe as the region

The control panel also has the ability to filter out 3 categories at the same time on a table. This would allow the data to be filtered to the exact content. As shown below, the control panel has filtered out the table which should only contain: Region: Asia, Vehicle: Elise and Year: 2012

![alt text]( https://i.ibb.co/Wf85PTL/6.png)



# Collection &amp; Bindings

When developing the business intelligence control panel, one of the important factors was to use the collection data structure to store the api result. It was done by allowing the data to read though a JSON format. The process of the JSON string is converted to **SellsData** which is named as a collection object. The collection object then binds with the table model. So any change of the collection object data will make an immediate effect on the table&#39;s representation. It was developed by using **ObservableList** as the binding collection data structure which allows the api to retrieve the data bind. So whenever the **ObservableList** has changed, then the table data would change automatically.

![alt text]( https://i.ibb.co/THZfLFT/7.png)

Figure 7 – Code of the **ObservableList**

# Data presentation

It is important for a business to show data and information graphically. Graphs and tables a common technique to visually show the relationships in the data. The purpose of a graph is to present the data in a more adequate way which would be a better alternative than a wall of text. The basic principal of a graph is to make the data clear and readable, so adding it to the control panel improves the readability of data and information. Developing the control panel with a table is done by using two separate illustrations; bar chart and a line chart. The graphs are loaded when the &quot;Prepare Chart&quot; button is clicked and displays both graphs with an animation.

![alt text]( https://i.ibb.co/FsCdwFy/8.png)

Figure 8 – Image of the graphs being outputted





## Stacked Area Chart

**stackedAreaChart** is a useful method to analyse data which contains a lot of units. It was developed to display the region and year section of the table. We have also developed footnotes below to show what each graph represents. The image below is the stacked area chart which is displaying the data for the Elise vehicle:

![alt text]( https://i.ibb.co/1GXKcMX/9.png)

Figure 9 – Image of StackedAreaChart

## Bar Chart (Graph)

A bar chart or programmed to be known as the **stackedBarChart** is a useful method to represent data and information. It uses multiple columns in which the data is visually represented by a vertical bar. Using a bar chart allow for comparisons between different values with each bar representing its own data. The developed bar chart is created to group together the vehicle and year of the data table as shown in the image which showcases the vehicles that are in America:

![alt text]( https://i.ibb.co/MhMGrQ1/10.png)

Figure 10 - Image of the StackedBarChart

# Conclusion

In conclusion, the business intelligence control panel which was developed on NetBeans using JavaFX is able to display data from a web service and output that data into a table format that has the ability to organize the information in the table by using the columns or the filter option above the window. Filtering is a useful option for the user to see the data that they want to be displayed. By applying the filter, the user is able to limit the data in the view without changing the design of the form.

