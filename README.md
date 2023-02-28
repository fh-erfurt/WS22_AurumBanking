<div id="top"></div>

<br />
<div align="center">
  <a href="https://github.com/fh-erfurt/ws2021_team_1_service_7_persons">
    <img src="https://raw.githubusercontent.com/fh-erfurt/ws2021_team_1_service_7_persons/java2/.github/assets/logo.png" width="80" alt="fhe logo" />
  </a>
  <br>
  <h3 align="center">PME Project WS_2022/23 - AurumBanking</h3>

</div>


<summary>Table of Contents</summary>
<ol>
  <li><a href="#note-of-thanks"> Note of thanks</a></li>
  <li><a href="#introduction"> Introduction</a></li>
  <li><a href="#app-guidance"> App Guidance</a></li>
  <li><a href="#business-diagram">Business Diagram</a></li>
  <li><a href="#entity-relationship-model">Entity Relationship Model</a></li>
  <li><a href="#app-backend-architecture">App Backend Architecture</a></li>
  <li><a href="#contributors">Contributors</a></li>
  <li><a href="#contributing">Contributing</a></li>
  <li><a href="#license">License</a></li>
</ol>



<details>
<summary>Note of thanks</summary>

# Note of thanks

I want to give a special thanks to Prof. Dr. Steffen Avemarg for the lecture, project consulting, help and the opportunity to try this project my own!
<p align="right">(<a href="#top">back to top</a>)</p>

</details>


<details>
<summary>Introduction</summary>

# Introduction

This repository is the PME Project of an banking-app. Focus on UI and specification from the predefined "Programmierung mobiler Endgeräte (PME)" Module.

The App was programmed and tested on a **Google Pixel 6 Pro API 27**.<br>
User Credentials to access the app is: <br>
* Email: t@t.de 
* Password: 123

The App has **only two view** with a completed implementation of a landscape mode:
* **Money Transfer/Überweisung**
* **Support**

For this Project the programming language Kotlin and Java was used. Besides that a relational database was implemented in a local database by using LiteSQL and Room from Android Jetpack. 

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>App Guidance</summary>


1. Login 
To Login into the App use the following credentials.

* Email: t@t.de 
* Password: 123

The user will be generated after every restart of the app, because the project is base on a local database in the RAM.


![alt text](docs/AppGuideImages/Login.png)

2. Overview/Übersicht

After the Login, the user will be forwarded to the Overview-Fragment. Which will be shown in the following image. 

![alt text](docs/AppGuideImages/Overview.png)

The lastest transaction value will show the newest transaction that's was made.

3. Profile/Profil

The user can see their personal information the tab "Profil". If the user want to change his/her password. The person has to change the switch to "yes", so the password-change section will be shown.  

![alt text](docs/AppGuideImages/Profil.png)

![alt text](docs/AppGuideImages/Profi_Password_Change.png)

4. Money Transfer/Überweisung

In the view "Überweisung" the user can execute a money transfer order. The person has to fill all the Edittext of the formula and click the "Überweisung durchführen"- Button. 

![alt text](docs/AppGuideImages/Ueberweisung_1.png)

![alt text](docs/AppGuideImages/Ueberweisung_2.png)

In this view the user can use the landscape mode by rotate the app with rotation function of the smartphone.

![alt text](docs/AppGuideImages/Ueberweisung_Landscape_1.png)

![alt text](docs/AppGuideImages/Ueberweisung_Landscape_2.png)

5. Deposit/Depot

In this view the user can check the current money value of her/his depot, search certain terms by click on the magnifying glass of the search bar and commit a search term. The user can also see all his transactions of the past in an recyclerview.

![alt text](docs/AppGuideImages/depot.png)

![alt text](docs/AppGuideImages/depot2.png)


6. Transaction Details/Transaktionsdetails


In this view the user can see the transaction detail by clicking on the value site of the textfield from the recyclerview. The app will guide the user to the detail view. This will be shown in the following images.

![alt text](docs/AppGuideImages/detail_view_1.png)

![alt text](docs/AppGuideImages/detail_view_2.png)

By clicking on the "Zurück zum Depot"-button of the smartphone. The user can get back to the deposit view.

7. Support

In the fragement "Support" the user can send the support a spefic request. The user has to fill all the Edittext and select one of the options in "Art der Anfrage". By pressing the button "Anfrage Absenden" the message will be "sent" to the support.

![alt text](docs/AppGuideImages/support_1.png)

This view can be used in a landscape-mode, too.

![alt text](docs/AppGuideImages/support_landscape_1.png)

![alt text](docs/AppGuideImages/support_landscape_2.png)


The user can use "back" button of the smartphone to return from the current tab to the tab before (example by using the "back" button: Deposit -> Überweisung -> Profil -> Login).

If the user click on "back" button of the smartphone while the user is on the login fragment, the app will be close.




<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>Business Diagram</summary>

# Business Diagram

At the beginning of the project I tried to evaluate the business usecase of the app. Basically the app has only one view and this is from the customer as you can see on the image below. The customer/user should be able to search assignments/orders, looking in his/her deposit, transaction history and details. Therefore the customers should be able to see their personal details and changing the password to login into the app. Besides that the user can write a message to the support in a formula fragment.

![alt text](docs/diagrams/Business_Usecase_Diagram.png)


<p align="right">(<a href="#top">back to top</a>)</p>

</details>

<details>
<summary>Entity Relationship Model</summary>

# Entity Relationship Model

In the following image is the ER-Model the database. Only for faster and simpler SQL-Queries the transactionlist table and customer table are 1 to 1 connect. In real project this kind of ER-Model is not recommended.

![alt text](docs/diagrams/ER_Modell_Aurumbank_Final.jpg)

<p align="right">(<a href="#top">back to top</a>)</p>

</details>

<details>
<summary>App Backend Architecture </summary>

# App Backend Architecture 

Basically the AurumBanking Backend Architecture is structured in _**8 specifics layers**_, which can be _**abstacted to 4 layers**_.

The 8 Layers you can see on the diagamm below. 

The _**abstacted to 4 layers**_ are:

* Activity/Fragement-Layer
* Viewmodel-Layer
* Repository-Layer
* DAO-Layer
* Database-Layer

The **Activity/Fragement-Layer** are the "User Interface"-Layer, in which the user can interact with the app. In this layer the data will be only process and displayed into the specific form of the UI.

The **Viemmodel-Layer** bypass the data into the **Activity/Fragment-Layer** from underlying data layer and process certain data for the overlying layers.

The **Repository-Layer** is an abstraction layer between the **Viewmodel-Layer** and the **DAO-Layers**. This layer process the function and data from both overlying and underlying layer.


The **DAO-Layer** is used for the definition of the SQL-queries and function, which will be transformed into LiteSQL. The input and output data will be operated by the CRUD-Methods.

The **Database-Layer** is the stored data layer.

![alt text](docs/diagrams/App_Backend_Architecture_Final.png)

<p align="right">(<a href="#top">back to top</a>)</p>

</details>

<details>
<summary>Contributors</summary>

# Contributors

This repository is maintained by Tran Anh Hoang as a Project of the University Module "Programmierung mobiler Endgeräte (PME)".

<p align="right">(<a href="#top">back to top</a>)</p>

</details>

<details>
<summary>Contributing</summary>

# Contributing

This repository was created for educational purposes only so no contributions are required.

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>License</summary>

# License

Distributed under the MIT License, see the [LICENSE](./LICENSE) file for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

</details>