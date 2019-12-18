# Project Title

TP – Intergiciel : FindRoof 

## Getting Started

En plus de ce qui a été réalisé sur la partie PC. Sur cette partie on a ajouté : 
	* [AWS lambda] - appel aux fonctions
	* [AWS S3]     - container
	* [AWS RDS]    - hébergement de la BD

### Prerequisites

* Java IDE
* Java 11 
* Maven 
* Junit

### AWS Lambda

Pour faire l'appel à des fonctions lambda il nous a fallu configuré un role IAM (pour notre cas on a fait un role basique qui se base sur une stratégie par défaut gérer par AWS 'AWSLambdaBasicExecutionRole' avec pour nom findroof-role).

* La fonction lambda, permettant de convertir les prix € -> $ des logements mis en location, a été ajoutée. Pour se faire, un bean @service ```AwsService``` se charge pour les appels de la fonction lambda pour le moment. 
* Lors de l'affichage de la liste des annonces publiées / les annonces publiées de l'utilisateur connecté / les biens reservés : un prix en dollars s'affiche. 
* Les appels à la fonction lambda se base sur des tokens qui sont statiques dans le code (expirent chaque 3h) : au dela de ce delais, il se peut que tous les prix en $ = 0
* Pour optimiser les couts de calcul, pour chaque annonce on fait un appel de façon independante.         

```
La fonction "ConvertPricePossession" est sur le projet "findroof.aws.lambda".  
```
#### Running the tests AWS Lambda

Un test unitaire Junit a été implémenté pour pouvoir tester la fonction ```ConvertPricePossession```.

### AWS RDS

SGBD MySQL est utilisé pour le type de la BD RDS. 

Toute la DB findroof en local a été remontée doréavant sur une instance BD RDS renomée ```findroofinstance```. L'Accès à l'instance BD est public. 

Le fichier "application.propereties" a été adapté pour pouvoir se connecter à la BD RDS. 

La région utilisée par l'instance est "us-east-1a". 


### Dépendances Maven ajoutées : 

* aws-java-sdk-lambda
* aws-lambda-java-core
* aws-lambda-java-events


## Authors

* **Said YEBBAH** - *said.yebbah@etu.uphf.fr* - M2 TNSI FA
* **Fabien LEMIRE** - *fabien.lemire@etu.uphf.fr* - M2 TNSI FA


## Acknowledgments

* Java 11 