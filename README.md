<div id="top"></div>

<br />
<div align="center">
  <a href="https://github.com/fh-erfurt/ws2021_team_1_service_7_persons">
    <img src="https://raw.githubusercontent.com/fh-erfurt/ws2021_team_1_service_7_persons/java2/.github/assets/logo.png" width="80" alt="fhe logo" />
  </a>
  <br>
  <h3 align="center">PME Project WS_2022/23 - Aurumbanking</h3>

</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Note Of Thanks"> Note of thanks</a></li>
    <li><a href="#Introduction"> Introduction</a></li>
    <li><a href="#Business Diagram">Business Diagram</a></li>
    <li><a href="#Entity Relationship Model">Entity Relationship Model</a></li>
    <li><a href="#App Backend Architecture">App Backend Architecture</a></li>
    <li><a href="#Contributors">Contributors</a></li>
    <li><a href="#Contributing">Contributing</a></li>
    <li><a href="#License">License</a></li>
  </ol>
</details>


# Note of thanks

I want to give a special thanks to Prof. Dr.  Steffen Avemarg for the lecture, project consulting, help and the opportunity to try this project my own!
<p align="right">(<a href="#top">back to top</a>)</p>

# Introduction

This repository is the PME Project of an banking-app. Focus on UI and specification from the predefined Programmierung mobiler Endgeräte (PME) Module.

For this Project the programming language Kotlin and Java was used. Besides that a relational database was implemented in a local database by using LiteSQL and Room from Android Jetpack. 

<p align="right">(<a href="#top">back to top</a>)</p>

# Business Diagram

At the beginning of the project I tried to evaluate the business usecase of the app. Basically the app has only one view and this is from the customer as you can see on the image below. The customer/user should be able to search assignments/orders, looking in his/her deposit, transaction history and details. Therefore the customers should be able to see their personal details and changing the password to login into the app. Besides that the user can write a message to the support in a formula fragment.

![alt text](docs/diagrams/Business_Usecase_Diagram.png)


<p align="right">(<a href="#top">back to top</a>)</p>

# Entity Relationship Model

In the following image is the ER-Model the database. Only for faster and simpler SQL-Queries the transactionlist table and customer table are 1 to 1 connect. In real project this kind of ER-Model is not recommended.

![alt text](docs/diagrams/ER_Modell_Aurumbank_Final.jpg)

<p align="right">(<a href="#top">back to top</a>)</p>

# App Backend Architecture 

Basically the AurumBanking Backend Architecture is structered in _**8 specifics layers**_, which can be _**abstacted to 4 layers**_.

The 8 Layers you can see on the diagamm below. 

The _**abstacted to 4 layers**_ are:

* Activity/Fragement-Layer
* Viewmodel-Layer
* Repository-Layer
* DAO-Layer
* Database-Layer

The **Activity/Fragement-Layer** are the "User Interface"-Layer, in which the user can interact with the app. In this layer the data will be only process and displayed into the specific form of the UI.

The **Viemmodel-Layer** bypass the data into the **Activity/Fragement-Layer** from underlying data layer and process certain data for the overlying layers. 

The **Repository-Layer** is an abstraction layer between the **Viemmodel-Layer** and the **DAO-Layers**. This layer process the function and datas from both overlying and underlying layer.


The **DAO-Layer** is used for the definition of the SQL-queries and fuction, which will be transformed into LiteSQL. The input and output data will be operated by the CRUD-Methods. 

The **Database-Layer** is the stored data layer.

![alt text](docs/diagrams/App_Backend_Architecture_Final.png)

<p align="right">(<a href="#top">back to top</a>)</p>

# Contributors

This repository is maintained by Tran Anh Hoang as a Project of the University Module "Programmierung mobiler Endgeräte (PME)".

<p align="right">(<a href="#top">back to top</a>)</p>


# Contributing

This repository was created for educational purposes only so no contributions are required.

<p align="right">(<a href="#top">back to top</a>)</p>

# License

Distributed under the MIT License, see the [LICENSE](./LICENSE) file for more information.

<p align="right">(<a href="#top">back to top</a>)</p>